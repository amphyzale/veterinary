create table service (
                         id int8 not null,
                         name varchar(255) null,
                         description varchar(5000) null,
                         price DECIMAL null,
                         duration int8 null,
                         image varchar(255) null,
                         primary key (id)
);

create table doctor (
                        id int8 not null,
                        guid varchar(255) not null,
                        username varchar(255) not null,
                        first_name varchar(255),
                        last_name varchar(255),
                        fio varchar(255),
                        password varchar(255) not null,
                        status varchar(255) default 'ACTIVE',
                        email varchar(255) not null,
                        created timestamp,
                        updated timestamp,
                        user_pic varchar(255),
                        gender varchar(255),
                        locale varchar(255),
                        last_visit timestamp,
                        specialization varchar(255) null,
                        start_of_practice timestamp default current_timestamp,
                        description varchar(5000) null,
                        primary key (id)
);

create table doctor_services (
                            doctor_id int8,
                            service_id int8
);

