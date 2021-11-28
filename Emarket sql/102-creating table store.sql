use emarketdb;

DROP TABLE IF EXISTS store;

CREATE TABLE store
(
	id int NOT NULL AUTO_INCREMENT,
    store_name varchar(77) NOT NULL,
    store_address varchar(77) NOT NULL,
    overall_rating double DEFAULT NULL,
    items_added int DEFAULT NULL,
    
    `creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;