alter table if exists doctor_services
    add constraint fk_doctor_service
        foreign key (doctor_id) references doctor;

alter table if exists doctor_services
    add constraint fk_service_doctor
        foreign key (service_id) references service;