# Импорт настроек стиля

Settings -> Editor -> Code style -> Java -> рядом со схемой шестеренка
-> Import

# Наименование веток

Все ветки для разработки конкретной задачи должны называться определенным образом:
task/{НОМЕР_ЗАДАЧИ}-{КРАТКОЕ-НАЗВАНИЕ-ЗАДАЧИ}
Пример:
task/1-add-user-service

# Комментарий к коммиту

Комментарии к коммиту должны быть осмысленные и иметь следующую структуру:
#1 Добавлен сервис пользователей
ИЛИ
#2 Добавлены тесты для сущности UserEntity

# Подключение к бд

Дописать после разработки сущностей и подключения к бд
СУБД - postgresql
БД - shop. Данная база будет использоваться для хранения данных нескольких микросервисов
Данные микросервиса пользователей хранятся в схеме:
user_service,
В таблице:
users

# Процесс создания реквеста

1. Выбрать ветку develop
2. Обновить develop до актуального состояния
3. Создать новую ветку для текущей задачи с учетом правил наименования веток
4. Написать код
5. Запушить изменения со смысловым комментарием к коммиту
6. Создать pull request

# Порты микросервисов

Gateway - 8081
UserService - 8082
OrderService - 8083

