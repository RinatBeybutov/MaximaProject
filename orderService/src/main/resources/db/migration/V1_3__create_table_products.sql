create table if not exists order_service.order(
     id BIGSERIAL PRIMARY KEY,
     uuid UUID,
     createdAt TIMESTAMP,
     user_uuid UUID,
     status INT
);
