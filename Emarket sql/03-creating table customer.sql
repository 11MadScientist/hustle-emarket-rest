USE emarketdb;
DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
	id int NOT NULL AUTO_INCREMENT,
    first_name varchar(77) DEFAULT NULL,
    last_name varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
	customer_detail_id int,

    PRIMARY KEY(id),
    FOREIGN KEY (customer_detail_id) REFERENCES customer_detail(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;