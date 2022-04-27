-- USE emarketdb;

DROP TABLE IF EXISTS promotion;

CREATE TABLE promotion(
	id int NOT NULL AUTO_INCREMENT,
    minimumspend double,
    discount double,
    `description` varchar(500),
    disabled boolean default false,
    

    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
