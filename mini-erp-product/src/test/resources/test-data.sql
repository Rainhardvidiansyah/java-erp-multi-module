--
CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    price DECIMAL(15, 2) NOT NULL,
    stocks INT NOT NULL,
    description TEXT,
    created_date TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_date TIMESTAMP NOT NULL
);

-- Masukkan data ke dalam tabel products
INSERT INTO products (product_name, price, stocks, description, created_date, updated_date) VALUES
('Product 1', 10.00, 100, 'Description for Product 1', NOW(), NOW()),
('Product 2', 20.00, 200, 'Description for Product 2', NOW(), NOW()),
('Product 3', 30.00, 300, 'Description for Product 3', NOW(), NOW()),
('Product 4', 40.00, 400, 'Description for Product 4', NOW(), NOW()),
('Product 5', 50.00, 500, 'Description for Product 5', NOW(), NOW()),
('Product 6', 60.00, 600, 'Description for Product 6', NOW(), NOW()),
('Product 7', 70.00, 700, 'Description for Product 7', NOW(), NOW()),
('Product 8', 80.00, 800, 'Description for Product 8', NOW(), NOW()),
('Product 9', 90.00, 900, 'Description for Product 9', NOW(), NOW()),
('Product 10', 100.00, 1000, 'Description for Product 10', NOW(), NOW()),
('Product 11', 110.00, 1100, 'Description for Product 11', NOW(), NOW()),
('Product 12', 120.00, 1200, 'Description for Product 12', NOW(), NOW()),
('Product 13', 130.00, 1300, 'Description for Product 13', NOW(), NOW()),
('Product 14', 140.00, 1400, 'Description for Product 14', NOW(), NOW()),
('Product 15', 150.00, 1500, 'Description for Product 15', NOW(), NOW()),
('Product 16', 160.00, 1600, 'Description for Product 16', NOW(), NOW()),
('Product 17', 170.00, 1700, 'Description for Product 17', NOW(), NOW()),
('Product 18', 180.00, 1800, 'Description for Product 18', NOW(), NOW()),
('Product 19', 190.00, 1900, 'Description for Product 19', NOW(), NOW()),
('Product 20', 200.00, 2000, 'Description for Product 20', NOW(), NOW()),
('Product 21', 210.00, 2100, 'Description for Product 21', NOW(), NOW()),
('Product 22', 220.00, 2200, 'Description for Product 22', NOW(), NOW()),
('Product 23', 230.00, 2300, 'Description for Product 23', NOW(), NOW()),
('Product 24', 240.00, 2400, 'Description for Product 24', NOW(), NOW()),
('Product 25', 250.00, 2500, 'Description for Product 25', NOW(), NOW()),
('Product 26', 260.00, 2600, 'Description for Product 26', NOW(), NOW()),
('Product 27', 270.00, 2700, 'Description for Product 27', NOW(), NOW()),
('Product 28', 280.00, 2800, 'Description for Product 28', NOW(), NOW()),
('Product 29', 290.00, 2900, 'Description for Product 29', NOW(), NOW()),
('Product 30', 300.00, 3000, 'Description for Product 30', NOW(), NOW());

-- Masukkan data ke dalam tabel categories
--INSERT INTO categories (category_name, description, created_date, updated_date) VALUES
--('Category 1', 'Description for Category 1', NOW(), NOW()),
--('Category 2', 'Description for Category 2', NOW(), NOW()),
--('Category 3', 'Description for Category 3', NOW(), NOW()),
--('Category 4', 'Description for Category 4', NOW(), NOW()),
--('Category 5', 'Description for Category 5', NOW(), NOW()),
--('Category 6', 'Description for Category 6', NOW(), NOW()),
--('Category 7', 'Description for Category 7', NOW(), NOW()),
--('Category 8', 'Description for Category 8', NOW(), NOW()),
--('Category 9', 'Description for Category 9', NOW(), NOW()),
--('Category 10', 'Description for Category 10', NOW(), NOW()),
--('Category 11', 'Description for Category 11', NOW(), NOW()),
--('Category 12', 'Description for Category 12', NOW(), NOW()),
--('Category 13', 'Description for Category 13', NOW(), NOW()),
--('Category 14', 'Description for Category 14', NOW(), NOW()),
--('Category 15', 'Description for Category 15', NOW(), NOW());
--
---- Masukkan data ke dalam tabel product_categories
--INSERT INTO product_categories (category_id, product_id) VALUES
--(1, 1),
--(2, 2),
--(3, 3),
--(4, 4),
--(5, 5),
--(6, 6),
--(7, 7),
--(8, 8),
--(9, 9),
--(10, 10),
--(11, 11),
--(12, 12),
--(13, 13),
--(14, 14),
--(15, 15);
