create table if not exists products
(
    id serial not null
        primary key,
    name varchar not null,
    price int not null,
    description varchar not null
);
