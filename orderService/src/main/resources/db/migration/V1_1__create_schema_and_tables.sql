CREATE SCHEMA IF NOT EXISTS order_service AUTHORIZATION postgres;

create table if not exists order_service.categories(
     id int primary key,
     uuid UUID,
     name varchar
);

create sequence order_service.cat_id_seq;