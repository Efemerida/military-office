insert into account(id, login, passport, password) values
(1,'user1','1234567890','$2a$10$EkjlQzcH1Q39ta5THnDI/eczgRVpNKIf2E0h9smH7nOzNv/gZY9fe'),
(2,'admin','0987654321','$2a$10$.ESz7YnYS8zFuiTF12bN..nQDRmhZR3t0MeVRpjNZv/Y/CGXo2u1G'),
(3,'doctor','3213213213','$2a$10$WwzBDNf/tEMPe3ISuBW1weiTfYpKue2et04F3djEqnOgBtXqFBc6u');

INSERT INTO public.military_office(
	id, adress, manager, name, phone)
	VALUES (1,'г. Саратов ул. Вязова д.2','Иванов Сергей Петрович','Районый военкомат Волжоского района города Саратова','546781');

INSERT INTO public.citizen(
	id, amount_of_children, date_of_birth, driver_license, experience, expiration_date, family_status, gender, index, is_archive, job_title, level_of_education, military_speciality, name, passport, patronimic, phone, place_of_birth, place_of_collection, place_of_work, political_views, religious_views, residence_address, residence_address_curr, salary, size_belt, size_contraindication, size_form, size_glove, size_height, size_shoe, speciality, status, status_code, surname, military_office)
	VALUES 
(8,2,'1992-12-01','2415519865',12,'2010-01-01','MARRIED',true,'123555',false,'Хирург','Высшее',null,'Сергей','1234567890','Сергеевич','89817778282','г. Саратов','Саратовский Государственный Медецинский Университет','Областная больница','Демократ','Христианин','г. Саратов ул. Петрова д. 8 кв. 128','г. Саратов ул. Петрова д. 8 кв. 128',200000,2,3,3,1,3,3,'радист','RESERVE','561821','Ворошилов',1),
(9,3,'1980-10-14','2819192819',60,'1996-06-05','MARRIED',true,'718251',false,'Электрик','Среднее','Механик-водитель','Петр','1231231231','Анатоотвевич','98122221212','г. Саратов','Колледж','Алмаз','Социалист','Атеист','г. Саратов ул. Петрова д. 19','г. Саратов ул. Петрова д. 19',100000,null,null,null,null,null,null,'Электрик','RESERVE','769564','Филатов',1);

INSERT INTO public.award(id, date_of_receipt, name, reason, id_user) VALUES
(2,'2023-10-24','Орден хорошего парня 1 степени','Хороший парень',9);

INSERT INTO public.employee(
	id, date_of_birth, department, experience, expiration_date, gender, job_title, level_of_education, name, passport, patronimic, phone, place_of_collection, salary, speciality, surname)
	VALUES
(1,'1980-02-13','SUMMONED',21,'2000-06-01',false,'Заведущая призывной комиссией','Высшее','Василиса','0987654321','Кириловна','89899992131','Саратовская Юридическая Академия',30000,'Юрист','Чуркас'),
(2,'1960-05-19','MEDICAL',240,'1980-06-01',false,'Глав. врач','Высшее','Мария','3213213213','Андреевна','89127777777','Саратовский Государственный Медецинский Университет',50000,'Педиатр','Сурова');

INSERT INTO public.inspection(
	id, category, conclusion, date, doctor, id_user)
	VALUES (1,'А','Здоров','2010-01-05',2,9);

INSERT INTO public.medical_card(
	id, category, chronic_diseases, diet, disability, height, mental_development, mental_disorders, physical_development, weight, id_user)
	VALUES (1,'А','Отсутствуют','Стандартная','Отсутствуют',172,'Нормальное','Отсутствуют','Нормальное',70,9);
	
INSERT INTO public.parent(
	id, affiliation, date_of_birth, job_title, name, patronimic, phone, place_of_work, residence_address, residence_address_curr, surname, id_user)
	VALUES 
	(1,'Мать','1950-06-01',null,'Мария','Сергеевна','89132188282','Пенсионер','г. Саратов ул. Петрова д. 21','г. Саратов ул. Петрова д. 21','Купатова',8),
	(2,'Отец','1994-06-07','Сварщик','Петр','Анатоотвевич','98122221212','Алмаз','г. Саратов ул. Петрова д. 19','г. Саратов ул. Петрова д. 19','Купатов',9);
	
INSERT INTO public.place_of_duty(
	id, address, manager, name, number_of_available_seats, phone, total_seats, type_of_army)
	VALUES (1,'Балашовская область, 2 военная часть','Конев Петр Валерьевич','Военная часть №2',512,'819191',1051,'Сухопутные');
	
INSERT INTO public.postponement(
	id, date_of_acceptance, end_date, reason, id_user)
	VALUES (1,'2016-06-08','2017-10-11','Надо',9);

INSERT INTO public.service(
	id, branch, date, form, job_title, military_unit, platoon, rota, "time", id_place, id_user)
	VALUES (1,3,'2008-05-13','По призыву','Рядовой','3','412','2',12,1,9);
	
INSERT INTO public.subpoena(
	id, date_of_attendance, date_of_delivery, documents, reason, id_user, military_office_id)
	VALUES (1,'2023-12-27','2023-12-11','Паспорт','Уточнение документов',9,1);
alter SEQUENCE public.account_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY account.id;
alter SEQUENCE public.award_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY award.id;
alter SEQUENCE public.citizen_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY citizen.id;
alter SEQUENCE public.employee_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY employee.id;
alter SEQUENCE public.inspection_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY inspection.id;
alter SEQUENCE public.medical_card_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY medical_card.id;
alter SEQUENCE public.military_office_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY military_office.id;
alter SEQUENCE public.parent_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY parent.id;
alter SEQUENCE public.place_of_duty_id_seq
    INCREMENT 1
    START 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY place_of_duty.id;
alter SEQUENCE public.postponement_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY postponement.id;
alter SEQUENCE public.service_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY service.id;
alter SEQUENCE public.subpoena_id_seq
    INCREMENT 1
    START with 20
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 20
    OWNED BY subpoena.id;

