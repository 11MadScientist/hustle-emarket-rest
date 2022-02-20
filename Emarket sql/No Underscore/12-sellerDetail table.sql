use emarketdb;

DROP TABLE IF EXISTS sellerDetail;

CREATE TABLE sellerDetail
(
	id int NOT NULL AUTO_INCREMENT,
    email varchar(77) UNIQUE DEFAULT NULL,
    phoneNumber varchar(12) DEFAULT NULL,
    `status` boolean DEFAULT false,
    authorized boolean DEFAULT false,
     
    dpLink varchar(255) DEFAULT NULL,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;