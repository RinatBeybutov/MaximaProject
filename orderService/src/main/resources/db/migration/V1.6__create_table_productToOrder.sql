create table if not exists order_service.product_to_order(
     id BIGSERIAL PRIMARY KEY,
     product_id BIGINT,
     count BIGINT,
     order_id BIGINT
);

alter table order_service.product_to_order add foreign key (product_id) references order_service.products(id);
alter table order_service.product_to_order add foreign key (order_id) references order_service.orders(id);
