use emarketdb;

DROP TABLE IF EXISTS basket;

CREATE TABLE basket
(
	id int NOT NULL AUTO_INCREMENT,
    itemId int NOT NULL,
    quantity double NOT NULL,
    customerId int NOT NULL,
    
	`creationDate` long NOT NULL,
    `modifiedDate` long NOT NULL,
    
    primary key(id),
	UNIQUE pair (customerId, itemId),
    FOREIGN KEY(itemId) REFERENCES item(id),
    FOREIGN KEY(customerId) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
    
    
)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

