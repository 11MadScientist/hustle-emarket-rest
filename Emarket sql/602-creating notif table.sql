USE emarketdb;

DROP TABLE IF EXISTS notification;

CREATE TABLE notification(
	id int NOT NULL AUTO_INCREMENT,
    user_id int,
    `role` varchar(11),
    message varchar(500),
    
    `creation_date` datetime default current_timestamp,
    `read_date` datetime default current_timestamp
					on update current_timestamp,

    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
