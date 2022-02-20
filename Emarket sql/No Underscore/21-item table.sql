use emarketdb;

DROP TABLE IF EXISTS item;

CREATE TABLE item
(
	id int NOT NULL AUTO_INCREMENT,
    category int  DEFAULT NULL,
    `name` varchar(77) NOT NULL,
    inStock double DEFAULT 0,
    stockSold double DEFAULT 0,
    price double NOT NULL,
    measurement varchar(11) NOT NULL,
    `increment` double NOT NULL,
    overallReview double DEFAULT 0,
    `description` varchar(111) DEFAULT NULL,
    ingredient varchar(111) DEFAULT NULL,
    direction varchar(111) DEFAULT NULL,
    delisted boolean DEFAULT false,
    storeId int,
    
    `creationDate` long NOT NULL,
    `modifiedDate` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(storeId) REFERENCES store(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;