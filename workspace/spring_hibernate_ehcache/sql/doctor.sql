CREATE TABLE `doctor_table` (                    
                `doctor_id` int(11) NOT NULL,                  
                `doctor_name` varchar(30) default NULL,        
                `doctor_speciality` varchar(30) default NULL,  
                PRIMARY KEY  (`doctor_id`)                     
              ) ENGINE=InnoDB DEFAULT CHARSET=utf8