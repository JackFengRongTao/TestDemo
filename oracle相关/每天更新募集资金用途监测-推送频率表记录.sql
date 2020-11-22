/*
--������     : gdsy.pro_update_mjzjytjc_dstspl
--���̹���   : ����ļ���ʽ���;���-����Ƶ�ʱ��¼
--��ע       ��

--�޸ļ�¼   : 
--�޸�����     �޸���  �汾     �޸�����
--2020.09.27  ������  V1.0.0   �½�
*/

BEGIN
    gdsy.pro_setversion('gdsy', 'pro_update_mjzjytjc_dstspl', '1.0.0');
END;
/

create or replace procedure pro_update_mjzjytjc_dstspl  --ÿ�����ļ���ʽ���;���-����Ƶ�ʱ��¼

 as
  v_shouldfsr number(8);--��һ�����͵�����
begin
  --1.��������������ծ������ݽ�����������Ƶ�ʼ�¼
     --��һ����������
       select c.rq into  v_shouldfsr    
        from (select rownum as rn, b.*
                from (select a.*
                        from gdsy_data.cif_tjyr a
                       where a.jyrbs = 1
                         and substr(a.rq, 1, 6) =
                             to_char(add_months(trunc(sysdate),
                                                1),
                                     'yyyymm')
                       order by a.rq asc) b
               where rownum <=
                     (select a.csz from txtcs a where a.csdm = 'ydgzpjzxrq')) c
       where c.rn = (select a.csz from txtcs a where a.csdm = 'ydgzpjzxrq');
    --������������
    insert into t_gdsy_mjzjytjc_dstspl
      (id, zx, zxjc, zxdm, tslx, tspl, xctsrq, cxqbz, sfts, cjsj, xgsj)
      select 
      func_nextid('t_gdsy_mjzjytjc_dstspl'),
      a.id,
      a.w_zqjc,
      a.w_zqdm,
      5,
      3,
      v_shouldfsr,
      1,
      10,
      sysdate,
      sysdate
        from t_gdsy_zxxx a
       where a.w_jxqsr <= to_number(to_char(sysdate, 'yyyymmdd'))
         and a.w_dfr > to_number(to_char(sysdate, 'yyyymmdd'))
         and not exists
       (select 1 from t_gdsy_mjzjytjc_dstspl b where b.zx = a.id);
  --2.���ڹ��˴����ڵ���Ŀ������������Ƶ�ʼ�¼�޸�
      --��������Ƶ�ʱ�
      update t_gdsy_mjzjytjc_dstspl a
         set a.cxqbz = 0, a.sfts = 20,a.xgsj = sysdate
       where exists (select 1
                from (select b.zx
                        from t_gdsy_mjzjytjc_dstspl b
                       where not exists
                       (select 1
                                from t_gdsy_zxxx a
                               where a.w_jxqsr <=
                                     to_number(to_char(sysdate, 'yyyymmdd'))
                                 and a.w_dfr >
                                     to_number(to_char(sysdate, 'yyyymmdd'))
                                 and a.id = b.zx)) c
               where c.zx = a.zx);
  --�˴������������񽻸�������������
end pro_update_mjzjytjc_dstspl;
/
