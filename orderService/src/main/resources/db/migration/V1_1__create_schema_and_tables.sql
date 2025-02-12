CREATE SCHEMA IF NOT EXISTS order_service AUTHORIZATION postgres;

create table if not exists order_service.categories( -- таблица категорий
     id int primary key,    -- идентификатор записи в БД, первичный ключ
     uuid UUID,             -- UUID - идентификатор записи
     name varchar           -- название категории
);

create sequence order_service.cat_id_seq; -- sequence для генерации значения поля id