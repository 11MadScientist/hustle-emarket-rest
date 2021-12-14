use emarketdb;

DROP TABLE IF EXISTS customer_detail;
CREATE TABLE customer_detail
(
	id int NOT NULL AUTO_INCREMENT,
    email varchar(77) UNIQUE DEFAULT NULL,
    phone_number varchar(22) DEFAULT NULL,
    `status` boolean DEFAULT false,
    dp_link varchar(255) DEFAULT NULL,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
	id int NOT NULL AUTO_INCREMENT,
    first_name varchar(77) DEFAULT NULL,
    last_name varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
	customer_detail_id int,
    
    `creation_date` long NOT NULL,
    `modified_date` long NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY (customer_detail_id) REFERENCES customer_detail(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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

DROP TABLE IF EXISTS seller_detail;

CREATE TABLE seller_detail
(
	id int NOT NULL AUTO_INCREMENT,
    email varchar(77) UNIQUE DEFAULT NULL,
    phone_number varchar(12) DEFAULT NULL,
    `status` boolean DEFAULT false,
    authorized boolean DEFAULT false,
     
    dp_link varchar(255) DEFAULT NULL,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS seller;

CREATE TABLE seller
(
	id int NOT NULL AUTO_INCREMENT,
    first_name varchar(77) DEFAULT NULL,
    last_name varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
    seller_detail_id int UNIQUE,
    
	`creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
	PRIMARY KEY(id),
    FOREIGN KEY (seller_detail_id) REFERENCES seller_detail(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS store;

CREATE TABLE store
(
	id int NOT NULL AUTO_INCREMENT,
    store_name varchar(77) NOT NULL,
    store_address varchar(77) NOT NULL,
    overall_rating double DEFAULT NULL,
    documents varchar(255) DEFAULT NULL,
    items_added int DEFAULT 0,
    
    seller_id int UNIQUE,
    
    `creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (seller_id) REFERENCES seller(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS item;

CREATE TABLE item
(
	id int NOT NULL AUTO_INCREMENT,
    category int  DEFAULT NULL,
    `name` varchar(77) NOT NULL,
    in_stock double DEFAULT 0,
    stock_sold double DEFAULT 0,
    price double NOT NULL,
    measurement varchar(11) NOT NULL,
    `increment` double NOT NULL,
    overall_review double DEFAULT 0,
    `description` varchar(111) DEFAULT NULL,
    ingredient varchar(111) DEFAULT NULL,
    direction varchar(111) DEFAULT NULL,
    delisted boolean DEFAULT false,
    store_id int,
    
    `creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(store_id) REFERENCES store(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;

DROP TABLE IF EXISTS item_image;

CREATE TABLE item_image
(
	id int NOT NULL AUTO_INCREMENT,
    image varchar(111),
    item_id int,
    
    PRIMARY KEY(ID),
    FOREIGN KEY(item_id) REFERENCES item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;

DROP TABLE IF EXISTS basket;

CREATE TABLE basket
(
	id int NOT NULL AUTO_INCREMENT,
    item_id int NOT NULL,
    quantity double NOT NULL,
    customer_id int NOT NULL,
    
	`creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
	UNIQUE pair (customer_id, item_id),
    FOREIGN KEY(item_id) REFERENCES item(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
    
    
)ENGINE InnoDB auto_increment=1 default CHARSET= latin1;

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

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history`
(
	id int NOT NULL AUTO_INCREMENT,
    transaction_id int NOT NULL,
    store_id int NOT NULL,	
    item_id int NOT NULL,
    item_name varchar(77) NOT NULL,
    price double NOT NULL,
    quantity double NOT NULL,
    total double NOT NULL,
    `status` varchar(77) default "Pending",
    
    `creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(transaction_id) REFERENCES transaction(id),
    FOREIGN KEY(store_id) REFERENCES store(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;