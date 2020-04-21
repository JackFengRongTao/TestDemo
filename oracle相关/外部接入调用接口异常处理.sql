--异常情况日志
select * from T_WBJR_JKDYYCCL a order by id desc;
--短信发送
select  *  from   T_DXGL_XXFSRZ order by id desc;
--员工基本信息
select * from tygjbxx a where a.gyh = (select b.userid from tUser b where b.id = 12911);
--股东号
select * from tUser a where a.id = 12911;
--系统参数
select a.csz from  txtcs a order  by id desc;
-- 查询产品
select * from lc_wbjr_jrcpgl;


--异常情况插入日志
--插入一条数据日志表
insert into T_WBJR_JKDYYCCL (id,dyjk,srcs,ycms,dysj) values(
func_nextid('T_WBJR_JKDYYCCL'),
'notifyUpdatedUsers',
'',
?,
sysdate
);
--插入短信
 insert into T_DXGL_XXFSRZ(id ,rq ,fssj ,xxlx ,xxbt ,xxnr ,xxcd ,fsr ,jsr ,cljg,xxly) values(  
                     func_nextid('T_DXGL_XXFSRZ'), 
                     to_number(to_char(sysdate,'yyyymmdd')), 
                     to_char(sysdate,'HH24:MI:SS'), 
                        1, 
                        ?, 
                        ?, 
                        ?, 
                        ?, 
                        ?, 
                        0,
                        2
                      );
                      
 insert into T_DXGL_XXFSRZ(id ,rq ,fssj ,xxlx ,xxbt ,xxnr ,xxcd ,fsr ,jsr ,cljg,xxly) values( 
                                       func_nextid('T_DXGL_XXFSRZ'), 
                                        to_number(to_char(sysdate,'yyyymmdd')), 
                                       to_char(sysdate,'HH24:MI:SS'), 
                                           1, 
                                           '调用风控接口rc_add_user_info出现问题', 
                                           'rc_add_user_info接口,接口不通,[rcAddUserInfoError]null', 
                                           48, 
                                           0, 
                                           '18059140973', 
                                           0,
                                           2
                                         )                     
                      

select    to_number(to_char(sysdate, 'yyyymmdd'))    from dual;

--插入系统参数
insert into txtcs
  (id, csdm, csmc, csz, qzsm)
values
  (func_nextid('TXTCS'),
   'zhyy.wbjr.jsr',
   '外部接入接口调用异常情况短信接收人',
   '18059140973',
   '外部接入接口调用异常情况短信接收人，手机号码以英文逗号隔开,例如：13636432122,15001805383');
   
select * from  txtds;  
 
