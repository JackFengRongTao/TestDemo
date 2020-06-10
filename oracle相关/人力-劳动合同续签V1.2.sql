instr(','||FUNC_GET_BMFZR({0})||',',','||id||',')>0

"【"+V_2.XM+"】续签征询"
<%@ livebos language='javascript' %>
ABS_SQLVALUE(" select a.orgid  from tuser a ,lborganization b where a.userid = ? and  a.orgid = b.id ",[V_2.GH]);


<%@ livebos language='javascript' %>
ABS_SQLVALUE(" 

select (case
         when a.new_conterm is null and a.new_conenddate is null then
          3
         when a.new_conterm is not null and a.new_conenddate is not null and
              a.new_conterm / 12 = 3 then
          1
         when a.new_conterm is not null and a.new_conenddate is not null and
              a.new_conterm / 12 = 5 then
          2
       end) as XQQX
  from zhyy_data.hr_econtract_register a
 where a.badge = 04017


",[$Login.User.UserID]);






  select
       a.name as XM,
       a.badge as GH,           
       a.gender as XB,
       a.birthday as CSRQ,    
       a.certno as ZJBH,
       a.age as NL,
       a.joindate as RGSRQ,              
       a.office_phone as DH,       
       a.mobile as SJ,
       a.email as YX,  
       a.conbegindate HTKSRQ,
       a.conenddate HTJSRQ,
       b.compabbr as GS,
       d.jobabbr as GW,
       e.new_conbegindate as   XHTKSRQ,
       E.NEW_CONENDDATE as XHTJSRQ,
       (case
         when e.new_conterm is null and e.new_conenddate is null then
          3
         when e.new_conterm is not null and e.new_conenddate is not null and
              e.new_conterm / 12 = 3 then
          1
         when e.new_conterm is not null and e.new_conenddate is not null and
              e.new_conterm / 12 = 5 then
          2
       end) as XQQX,
       func_get_yjbm (f.orgid) as BM
  from zhyy_data.hr_evw_employee a,zhyy_data.hr_ocompany b,zhyy_data.hr_odepartment c,zhyy_data.hr_ojob d,zhyy_data.hr_econtract_register e,tuser f ,lborganization g   
  where 
   a.compid = b.compid and a.depid = c.depid and a.jobid = d.jobid and a.badge = e.badge and a.badge = f.userid and f.orgid = g.id
 and a.empstatus = 1 and a.conenddate is not null 
  and a.conenddate <= sysdate+90 and a.conenddate >= sysdate;
   
 
select * from lborganization a where a.id = 9002;  -- 851 90139013


select * from tuser  a where a.name like '%吴李%';


begin
  
 dbms_output.put_line(func_get_yjbm(100042));  
 
end;


select x.name from lborganization x where x.id = (select func_get_yjbm(a.orgid)  from tuser a  where a.userid = '04087') ;


select * from lc_hr_ldhtxq;


update lc_hr_ldhtxq  a set a.sftyxq = 1 where a.id in('130');


--查询各个部门这批次续签
select a.id,
       a.zt,
       a.xm,
       a.gh,
       a.xb,
       a.zjbh,
       a.nl,
       a.csrq,
       a.rgsrq,
       a.gs,
       a.bm,
       a.gw,
       a.sj,
       a.dh,
       a.yx,
       a.htksrq,
       a.htjsrq,
       a.xqqx,
       a.xhtksrq,
       a.xhtjsrq,
       a.sftyxq,
       a.fqsj
  from lc_hr_ldhtxq a
 where to_char(a.fqsj, 'yyyymm') = to_char(sysdate, 'yyyymm') ;


select b.id, b.name
  from lborganization b
 where b.id in
       (select a.bm
          from lc_hr_ldhtxq a
         where to_char(a.fqsj, 'yyyymm') = to_char(sysdate, 'yyyymm')
         group by a.bm);

select * from lc_GDSY_LDHTXQHBDB;




//<%@ livebos language='javascript' %>
//"<iframe  src='"+LB_ObjURI("VLDHTXQDBHB",[],0,[],["NoTitle"])+"' width=\"100%\" height=\"400\" ></iframe>";	


select func_get_bmfzr(9013) from dual;

 
select * from tuser a where a.id in(10982);
  

select to_number(to_char(sysdate,'dd')) from dual;
