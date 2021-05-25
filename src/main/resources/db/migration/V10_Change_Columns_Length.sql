alter table if exists doctor
    alter column user_pic TYPE varchar(1000);

alter table if exists pet_data
    alter column pet_pic TYPE varchar(1000);

alter table if exists veterinary.public.service
    alter column image TYPE varchar(1000);

alter table if exists user_data
    alter column user_pic TYPE varchar(1000);
