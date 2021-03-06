create table lc_gdsy_ydgzpj_sheyj_bak as select * from lc_gdsy_ydgzpj_sheyj a;

delete from lc_gdsy_ydgzpj_sheyj;

select * from  lc_gdsy_ydgzpj_sheyj_bak a where  a.lc_gdsy_ydgzpj_id = 2176;

select * from lbworkcommunication a;

select * from lbworkcommunication a where a.originstepid = 13527;

select * from lc_gdsy_ydgzpj a order by id desc;

select * from lc_gdsy_ydgzpj_sheyj a  where a.lc_gdsy_ydgzpj_id = 2252 order by id desc;

select * from v_os_historystep a where a.entry_id = 6326   ; --6324 6281 6325

select * from v_os_historystep a where a.entry_id = 6326 and a.step_id > 0 and a.action_id > 0; --6324 6281 6325

select * from os_historystep;

select * from os_currentstep a where a.entry_id = 6324;




select * from lc_gdsy_ydgzpj a,lc_gdsy_ydgzpj_sheyj b,v_os_historystep c where 
a.id = b.lc_gdsy_ydgzpj_id and a.instid = c.entry_id;

select * from  WF_YDGZPJ;

select * from os_workflowdefs;

select * from  OS_WFENTRY a where a.id = 6324;

SELECT WF_NAME,WF_DEFINITION FROM OS_WORKFLOWDEFS;



select * from lc_gdsy_ydgzpj_sheyj_bak;

select * from lc_gdsy_ydgzpj_sheyj_bak a where   a.lc_gdsy_ydgzpj_id = 2176 and a.shr is not null;

select * from lc_gdsy_ydgzpj a order by id desc; -- 2257

select * from lbworkcommunication a  order by id desc;

select * from v_os_historystep a where a.entry_id =6211 ; --6324 6281 6325 6326 6327 6328  6327 6211

select * from lc_gdsy_ydgzpj_sheyj a where  a.lc_gdsy_ydgzpj_id = 2176;

select * from  tuser;

select count(1) from  tuser;

MergeCommunicationDao

select * from tuser a where a.id in(0, 1781, 1610, 602);

select * from v_os_historystep a order by id desc;


select id, userid, password, name, grade, lastlogin, logins, chgpwdtime, chgpwdlimit, status, iplimit, certno, orgid, locktime, userattribute, oa_status, oa_post, oa_duty, post, oa_id from tuser where instr(','||'0,1781,1610,602'||',' , ','||id||',')>0


select a.id, a.finish_date ,
(case 
       when  a.step_id = 3 then '自动发起'
       when  a.step_id = 6 then '后续跟踪及受托管理人员'
       when  a.step_id = 24 then '项目现场负责人'
       when  a.step_id = 30 then '项目负责人'
       when  a.step_id = 36 then '质控人员' 
        when  a.step_id = -10001 then '被沟通'   
          when  a.step_id = 1 then '结束'    
  end  ) as processNode,
  b.name as operator,
  (case 
       when  a.action_id = 4 then '提交'
       when  a.action_id = 7 then '提交'
       when  a.action_id = 25 then '通过'
       when  a.action_id = 31 then '通过'
       when  a.action_id = 37 then '通过' 
       when  a.action_id = -11010 then '沟通'   
       when  a.action_id = -11020 then '结束沟通'   
       when  a.action_id = -11030 then '沟通完成'   
       when  a.action_id = -12010 then '沟通结束'   
       when  a.action_id = -12020 then '沟通'   
       when  a.action_id = -12030 then '回复'   
       when  a.action_id = -12040 then '结束沟通'   
  end  ) as operation, 'handling_opinions'
  from v_os_historystep a,tuser b
 where a.caller = b.id and    a.caller is not null and  a.entry_id = 6326 order by a.id,;


select * from (select a.id, a.finish_date ,
(case 
       when  a.step_id = 3 then '自动发起'
       when  a.step_id = 6 then '后续跟踪及受托管理人员'
       when  a.step_id = 24 then '项目现场负责人'
       when  a.step_id = 30 then '项目负责人'
       when  a.step_id = 36 then '质控人员' 
        when  a.step_id = -10001 then '被沟通'   
          when  a.step_id = 1 then '结束'    
  end  ) as processNode,
  b.name as operator,
  (case 
       when  a.action_id = 4 then '提交'
       when  a.action_id = 7 then '提交'
       when  a.action_id = 25 then '通过'
       when  a.action_id = 31 then '通过'
       when  a.action_id = 37 then '通过' 
       when  a.action_id = -11010 then '沟通'   
       when  a.action_id = -11020 then '结束沟通'   
       when  a.action_id = -11030 then '沟通完成'   
       when  a.action_id = -12010 then '沟通结束'   
       when  a.action_id = -12020 then '沟通'   
       when  a.action_id = -12030 then '回复'   
       when  a.action_id = -12040 then '结束沟通'   
  end  ) as operation, 'handling_opinions'
  from v_os_historystep a,tuser b
 where a.caller = b.id and    a.caller is not null and  a.entry_id = 6327 ) c 
 left join 
 lbworkcommunication d 
 on c.id = d.originstepid    order by c.id,d.id;


