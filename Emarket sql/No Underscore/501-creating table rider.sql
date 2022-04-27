-- use emarketdb;

DROP TABLE IF EXISTS rider;

CREATE TABLE rider
(
	id int NOT NULL AUTO_INCREMENT,
	firstname varchar(77) DEFAULT NULL,
    lastname varchar(77) NOT NULL,
    username varchar(77) UNIQUE NOT NULL,
    `password` varchar(77) NOT NULL,
    `status` varchar(22) DEFAULT "Offline",
    riderdetailid int,
    `role` varchar(11) DEFAULT "RIDER",
    
   `creationdate` datetime default current_timestamp,
    `modifieddate` datetime default current_timestamp
					on update current_timestamp,
    
    primary key(id),
    FOREIGN KEY(riderdetailid) REFERENCES riderdetail(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=Latin1;