use emarketdb;

DROP TABLE IF EXISTS user;

CREATE TABLE user 
AS (
SELECT customer.id, customer.first_name, customer.last_name, customer.username,
customer_detail.email, customer_detail.phone_number
FROM customer INNER JOIN  customer_detail on customer.customer_detail_id = customer_detail.id
)union
SELECT seller.id, seller.first_name, seller.last_name, seller.username,
seller_detail.email, seller_detail.phone_number
FROM seller INNER JOIN seller_detail on seller.seller_detail_id = seller_detail.id


