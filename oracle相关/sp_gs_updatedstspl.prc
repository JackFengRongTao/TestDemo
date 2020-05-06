create or replace procedure sp_gs_updatedstspl(in_fxr in number)  --发行人id --更新实际推送日期和下次推送日期

 as
  v_sjtsrq    number(8); --实际推送日期
  v_tspl      number(12);--推送频率
  v_shouldfsr number(8); --根据推送频率和实际推送日计算的下次发送日期
begin

  select a.sjtsrq, a.tspl
    into v_sjtsrq, v_tspl
    from t_gdsy_dstspl a
   where a.fxr = in_fxr;
  -- 更新实际推送日期和下次推送日期
   select c.rq
      into v_shouldfsr
      from (select rownum as rn, b.*
              from (select a.*
                      from gdsy_data.cif_tjyr a
                     where a.jyrbs = 1
                       and substr(a.rq, 1, 6) =
                           to_char(add_months(trunc(sysdate),
                                              v_tspl),
                                   'yyyymm')
                     order by a.rq asc) b
             where rownum <=
                   (select a.csz from txtcs a where a.csdm = 'ydgzpjzxrq')) c
     where c.rn = (select a.csz from txtcs a where a.csdm = 'ydgzpjzxrq');
      update t_gdsy_dstspl w set w.xctsrq = v_shouldfsr,w.sjtsrq = to_number(to_char(sysdate,'yyyymmdd')) where w.fxr = in_fxr;

 --此处不加事务，事务交给调用者来控制
end sp_gs_updatedstspl;
/
