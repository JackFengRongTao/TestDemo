-- 菜单数据展示有无问题——数据筛选
exists
 (select 1
          from t_gdsy_fxrxx a
         where a.id = lc_gdsy_ydgzpj.fxr
           and a.cxqbz_gsz = 1
           and exists
         (select 1
                  from t_gdsy_xmjbxx b
                 where b.tyshxydm = a.tyshxydm
                   and b.cxqbz = 1
                   and b.zqlx = 'GSZ'
                   and exists
                 (select 1
                          from t_gdsy_jsryxmgl c
                         where c.xm = b.id
                           and (c.fzr = {0} or 0 = {0} or
                               {0} = (select distinct a.id
                                         from tuser a, lbmember b, lbrole d
                                        where a.id = b.userid
                                          and b.roleid = d.id
                                          and a.id = {0}
                                          and d.rolecode = 'XM01')))))
and  ({1} is null or  lc_GDSY_YDGZPJ.Fxr = {1})
and ({2} is null or  lc_GDSY_YDGZPJ.Yf >= {2})
and ({3} is null or lc_GDSY_YDGZPJ.Yf <= {3})
and ({4} is null or exists (  select 1 from os_wfentry a where a.id =  lc_GDSY_YDGZPJ.Instid and  instr(';'||{4}||';',a.state) > 0));

select count(1) from lc_gdsy_ydgzpj;
select distinct a.id
                                         from tuser a, lbmember b, lbrole d
                                        where a.id = b.userid
                                          and b.roleid = d.id
                                          --and a.id = 0
                                          and d.rolecode = 'XM01';
 -- 目前版本                                         
select * from lc_GDSY_YDGZPJ lc_GDSY_YDGZPJ where
exists (select 1
                          from t_gdsy_jsryxmgl c
                         where 
                         (c.fzr = 1610 or 0 = 1610 
                               ))
and  ({1} is null or  lc_GDSY_YDGZPJ.Fxr = {1})
and ({2} is null or  lc_GDSY_YDGZPJ.Yf >= {2})
and ({3} is null or lc_GDSY_YDGZPJ.Yf <= {3})
and ({4} is null or exists (  select 1 from os_wfentry a where a.id =  lc_GDSY_YDGZPJ.Instid and  instr(';'||{4}||';',a.state) > 0));


select * from tuser tuser where tUser.ID in(select distinct a.fzr--,b.jsmc
  from T_GDSY_JSRYXMGL a, T_GDSY_JSJBXX b, t_gdsy_xmjbxx c, t_gdsy_fxrxx d
 where a.js = b.id
   and a.xm = c.id
   and c.tyshxydm = d.tyshxydm
   and c.cxqbz = 1
   and d.cxqbz_gsz = 1  
   and c.zqlx = 'GSZ'
   and d.id = 1465
  and b.jsmc = '质控人员'
   );

select * from t_gdsy_fxrxx a where a.gsmc like'%常熟%';

select * from tuser a where a.name like'%金韬%';
select * from tuser a where a.name like'%任越%';
select * from tuser a where a.name like'%李倩%';
select * from tuser a where a.name like'%朱嘉%';
select distinct a.gsmc,a.id
  from t_gdsy_fxrxx a, t_gdsy_xmjbxx b, t_gdsy_jsryxmgl c
 where a.tyshxydm = b.tyshxydm
   and b.id = c.xm
   and c.fzr = 1915
   and a.cxqbz_gsz =1
   and b.cxqbz =1 
   and b.zqlx ='GSZ'
   ---and a.id = 1450;

select * from  t_gdsy_jsjbxx ;
select * from  t ;


-- 项目数据筛选有无问题
T_GDSY_XMJBXX.zqlx = 'GSZ'
   and T_GDSY_XMJBXX.cxqbz = 1
   and exists (select 1
          from t_gdsy_fxrxx b
         where b.tyshxydm = T_GDSY_XMJBXX.tyshxydm
           and b.cxqbz_gsz = 1)
   and (0 = {0} or {0} =
        (select distinct a.id
  from tuser a, lbmember b, lbrole d
 where a.id = b.userid 
   and b.roleid = d.id   and a.id = {0} and  d.rolecode = 'XM01') or exists (select 1
                           from t_gdsy_jsryxmgl gl
                          where gl.fzr = {0}
                            and gl.xm = T_GDSY_XMJBXX.ID))
                            
select * from lc_gdsy_ydgzpj order by id desc;

select * from t_gdsy_fxrxx a where a.tyshxydm='91320300673906392P';

-- 催办日期 跨月问题
select rq
  from (select rownum as rn, b.*
          from (select a.*
                  from gdsy_data.cif_tjyr a
                 where a.jyrbs = 1
                   and (substr(a.rq, 1, 6) =
                       substr(to_char(sysdate, 'yyyymmdd'), 1, 6) or 
                       substr(a.rq, 1, 6) =
                       substr(to_char(add_months(sysdate,1), 'yyyymmdd'), 1, 6))
                   and a.rq >= to_number(to_char(sysdate, 'yyyymmdd'))
                 order by a.rq asc) b
         where rownum <= 6) c
 where c.rn = 6;
 
 
 select a.*
                  from gdsy_data.cif_tjyr a
                 where a.jyrbs = 1
                   and (substr(a.rq, 1, 6) =
                       substr(to_char(sysdate, 'yyyymmdd'), 1, 6) or 
                       substr(a.rq, 1, 6) =
                       substr(to_char(add_months(sysdate,1), 'yyyymmdd'), 1, 6))
                   and a.rq >= to_number(to_char(sysdate, 'yyyymmdd'))
                 order by a.rq asc;
 
--项目档案袋
select * from T_GS_DAD order by id desc;
