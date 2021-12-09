use emarketdb;

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
    total double NOT NULL,
    `status` varchar(77) default "Pending",
    
    `creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(transaction_id) REFERENCES transaction(id),
    FOREIGN KEY(store_id) REFERENCES store(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;