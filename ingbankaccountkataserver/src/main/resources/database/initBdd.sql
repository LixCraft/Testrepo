
DROP TABLE customer;
DROP TABLE account;
DROP TABLE operation;

CREATE TABLE customer (
	customer_id INTEGER PRIMARY KEY NOT NULL,
	first_name VARCHAR(150) NOT NULL,
	last_name VARCHAR(150) NOT NULL,
	birth_date TIMESTAMP NOT NULL
);

CREATE TABLE account (
	account_id INTEGER PRIMARY KEY NOT NULL,
	customer_id INTEGER NOT NULL,
	account_number VARCHAR(50) NOT NULL,
	type_code VARCHAR(20) NOT NULL,
	type_label VARCHAR(50)
);

CREATE TABLE operation (
	operation_id INTEGER PRIMARY KEY NOT NULL,
	account_id INTEGER NOT NULL,
	category VARCHAR(50),
	description VARCHAR(200),
	amount NUMERIC,
	date TIMESTAMP NOT NULL
);


delete from customer;
delete from account;
delete from operation;
insert into customer values (1, 'Dupont', 'Alain', '1980-01-02'),
(2, 'Perra', 'Luigi', '1982-05-02'),
(3, 'Olivera', 'Alexandre', '1970-04-28'),
(4, 'Lamarte', 'Alexia', '1982-05-02'),
(5, 'Fons', 'Morgane', '1974-11-09');
insert into account values (1,1,  '40004586701', '1', 'Compte courant'),
(2,2,  '40003597226', '1', 'Compte courant'),
(3,2,  '40005198733', '1', 'Livret A'),
(4,2,  '40001959841', '1', 'Livret Développement Durable et Solidaire'),
(5,3,  '40009647224', '1', 'Compte courant'),
(6,3,  '40001295659', '1', 'Livret A'),
(7,4,  '40008612157', '1', 'Compte courant'),
(8,5,  '40009863152', '1', 'Compte courant'),
(9,5,  '40006204901', '1', 'Livret Développement Durable et Solidaire');
insert into operation values (1, 1,'Epargne', 'Depot', 100, '2020-03-19'),
(2, 2,'Epargne', 'Depot', 50, '2020-03-18'),
(3, 3,'Epargne', 'Depot', 30, '2020-03-17'),
(4, 4,'Epargne', 'Depot', 60, '2020-03-16'),
(5, 5,'Epargne', 'Depot', 150, '2020-03-15'),
(6, 6,'Epargne', 'Depot', 200, '2020-03-14'),
(7, 7,'Epargne', 'Depot', 70, '2020-03-13'),
(8, 8,'Epargne', 'Depot', 65, '2020-03-12'),
(9, 9,'Epargne', 'Depot', 45, '2020-03-11'),
(10, 1,'Loisir', 'Escape Game', -15, '2020-04-10'),
(11, 2,'Alimentation', 'Fusion Ramen', -10, '2020-04-11'),
(12, 5,'Voyage', 'Captain Train', -50, '2020-04-12'),
(13, 7,'Alimentation', 'Supermarket', -12.9, '2020-04-13'),
(14, 8,'Sport', 'Abonnement Club', -40, '2020-04-14'),
(15, 1,'Salaire', 'Depot', 1000, '2020-04-15');
