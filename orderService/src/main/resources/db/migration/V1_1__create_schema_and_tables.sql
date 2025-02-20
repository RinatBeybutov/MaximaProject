CREATE SCHEMA IF NOT EXISTS order_service;

create table if not exists order_service.categories(
     id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
     uuid UUID,
     name varchar,
     CONSTRAINT pk_categories PRIMARY KEY (id)
);

COMMENT ON TABLE order_service.categories IS 'таблица категорий';

COMMENT ON COLUMN order_service.categories.id IS 'идентификатор записи в БД, первичный ключ';

COMMENT ON COLUMN order_service.categories.UUID IS 'UUID - идентификатор записи';

COMMENT ON COLUMN order_service.categories.name IS 'название категории';
