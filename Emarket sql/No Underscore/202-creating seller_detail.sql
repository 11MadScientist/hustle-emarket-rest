-- use emarketdb;

DROP TABLE IF EXISTS sellerdetail;

CREATE TABLE sellerdetail
(
	id int NOT NULL AUTO_INCREMENT,
    age int,
    gender varchar(11),
    dateofbirth date,
    email varchar(77) UNIQUE DEFAULT NULL,
    phonenumber varchar(12) DEFAULT NULL,
    `status` boolean DEFAULT false,
    authorized boolean DEFAULT false,
    prohibited boolean DEFAULT false,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;