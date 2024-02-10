create table if not exists buckets_products
(
    bucket_id int not null,
    product_id int not null
);

create table if not exists buckets
(
    id serial not null
        primary key,
    user_id int not null
        unique
);