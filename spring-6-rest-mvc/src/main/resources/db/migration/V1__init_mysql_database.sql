drop table if exists beer cascade;
drop table if exists customer cascade;

create table if not exists beer (
    id varchar(36) not null,
    beer_name varchar(50) not null,
    beer_style varchar(50) not null,
    created_date datetime(6),
    price decimal(38,2) not null,
    quantity_on_hand integer,
    upc varchar(255) not null,
    update_date datetime(6),
    version integer,
    primary key (id)
);

create table if not exists customer (
    id varchar(36) not null,
    created_date datetime(6),
    name varchar(255),
    update_date datetime(6),
    version integer,
    primary key (id)
);
