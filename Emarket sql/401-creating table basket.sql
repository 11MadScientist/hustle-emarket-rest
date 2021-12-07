use emarketdb;

DROP TABLE IF EXISTS basket;

CREATE TABLE basket
(
	id int NOT NULL AUTO_INCREMENT,
    item_id int NOT NULL,
    quantity double NOT NULL,
    customer_id int NOT NULL,
    
	`creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
	UNIQUE pair (customer_id, item_id),
    FOREIGN KEY(item_id) REFERENCES item(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
    
    
)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

