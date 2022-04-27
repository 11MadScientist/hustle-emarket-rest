-- USE emarketdb;

DROP TABLE IF EXISTS notification;

CREATE TABLE notification(
	id int NOT NULL AUTO_INCREMENT,
    userid int,
    link varchar(112),
    notiftype varchar(25),
    isread boolean default false,
    `role` varchar(11),
    message varchar(500),
    
    `creationdate` datetime default current_timestamp,
    `readdate` datetime default current_timestamp
					on update current_timestamp,

    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
