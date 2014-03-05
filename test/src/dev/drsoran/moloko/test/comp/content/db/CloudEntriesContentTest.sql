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
   VALUES (1001,
           "List2",
           1356998400000,
           1356998400001,
           NULL,
           0,
           0,
           -1,
           0,
           NULL,
           "10001");
           
INSERT INTO locations (  _id,
                         location_name,
                         longitude,
                         latitude,
                         address,
                         viewable,
                         zoom,                       
                         rtm_location_id)
   VALUES (1,
           "Home",
           1.0,
           2.0,
           NULL,
           1,
           10,
           "1000");
        
INSERT INTO locations (  _id,
                         location_name,
                         longitude,
                         latitude,
                         address,
                         viewable,
                         zoom,                       
                         rtm_location_id)
   VALUES (2,
           "LocationWithAddr",
           3.0,
           4.0,
           "Main Street",
           1,
           20,
           "1001");

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
           null,
           null,
           -1,
           null,
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
           null,
           0,
           1356998400003,
           1356998400004,
           1356998400005,
           'n',
           1,
           null,
           -1,
           "5000",
           10);
           
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
   VALUES (2,
           null,
           0,
           1356998400003,
           null,
           null,
           'n',
           2,
           "11 minutes",
           660000,
           "5001",
           10);
           
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
   VALUES (11,
           1356998400000,
           1356998400001,
           "NonTaggedTask",
           "JUNIT",
           null,
           null,
           0,
           1,
           1000,
           null,
           "1001");
        
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
   VALUES (3,
           null,
           0,
           1356998400003,
           null,
           null,
           '1',
           0,
           null,
           -1,
           "5002",
           11);
           
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
   VALUES (12,
           1356998400000,
           1356998400001,
           "TaggedTask2",
           "JUNIT",
           null,
           null,
           0,
           1,
           1000,
           "tag1,Tag1,Tag3",
           "1002");
        
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
   VALUES (4,
           null,
           0,
           1356998400003,
           null,
           null,
           '1',
           0,
           null,
           -1,
           "5003",
           12);