-- USE emarketdb;
DROP TABLE IF EXISTS riderdetail;
CREATE TABLE riderdetail
(
	id int NOT NULL AUTO_INCREMENT,
    age int,
    gender varchar(11),
    dateofbirth date,
    email varchar(77) unique DEFAULT NULL,
    phonenumber varchar(22) NOT NULL,
    station varchar(22) NOT NULL,
    documents varchar(77) DEFAULT NULL,
    authorized boolean default false,
    prohibited boolean DEFAULT false,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;