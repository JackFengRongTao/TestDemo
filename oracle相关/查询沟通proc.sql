/*
--过程名     : gdsy.sp_gs_updateshryj
--过程功能   : 合并审核意见与沟通的相关查询
--备注       :

--修改记录   : 
--修改日期    修改人  版本     修改内容
--2020.04.30  冯荣涛  V1.0.0   新建
*/

BEGIN
    gdsy.pro_setversion('gdsy', 'sp_gs_updateshryj', '1.0.0');
END;
/
create or replace procedure sp_gs_updateshryj(in_entry_id          in number, --流程InstID
                                              in_lc_gdsy_ydgzpj_id in number) --流程id
 as
 v_owner varchar2(200);
 v_bgtr  varchar2(300);
  --沟通内容查询,只查询新增沟通记录，不查询已经存在于lc_gdsy_ydgzpj_sheyj中的记录 
  cursor lbwork_cursor is
    select null id,
           null shr,
           null shryj,
           (select id from tuser where id = t.inviter) fqgtr,
           (select name from tuser where id = t.inviter) fqgtrname,
           t.subject gtnr,
           (select id from tuser where id = t.answerer) hfr,
           t.response hfnr,
           t.attachment fj,
           t."DATE" shsj,
           t.id lbworkcommunication_id,
           t.originstepid
      from LBWORKCOMMUNICATION t
     where t.originstepid in
           (select id from v_os_historystep a where a.entry_id = in_entry_id)
       and t.id not in
           (select z.lbworkcommunication_id
              from lc_gdsy_ydgzpj_sheyj z
             where z.lc_gdsy_ydgzpj_id = in_lc_gdsy_ydgzpj_id
               and z.lbworkcommunication_id is not null);
    current_cursor lbwork_cursor%rowtype;            
begin
   --循环往lc_gdsy_ydgzpj_sheyj插入新记录
   for  current_cursor in  lbwork_cursor loop
        --查询出来被沟通的人的id
        select b.owner into  v_owner from (
                      select a.* ,rownum rn
                  from v_os_historystep a
                 where a.entry_id = in_entry_id
                   and a.id >= current_cursor.originstepid and rownum <= 3) b where rn=3;
        --查询出来被沟通的人的name
        select to_char(substr(wm_concat(name), 1, 1000)) into v_bgtr
          from tuser f
         where f.id in
               (SELECT REGEXP_SUBSTR(v_owner, '[^,]+', 1, rownum)
                  FROM DUAL
                CONNECT BY ROWNUM <=
                           LENGTH(v_owner) - LENGTH(REPLACE(v_owner, ',', '')) + 1);
         --往lc_gdsy_ydgzpj_sheyj插入新记录
         insert into lc_gdsy_ydgzpj_sheyj
           (id,
            shr,
            shryj,
            shsj,
            lc_gdsy_ydgzpj_id,
            fqgtr,
            gtnr,
            hfr,
            hfnr,
            fj,
            lbworkcommunication_id,
            fqgt)
         values
           (func_nextid('lc_gdsy_ydgzpj_sheyj'),
           current_cursor.shr,
           current_cursor.shryj,
           current_cursor.shsj,
           in_lc_gdsy_ydgzpj_id,
           current_cursor.fqgtr,
           current_cursor.gtnr,
           current_cursor.hfr,
           current_cursor.hfnr,
           current_cursor.fj,
           current_cursor.lbworkcommunication_id,
           v_bgtr
           );                   
   end loop;  
  commit;
  --异常情况处理
  EXCEPTION
  WHEN OTHERS THEN
    dbms_output.put_line(SQLCODE || ' : ' || SQLERRM);
    dbms_output.put_line(dbms_utility.format_error_backtrace);
    rollback;
end sp_gs_updateshryj;

call sp_gs_updateshryj();
