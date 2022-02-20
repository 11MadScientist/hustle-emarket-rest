USE emarketdb;

DROP TABLE IF EXISTS customerAddress;
CREATE TABLE customerAddress
(
	id int NOT NULL AUTO_INCREMENT,
    `name` varchar(77) NOT NULL,
    city varchar(77) NOT NULL,
    province varchar(77) NOT NULL,
    zipCode int NOT NULL,
    direction varchar(77) NOT NULL,

    customerId int NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (customerId) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
