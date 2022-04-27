-- use emarketdb;

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction`
(
	id int NOT NULL AUTO_INCREMENT,
    customerid int NOT NULL,
    customeraddressid int NOT NULL,
    station varchar(22) NOT NULL,
	`status` varchar(77) default "Pending",
    note varchar(500) default null,
    subtotal double NOT NULL,
    servicefee double DEFAULT 0,
    deliveryfee double DEFAULT 0,
    promotion int,
    grandtotal double NOT NULL,
    ordertype varchar(17),    
    riderid int,
    scheduledtime datetime,
	deliveredtime datetime,
    
	`creationdate` datetime default current_timestamp,
    `modifieddate` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
    FOREIGN KEY(customerid) REFERENCES customer(id),
	FOREIGN KEY(customeraddressid) REFERENCES customeraddress(id),
    FOREIGN KEY(promotion) REFERENCES promotion(id),
    FOREIGN KEY(riderid) REFERENCES rider(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION

)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

