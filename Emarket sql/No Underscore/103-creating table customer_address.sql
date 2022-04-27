-- USE emarketdb;

DROP TABLE IF EXISTS customeraddress;
CREATE TABLE customeraddress
(
	id int NOT NULL AUTO_INCREMENT,
    `name` varchar(77) NOT NULL,
    province varchar(77) NOT NULL,
    city varchar(77) NOT NULL,
    barangay varchar(77) NOT NULL,
    zipcode int NOT NULL,
    direction varchar(77) NOT NULL,
    latitude double,
    longitude double,

    customerid int NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (customerid) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
