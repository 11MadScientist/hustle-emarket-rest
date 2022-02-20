use emarketdb;

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction`
(
	id int NOT NULL AUTO_INCREMENT,
    customerId int NOT NULL,
    customerUsername varchar(77) NOT NULL,
    customerAddressId int NOT NULL,
	`status` varchar(77) default "Preparing",
    subTotal double NOT NULL,
    serviceFee double DEFAULT 0,
    deliveryFee double DEFAULT 0,
    paymentMethod varchar(77),
    grandTotal double NOT NULL,
    
    preferredTimeDelivered long,
    approvedTime long,
	deliveredTime long,
	`creationDate` long NOT NULL,
    `modifiedDate` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(customerId) REFERENCES customer(id),
	FOREIGN KEY(customerAddressId) REFERENCES customeraddress(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION

)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

