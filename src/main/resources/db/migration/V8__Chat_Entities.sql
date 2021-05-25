create table chat_room (
                             id int8 not null,
                             chat_id varchar(255),
                             sender_id int8,
                             recipient_id int8,
                             primary key (id)
);

create table chat_message (
                           id int8 not null,
                           chat_id varchar(255),
                           sender_id int8,
                           recipient_id int8,
                           sender_name varchar(255),
                           recipient_name varchar(255),
                           content text,
                           message_date_time timestamp,
                           message_status varchar(255),
                           primary key (id)
);

alter table if exists chat_room
    add foreign key (sender_id)
        references user_data(id);

alter table if exists chat_room
    add foreign key (recipient_id)
        references user_data(id);

alter table if exists chat_message
    add foreign key (sender_id)
        references user_data(id);

alter table if exists chat_message
    add foreign key (recipient_id)
        references user_data(id);
