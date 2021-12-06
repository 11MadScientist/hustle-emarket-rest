use emarketdb;

DROP TABLE IF EXISTS item;

CREATE TABLE item
(
	id int NOT NULL AUTO_INCREMENT,
    category int  DEFAULT NULL,
    `name` varchar(77) NOT NULL,
    in_stock int DEFAULT 0,
    stock_sold int DEFAULT 0,
    price double NOT NULL,
    measurement varchar(11) NOT NULL,
    overall_review double DEFAULT 0,
    `description` varchar(111) DEFAULT NULL,
    ingredient varchar(111) DEFAULT NULL,
    direction varchar(111) DEFAULT NULL,
    store_id int,
    
    `creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(store_id) REFERENCES store(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;