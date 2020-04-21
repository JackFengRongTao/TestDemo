select * from tuser a where a.name in('吴李沅','唐淑艳','褚海峰');

select * from  lc_gdsy_ydgzpj a  order by a.id;

select * from  t_gdsy_dstspl ;

select a.id, a.zt, a.fxr, a.fqsj, to_char(to_date(a.yf||'','yyyymm'),'yyyy') as nf ,to_char(to_date(a.yf||'','yyyymm'),'mm') as yf,b.gsmc
  from lc_gdsy_ydgzpj a,t_gdsy_fxrxx b
 where a.fxr = b.id 
 and to_number(to_char(sysdate, 'yyyymmdd')) >=
       a.cbrq
   and a.tjrq is null

select e.id,e.userId,e.name from tUser e where e.ID in(select distinct fzr
  from T_GDSY_JSRYXMGL a, T_GDSY_JSJBXX b, t_gdsy_xmjbxx c, t_gdsy_fxrxx d
 where a.js = b.id
   and a.xm = c.id
   and c.tyshxydm = d.tyshxydm
   and c.cxqbz = 1
   and d.cxqbz_gsz = 1  
   and c.zqlx = 'GSZ'
   and d.id = 1402
   and b.jsmc in('后续跟踪及受托管理人员','项目现场负责人','项目负责人','质控人员'));
   
select  *  from tsysparam;

select  *  from txtcs;


