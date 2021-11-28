use emarketdb;

DROP TABLE IF EXISTS seller;

CREATE TABLE seller
(
	id int NOT NULL AUTO_INCREMENT,
    first_name varchar(77) DEFAULT NULL,
    last_name varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
    seller_store_id int,
    
	`creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
	PRIMARY KEY(id),
    FOREIGN KEY (seller_store_id) REFERENCES store(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;