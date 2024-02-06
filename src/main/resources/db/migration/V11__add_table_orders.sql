create table if not exists orders
(
    id serial not null
        primary key,
    user_id int not null,
    address varchar not null
);

create table if not exists orders_products
(
    order_id int not null,
    product_id int not null
);