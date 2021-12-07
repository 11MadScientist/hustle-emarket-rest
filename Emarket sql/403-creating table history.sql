use emarketdb;

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history`
(
	id int NOT NULL AUTO_INCREMENT,
    customer_id int NOT NULL,
	`status` varchar(77) NOT NULL,
    sub_total double NOT NULL,
    service_fee double DEFAULT 0,
    delivery_fee double DEFAULT 0,
    payment_method varchar(77),
    
    primary key(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;