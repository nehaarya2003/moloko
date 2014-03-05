INSERT INTO lists (_id,
                   list_name,
                   list_created,
                   list_modified,
                   list_deleted,
                   locked,
                   archived,
                   position,
                   smart,
                   filter,
                   rtm_list_id)
   VALUES (1000,
           "List1",
           1356998400000,
           1356998400001,
           NULL,
           0,
           0,
           -1,
           0,
           NULL,
           "10000");          

INSERT INTO taskseries ( _id,
                         task_created,
                         task_modified,
                         task_name,
                         source,
                         url,
                         recurrence,
                         recurrence_every,
                         location_id,
                         list_id,
                         tags,
                         rtm_taskseries_id)
   VALUES (10,
           1356998400000,
           1356998400001,
           "Task1",
           "JUNIT",
           "http://abc.de",
           "every 2 days",
           1,
           2000,
           1000,
           "tag1,tag2",
           "1000");
        
INSERT INTO rawtasks ( _id,
                      due,
                      has_due_time,
                      added,
                      completed,
                      deleted,
                      priority,
                      postponed,
                      estimate,
                      estimateMillis,
                      rtm_rawtask_id,
                      taskseries_id)
   VALUES (1,
           1356998400002,
           1,
           1356998400003,
           1356998400004,
           1356998400005,
           'n',
           2,
           "1h",
           36000000,
           "5000",
           10);
           
INSERT INTO contacts (  _id,
                         fullname,
                         username,
                         rtm_contact_id)
   VALUES (1,
           "Full Name1",
           "User1",
           "1000");
        
INSERT INTO contacts (  _id,
                         fullname,
                         username,
                         rtm_contact_id)
   VALUES (2,
           "Full Name2",
           "User2",
           "1001");
           
INSERT INTO participants ( _id,
                           contact_id,
                           fullname,
                           username,
                           taskseries_id)
   VALUES (1,
           1,
           "Full Name1",
           "User1",
           10);

INSERT INTO participants ( _id,
                           contact_id,
                           fullname,
                           username,
                           taskseries_id)
   VALUES (2,
           2,
           "Full Name2",
           "User2",
           10);
           