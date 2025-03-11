create table if not exists order_service.orders(
    id bigserial primary key,
    uuid UUID UNIQUE,
    created_at TIMESTAMP,
    status integer,
    user_uuid UUID
);

comment on TABLE order_service.orders is 'таблица заказов';

comment on COLUMN order_service.orders.id is 'идентификатор записи в БД, первичный ключ';

comment on COLUMN order_service.orders.uuid is 'UUID - идентификатор записи';

comment on COLUMN order_service.orders.created_at is 'дата и время создания заказа';

comment on COLUMN order_service.orders.status is 'статус заказа';

comment on COLUMN order_service.orders.user_uuid is 'UUID пользователя';