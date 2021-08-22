
--  PRODUCTS
INSERT INTO product(product_id, product_name, product_price, product_icon, product_description) VALUES(1, 'FXG SHIRT', 200, 'https://personal-images-store.s3.us-east-2.amazonaws.com/fxg-shirt.jpg', 'FXG Main Jersey gaming shirt');
INSERT INTO product(product_id, product_name, product_price, product_icon, product_description) VALUES(2, 'FXG Akashi', 200, 'https://personal-images-store.s3.us-east-2.amazonaws.com/akashi-shirt.jpg', 'FXG Akashi Jersey gaming shirt');
INSERT INTO product(product_id, product_name, product_price, product_icon, product_description) VALUES(3, 'FXG Akazan', 200, 'https://personal-images-store.s3.us-east-2.amazonaws.com/akazan-shirt.jpg', 'FXG Akazan Jersey gaming shirt');

-- ROLES
INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');