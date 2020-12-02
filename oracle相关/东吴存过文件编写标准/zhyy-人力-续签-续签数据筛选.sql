/*
--������     : ZHYY.sp_zhyy_hr_queryldhtxqdata
--���̹���   : ��ѯ����-��Ҫ��ǩ��Ա����
--��ע       ��

--�޸ļ�¼   : 
--�޸�����    �޸���  �汾     �޸�����
--2020.12.02  ������  V1.0.0   �½�
*/

BEGIN
    zhyy.pro_setversion('ZHYY', 'sp_zhyy_hr_queryldhtxqdata', '1.0.0');
END;
/

create or replace procedure sp_zhyy_hr_queryldhtxqdata(ldhtxqdata out types.cursortype)  --���ؽ�����α�

is 
  v_orgtype  varchar2(200);
  v_userids  varchar2(2000);
begin
  v_userids:='';
  --1.��ѯ���з���������orgtype = 7��һ�����ţ��Ĺ��Ŵ�
  for rowentry in (select a.badge as GH
                     from zhyy_data.hr_evw_employee       a,
                          zhyy_data.hr_econtract_register e,
                          tuser                           f,
                          lborganization                  g
                    where a.badge = e.badge
                      and a.badge = f.userid
                      and f.orgid = g.id
                      and a.empstatus = 1
                      and a.conenddate is not null
                      and a.conenddate <= sysdate + 90
                      and a.conenddate >= sysdate
                      and not exists
                    (select 1 from lc_hr_ldhtxq h where h.gh = a.badge)) loop
    select a.orgtype
      into v_orgtype
      from lborganization a
     where a.id in (select func_get_yjbm(a.orgid)
                      from tuser a
                     where a.userid = rowentry.GH);
    if (v_orgtype = 7) then
      v_userids := v_userids||';'||rowentry.GH;     
    end if;  
  end loop;
     if length(v_userids)>0 then
       v_userids := v_userids||';';
       dbms_output.put_line(v_userids);  
     end if; 
     --2.���ط������������Ľ����
     open ldhtxqdata for
    select a.name as XM,
       a.badge as GH,
       a.gender as XB,
       a.birthday as CSRQ,
       a.certno as ZJBH,
       a.age as NL,
       a.joindate as RGSRQ,
       f.oa_post as GW,
       f.oa_workphone as DH,
       f.oa_telphone as SJ,
       f.oa_email as YX,
       a.conbegindate HTKSRQ,
       a.conenddate HTJSRQ,
       e.new_conbegindate as XHTKSRQ,
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
       func_get_yjbm(f.orgid) as BM
  from zhyy_data.hr_evw_employee       a,
       zhyy_data.hr_econtract_register e,
       tuser                           f,
       lborganization                  g
 where a.badge = e.badge
   and a.badge = f.userid
   and f.orgid = g.id
   and a.empstatus = 1
   and a.conenddate is not null
   and a.conenddate <= sysdate + 90
   and a.conenddate >= sysdate
   and instr(v_userids,';'||a.badge||';')>0
   and not exists( select 1 from  lc_hr_ldhtxq  h where h.gh = a.badge);        
end sp_zhyy_hr_queryldhtxqdata;
/
   
