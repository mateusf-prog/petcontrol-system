INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('12212700', 'Rua BPL', 170, 'Jardim Telespark', 'São José dos Campos', 'SP');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('12236690', 'Rua JAO', 526, 'Morumbi', 'São José dos Campos', 'SP');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('12547800', 'Rua ADBC', 700, 'Centro', 'São José dos Campos', 'SP');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('12345678', 'Rua XYZ', 123, 'Bairro ABC', 'Cidade DEF', 'SP');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('23456789', 'Avenida GHI', 456, 'Bairro JKL', 'Cidade MNO', 'RJ');
INSERT INTO tb_address(cep, street, number, district, city, uf) VALUES ('34567890', 'Travessa PQR', 789, 'Bairro STU', 'Cidade VWX', 'MG');

INSERT INTO tb_users(name, email, phone, birth_date, password, cpf_cnpj, address_id) VALUES ('Mateus', 'mateus@email.com', '12991978003', '1997-12-15', 'passwordmateus', '11122233344', 1);
INSERT INTO tb_users(name, email, phone, birth_date, password, cpf_cnpj, address_id) VALUES ('João', 'joao@email.com', '11965985748', '2000-02-20', 'passwordjoao', '11122233344', 2);
INSERT INTO tb_users(name, email, phone, birth_date, password, cpf_cnpj, address_id) VALUES ('Maria', 'maria@email.com', '1284754165', '1985-05-29', 'passwordmaria', '11122233344', 3);

INSERT INTO tb_clients(name, email, phone, birth_date, cpf, address_id) VALUES ('Carlos', 'carlos@email.com', '11912345678', '1980-06-15', '12345678901', 4);
INSERT INTO tb_clients(name, email, phone, birth_date, cpf, address_id) VALUES ('Ana', 'ana@email.com', '11923456789', '1990-07-25', '23456789012', 5);
INSERT INTO tb_clients(name, email, phone, birth_date, cpf, address_id) VALUES ('Pedro', 'pedro@email.com', '11934567890', '2000-08-05', '34567890123', 6);