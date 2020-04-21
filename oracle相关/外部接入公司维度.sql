--1.
select a.id,
       a.tyshxydm,
       a.txmm,
       TO_NUMBER( a.jsrq - TO_DATE('1970-01-01 8:0:0', 'YYYY-MM-DD HH24:MI:SS')) * 24 * 60 * 60+(24*3600 -1) as jsrq ,    
       a.jrzt,
       a.zsbh,
       a.appmc,
       a.appbb,
       a.apibb,
       a.yxfwgnb,   
       a.zdljs,
       a.sfrzlp,
       a.lpyxq,
       a.jrcszt,
       a.ljgtfs,
       a.wbjrfs,
       a.fkms,
       a.zll,
       a.ls,
       a.lsdwsj,
       a.sfyzxx,
       a.txyhm,
       a.qymc,
       a.qyzzxs,
       a.zcdd,
       a.bgdd,
       a.fddbr,
       a.jglx,
       a.jjyxhdjbh,
       a.sjkzr,
       a.sjkzrzjbh       
  from T_WBJR_JGGL a where 1=1  order by a.id;  
--2.
 select a.id, a.t_wbjr_jggl_id, a.mac, a.ip, a.cpxlh, a.jqtzm, a.zt
   from T_WBJR_JGGL_JYZD a
  where 1 = 1
    --and a.t_wbjr_jggl_id in (3) 
    order by a.t_wbjr_jggl_id;    
--3.
select a.id, a.t_wbjr_jggl_id, a.svc_id, a.zll, a.ls, a.lsdwsj
  from T_WBJR_JGGL_GNMXS a
 where 1 = 1
  -- and a.t_wbjr_jggl_id in (1, 2)
 order by a.t_wbjr_jggl_id;
  
select  100*365*24*60*60 from  dual;

 select a.id,
        a.tyshxydm,
        a.txmm,
        TO_NUMBER(a.jsrq -
                  TO_DATE('1970-01-01 8:0:0', 'YYYY-MM-DD HH24:MI:SS')) * 24 * 60 * 60 +
        (24 * 3600 - 1) as jsrq,
        a.jrzt,
        a.zsbh,
        a.appmc,
        a.appbb,
        a.apibb,
        a.yxfwgnb,
        a.zdljs,
        a.sfrzlp,
        a.lpyxq,
        a.jrcszt,
        a.ljgtfs,
        a.wbjrfs,
        a.fkms,
        a.zll,
        a.ls,
        a.lsdwsj,
        "A"."SFYZXX" 
   from T_WBJR_JGGL a
  where 1 = 1
  order by a.id;
 
 //--  
 select a.id, a.t_wbjr_jggl_id, a.mac, a.ip, a.cpxlh, a.jqtzm, a.zt   from T_WBJR_JGGL_JYZD a  where 1 = 1  order by a.t_wbjr_jggl_id 
  //--   select a.id, a.t_wbjr_jggl_id, a.svc_id, a.zll, a.ls, a.lsdwsj  from T_WBJR_JGGL_GNMXS a where 1 = 1  order by a.t_wbjr_jggl_id


select * from t_wbjr_jggl;

select * from lc_wbjr_jrcpgl;
