-- use emarketdb;

DROP TABLE IF EXISTS item_image;

CREATE TABLE item_image
(
	id int NOT NULL AUTO_INCREMENT,
    image varchar(111),
    item_id int,
    
    PRIMARY KEY(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;
