
insert into order_service.categories(id,uuid,name) values(1,'fcc49792-9c0b-49f7-9fce-5d9d631d045f','Напитки');

insert into order_service.categories(id,uuid,name) values(2,'fcc49792-9c0b-49f7-9fce-5d9d631d044f','Пицца');

insert into order_service.categories(id,uuid,name) values(3,'fcc49792-9c0b-49f7-9fce-5d9d631d043f','Комбо');

insert into order_service.categories(id,uuid,name) values(4,'fcc49792-9c0b-49f7-9fce-5d9d631d042f','Чипсы');

SELECT SETVAL('order_service.cat_id_seq', 4, TRUE);