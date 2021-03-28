
alter table if exists message
    add constraint message_user_fk
        foreign key (user_id) references user_data;

alter table if exists message
    add constraint message_status_fk
        foreign key (status_id) references statuses;

alter table if exists message
    add constraint message_car1_fk
        foreign key (car1_id) references cars;

alter table if exists message
    add constraint message_car2_fk
        foreign key (car2_id) references cars;

alter table if exists message
    add constraint message_street1_fk
        foreign key (street1_id) references streets;

alter table if exists message
    add constraint message_street2_fk
        foreign key (street2_id) references streets;

alter table if exists message
    add constraint message_road_obj_fk
        foreign key (road_obj_id) references type_of_road_objects;

alter table if exists message
    add constraint message_images_fk
        foreign key (images_id) references images;

alter table if exists message
    add constraint comment_id_fk
        foreign key (comment_id) references comments;

alter table if exists cars
    add constraint cars_brand_fk
        foreign key (brand_id) references brand_of_cars;

alter table if exists cars
    add constraint cars_model_fk
        foreign key (model_id) references model_of_cars;

alter table if exists cars
    add constraint cars_body_fk
        foreign key (body_id) references type_of_bodies;

alter table if exists cars
    add constraint cars_transport_fk
        foreign key (transport_id) references type_of_transports;

alter table if exists cities
    add constraint cities_region_fk
        foreign key (region_id) references regions;

alter table if exists model_of_cars
    add constraint model_of_brand_fk
        foreign key (brand_id) references brand_of_cars;

alter table if exists streets
    add constraint street_of_city_fk
        foreign key (city_id) references cities;

alter table if exists message
    add constraint user_id_fk
        foreign key (user_id) references user_data;