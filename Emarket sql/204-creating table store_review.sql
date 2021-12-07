use emarketdb;

DROP TABLE IF EXISTS store_review;

CREATE TABLE store_review
(
	id int NOT NULL AUTO_INCREMENT,
    word_tag varchar(111) DEFAULT NULL,
    rating double NOT NULL,
    review varchar(111) DEFAULT NULL,
    store_id int,
    
	`creation_date` long NOT NULL,
    `modified_date` long NOT NULL,
    
    primary key(id),
    FOREIGN KEY(store_id) REFERENCES store(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET Latin1;