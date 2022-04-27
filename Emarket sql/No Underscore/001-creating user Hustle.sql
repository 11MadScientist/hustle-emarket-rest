DROP USER IF exists 'Hustle'@'localhost';
flush PRIVILEGES;

CREATE USER 'Hustle'@'localhost' IDENTIFIED BY 'teamhustle';
GRANT All Privileges ON *.* TO 'Hustle'@'localhost';

#
# # This is just for the localhost, we can change the password and @'localhost' to 
# # @'ip_address'
#