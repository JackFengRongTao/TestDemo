package com.dwzq.gdsy.dao;

import com.apex.form.DataAccess;
import com.apex.form.User;
import com.apex.form.UserContext;
import com.dwzq.livebos.utils.DBUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ImportAmlDao
{
    private static Logger logger = LoggerFactory.getLogger(ImportAmlDao.class);

    public JsonArray getAllKhxx()
    {
        JsonArray result = new JsonArray();
        DataSource ds = DataAccess.getDataSource();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            StringBuffer sql = new StringBuffer("select a.id from t_gdsy_khxx a");
            conn = ds.getConnection();
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            result = DBUtils.resultSetToJsonArray(rs);
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            DBUtils.closeDB(conn, statement);
        }
        return result;
    }

    public int addKhxxs(List<JsonObject> list)
    {
        List<Long> idList = new ArrayList<>();
        DataSource ds = DataAccess.getDataSource();
        Connection conn = null;
        PreparedStatement   ifIdNullStatement = null;
        PreparedStatement   insertIfIdNullStatement = null;
        PreparedStatement lockStatement = null;
        PreparedStatement maxIdStatement = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Long ifNullId = -1L;
        Long startId = 0L;
        Long endId = 0L;
        int code = 1;
        try
        {
            conn = ds.getConnection();
            //关闭自动commit
            conn.setAutoCommit(false);
            User user = UserContext.getContext().getUser();
            String  ifIdNullSql ="select nvl(max(id),-1) as ifNullId from tSequence where upper(name) = upper('T_GDSY_KHXX')";
            String  insertIfIdNullSql ="INSERT INTO tsequence(name,id) VALUES('T_GDSY_KHXX',0)";
            String  lockSql ="update tSequence set id = id + 0 where upper(name) = upper('T_GDSY_KHXX')";
            String  maxSql ="select max(id) as max_id  from tSequence where upper(name) = upper('T_GDSY_KHXX')";
            String sql = "insert into t_gdsy_khxx (id,khmc,zjlx,zjbh,lrsj,lrr,khlx,bz) values(func_nextid('T_GDSY_KHXX'),?,?,?,sysdate,?,?,?)";
            String insertKhcpxxSql = "insert into T_GDSY_KHCPXX (id,khmc,cpqk,fxqfs,fxqdjrq,pgrq,pg,rdfs,cpr,fxdj) values(func_nextid('T_GDSY_KHCPXX'),?,?,?,?,?,?,?,?,?)";

            //--1.T_GDSY_KHXX相关--
            //查出来tSequence表中，T_GDSY_KHXX表这条记录
            ifIdNullStatement = conn.prepareStatement(ifIdNullSql);
            rs = ifIdNullStatement.executeQuery();
            logger.info("执行sql："+ifIdNullSql);
            if (rs.next()){
                ifNullId = rs.getLong(1);
            }
            //判断记录是否存在，为-1则是不存在或者这列为空值，则往tSequence表中插入T_GDSY_KHXX表这条记录，id设为0
            if(ifNullId == -1){
                insertIfIdNullStatement = conn.prepareStatement(insertIfIdNullSql);
                insertIfIdNullStatement.executeUpdate();
                logger.info("执行sql："+insertIfIdNullSql);
                conn.commit();
            }
            //先锁住 tSequence表中，T_GDSY_KHXX表这条记录
            lockStatement = conn.prepareStatement(lockSql);
            lockStatement.executeUpdate();
            logger.info("执行sql："+lockSql);
            //查询T_GDSY_KHXX表，id开始的值
            maxIdStatement = conn.prepareStatement(maxSql);
            rs = maxIdStatement.executeQuery();
            logger.info("执行sql："+maxSql);
            if (rs.next()){
                startId = rs.getLong(1);
            }
            //客户信息入库
            statement = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++)
            {
                statement.setString(1, ((JsonObject)list.get(i)).get("khmc").getAsString());
                statement.setInt(2, 0);
                statement.setString(3, ((JsonObject)list.get(i)).get("zjbh").getAsString());
                statement.setInt(4, Integer.parseInt(user.getId()));
                statement.setInt(5, 2);
                statement.setString(6, null);
                statement.addBatch();
            }
            statement.executeBatch();
            logger.info("执行sql："+sql);
            //查询T_GDSY_KHXX表，id最后的值
            maxIdStatement = conn.prepareStatement(maxSql);
            rs = maxIdStatement.executeQuery();
            logger.info("执行sql："+maxSql);
            if (rs.next()){
                endId = rs.getLong(1);
            }
            logger.info("startId:"+startId);
            logger.info("endId:"+endId);
            //先生成客户信息id
            for (long i = startId+1;i <= endId;i++){
                //获取id
                idList.add(i);
            }
            logger.info("idList大小："+idList.size());
            //--2.--


            conn.commit();
        }
        catch (SQLException e)
        {
            try {
                //回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            code = -1;
        }
        finally
        {
            try {
                if (rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtils.closeDB(conn, ifIdNullStatement);
            DBUtils.closeDB(conn, insertIfIdNullStatement);
            DBUtils.closeDB(conn, lockStatement);
            DBUtils.closeDB(conn, maxIdStatement);
            DBUtils.closeDB(conn, statement);
        }
        return code;
    }

    public void addData_khcpxxs(Connection conn, List<JsonObject> list,String sql) {//新增客户测评信息
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0;i<list.size();i++){
                statement.setInt(1,list.get(i).get("khmc").getAsInt());//客户名称即客户id
                statement.setInt(2,list.get(i).get("cpqk").getAsInt());//测评情况
                statement.setBigDecimal(3,null);//反洗钱分数
                statement.setInt(4,88888888);//反洗钱登记日期
                statement.setInt(5,77777777);//评估日期
                statement.setInt(6,66666666);//评估有效期
                statement.setLong(7,1);//认定方式
                statement.setLong(8,0);//测评人
                statement.setLong(9,0);//风险等级
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addData_zqxsxxs(Connection conn,List<JsonObject> list,String sql){//新增债券销售信息
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0;i<list.size();i++){
                statement.setLong(1,list.get(i).get("zqid").getAsInt());//债券id
                statement.setLong(2,list.get(i).get("khid").getAsInt());//客户id
                statement.setBigDecimal(3,null);//销售份额
//                statement.setString(4,88888888);//录入时间
                statement.setLong(4,0);//录入人
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

