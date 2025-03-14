create table if not exists order_service.products(
     id BIGSERIAL PRIMARY KEY,
     uuid UUID UNIQUE,
     name varchar,
     category_id INT
);

alter table order_service.products add constraint fk_products_category_id foreign key(category_id) references order_service.categories(id);
