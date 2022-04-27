-- USE emarketdb;
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
	id int NOT NULL AUTO_INCREMENT,
    firstname varchar(77) DEFAULT NULL,
    lastname varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
    `role` varchar(11) DEFAULT "ADMIN",
	
    
    `creationdate` datetime default current_timestamp,
    `modifieddate` datetime default current_timestamp
					on update current_timestamp,

    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;