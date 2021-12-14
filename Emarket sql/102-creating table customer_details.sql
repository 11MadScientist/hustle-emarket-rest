USE emarketdb;
DROP TABLE IF EXISTS customer_detail;
CREATE TABLE customer_detail
(
	id int NOT NULL AUTO_INCREMENT,
    email varchar(77) UNIQUE DEFAULT NULL,
    phone_number varchar(22) DEFAULT NULL,
    `status` boolean DEFAULT false,
    dp_link varchar(255) DEFAULT NULL,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;