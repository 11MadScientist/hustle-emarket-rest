-- use emarketdb;

DROP TABLE IF EXISTS store;

CREATE TABLE store
(
	id int NOT NULL AUTO_INCREMENT,
    store_name varchar(77) NOT NULL,
    store_address varchar(77) NOT NULL,
    overall_rating double DEFAULT NULL,
    documents varchar(255) DEFAULT NULL,
    items_added int DEFAULT 0,
    
    seller_id int UNIQUE,
    
   `creation_date` datetime default current_timestamp,
    `modified_date` datetime default current_timestamp
					on update current_timestamp,
    
    PRIMARY KEY(id),
    FOREIGN KEY (seller_id) REFERENCES seller(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;