USE emarketdb;
DROP TABLE IF EXISTS customerDetail;
CREATE TABLE customerDetail
(
	id int NOT NULL AUTO_INCREMENT,
    email varchar(77) UNIQUE DEFAULT NULL,
    phoneNumber varchar(22) DEFAULT NULL,
    `status` boolean DEFAULT false,
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;