CREATE TABLE IF NOT EXISTS order_service.products (
   id BIGSERIAL PRIMARY KEY,
   uuid UUID,
   name TEXT,
   category_id INTEGER,
   CONSTRAINT pk_products PRIMARY KEY (id)
);

COMMENT ON TABLE order_service.products IS 'Таблица продуктов';
COMMENT ON COLUMN order_service.products IS 'Идентификатор продукта';
COMMENT ON COLUMN order_service.products IS 'Глобальный идентификатор продукта';
COMMENT ON COLUMN order_service.products IS 'Название продукта';
COMMENT ON COLUMN order_service.products IS 'Глобальный идентификатор категории, к которой относится продукт';