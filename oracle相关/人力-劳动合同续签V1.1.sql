--
select * from zhyy_data.hr_ecd_emptype;
select * from zhyy_data.hr_ecd_edutype;
select * from zhyy_data.Hr_Abalance;
select * from zhyy_data.Hr_Abalance_All;
select * from zhyy_data.hr_celeave_all;
select * from zhyy_data.hr_celeave_register;
select * from zhyy_data.hr_ebg_capacity;
select * from zhyy_data.hr_ebg_job;
select * from zhyy_data.hr_ecd_empcustom2;
select * from zhyy_data.hr_ecd_empcustom3;
select * from zhyy_data.hr_ecd_empcustom4;
select * from zhyy_data.hr_ecd_empstatus;
select * from zhyy_data.hr_econtract_register;
select * from zhyy_data.hr_evw_employee;
select * from zhyy_data.hr_evw_employee_his;
select * from zhyy_data.hr_ocd_technicalrank;
select * from zhyy_data.hr_ocompany;
select * from zhyy_data.hr_odepartment;
select * from zhyy_data.Hr_Ojob;
select * from zhyy_data.Hr_Skypaycostcode;
select * from zhyy_data.hr_vw_skycostcode;

--
select a.badge,a.name,a.contype,a.conenddate from zhyy_data.hr_evw_employee a ;


--吴李沅
select * from tuser a where a.name like'%吴李%';

select * from zhyy_data.hr_econtract_register; --02731 02731 02771   03941
  









select a.badge,a.name,a.new_contype,to_char(cast(cast(a.conbegindate as timestamp) as date),'yyyymmdd') conbegindate,to_char(cast(cast(a.conenddate as timestamp) as date),'yyyymmdd') conenddate from zhyy_data.hr_econtract_register a ;

--转换成正常日期格式（yyyy-mm-dd hh24:mi:ss）--
select to_char(cast(cast('01-9月 -17 12.00.00.000000 上午' as timestamp) as date),'yyyy-mm-dd hh24:mi:ss') dateStr from dual;

select to_char(cast(cast('31-8月 -20 12.00.00.000000 上午' as timestamp) as date),'yyyy-mm-dd hh24:mi:ss') dateStr from dual;

--现合同开始日期
ABS_SQLVALUE(" select to_number(to_char(a.conbegindate,'yyyymmdd')) as HTKSRQ from zhyy_data.hr_evw_employee a where  a.badge = ?",[$Login.User.UserID]);

--合同续签隐藏
<%@ livebos language='javascript' %>
XQQX!=1&&XQQX!=2&&XQQX!=3


--新合同期限
ABS_SQLVALUE(" select 
(case 
  when  a.new_conterm is null and  a.new_conenddate is  null then 3 
  when  a.new_conterm is not null and  a.new_conenddate is not null and a.new_conterm/12 = 3  then 1
  when  a.new_conterm is not null and  a.new_conenddate is not null  and a.new_conterm/12 = 5  then 2
end ) as  XQQX
 from zhyy_data.hr_econtract_register a  where    a.badge = ? ",[$Login.User.UserID]);


select a.* from zhyy_data.hr_econtract_register a; 

select 
(case 
  when  a.new_conterm is null and  a.new_conenddate is  null then 3 
  when  a.new_conterm is not null and  a.new_conenddate is not null and a.new_conterm/12 = 3  then 1
  when  a.new_conterm is not null and  a.new_conenddate is not null  and a.new_conterm/12 = 5  then 2
end ) as  XQQX
 from zhyy_data.hr_econtract_register a  where    a.badge = '02731';

<%@ livebos language='javascript' %>
ABS_SQLVALUE(" select  (case  when  a.new_conterm is null and  a.new_conenddate is  null then 3  when  a.new_conterm is not null and  a.new_conenddate is not null and a.new_conterm/12 = 3  then 1 when  a.new_conterm is not null and  a.new_conenddate is not null  and a.new_conterm/12 = 5  then 2 end ) as  XQQX from zhyy_data.hr_econtract_register a  where    a.badge = ? ",[$Login.User.UserID]);



--新合同开始日期

ABS_SQLVALUE(" select to_char(cast(cast(a.new_conbegindate as timestamp) as date),'yyyymmdd') new_conbegindate from zhyy_data.hr_econtract_register a  where a.badge = ? ",[$Login.User.UserID]);

select to_char(cast(cast(a.new_conbegindate as timestamp) as date),'yyyymmdd') new_conbegindate from zhyy_data.hr_econtract_register a  where a.badge = '02731';

select to_char(cast(cast(a.new_conbegindate as timestamp) as date),'yyyymmdd') new_conbegindate,to_char(cast(cast(a.new_conenddate as timestamp) as date),'yyyymmdd') new_conenddate from zhyy_data.hr_econtract_register a ;


--新合同结束日期

ABS_SQLVALUE(" select to_char(cast(cast(a.new_conenddate as timestamp) as date),'yyyymmdd') new_conenddate from zhyy_data.hr_econtract_register a where a.badge = ? ",[$Login.User.UserID]);

select to_char(cast(cast(a.new_conenddate as timestamp) as date),'yyyymmdd') new_conenddate from zhyy_data.hr_econtract_register a where a.badge = '02731' ;

--入公司日期
<%@ livebos language='javascript' %>
ABS_SQLVALUE(" select  to_number(to_char(a.joindate,'yyyymmdd')) as RGSRQ  from zhyy_data.hr_evw_employee a where a.badge = ?  ",[$Login.User.UserID]);

select  to_number(to_char(a.joindate,'yyyymmdd')) as RGSRQ  from zhyy_data.hr_evw_employee a ;

select  to_number(to_char(a.joindate,'yyyymmdd')) as RGSRQ  from zhyy_data.hr_evw_employee a where a.badge = '03583' 

select  a.*  from zhyy_data.hr_evw_employee a;

--年龄
<%@ livebos language='javascript' %>
ABS_SQLVALUE(" select a.age  from zhyy_data.hr_evw_employee a where a.badge = ? ",[$Login.User.UserID]);

select a.age  from zhyy_data.hr_evw_employee a where a.badge = '03583' 






--
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
       end) as XQQX
  from zhyy_data.hr_evw_employee a,zhyy_data.hr_ocompany b,zhyy_data.hr_odepartment c,zhyy_data.hr_ojob d,zhyy_data.hr_econtract_register e,lborganization   
  where 
   a.compid = b.compid and a.depid = c.depid and a.jobid = d.jobid and a.badge = e.badge
 and a.empstatus = 1 and a.conenddate is not null 
  and a.conenddate <= sysdate+90 and a.conenddate >= sysdate;
