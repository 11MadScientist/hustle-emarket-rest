USE emarketdb;

DROP TABLE IF EXISTS customer_address;
CREATE TABLE customer_address
(
	id int NOT NULL AUTO_INCREMENT,
    address varchar(77) UNIQUE NOT NULL,
    customer_detail int,
    
    PRIMARY KEY(id),
    FOREIGN KEY (customer_detail) REFERENCES customer_detail(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)
