--2.É¾³ý£º
-- delete  
 --1.
delete
  from os_historystep_prev pr
 where exists (select 1
          from os_historystep p
         where exists (select 1             
                         from lc_hr_ygtxsq j
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
                         from lc_hr_ygtxsq j
                        where j.instid = p.entry_id                         
                )
           and p.id = pr.id);            
--3.
delete
  from os_historystep t
 where exists (select 1
                 from lc_hr_ygtxsq j
                where j.instid = t.entry_id               
        );
  commit;       

           
--4.
delete
  from os_currentstep p
 where exists (select 1
                 from lc_hr_ygtxsq j
                where j.instid = p.entry_id
        );
 commit;       
--5.
delete
  from os_wfentry y
 where exists (select 1
          from lc_hr_ygtxsq j
         where j.instid = y.id           
           );
 commit;          
--6.
   );
commit;          
--7.
delete from lc_hr_ygtxsq x  
;
 commit; 
