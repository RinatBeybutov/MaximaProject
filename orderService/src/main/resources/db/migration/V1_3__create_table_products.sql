create table if not exists order_service.products(
     id BIGSERIAL PRIMARY KEY,
     uuid UUID,
     createdAt TIMESTAMP,
     user_uuid UUID,
     status INT
);
