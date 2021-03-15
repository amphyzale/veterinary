insert into role_data (id, name)
VALUES (1, 'USER'),
       (2, 'GRAND_ADMIN'),
       (3, 'ADMINISTRATOR'),
       (4, 'DOCTOR');


create extension if not exists pgcrypto;
update user_data set password = crypt(password, gen_salt('bf', 8));
