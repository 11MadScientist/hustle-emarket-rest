use emarketdb;

DROP TABLE IF EXISTS itemImage;

CREATE TABLE itemImage
(
	id int NOT NULL AUTO_INCREMENT,
    image varchar(111),
    itemId int,
    
    PRIMARY KEY(id),
    FOREIGN KEY(itemId) REFERENCES item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;
