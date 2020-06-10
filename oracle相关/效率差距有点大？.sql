--效率差距有点大？

select func_get_hqyhsjqx(1781,1) from dual;


select a.id from t_gdsy_fxrxx a where instr(','||func_get_hqyhsjqx(1781,1)||',',','||a.id||',') > 0;


select a.id from t_gdsy_fxrxx a where instr(','||(select func_get_hqyhsjqx(1781,1) from dual)||',',','||a.id||',') > 0; 

