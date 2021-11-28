use emarketdb;

DROP TABLE IF EXISTS seller_notification;

CREATE TABLE seller_notification
(
	id int NOT NULL AUTO_INCREMENT,
    notification varchar(77) NOT NULL,
    `timestamp` long,
    seller_id int,
    
	`creation_date` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(seller_id) REFERENCES seller(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;