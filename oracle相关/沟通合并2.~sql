select * from lc_gdsy_ydgzpj a order by id desc;  -- 2272  2273 2274

select * from lc_gdsy_ydgzpj_sheyj a order by id desc;

select * from lbworkcommunication a order by id desc;

select * from os_historystep a where  a.entry_id = 6346  order by id;  -- 6344 6345 

select * from os_currentstep a where  a.entry_id = 6346  order by id;

-- select * from os_workflowdefs;

-- select * from tuser a where a.id = 1813;


select id,
       entry_id,
       step_id,
       action_id,
       owner,
       start_date,
       finish_date,   
       caller,
       status
  from os_historystep a
 where a.entry_id = 6346 
union
select id,
       entry_id,
       step_id,
       action_id,
       owner,
       start_date,
       finish_date,  
       caller,
       status
  from os_currentstep a
 where a.entry_id = 6346;

