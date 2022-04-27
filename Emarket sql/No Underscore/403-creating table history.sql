-- use emarketdb;

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history`
(
	id int NOT NULL AUTO_INCREMENT,
    transactionid int NOT NULL,
    storeid int NOT NULL,	
    itemid int NOT NULL,
    itemname varchar(77) NOT NULL,
    price double NOT NULL,
    quantity double NOT NULL,
    `status` varchar(77) default "Pending",
    
    itemreviewid int,
    
    `creationdate` datetime default current_timestamp,
    `modifieddate` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
    FOREIGN KEY(transactionid) REFERENCES transaction(id),
    FOREIGN KEY(storeid) REFERENCES store(id),
    FOREIGN KEY(itemid) REFERENCES item(id),
    FOREIGN KEY(itemreviewid) REFERENCES itemreview(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;