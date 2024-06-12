INSERT INTO "TYPE" (ID, NAME) VALUES
(1, 'Meat'),
(2, 'Vegetables'),
(3, 'Dairy Products');
INSERT INTO "BRAND" (ID, NAME) VALUES
(6, 'Phillips'),
(7, 'Avaya '),
(8, 'America Holdings');
INSERT INTO "PRODUCT" (ID, DESCRIPTION, STOCK, PRICE, DISCOUNT, TYPE_ID, BRAND_ID) VALUES
(9, 'SERENITO', 56, 10.5, 0.0, 3, 6),
(10, 'LECHUGA', 23, 17.8, 0.5, 2, 8),
(11, 'YOGURT', 11, 45.2, 0.7, 3, 6),
(12, 'VACIO', 23, 95.0, 0.6, 1, 7),
(13, 'TOMATE', 78, 43.6, 0.1, 2, 7),
(14, 'PALTA', 14, 24.7, 0.2, 2, 8),
(15, 'MANTECA', 38, 78.3, 0.8, 3, 7);