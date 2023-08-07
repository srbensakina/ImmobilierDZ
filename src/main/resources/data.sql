
INSERT INTO address (id , door_number, street_name, city) VALUES (1 ,'52' , 'med Khmisti' , 'oran');
INSERT INTO address (id , door_number, street_name, city) VALUES (2 ,'53' , 'amir aek' , 'oran');
INSERT INTO address (id , door_number, street_name, city) VALUES (3 ,'54' , 'bab ezzouar' , 'alger');
INSERT INTO address (id , door_number, street_name, city) VALUES (4 ,'55' , 'mouzaya' , 'blida');
INSERT INTO address (id , door_number, street_name, city) VALUES (5 ,'554' , 'lsflsjq' , 'Chrea');
INSERT INTO address (id , door_number, street_name, city) VALUES (6 ,'35' , 'mhaji el hbib' , 'Chlef');

INSERT INTO app_user (id , app_user_role, email, enabled ,first_name, last_name,locked, password) VALUES (1 ,'OWNER' , 'ranran@gmail.com' , true, 'Rania', 'Bensakina',false , '$2a$10$a3ITSgbOK0fNKeO4LZYOROtzYl7WyAtMAQWszV8hZxJWUiBmrtbE6');
INSERT INTO app_user (id , app_user_role, email, enabled ,first_name, last_name,locked, password) VALUES (2 ,'OWNER' , 'malmal@gmail.com' , true, 'Malek', 'Bensakina',false , '$2a$10$a3ITSgbOK0fNKeO4LZYOROtzYl7WyAtMAQWszV8hZxJWUiBmrtbE6');


INSERT INTO house (id , name, description,occupied ,  type, price, address_id, owner_id,number_of_floors,average_rating) VALUES (5 ,'drgrsgse' , 'this is a house' , 'true', 'RENT' , 10000 , 5 , 1,2 ,5);
INSERT INTO house (id , name, description,occupied ,  type, price, address_id, owner_id,number_of_floors,average_rating) VALUES (6 ,'srgsegegh' , 'medim house2' , 'false', 'SALE' , 10240 ,6  , 3,1 ,5);

INSERT INTO flat ( id,name, description,occupied , type, price, address_id , owner_id ,floor , number_of_rooms ) VALUES (2 ,'rgegrg' , 'no no house' , 'true', 'RENT' , 10500 , 2 , 1,5, 3);
INSERT INTO flat ( id,name, description,occupied , type, price, address_id , owner_id, floor , number_of_rooms) VALUES (4 ,'fgrzegdfg' , 'big big' , 'true', 'COLLOCATION' , 100450 , 4 , 3,1,1);


INSERT INTO rating (id , rating, customer_id,house_id) VALUES (1 ,5 ,1,5);
INSERT INTO rating (id , rating, customer_id, house_id) VALUES (2 ,3 , 2,6);












