create table if not exists order_service.product_to_order(
     id BIGSERIAL PRIMARY KEY,
     product_id BIGINT,
     count BIGINT,
     order_id BIGINT
);
