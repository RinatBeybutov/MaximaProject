CREATE SCHEMA IF NOT EXISTS order_service AUTHORIZATION postgres;

create table if not exists order_service.categories(
     id int primary key,
     uuid UUID,
     name varchar
);

create sequence order_service.cat_id_seq;

COMMENT ON TABLE order_service.categories IS 'таблица категорий';

COMMENT ON COLUMN order_service.categories.id IS 'идентификатор записи в БД, первичный ключ';

COMMENT ON COLUMN order_service.categories.UUID IS 'UUID - идентификатор записи';

COMMENT ON COLUMN order_service.categories.name IS 'название категории';

COMMENT ON sequence order_service.cat_id_seq IS 'sequence для генерации значения поля id';