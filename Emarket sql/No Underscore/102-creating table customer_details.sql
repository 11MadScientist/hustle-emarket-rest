 -- USE emarketdb;
DROP TABLE IF EXISTS customerdetail;
CREATE TABLE customerdetail
(
	id int NOT NULL AUTO_INCREMENT,
    age int,
    gender varchar(11),
    dateofbirth date,
    email varchar(77) unique DEFAULT NULL,
    phonenumber varchar(22) DEFAULT NULL,
    `status` boolean DEFAULT false,
    prohibited boolean DEFAULT false,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;