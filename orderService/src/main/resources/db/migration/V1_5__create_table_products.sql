create table if not exists order_service.products(
     id BIGSERIAL PRIMARY KEY,
     uuid UUID UNIQUE,
     category_id INT
);
