----ɾ���¶ȸ�����������----- 
 --1.
delete
  from os_historystep_prev pr
 where exists (select 1
          from os_historystep p
         where exists (select 1               
                         from lc_gdsy_ydgzpj j
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
                         from lc_gdsy_ydgzpj j
                        where j.instid = p.entry_id
                )
           and p.id = pr.id);
    commit;       
           
--3.
delete
  from os_historystep t
 where exists (select 1        
                 from lc_gdsy_ydgzpj j
                where j.instid = t.entry_id               
        );
  commit;       

           
--4.
delete
  from os_currentstep p
 where exists (select 1       
                 from lc_gdsy_ydgzpj j
                where j.instid = p.entry_id              
        );
 commit;       
--5.
delete
  from os_wfentry y
 where exists (select 1
          from lc_gdsy_ydgzpj j
         where j.instid = y.id      
           );
 commit;          
--6.
delete
  from lc_gdsy_ydgzpj_zxxx xx
 where exists (select 1
          from lc_gdsy_ydgzpj pj
         where 
            pj.id = xx.lc_gdsy_ydgzpj_id                   
            );
commit; 
--7.
delete
  from lc_gdsy_ydgzpj_sheyj xx
 where exists (select 1
          from lc_gdsy_ydgzpj pj
         where 
            pj.id = xx.lc_gdsy_ydgzpj_id                   
            );
commit;     
--8.
delete from lc_gdsy_ydgzpj x ;
 commit;
  
--9.ɾ���¶ȸ�����ص���Ŀ�׸嵵����
delete from t_gs_dad a
 where exists (select 1
          from t_gs_dadcs b
         where b.id = a.dadid
           and b.bh = 'A004'
           and b.name = '�¶ȸ������۱�');
commit;

--10.
update t_gdsy_dstspl a set  a.sjtsrq = null;
commit;
