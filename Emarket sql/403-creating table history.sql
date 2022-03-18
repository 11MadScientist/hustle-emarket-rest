-- use emarketdb;

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history`
(
	id int NOT NULL AUTO_INCREMENT,
    transaction_id int NOT NULL,
    store_id int NOT NULL,	
    item_id int NOT NULL,
    item_name varchar(77) NOT NULL,
    price double NOT NULL,
    quantity double NOT NULL,
    `status` varchar(77) default "Pending",
    
    item_review_id int,
    
    `creation_date` datetime default current_timestamp,
    `modified_date` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
    FOREIGN KEY(transaction_id) REFERENCES transaction(id),
    FOREIGN KEY(store_id) REFERENCES store(id),
    FOREIGN KEY(item_id) REFERENCES item(id),
    FOREIGN KEY(item_review_id) REFERENCES item_review(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;