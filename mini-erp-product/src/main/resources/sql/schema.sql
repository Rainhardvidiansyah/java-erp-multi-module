CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    price DECIMAL(15, 2) NOT NULL,
    stocks INT NOT NULL,
    description TEXT,
    created_date TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS categories(
id BIGSERIAL PRIMARY KEY,
category_name varchar(255) NOT NULL,
description TEXT,
created_date TIMESTAMP NOT NULL DEFAULT NOW(),
updated_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS product_categories(
category_id BIGSERIAL REFERENCES categories(id),
product_id BIGSERIAL REFERENCES products(id),
CONSTRAINT category_product_pk PRIMARY KEY (category_id, product_id)
);


ALTER TABLE products
ALTER COLUMN price TYPE numeric(15,2) USING price::numeric(15,2);













