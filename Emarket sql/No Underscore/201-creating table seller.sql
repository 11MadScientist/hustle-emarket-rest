-- use emarketdb;

DROP TABLE IF EXISTS seller;

CREATE TABLE seller
(
	id int NOT NULL AUTO_INCREMENT,
    firstname varchar(77) DEFAULT NULL,
    lastname varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
    sellerdetailid int UNIQUE,
    `role` varchar(11) DEFAULT "SELLER",
    
	`creationdate` datetime default current_timestamp,
    `modifieddate` datetime default current_timestamp
					on update current_timestamp,
    
	PRIMARY KEY(id),
    FOREIGN KEY (sellerdetailid) REFERENCES sellerdetail(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;