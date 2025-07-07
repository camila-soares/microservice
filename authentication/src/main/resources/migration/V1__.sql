-- auto-generated definition
create table users
(
    id         bigserial
        primary key,
    email      varchar(255)
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    password   varchar(255),
    user_role  varchar(255)
        constraint users_user_role_check
            check ((user_role)::text = ANY
                ((ARRAY ['ADMIN'::character varying, 'GERENTE'::character varying, 'CLIENTE'::character varying])::text[]))
);

alter table users
    owner to postgres;

-- auto-generated definition
create table log_audit_users
(
    id             integer   default nextval('log_audit_users_id_seq'::regclass) not null
        constraint log_update_users_pkey
            primary key,
    user_id        integer                                                        not null,
    first_name_old text,
    first_name_new text,
    last_name_old  text,
    last_name_new  text,
    email_old      text,
    email_new      text,
    updateat       timestamp default CURRENT_TIMESTAMP,
    updatedby      text      default SESSION_USER,
    user_role_old  text,
    user_role_new  text
);

alter table log_audit_users
    owner to postgres;


create view vw_log_audit_users
            (id, user_id, first_name_old, first_name_new, last_name_old, last_name_new, email_old, email_new,
             user_role_old, user_role_new, updateat, updatedby)
as
SELECT l.id,
       l.user_id,
       l.first_name_old,
       l.first_name_new,
       l.last_name_old,
       l.last_name_new,
       l.email_old,
       l.email_new,
       l.user_role_old,
       l.user_role_new,
       l.updateat,
       l.updatedby
FROM log_audit_users l
         JOIN users u ON u.id = l.user_id;

alter table vw_log_audit_users
    owner to postgres;



--functions

create or replace function fn_audit_delete_user() returns trigger
    language plpgsql
as
$$
begin
    insert into log_audit_users(user_id, first_name_old, last_name_old
                                ,email_old, updateat, updatedby) values (old.id,
                                                                         old.first_name,
                                                                         old.last_name,
                                                                         old.email,
                                                                         CURRENT_TIMESTAMP,
                                                                         email_old); return old;
end;
$$;

alter function fn_audit_delete_user() owner to postgres;



create function fn_audit_update_users() returns trigger
    language plpgsql
as
$$
BEGIN
    --Registrar apenas se houver mudança real
    if old.first_name is distinct from new.first_name
        OR old.last_name is distinct from new.last_name
        OR old.email is distinct from new.email
        OR old.user_role is distinct from new.user_role THEN INSERT INTO
                                                                 log_audit_users
                                                             (user_id, first_name_old, first_name_new, last_name_old,
                                                              last_name_new, email_old, email_new, user_role_old, user_role_new, updateat, updatedby) VALUES
                                                                 (old.id, old.first_name, new.first_name,
                                                                  old.last_name, new.last_name,
                                                                  old.email, new.email, old.user_role, new.user_role,
                                                                  CURRENT_TIMESTAMP,
                                                                  old.email);
    end if;
    return new;
end;
$$;

alter function fn_audit_update_users() owner to postgres;

create function fn_audit_insert_user() returns trigger
    language plpgsql
as
$$
begin
    insert into log_audit_users(user_id, first_name_new, last_name_new
                               ,email_new, updateat, updatedby) values (new.id,
                                                                        new.first_name,
                                                                        new.last_name,
                                                                        new.email,
                                                                        CURRENT_TIMESTAMP,
                                                                        session_user); return new;
end;
$$;

alter function fn_audit_insert_user() owner to postgres;

-- auto-generated definition
create sequence log_audit_users_id_seq
    as integer;

alter sequence log_audit_users_id_seq owner to postgres;

alter sequence log_audit_users_id_seq owned by log_audit_users.id;



