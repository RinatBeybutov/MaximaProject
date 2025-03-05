create table if not exists order_service.orders(
     id BIGSERIAL PRIMARY KEY,
     uuid UUID UNIQUE,
     created_at TIMESTAMP,
     user_uuid UUID,
     status INT
);
