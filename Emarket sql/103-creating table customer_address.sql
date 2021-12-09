USE emarketdb;

DROP TABLE IF EXISTS customer_address;
CREATE TABLE customer_address
(
	id int NOT NULL AUTO_INCREMENT,
    `name` varchar(77) NOT NULL,
    city varchar(77) NOT NULL,
    province varchar(77) NOT NULL,
    zip_code int NOT NULL,
    direction varchar(77) NOT NULL,

    customer_id int NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
