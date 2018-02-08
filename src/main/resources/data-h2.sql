INSERT INTO CATEGORY (ID, NAME) VALUES (1, 'Informática');
INSERT INTO CATEGORY (ID, NAME) VALUES (2, 'Escritório');
INSERT INTO CATEGORY (ID, NAME) VALUES (3, 'Cama mesa e banho');
INSERT INTO CATEGORY (ID, NAME) VALUES (4, 'Eletrônicos');
INSERT INTO CATEGORY (ID, NAME) VALUES (5, 'Jardinagem');
INSERT INTO CATEGORY (ID, NAME) VALUES (6, 'Decoração');
INSERT INTO CATEGORY (ID, NAME) VALUES (7, 'Perfumaria');

INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (1, 'Computador', 2000);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (2, 'Impressora', 800);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (3, 'Mouse', 80);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (4, 'Mesa de escritório', 300);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (5, 'Toalha', 50);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (6, 'Colcha', 200);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (7, 'TV', 1200);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (8, 'Roçadeira', 750);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (9, 'Abajour', 100);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (10, 'Pendente', 180);
INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES (11, 'Shampoo', 20);

INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (1, 1);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (2, 1);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (3, 1);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (2, 2);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (4, 2);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (5, 3);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (6, 3);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (1, 4);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (2, 4);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (3, 4);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (7, 4);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (8, 5);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (9, 6);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (10, 6);
INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID) VALUES (11, 7);

INSERT INTO STATE (ID, NAME) VALUES (1, 'Minas Gerais');
INSERT INTO STATE (ID, NAME) VALUES (2, 'São Paulo');

INSERT INTO CITY (ID, NAME, STATE_ID) VALUES (1, 'Uberlândia', 1);
INSERT INTO CITY (ID, NAME, STATE_ID) VALUES (2, 'Campinas', 2);

INSERT INTO CLIENT (ID, NAME, EMAIL, CPF_CNPJ, TYPE, PASSWORD) VALUES (1, 'Maria Silva', 'maria@gmail.com', '03027289535', 1, '$2a$10$fZKLtakUGbexSs3xdFoVzevBMD91.Oj/Bb9wHzQZnNDX0IgN89.x6');
INSERT INTO CLIENT (ID, NAME, EMAIL, CPF_CNPJ, TYPE, PASSWORD) VALUES (2, 'João Silva', 'joao@gmail.com', '15127257491', 1, '$2a$10$fZKLtakUGbexSs3xdFoVzevBMD91.Oj/Bb9wHzQZnNDX0IgN89.x6');

INSERT INTO PHONENUMBER (CLIENT_ID, PHONENUMBERS) VALUES (1, '33338888');
INSERT INTO PHONENUMBER (CLIENT_ID, PHONENUMBERS) VALUES (1, '99997777');
INSERT INTO PHONENUMBER (CLIENT_ID, PHONENUMBERS) VALUES (2, '55554444');

INSERT INTO PROFILES (CLIENT_ID, PROFILES) VALUES (1, 2);
INSERT INTO PROFILES (CLIENT_ID, PROFILES) VALUES (2, 1);
INSERT INTO PROFILES (CLIENT_ID, PROFILES) VALUES (2, 2);

INSERT INTO ADDRESS (ID, NEIGHBORHOOD, NUMBER, STREET, ZIPCODE, CITY_ID, CLIENT_ID) VALUES (1, 'Bairro 1', '123', 'Street 20', '12345678', 1, 1);
INSERT INTO ADDRESS (ID, NEIGHBORHOOD, NUMBER, STREET, ZIPCODE, CITY_ID, CLIENT_ID) VALUES (2, 'Bairro 2', '456', 'Street 79', '87654321', 2, 1);
INSERT INTO ADDRESS (ID, NEIGHBORHOOD, NUMBER, STREET, ZIPCODE, CITY_ID, CLIENT_ID) VALUES (3, 'Bairro 3', '789', 'Street 80', '95842654', 2, 2);

INSERT INTO PURCHASE (ID, INSTANT, CLIENT_ID, DELIVERY_ADDRESS_ID) VALUES (1, {ts '2018-01-17 12:40:15.20'}, 1, 1);
INSERT INTO PURCHASE (ID, INSTANT, CLIENT_ID, DELIVERY_ADDRESS_ID) VALUES (2, {ts '2018-02-03 20:10:52.69'}, 1, 2);

INSERT INTO PAYMENT (PURCHASE_ID, STATE) VALUES (1, 2);
INSERT INTO PAYMENT (PURCHASE_ID, STATE) VALUES (2, 1);

INSERT INTO PAYMENT_WITH_CARD (PURCHASE_ID, INSTALLMENTS) VALUES (1, 6);

INSERT INTO PAYMENT_WITH_BOLETO (PURCHASE_ID, DUE_DATE, PAYMENT_DATE) VALUES (2, {ts '2018-03-18 20:10:52.69'}, null);

INSERT INTO PURCHASE_ITEM (PURCHASE_ID, PRODUCT_ID, AMOUNT, DISCOUNT, PRICE) VALUES (1, 1, 1, 0, 2000);
INSERT INTO PURCHASE_ITEM (PURCHASE_ID, PRODUCT_ID, AMOUNT, DISCOUNT, PRICE) VALUES (1, 3, 2, 0, 80);
INSERT INTO PURCHASE_ITEM (PURCHASE_ID, PRODUCT_ID, AMOUNT, DISCOUNT, PRICE) VALUES (2, 2, 1, 100, 800);