USE emarketdb;
DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
	id int NOT NULL AUTO_INCREMENT,
    firstName varchar(77) DEFAULT NULL,
    lastName varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
	customerDetailId int,
    
    `creationDate` long NOT NULL,
    `modifiedDate` long NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY (customerDetailId) REFERENCES customerdetail(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;