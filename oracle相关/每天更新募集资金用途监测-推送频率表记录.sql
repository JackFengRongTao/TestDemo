/*
--过程名     : gdsy.pro_update_mjzjytjc_dstspl
--过程功能   : 更新募集资金用途监测-推送频率表记录
--备注       ：

--修改记录   : 
--修改日期     修改人  版本     修改内容
--2020.09.27  冯荣涛  V1.0.0   新建
*/

BEGIN
    gdsy.pro_setversion('gdsy', 'pro_update_mjzjytjc_dstspl', '1.0.0');
END;
/

create or replace procedure pro_update_mjzjytjc_dstspl  --每天更新募集资金用途监测-推送频率表记录

 as
  v_shouldfsr number(8);--第一次推送的日期
begin
  --1.对于新增存续期债项的数据进行新增推送频率记录
     --第一次推送日期
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
    --插入新增数据
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
  --2.对于过了存续期的项目进行新增推送频率记录修改
      --更新推送频率表
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
  --此处不加事务，事务交给调用者来控制
end pro_update_mjzjytjc_dstspl;
/
