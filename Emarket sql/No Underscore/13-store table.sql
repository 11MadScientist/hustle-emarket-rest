use emarketdb;

DROP TABLE IF EXISTS store;

CREATE TABLE store
(
	id int NOT NULL AUTO_INCREMENT,
    storeName varchar(77) NOT NULL,
    storeAddress varchar(77) NOT NULL,
    overallRating double DEFAULT NULL,
    documents varchar(255) DEFAULT NULL,
    itemsAdded int DEFAULT 0,
    
    sellerId int UNIQUE,
    
    `creationDate` long NOT NULL,
    `modifiedDate` long NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (sellerId) REFERENCES seller(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;