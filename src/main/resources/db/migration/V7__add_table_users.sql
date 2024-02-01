create table if not exists users
(
    id serial not null
        primary key,
    first_name varchar not null,
    last_name varchar not null,
    phone_number text not null,
    password text not null
);