-- use emarketdb;

DROP TABLE IF EXISTS seller;

CREATE TABLE seller
(
	id int NOT NULL AUTO_INCREMENT,
    first_name varchar(77) DEFAULT NULL,
    last_name varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
    seller_detail_id int UNIQUE,
    
	`creation_date` datetime default current_timestamp,
    `modified_date` datetime default current_timestamp
					on update current_timestamp,
    
	PRIMARY KEY(id),
    FOREIGN KEY (seller_detail_id) REFERENCES seller_detail(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;