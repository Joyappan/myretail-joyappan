use dev;


CREATE TABLE product_price(
    product_price_id int PRIMARY KEY,
    clearance_price double,
    currency text,
    price_type text,
    regular_price double,
    create_time timestamp,
    last_updated_time timestamp
);


INSERT INTO product_price(product_price_id, clearance_price, currency, price_type,regular_price,create_time,last_updated_time) VALUES (13860428, 255.78,’USD’,’RC,175.77, '2017-03-01 09:58:06.298', '2017-03-01 09:58:06.298');
INSERT INTO product_price(product_price_id, clearance_price, currency, price_type,regular_price,create_time,last_updated_time) VALUES (15117729, 66.66,'USD’,’R’,00.00, ‘2017-03-01 09:58:06.298’, ‘2017-03-01 09:58:06.298’);