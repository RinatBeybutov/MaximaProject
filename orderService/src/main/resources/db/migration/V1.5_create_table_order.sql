create table if not exists order_service.orders(
    id bigserial primary key,
    uuid UUID UNIQUE,
    date TIMESTAMP,
    status integer,
    user_uuid UUID
    );

comment on TABLE order_service.orders is 'таблица заказов';

comment on COLUMN order_service.orders.id is 'идентификатор записи в БД, первичный ключ';

comment on COLUMN order_service.orders.UUID is 'UUID - идентификатор записи';

comment on COLUMN order_service.orders.date is 'дата и время создания заказа';

comment on COLUMN order_service.orders.status is 'статус заказа';

comment on COLUMN order_service.orders.user_uuid is 'UUID пользователя';