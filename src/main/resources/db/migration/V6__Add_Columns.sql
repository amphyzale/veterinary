alter table if exists service
    add column isPromo bool default false not null;

create table pet_data (
                          id int8 not null,
                          guid varchar(255) not null,
                          nickname varchar(255) not null,
                          pet_pic varchar(255),
                          kind varchar(255),
                          breed varchar(255),
                          gender varchar(255),
                          birth_date timestamp,
                          status varchar(255) default 'ACTIVE',
                          created timestamp,
                          updated timestamp,
                          user_id int8,
                          primary key (id)
);

alter table if exists pet_data
    add constraint pet_user_fk
        foreign key (user_id) references user_data;


