----删除月度跟踪评价推送频率流程-----
 --1.
delete
  from os_historystep_prev pr
 where exists (select 1
          from os_historystep p
         where exists (select 1                
                         from lc_gdsy_ydgzpjtspl j
                        where j.instid = p.entry_id  
                )
           and p.id = pr.id); 
 commit;          
--2.
delete
  from os_currentstep_prev pr
 where exists (select 1
          from os_currentstep p
         where exists (select 1                 
                         from lc_gdsy_ydgzpjtspl j
                        where j.instid = p.entry_id
                         )
           and p.id = pr.id);
    commit;       
           
--3.
delete
  from os_historystep t
 where exists (select 1       
                 from lc_gdsy_ydgzpjtspl j
                where j.instid = t.entry_id
                         );
  commit;       

           
--4.
delete
  from os_currentstep p
 where exists (select 1       
                 from lc_gdsy_ydgzpjtspl j
                where j.instid = p.entry_id                
        );
 commit;       
--5.
delete
  from os_wfentry y
 where exists (select 1
          from lc_gdsy_ydgzpjtspl j
         where j.instid = y.id         
           );
 commit;  
--6. 
delete
  from lc_gdsy_ydgzpjtspl_shryj y
 where exists (select 1
          from lc_gdsy_ydgzpjtspl j
         where j.id = y.lc_gdsy_ydgzpjtspl_id         
           );
 commit; 
--7.
delete from lc_gdsy_ydgzpjtspl x  
;
 commit; 

--8.
update t_gdsy_dstspl a set a.tslx=1 , a.tspl = 1;
commit;
