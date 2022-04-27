--  use emarketdb;

DROP TABLE IF EXISTS itemreview;

CREATE TABLE itemreview
(
	id int NOT NULL AUTO_INCREMENT,
    rating double DEFAULT 0,
    review varchar(500),
    itemid int,
    customerid int,
    
   `creationdate` datetime default current_timestamp,
    `modifieddate` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
    FOREIGN KEY(itemid) REFERENCES item(id),
    FOREIGN KEY (customerid) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;