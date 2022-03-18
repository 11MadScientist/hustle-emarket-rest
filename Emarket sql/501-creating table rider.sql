-- use emarketdb;

DROP TABLE IF EXISTS rider;

CREATE TABLE rider
(
	id int NOT NULL AUTO_INCREMENT,
	first_name varchar(77) DEFAULT NULL,
    last_name varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
    `status` boolean DEFAULT false,
    rider_detail_id int,
    `role` varchar(11) DEFAULT "RIDER",
    
   `creation_date` datetime default current_timestamp,
    `modified_date` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
    FOREIGN KEY(rider_detail_id) REFERENCES rider_detail(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;