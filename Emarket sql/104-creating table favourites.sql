-- use emarketdb;

DROP TABLE IF EXISTS favourites;

CREATE TABLE favourites
(
	id int NOT NULL AUTO_INCREMENT,
    customer_id int NOT NULL,
    item_id int NOT NULL,

    
    primary key(id),
	UNIQUE pair (customer_id, item_id),
    FOREIGN KEY(customer_id) REFERENCES customer(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
    
    
)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

