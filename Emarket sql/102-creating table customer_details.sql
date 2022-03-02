-- USE emarketdb;
DROP TABLE IF EXISTS customer_detail;
CREATE TABLE customer_detail
(
	id int NOT NULL AUTO_INCREMENT,
    age int,
    gender varchar(11),
    date_of_birth date,
    email varchar(77) unique DEFAULT NULL,
    phone_number varchar(22) DEFAULT NULL,
    `status` boolean DEFAULT false,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;