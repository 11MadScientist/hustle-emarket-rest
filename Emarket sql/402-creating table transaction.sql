-- use emarketdb;

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction`
(
	id int NOT NULL AUTO_INCREMENT,
    customer_id int NOT NULL,
    customer_address_id int NOT NULL,
	`status` varchar(77) default "Preparing",
    note varchar(500) default null,
    sub_total double NOT NULL,
    service_fee double DEFAULT 0,
    delivery_fee double DEFAULT 0,
    grand_total double NOT NULL,
    order_type varchar(17),    
    rider_id int,
    scheduled_time datetime,
	delivered_time datetime,
    
	`creation_date` datetime default current_timestamp,
    `modified_date` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id),
	FOREIGN KEY(customer_address_id) REFERENCES customer_address(id),
    FOREIGN KEY(rider_id) REFERENCES rider(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION

)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

