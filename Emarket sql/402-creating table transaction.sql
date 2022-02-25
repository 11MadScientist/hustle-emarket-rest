-- use emarketdb;

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction`
(
	id int NOT NULL AUTO_INCREMENT,
    customer_id int NOT NULL,
    customer_username varchar(77) NOT NULL,
    customer_address_id int NOT NULL,
	`status` varchar(77) default "Preparing",
    sub_total double NOT NULL,
    service_fee double DEFAULT 0,
    delivery_fee double DEFAULT 0,
    payment_method varchar(77),
    grand_total double NOT NULL,
    
    preferred_time_delivered long,
    approved_time long,
	delivered_time long,
	`creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id),
	FOREIGN KEY(customer_address_id) REFERENCES customer_address(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION

)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

