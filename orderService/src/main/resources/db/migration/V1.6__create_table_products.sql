create table if not exists order_service.products(
     id BIGSERIAL PRIMARY KEY,
     uuid UUID UNIQUE,
     name varchar,
     category_id INT
);
