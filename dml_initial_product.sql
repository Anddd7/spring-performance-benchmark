CREATE TABLE PRODUCTS
(
    id    SERIAL NOT NULL
        CONSTRAINT PRODUCTS_PK PRIMARY KEY,
    name  VARCHAR,
    price NUMERIC(19, 2)
);

insert into products (name, price) values
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),
('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20),('product-1', 1999.20);
