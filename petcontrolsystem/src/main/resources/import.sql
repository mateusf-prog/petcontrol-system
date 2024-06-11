INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('12212700', 'Rua BPL', 170, 'Jardim Telespark', 'São José dos Campos', 'SP');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('12236690', 'Rua JAO', 526, 'Morumbi', 'São José dos Campos', 'SP');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('12547800', 'Rua ADBC', 700, 'Centro', 'São José dos Campos', 'SP');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('12345678', 'Rua XYZ', 123, 'Bairro ABC', 'Cidade DEF', 'SP');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('23456789', 'Avenida GHI', 456, 'Bairro JKL', 'Cidade MNO', 'RJ');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('34567890', 'Travessa PQR', 789, 'Bairro STU', 'Cidade VWX', 'MG');

INSERT INTO tb_users(name, email, phone, birth_date, password, cpf_cnpj, role, address_id) VALUES ('Mateus', 'mateus@email.com', '12991978003', '1997-12-15', 'passwordmateus', '11122233344', 'ADMIN', 1);
INSERT INTO tb_users(name, email, phone, birth_date, password, cpf_cnpj, role, address_id) VALUES ('João', 'joao@email.com', '11965985748', '2000-02-20', 'passwordjoao', '11122233344', 'COMMON', 2);
INSERT INTO tb_users(name, email, phone, birth_date, password, cpf_cnpj, role, address_id) VALUES ('Maria', 'maria@email.com', '1284754165', '1985-05-29', 'passwordmaria', '11122233344', 'ADMIN',3);

INSERT INTO tb_clients(name, email, phone, birth_date, cpf, address_id) VALUES ('Carlos', 'carlos@email.com', '11912345678', '1980-06-15', '12345678901', 4);
INSERT INTO tb_clients(name, email, phone, birth_date, cpf, address_id) VALUES ('Ana', 'ana@email.com', '11923456789', '1990-07-25', '23456789012', 5);
INSERT INTO tb_clients(name, email, phone, birth_date, cpf, address_id) VALUES ('Pedro', 'pedro@email.com', '11934567890', '2000-08-05', '34567890123', 6);

INSERT INTO tb_products(name, category, supplier, price, stock, description) VALUES ('Coleira', 'ACCESSORY', 'empresa1', 30.00, 50, 'Coleira simples colorida');
INSERT INTO tb_products(name, category, supplier, price, stock, description) VALUES ('Vacina Antirábica', 'VACCINE', 'Labovet', 60.00, 40, 'vacina de raiva para pets');
INSERT INTO tb_products(name, category, supplier, price, stock, description) VALUES ('Vacina V8', 'VACCINE', 'VetSim 3', 100.00, 30, 'Vacina v8 para cães');
INSERT INTO tb_products(name, category, supplier, price, stock, description) VALUES ('Ração 5KG premium', 'FOOD', 'Anamar', 25.00, 100, 'ração 5kg da anamar');

INSERT INTO tb_pets(name, gender, type, weight, birth_date, description, client_id) VALUES ('Thor', 'MALE', 'DOG', 35.0, '2015-06-19', 'Cachorro apresenta leves coceiras', 2);
INSERT INTO tb_pets(name, gender, type, weight, birth_date, description, client_id) VALUES ('Maya', 'FEMALE', 'DOG', 5.0, '2022-11-10', 'Cachorro apresenta otite', 1);
INSERT INTO tb_pets(name, gender, type, weight, birth_date, description, client_id) VALUES ('Mavie', 'FEMALE', 'DOG', 7.0, '2021-08-20', 'Nenhuma observação', 3);

INSERT INTO tb_health_care_log(application_date, pet_id, details, type) VALUES ('2024-07-24', 1, 'aplicação de vacina V8', 'VACCINE');
INSERT INTO tb_health_care_log(application_date, pet_id, details, type) VALUES ('2024-08-24', 3, 'aplicação de vermífugo', 'VERMIFUGE');
INSERT INTO tb_health_care_log(application_date, pet_id, details, type) VALUES ('2024-09-24', 2, 'aplicação de prevenção de pulgas', 'FLEAPREVENTION');
INSERT INTO tb_health_care_log(application_date, pet_id, details, type) VALUES ('2024-10-24', 1, 'aplicação de vacina antirrábica', 'VACCINE');

INSERT INTO tb_orders(date, client_id) VALUES ('2024-05-15', 1);
INSERT INTO tb_orders(date, client_id) VALUES ('2024-06-20', 1);
INSERT INTO tb_orders(date, client_id) VALUES ('2024-04-21', 2);
INSERT INTO tb_orders(date, client_id) VALUES ('2024-10-25', 3);
INSERT INTO tb_orders(date, client_id) VALUES ('2024-02-23', 2);

INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (30.00, 2, 1, 1);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (60.00, 1, 1, 2);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (100.00, 1, 2, 2);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (25.00, 1, 2, 3);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (30.00, 1, 4, 4);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (60.00, 1, 4, 3);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (100.00, 1, 4, 1);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (25.00, 1, 3, 2);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (30.00, 1, 3, 1);