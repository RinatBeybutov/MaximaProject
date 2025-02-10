create table public.category(
     id int primary key,
     uuid UUID,
     name varchar
);

create sequence public.cat_id_seq;