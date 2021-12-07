use emarketdb;

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order`
(
	id int NOT NULL AUTO_INCREMENT,
    customer_id int NOT NULL,
	`status` varchar(77) NOT NULL,
    sub_total double NOT NULL,
    service_fee double DEFAULT 0,
    delivery_fee double DEFAULT 0,
    payment_method varchar(77),
    
    approved_time long,
	`creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
    
    
)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

