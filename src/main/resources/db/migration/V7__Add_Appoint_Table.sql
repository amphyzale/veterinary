create table appointment (
                          id int8 not null,
                          guid varchar(255) not null,
                          date_start timestamp,
                          date_and timestamp,
                          status varchar(255) default 'ACTIVE',
                          created timestamp,
                          updated timestamp,
                          user_id int8,
                          service_id int8,
                          doctor_id int8,
                          pet_id int8,
                          primary key (id)
);

alter table if exists appointment
    add foreign key (user_id)
        references user_data(id);

alter table if exists appointment
    add foreign key (service_id)
        references service(id);

alter table if exists appointment
    add foreign key (doctor_id)
        references doctor(id);

alter table if exists appointment
    add foreign key (pet_id)
        references pet_data(id);

