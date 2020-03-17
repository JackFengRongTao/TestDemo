---------删除流程脚本----------

-- 1.查询：
select *
  from os_historystep_prev pr
 where exists (select 1
          from os_historystep p
         where exists (select 1                                             
                         from lc_gdsy_ydgzpj j
                        where j.instid = p.entry_id
                )
           and p.id = pr.id);
  select *
          from os_historystep t
         where exists (select 1                 
                         from lc_gdsy_ydgzpj j
                        where j.instid = t.entry_id
                          and j.lcly = 0
                );
          select *
                  from os_currentstep_prev pr
                 where exists
                 (select 1
                          from os_currentstep p
                         where exists (select 1                                
                                         from lc_gdsy_ydgzpj j
                                        where j.instid = p.entry_id
                                          and j.lcly = 0
                                )
                           and p.id = pr.id);
                  select *
                          from os_currentstep p
                         where exists (select 1                               
                                         from lc_gdsy_ydgzpj j
                                        where j.instid = p.entry_id
                                          and j.lcly = 0
                                );
                          select *
                                  from os_wfentry y
                                 where exists (select 1
                                          from lc_gdsy_ydgzpj j
                                         where j.instid = y.id
                                           --and j.lcly = 0
                                           )
                                  select *
                                          from lc_gdsy_ydgzpj_zxxx xx
                                         where exists
                                         (select 1
                                                  from lc_gdsy_ydgzpj pj
                                                 where 
                                                 --pj.lcly = 0
                                                  -- and 
                                                   pj.id =
                                                       xx.lc_gdsy_ydgzpj_id)
select * from lc_gdsy_ydgzpj x where x.lcly=0
--2.删除：
-- delete  
 --1.
delete
  from os_historystep_prev pr
 where exists (select 1
          from os_historystep p
         where exists (select 1
                 -- from select 1
                         from lc_gdsy_ydgzpj j
                        where j.instid = p.entry_id  -- and j.lcly = 0 
                )
           and p.id = pr.id); 
 --commit;          
--2.
delete
  from os_currentstep_prev pr
 where exists (select 1
          from os_currentstep p
         where exists (select 1
                  --from select 1
                         from lc_gdsy_ydgzpj j
                        where j.instid = p.entry_id
                          --and j.lcly = 0
                )
           and p.id = pr.id);
   -- commit;       
           
--3.
delete
  from os_historystep t
 where exists (select 1
          --from select 1
                 from lc_gdsy_ydgzpj j
                where j.instid = t.entry_id
                  --and j.lcly = 0
        );
 -- commit;       

           
--4.
delete
  from os_currentstep p
 where exists (select 1
          -- from select 1
                 from lc_gdsy_ydgzpj j
                where j.instid = p.entry_id
                  --and j.lcly = 0
        );
 --commit;       
--5.
delete
  from os_wfentry y
 where exists (select 1
          from lc_gdsy_ydgzpj j
         where j.instid = y.id
           --and j.lcly = 0
           );
 --commit;          
--6.
delete
  from lc_gdsy_ydgzpj_zxxx xx
 where exists (select 1
          from lc_gdsy_ydgzpj pj
         where 
            pj.id = xx.lc_gdsy_ydgzpj_id
            --and pj.lcly = 0
            
            );
--commit;          
--7.
delete from lc_gdsy_ydgzpj x  -- where x.lcly=0
;
 commit; 

