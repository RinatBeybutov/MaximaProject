
insert into order_service.categories(id,uuid,name) values(1,'fcc49792-9c0b-49f7-9fce-5d9d631d045f','Категория 1');

insert into order_service.categories(id,uuid,name) values(2,'fcc49792-9c0b-49f7-9fce-5d9d631d045f','Категория 2');

insert into order_service.categories(id,uuid,name) values(3,'fcc49792-9c0b-49f7-9fce-5d9d631d045f','Категория 3');

insert into order_service.categories(id,uuid,name) values(4,'fcc49792-9c0b-49f7-9fce-5d9d631d045f','Категория 4');

SELECT SETVAL('order_service.cat_id_seq', 4, TRUE);