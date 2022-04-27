-- use emarketdb;

DROP TABLE IF EXISTS store;

CREATE TABLE store
(
	id int NOT NULL AUTO_INCREMENT,
    storename varchar(77) NOT NULL,
    storeaddress varchar(77) NOT NULL,
    overallrating double DEFAULT NULL,
    documents varchar(255) DEFAULT NULL,
    itemsadded int DEFAULT 0,
    
    sellerid int UNIQUE,
    
   `creationdate` datetime default current_timestamp,
    `modifieddate` datetime default current_timestamp
					on update current_timestamp,
    
    PRIMARY KEY(id),
    FOREIGN KEY (sellerid) REFERENCES seller(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;