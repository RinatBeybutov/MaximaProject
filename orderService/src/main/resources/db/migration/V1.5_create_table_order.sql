create table if not exists order_service.orders(
    id BIGSERIAL PRIMARY KEY,
    uuid UUID,
    date DATE,
    status INTEGER
    user_uuid UUID
);

COMMENT ON TABLE order_service.orders IS 'таблица заказов';

COMMENT ON COLUMN order_service.orders.id IS 'идентификатор записи в БД, первичный ключ';

COMMENT ON COLUMN order_service.orders.UUID IS 'UUID - идентификатор записи';

COMMENT ON COLUMN order_service.orders.date IS 'дата заказа';

COMMENT ON COLUMN order_service.orders.status IS 'статус заказа';

COMMENT ON COLUMN order_service.orders.user_uuid IS 'UUID пользователя';