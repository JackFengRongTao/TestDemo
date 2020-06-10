CREATE OR REPLACE FUNCTION func_get_hqyhsjqx(i_userid IN VARCHAR2,
                                             i_type   IN VARCHAR2) ---0代表项目，1代表发行人
 RETURN VARCHAR2 IS
  sjqx VARCHAR2(9000);
BEGIN
  if i_type = 0 then
    SELECT to_char(wm_concat(t.id))
      INTO sjqx
      FROM t_gdsy_xmjbxx t
     WHERE i_userid = 0
        or i_userid in (select lb.userid
                          from lbmember lb
                         where exists (select 1
                                  from lbrole l
                                 where l.rolecode = 'XM01'
                                   and l.id = lb.roleid))
        or exists (select 1
              from t_gdsy_jsryxmgl gl
             where gl.fzr = i_userid
               and gl.xm = t.id);
  else
    SELECT to_char(wm_concat(xx.id))
      INTO sjqx
      FROM t_gdsy_fxrxx xx
     WHERE i_userid = 0
        or i_userid in (select lb.userid
                          from lbmember lb
                         where exists (select 1
                                  from lbrole l
                                 where l.rolecode = 'XM01'
                                   and l.id = lb.roleid))
        or exists (select 1
              from t_gdsy_xmjbxx b
             where b.tyshxydm = xx.tyshxydm
               and exists (select 1
                      from t_gdsy_jsryxmgl ll
                     where ll.fzr = i_userid
                       and ll.xm = b.id));
  end if;
  RETURN(sjqx);
END func_get_hqyhsjqx;
/
