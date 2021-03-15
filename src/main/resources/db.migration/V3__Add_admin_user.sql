insert into user_data (id, name, first_name, last_name, password, status, email, created, updated, last_visit)
VALUES ('1', 'admin', 'Alex', 'Pyanov', '$2a$08$7qrVVOtZUhtfOYJ2ScEu5eTZvgQIQOJxP0U8qTYm/NxHraroNlgg6',
        'ACTIVE', 'enforcer.snk@gmail.com', current_timestamp, current_timestamp, current_timestamp);


insert into user_roles(user_id, role_id)
values (1, 1),
       (1, 2),
       (1, 3);