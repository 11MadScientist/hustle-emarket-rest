use emarketdb;

DROP TABLE IF EXISTS seller_detail;

CREATE TABLE seller_detail
(
	id int NOT NULL AUTO_INCREMENT,
    email varchar(77) UNIQUE DEFAULT NULL,
    phone_number varchar(12) DEFAULT NULL,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;