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
           null,
           'n',
           2,
           "1h",
           36000000,
           "1",
           10);

INSERT INTO modifications ( _id,
                            entity_uri,
                            property,
                            new_value,
                            synced_value,
                            timestamp)
   VALUES (1,
           "content://dev.drsoran.provider.Rtm/tasks/1",
           "task_name",
           "Task1",
           "Task",
           1356998400000);
           
INSERT INTO modifications ( _id,
                            entity_uri,
                            property,
                            new_value,
                            synced_value,
                            timestamp)
   VALUES (2,
           "content://dev.drsoran.provider.Rtm/tasks/2",
           "task_name",
           "Task2",
           "Task",
           1356998400000);           