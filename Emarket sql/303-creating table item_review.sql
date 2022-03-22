 use emarketdb;

DROP TABLE IF EXISTS item_review;

CREATE TABLE item_review
(
	id int NOT NULL AUTO_INCREMENT,
    rating double DEFAULT 0,
    review varchar(500),
    item_id int,
    customer_id int,
    
   `creation_date` datetime default current_timestamp,
    `modified_date` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
    FOREIGN KEY(item_id) REFERENCES item(id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;