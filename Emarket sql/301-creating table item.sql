-- use emarketdb;

DROP TABLE IF EXISTS item;

CREATE TABLE item
(
	id int NOT NULL AUTO_INCREMENT,
    category int  DEFAULT NULL,
    `name` varchar(77) NOT NULL,
    in_stock double DEFAULT 0,
    stock_sold double DEFAULT 0,
    price double NOT NULL,
    measurement varchar(11) NOT NULL,
    `increment` double NOT NULL,
    overall_review double DEFAULT 0,
    `description` varchar(111) DEFAULT NULL,
    ingredient varchar(111) DEFAULT NULL,
    direction varchar(111) DEFAULT NULL,
    delisted boolean DEFAULT false,
    store_id int,
    
    `creation_date` datetime default current_timestamp,
    `modified_date` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
    FOREIGN KEY(store_id) REFERENCES store(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;