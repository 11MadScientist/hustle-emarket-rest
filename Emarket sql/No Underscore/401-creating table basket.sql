-- use emarketdb;

DROP TABLE IF EXISTS basket;

CREATE TABLE basket
(
	id int NOT NULL AUTO_INCREMENT,
    itemid int NOT NULL,
    quantity double NOT NULL,
    customerid int NOT NULL,
    
	`creationdate` datetime default current_timestamp,
    `modifieddate` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
	UNIQUE pair (customerid, itemid),
    FOREIGN KEY(itemid) REFERENCES item(id),
    FOREIGN KEY(customerid) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
    
    
)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

