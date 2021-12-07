use emarketdb;

DROP TABLE IF EXISTS item_review;

CREATE TABLE item_review
(
	id int NOT NULL AUTO_INCREMENT,
    word_tag varchar(111),
    rating double DEFAULT 0,
    review varchar(111),
    item_id int,
    
    `creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;