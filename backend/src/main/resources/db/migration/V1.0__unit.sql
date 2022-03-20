drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;

-- to store password as hash
--CREATE EXTENSION chkpass;

drop table if exists users_roles;
drop table if exists roles;
drop table if exists users;

create table roles (
    id bigserial not null,
    uuid uuid default gen_random_uuid() not null,
    name varchar not null,
    primary key (id)
);
alter table if exists roles add constraint roles_unique_uuid unique (uuid);
alter table if exists roles add constraint roles_unique_name unique (name);

create table users (
    id bigserial not null,
    uuid uuid default gen_random_uuid() not null,
    name varchar not null,
    username varchar not null,
    password varchar not null,
    primary key (id)
);
alter table if exists users add constraint users_unique_uuid unique (uuid);
alter table if exists users add constraint users_unique_username unique (username);

create table users_roles (
    users_id int8 not null,
    roles_id int8 not null,
    primary key (users_id, roles_id)
);
alter table if exists users_roles add constraint users_id_fkey foreign key (users_id) references users;
alter table if exists users_roles add constraint roles_id_fkey foreign key (roles_id) references roles;


--add users roles etc
insert into users (name, username, password) values('Administrator','admin', 'admin');
insert into users (name, username, password) values('Nuno G.','nuno', '12345');

insert into roles (name) values('ROLE_ADMIN');
insert into roles (name) values('ROLE_USER');

insert into users_roles (users_id, roles_id) values(1,1);
insert into users_roles (users_id, roles_id) values(1,2);
insert into users_roles (users_id, roles_id) values(2,2);