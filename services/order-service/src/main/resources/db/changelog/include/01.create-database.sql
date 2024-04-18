create table app_order (
    created_at time(6) not null,
    finished_at time(6),
    is_pickup boolean not null,
    status smallint not null check (status between 0 and 5),
    id bigserial not null,
    store_branch_id bigint not null,
    user_id bigint not null,
    address varchar(255) not null,
    contact_phone varchar(255) not null,
    primary key (id)
);

create table app_order_item (
    cost_per_item numeric(38,2) not null,
    count bigint not null,
    id bigserial not null,
    order_id bigint not null,
    product_id bigint not null,
    primary key (id)
);

create table app_product (
    id bigserial not null,
    title varchar(255) not null,
    url varchar(255) not null,
    primary key (id)
);

create table app_store_branch (
    id bigserial not null,
    store_title varchar(255) not null,
    url_branch varchar(255) not null,
    url_store varchar(255) not null,
    primary key (id)
);

create table app_user (
    id bigserial not null,
    url varchar(255) not null,
    primary key (id)
);

alter table if exists app_order 
   add constraint FKm4875gb1qlkueld2wg4dw7ary 
   foreign key (store_branch_id) 
   references app_store_branch;

alter table if exists app_order 
   add constraint FKesrwi8i75102xo4k9ykicpp7v 
   foreign key (user_id) 
   references app_user;

alter table if exists app_order_item 
   add constraint FK7i6akh4pavus34ajv9natir4l 
   foreign key (order_id) 
   references app_order;

alter table if exists app_order_item 
   add constraint FK22qsm7glcufrru5f94yrebofi 
   foreign key (product_id) 
   references app_product;