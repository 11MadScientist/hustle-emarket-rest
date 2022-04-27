-- use emarketdb;

DROP TABLE IF EXISTS favourites;

CREATE TABLE favourites
(
	id int NOT NULL AUTO_INCREMENT,
    customerid int NOT NULL,
    itemid int NOT NULL,

    
    primary key(id),
	UNIQUE pair (customerid, itemid),
    FOREIGN KEY(customerid) REFERENCES customer(id),
    FOREIGN KEY(itemid) REFERENCES item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
    
    
)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

