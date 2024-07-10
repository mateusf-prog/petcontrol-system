
INSERT INTO tb_users(name, email, phone, birth_date, password, cpf_cnpj, zip_code, street, number, district, city, uf) VALUES ('Mateus', 'mateus@email.com', '12991978003', '1997-12-15', 'Password1234', '19573556485', '12212700', 'Rua das Flores', '123', 'Centro', 'São José dos Campos', 'SP');
INSERT INTO tb_users(name, email, phone, birth_date, password, cpf_cnpj, zip_code, street, number, district, city, uf) VALUES ('João', 'joao@email.com', '11965985748', '2000-02-20', 'Password1234', '73545687956', '35748000', 'Rua das Palmeiras', '456', 'Centro', 'São Paulo', 'SP');
INSERT INTO tb_users(name, email, phone, birth_date, password, cpf_cnpj, zip_code, street, number, district, city, uf) VALUES ('Maria', 'maria@email.com', '1284754165', '1985-05-29', 'Password1234', '32198765452', '12345678', 'Rua das Avenidas', '789', 'Centro', 'São Bernardo do Campo', 'SP');

INSERT INTO tb_customers(name, email, phone, birth_date, cpf_cnpj, zip_code, street, number, district, city, uf) VALUES ('Carlos', 'carlos@email.com', '11912345678', '1980-06-15', '974646546413', '12212700', 'Rua das Flores', '123', 'Centro', 'São José dos Campos', 'SP');
INSERT INTO tb_customers(name, email, phone, birth_date, cpf_cnpj, zip_code, street, number, district, city, uf) VALUES ('Ana', 'ana@email.com', '11923456789', '1990-07-25', '64654649987', '35748000', 'Rua das Palmeiras', '456', 'Centro', 'Campinas', 'SP');
INSERT INTO tb_customers(name, email, phone, birth_date, cpf_cnpj, zip_code, street, number, district, city, uf) VALUES ('Pedro', 'pedro@email.com', '11934567890', '2000-08-05', '65432198745', '12345678', 'Rua das Avenidas', '789', 'Centro', 'Paraisópolis', 'SP');

INSERT INTO tb_products(name, category, supplier, price, stock, description) VALUES ('Coleira', 'ACCESSORY', 'empresa1', 30.00, 50, 'Coleira simples colorida');
INSERT INTO tb_products(name, category, supplier, price, stock, description) VALUES ('Vacina Antirábica', 'VACCINE', 'Labovet', 60.00, 40, 'vacina de raiva para pets');
INSERT INTO tb_products(name, category, supplier, price, stock, description) VALUES ('Vacina V8', 'VACCINE', 'VetSim 3', 100.00, 30, 'Vacina v8 para cães');
INSERT INTO tb_products(name, category, supplier, price, stock, description) VALUES ('Ração 5KG premium', 'FOOD', 'Anamar', 25.00, 100, 'ração 5kg da anamar');

INSERT INTO tb_pets(name, gender, type, weight, birth_date, description, customer_id) VALUES ('Thor', 'MALE', 'DOG', 35.0, '2015-06-19', 'Cachorro apresenta leves coceiras', 2);
INSERT INTO tb_pets(name, gender, type, weight, birth_date, description, customer_id) VALUES ('Maya', 'FEMALE', 'DOG', 5.0, '2022-11-10', 'Cachorro apresenta otite', 1);
INSERT INTO tb_pets(name, gender, type, weight, birth_date, description, customer_id) VALUES ('Mavie', 'FEMALE', 'DOG', 7.0, '2021-08-20', 'Nenhuma observação', 3);

INSERT INTO tb_treatments(application_date, pet_id, details, type) VALUES ('2024-07-24', 1, 'aplicação de vacina V8', 'VACCINE');
INSERT INTO tb_treatments(application_date, pet_id, details, type) VALUES ('2024-08-24', 3, 'aplicação de vermífugo', 'VERMIFUGE');
INSERT INTO tb_treatments(application_date, pet_id, details, type) VALUES ('2024-09-24', 2, 'aplicação de prevenção de pulgas', 'FLEAPREVENTION');
INSERT INTO tb_treatments(application_date, pet_id, details, type) VALUES ('2024-10-24', 1, 'aplicação de vacina antirrábica', 'VACCINE');

INSERT INTO tb_services (name, small_dog_price, medium_size_dog_price, big_dog_price) VALUES ('Tosa Higiênica', 50.00, 60.00, 70.00);
INSERT INTO tb_services (name, small_dog_price, medium_size_dog_price, big_dog_price) VALUES ('Tosa Tesoura', 80.00, 90.00, 100.00);
INSERT INTO tb_services (name, small_dog_price, medium_size_dog_price, big_dog_price) VALUES ('Tosa Máquina', 120.00, 150.00, 180.00);
INSERT INTO tb_services (name, small_dog_price, medium_size_dog_price, big_dog_price) VALUES ('Táxi Dog', 30.00, 40.00, 50.00);

INSERT INTO tb_agenda (date, customer_id) VALUES ('2024-06-20 10:00:00', 1);
INSERT INTO tb_agenda (date, customer_id) VALUES ('2024-06-21 14:00:00', 2);
INSERT INTO tb_agenda (date, customer_id) VALUES ('2024-06-22 16:00:00', 3);

INSERT INTO tb_agenda_service (appointment_id, service_id) VALUES (1, 1);
INSERT INTO tb_agenda_service (appointment_id, service_id) VALUES (1, 4);
INSERT INTO tb_agenda_service (appointment_id, service_id) VALUES (2, 3);
INSERT INTO tb_agenda_service (appointment_id, service_id) VALUES (2, 2);
INSERT INTO tb_agenda_service (appointment_id, service_id) VALUES (3, 1);
INSERT INTO tb_agenda_service (appointment_id, service_id) VALUES (3, 4);

INSERT INTO tb_orders(date, customer_id) VALUES ('2024-05-15', 1);
INSERT INTO tb_orders(date, customer_id) VALUES ('2024-06-20', 1);
INSERT INTO tb_orders(date, customer_id) VALUES ('2024-04-21', 2);
INSERT INTO tb_orders(date, customer_id) VALUES ('2024-10-25', 3);
INSERT INTO tb_orders(date, customer_id) VALUES ('2024-02-23', 2);

INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (30.00, 2, 1, 1);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (60.00, 1, 1, 2);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (100.00, 1, 2, 2);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (25.00, 1, 2, 3);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (30.00, 1, 4, 4);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (60.00, 1, 4, 3);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (100.00, 1, 4, 1);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (25.00, 1, 3, 2);
INSERT INTO tb_order_item(price, quantity, order_id, product_id) VALUES (30.00, 1, 3, 1);