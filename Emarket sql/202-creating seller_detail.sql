use emarketdb;

DROP TABLE IF EXISTS seller_detail;

CREATE TABLE seller_detail
(
	id int NOT NULL AUTO_INCREMENT,
    age int NOT NULL,
    gender varchar(11) NOT NULL,
    date_of_birth date,
    email varchar(77) UNIQUE DEFAULT NULL,
    phone_number varchar(12) DEFAULT NULL,
    `status` boolean DEFAULT false,
    authorized boolean DEFAULT false,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;