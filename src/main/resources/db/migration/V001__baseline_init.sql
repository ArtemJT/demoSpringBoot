create table if not exists public.managers
(
    id         serial
        primary key,
    name       varchar(255) not null,
    email      varchar(255) not null,
    is_deleted boolean default false
);

create table if not exists public.clients
(
    id         serial
        primary key,
    login      varchar(255) unique not null,
    name       varchar(255)        not null,
    email      varchar(255) unique not null,
    is_deleted boolean default false
);

create table if not exists public.requests
(
    id            serial
        primary key,
    creation_date timestamp,
    status        varchar,
    client_id     integer
        constraint fk_requests_clients references public.clients,
    manager_id    integer
        constraint fk_requests_managers references public.managers
);



