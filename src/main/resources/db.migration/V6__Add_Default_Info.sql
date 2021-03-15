insert into brand_of_cars(id, name) VALUES (1, 'Audi'),
                                           (2, 'BMW'),
                                           (3, 'KIA');

insert into model_of_cars(id, name, brand_id) VALUES (1, 'TT', 1),
                                                     (2, 'A3', 1),
                                                     (3, 'M3', 2),
                                                     (4, 'M5', 2),
                                                     (5, 'Rio', 3),
                                                     (6, 'Spectra', 3);

insert into type_of_transports(id, name) VALUES (1, 'Общественный'),
                                                (2, 'Такси'),
                                                (3, 'Индивидуальный'),
                                                (4, 'Организация');

insert into type_of_bodies(id, name) VALUES (1, 'Седан'),
                                            (2, 'Купе'),
                                            (3, 'Грузовик'),
                                            (4, 'Автобус');

insert into type_of_road_objects(id, name) VALUES (1, 'Перекресток'),
                                                  (2, 'Дорога'),
                                                  (3, 'Обочина'),
                                                  (4, 'Пешеходный переход'),
                                                  (5, 'Тротуар');

insert into regions (id, name) VALUES (1, 'Владимирская область'),
                                      (2, 'Ивановская область'),
                                      (3, 'Ярославская область'),
                                      (4, 'Рязанская область'),
                                      (5, 'Московская область');

insert into cities (id, name, region_id) VALUES (1, 'Владимир', 1),
                                                (2, 'Иваново', 2),
                                                (3, 'Ярославль', 3),
                                                (4, 'Рязань', 4),
                                                (5, 'Москва', 5);

insert into streets (id, name, city_id) VALUES (1, 'Горького', 1),
                                               (2, 'Мира', 1),
                                               (3, 'пр-кт Строителей', 1),
                                               (4, 'Ленинский пр-кт', 1),
                                               (5, 'Октябрьский пр-кт', 1);

insert into statuses (id, name) values (1, 'Создано'), (2, 'Отправлено'), (3, 'Опубликовано'), (4, 'Отклонено');