use emarketdb;

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history`
(
	id int NOT NULL AUTO_INCREMENT,
    transactionId int NOT NULL,
    storeId int NOT NULL,	
    itemId int NOT NULL,
    itemName varchar(77) NOT NULL,
    price double NOT NULL,
    quantity double NOT NULL,
    total double NOT NULL,
    `status` varchar(77) default "Pending",
    
    `creationDate` long NOT NULL,
    `modifiedDate` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(transactionId) REFERENCES transaction(id),
    FOREIGN KEY(storeId) REFERENCES store(id),
    FOREIGN KEY(itemId) REFERENCES item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;