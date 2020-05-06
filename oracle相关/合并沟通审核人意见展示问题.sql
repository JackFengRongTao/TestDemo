select * from gdsy.ttable a where a.tablename like'%lbw%';

select a.attachment from lbworkcommunication  a order by id desc;
select a.* from lbworkcommunication  a order by id desc;
select * from user_tables a where a.TABLE_NAME like '%LBW%';

select * from lc_gdsy_ydgzpj a order by id desc;

select * from ttableobj a where a.tablename like'%lbw%';

select * from ttable a where a.tablename like'%lb%';



select (select name from tuser where id = t.inviter) 沟通人,
       t.subject 沟通方案,
       (select name from tuser where id = t.answerer) 回复人,
       to_char(t."DATE", 'yyyy-mm-dd hh24:mi:ss') 时间,
       t.response 回复内容,
       case
         when t.status = -1 then
          '沟通中'
         when t.status = 2 then
          '结束沟通'
         when t.status = 3 then
          '结束沟通'
         when t.status = 4 then
          '沟通完成'
       end 状态,
       t.attachment 附件
  from LBWORKCOMMUNICATION t
 where t.originstepid in
       (select id from v_os_historystep a where a.entry_id=6184)
   --and t.status NOT IN (-1, 1)
 order by t.originstepid;

select * from v_os_historystep;

select  * from os_historystep a   where a.entry_id = 2168  order by id desc;

select * from lc_gdsy_ydgzpj_sheyj a  where a.lc_gdsy_ydgzpj_id = 2168 order by id desc;


---
select * from lc_gdsy_ydgzpj a order by id desc;
select * from lc_gdsy_ydgzpj_sheyj a  where a.lc_gdsy_ydgzpj_id = 2169 order by id desc;
select a.* from lbworkcommunication  a order by id desc;
select * from v_os_historystep a where a.entry_id = 6199;  -- -11030   -10001   -12030
select name from tuser  f where f.id in(select a.owner from v_os_historystep a where a.entry_id = 6199 and  a.id = (13147+2)); --738,1345


select to_char(substr(wm_concat(name), 1, 1000)) as bgtr

select b.owner from (
select a.* ,rownum rn
                  from v_os_historystep a
                 where a.entry_id = 6198
                   and a.id >= 13170 and rownum <= 3) b where rn=3;



select a.owner
                  from v_os_historystep a
                 where a.entry_id = 6198
     
              and a.id = (13170 + 2)

select *    from tuser f
 where f.id in
       (SELECT REGEXP_SUBSTR((select a.owner
                               from v_os_historystep a
                              where a.entry_id = 6194
                                and a.id = (13147 + 2)),
                             '[^,]+',
                             1,
                             rownum)
          FROM DUAL
        CONNECT BY ROWNUM <= LENGTH((select a.owner
                                      from v_os_historystep a
                                     where a.entry_id = 6194
                                       and a.id = (13147 + 2))) -
                   LENGTH(REPLACE((select a.owner
                                              from v_os_historystep a
                                             where a.entry_id = 6194
                                               and a.id = (13147 + 2)),
                                            ',',
    
                                         '')) + 1);
 -----------------------------------------                                      
         select to_char(substr(wm_concat(name), 1, 1000)) as bgtr
           from tuser f
          where f.id in
                (SELECT REGEXP_SUBSTR('738,1345', '[^,]+', 1, rownum)
                   FROM DUAL
                 CONNECT BY ROWNUM <=
                            LENGTH('738,1345') -
                            LENGTH(REPLACE('738,1345', ',', '')) + 1);
                                         
                                         

  ----------------------------------------
  
        select null id,
               null shr,
               null shryj,
               (select id from tuser where id = t.inviter) fqgtr,
               t.subject gtnr,
               (select id from tuser where id = t.answerer) hfr,
               t.response hfnr,
               t.attachment fj,
               t."DATE" shsj,
               t.id lbworkcommunication_id
          from LBWORKCOMMUNICATION t
         where t.originstepid in
               (select id from v_os_historystep a where a.entry_id = 6194)
           and t.id not in
               (select z.lbworkcommunication_id
                  from lc_gdsy_ydgzpj_sheyj z
                 where z.lc_gdsy_ydgzpj_id = 2164
                   and z.lbworkcommunication_id is not null);
                   
---最终版                   
select u.id,
u.shr,
u.shryj,
u.fqgtr,
u.gtnr,
u.hfr,
u.hfnr,
u.fj,
u.shsj,
u.lbworkcommunication_id,
       u.fqgtrname || '->' ||
       (select to_char(substr(wm_concat(name), 1, 1000)) as bgtr
          from tuser f
         where f.id in
               (SELECT REGEXP_SUBSTR(u.bgtr, '[^,]+', 1, rownum)
                  FROM DUAL
                CONNECT BY ROWNUM <=
                           LENGTH(u.bgtr) - LENGTH(REPLACE(u.bgtr, ',', '')) + 1)) fqgt
  from (select null id,
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
               ( select b.owner from (
                 select a.* ,rownum rn
                  from v_os_historystep a
                 where a.entry_id = 6198
                   and a.id >= t.originstepid and rownum <= 3) b where rn=3 ) bgtr
          from LBWORKCOMMUNICATION t
         where t.originstepid in
               (select id from v_os_historystep a where a.entry_id = 6198)
        -- and t.id not in
         --   (select z.lbworkcommunication_id
          --     from lc_gdsy_ydgzpj_sheyj z
         --     where z.lc_gdsy_ydgzpj_id = 2168 
          --     and z.lbworkcommunication_id is not null
              --)        
        ) u
;


--

select u.id,
u.shr,
u.shryj,
u.fqgtr,
u.gtnr,
u.hfr,
u.hfnr,
u.fj,
u.shsj,
u.lbworkcommunication_id,
       u.fqgtrname || '->' ||
       (select to_char(substr(wm_concat(name), 1, 1000)) as bgtr
          from tuser f
         where f.id in
               (SELECT REGEXP_SUBSTR(u.bgtr, '[^,]+', 1, rownum)
                  FROM DUAL
                CONNECT BY ROWNUM <=
                           LENGTH(u.bgtr) - LENGTH(REPLACE(u.bgtr, ',', '')) + 1)) fqgt
  from (select null id,
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
               (select a.owner
                  from v_os_historystep a
                 where a.entry_id = {0}
                   and a.id = (t.originstepid + 2)) bgtr
          from LBWORKCOMMUNICATION t
         where t.originstepid in
               (select id from v_os_historystep a where a.entry_id = {0})
         and t.id not in
            (select z.lbworkcommunication_id
               from lc_gdsy_ydgzpj_sheyj z
              where z.lc_gdsy_ydgzpj_id = {1} 
               and z.lbworkcommunication_id is not null
              )
        
        ) u

     
              
