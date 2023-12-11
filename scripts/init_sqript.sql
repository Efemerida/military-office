CREATE TABLE IF NOT EXISTS public.account
(
    id serial NOT NULL,
    login character varying(50) COLLATE pg_catalog."default" NOT NULL,
    passport character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(200) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.employee
(
    id serial NOT NULL,
    date_of_birth date NOT NULL,
    department character varying(200) COLLATE pg_catalog."default",
    experience integer NOT NULL,
    expiration_date date,
    gender boolean NOT NULL,
    job_title character varying(300) COLLATE pg_catalog."default",
    level_of_education character varying(300) COLLATE pg_catalog."default" NOT NULL,
    name character varying(150) COLLATE pg_catalog."default" NOT NULL,
    passport character varying(10) COLLATE pg_catalog."default" NOT NULL,
    patronimic character varying(150) COLLATE pg_catalog."default",
    phone character varying(20) COLLATE pg_catalog."default",
    place_of_collection character varying(300) COLLATE pg_catalog."default",
    salary integer NOT NULL,
    speciality character varying(300) COLLATE pg_catalog."default",
    surname character varying(150) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT employee_department_check CHECK (department::text = ANY (ARRAY['MEDICAL'::character varying, 'SUMMONED'::character varying]::text[]))
);
CREATE TABLE IF NOT EXISTS public.military_office
(
    id serial NOT NULL,
    adress character varying(150) COLLATE pg_catalog."default" NOT NULL,
    manager character varying(50) COLLATE pg_catalog."default" NOT NULL,
    name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT military_office_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.citizen
(
    id serial NOT NULL,
    amount_of_children integer NOT NULL,
    date_of_birth date NOT NULL,
    driver_license character varying(150) COLLATE pg_catalog."default",
    experience integer,
    expiration_date date,
    family_status character varying(50) COLLATE pg_catalog."default" NOT NULL,
    gender boolean NOT NULL,
    index character varying(10) COLLATE pg_catalog."default" NOT NULL,
    is_archive boolean NOT NULL,
    job_title character varying(150) COLLATE pg_catalog."default",
    level_of_education character varying(150) COLLATE pg_catalog."default" NOT NULL,
    military_speciality character varying(150) COLLATE pg_catalog."default",
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    passport character varying(10) COLLATE pg_catalog."default" NOT NULL,
    patronimic character varying(150) COLLATE pg_catalog."default",
    phone character varying(20) COLLATE pg_catalog."default" NOT NULL,
    place_of_birth character varying(150) COLLATE pg_catalog."default" NOT NULL,
    place_of_collection character varying(150) COLLATE pg_catalog."default",
    place_of_work character varying(150) COLLATE pg_catalog."default",
    political_views character varying(150) COLLATE pg_catalog."default" NOT NULL,
    religious_views character varying(150) COLLATE pg_catalog."default" NOT NULL,
    residence_address character varying(150) COLLATE pg_catalog."default" NOT NULL,
    residence_address_curr character varying(150) COLLATE pg_catalog."default" NOT NULL,
    salary integer,
    size_belt character varying(10) COLLATE pg_catalog."default",
    size_contraindication character varying(10) COLLATE pg_catalog."default",
    size_form character varying(10) COLLATE pg_catalog."default",
    size_glove character varying(10) COLLATE pg_catalog."default",
    size_height character varying(10) COLLATE pg_catalog."default",
    size_shoe character varying(10) COLLATE pg_catalog."default",
    speciality character varying(150) COLLATE pg_catalog."default",
    status character varying(50) COLLATE pg_catalog."default" NOT NULL,
    status_code character varying(50) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(50) COLLATE pg_catalog."default" NOT NULL,
    military_office integer,
    CONSTRAINT citizen_pkey PRIMARY KEY (id),
    CONSTRAINT fkukyt41xjcr6slmc9alk75w3i FOREIGN KEY (military_office)
        REFERENCES public.military_office (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT citizen_family_status_check CHECK (family_status::text = ANY (ARRAY['SINGLE'::character varying, 'MARRIED'::character varying, 'WIDOWER'::character varying, 'DIVORCED'::character varying]::text[])),
    CONSTRAINT citizen_status_check CHECK (status::text = ANY (ARRAY['RESERVE'::character varying, 'ACCOUNTING'::character varying, 'SERVICE'::character varying]::text[]))
);
CREATE TABLE IF NOT EXISTS public.award
(
    id serial NOT NULL,
    date_of_receipt date NOT NULL,
    name character varying(150) COLLATE pg_catalog."default" NOT NULL,
    reason character varying(350) COLLATE pg_catalog."default" NOT NULL,
    id_user integer NOT NULL,
    CONSTRAINT award_pkey PRIMARY KEY (id),
    CONSTRAINT fkhmg1s9uutqlesvojrlm0q55pt FOREIGN KEY (id_user)
        REFERENCES public.citizen (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE IF NOT EXISTS public.inspection
(
    id serial NOT NULL,
    category character varying(10) COLLATE pg_catalog."default" NOT NULL,
    conclusion character varying(400) COLLATE pg_catalog."default" NOT NULL,
    date date NOT NULL,
    doctor integer,
    id_user integer NOT NULL,
    CONSTRAINT inspection_pkey PRIMARY KEY (id),
    CONSTRAINT fk6eyq74fs5l4duyx4p1k3kjt0n FOREIGN KEY (doctor)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkohqsc088xn1i49ggoqs1elny1 FOREIGN KEY (id_user)
        REFERENCES public.citizen (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE IF NOT EXISTS public.medical_card
(
    id serial NOT NULL,
    category character varying(10) COLLATE pg_catalog."default" NOT NULL,
    chronic_diseases character varying(150) COLLATE pg_catalog."default" NOT NULL,
    diet character varying(150) COLLATE pg_catalog."default" NOT NULL,
    disability character varying(150) COLLATE pg_catalog."default" NOT NULL,
    height integer NOT NULL,
    mental_development character varying(50) COLLATE pg_catalog."default" NOT NULL,
    mental_disorders character varying(150) COLLATE pg_catalog."default" NOT NULL,
    physical_development character varying(50) COLLATE pg_catalog."default" NOT NULL,
    weight integer NOT NULL,
    id_user integer NOT NULL,
    CONSTRAINT medical_card_pkey PRIMARY KEY (id),
    CONSTRAINT fkjbc6gskou0pw3mg50am0y5coi FOREIGN KEY (id_user)
        REFERENCES public.citizen (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE IF NOT EXISTS public.parent
(
    id serial NOT NULL,
    affiliation character varying(50) COLLATE pg_catalog."default" NOT NULL,
    date_of_birth date NOT NULL,
    job_title character varying(150) COLLATE pg_catalog."default",
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    patronimic character varying(50) COLLATE pg_catalog."default",
    phone character varying(20) COLLATE pg_catalog."default" NOT NULL,
    place_of_work character varying(150) COLLATE pg_catalog."default",
    residence_address character varying(150) COLLATE pg_catalog."default" NOT NULL,
    residence_address_curr character varying(150) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(50) COLLATE pg_catalog."default" NOT NULL,
    id_user integer,
    CONSTRAINT parent_pkey PRIMARY KEY (id),
    CONSTRAINT fkm459kxws1urf0h55i6gsm0cqo FOREIGN KEY (id_user)
        REFERENCES public.citizen (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE IF NOT EXISTS public.place_of_duty
(
    id serial NOT NULL,
    address character varying(150) COLLATE pg_catalog."default" NOT NULL,
    manager character varying(50) COLLATE pg_catalog."default" NOT NULL,
    name character varying(150) COLLATE pg_catalog."default" NOT NULL,
    number_of_available_seats integer NOT NULL,
    phone character varying(25) COLLATE pg_catalog."default" NOT NULL,
    total_seats integer NOT NULL,
    type_of_army character varying(150) COLLATE pg_catalog."default",
    CONSTRAINT place_of_duty_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.postponement
(
    id serial NOT NULL,
    date_of_acceptance date NOT NULL,
    end_date date NOT NULL,
    reason character varying(300) COLLATE pg_catalog."default" NOT NULL,
    id_user integer NOT NULL,
    CONSTRAINT postponement_pkey PRIMARY KEY (id),
    CONSTRAINT fksxtk8fff16uyvvq42vq01dkf5 FOREIGN KEY (id_user)
        REFERENCES public.citizen (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE IF NOT EXISTS public.service
(
    id serial NOT NULL,
    branch character varying(10) COLLATE pg_catalog."default",
    date date NOT NULL,
    form character varying(50) COLLATE pg_catalog."default" NOT NULL,
    job_title character varying(150) COLLATE pg_catalog."default",
    military_unit character varying(10) COLLATE pg_catalog."default",
    platoon character varying(10) COLLATE pg_catalog."default",
    rota character varying(10) COLLATE pg_catalog."default",
    "time" integer NOT NULL,
    id_place integer NOT NULL,
    id_user integer NOT NULL,
    CONSTRAINT service_pkey PRIMARY KEY (id),
    CONSTRAINT fkgeokt91jpkhxlod2wltl0d86l FOREIGN KEY (id_user)
        REFERENCES public.citizen (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fknrk71pjpj3r1qeosl8ldi3v2l FOREIGN KEY (id_place)
        REFERENCES public.place_of_duty (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE IF NOT EXISTS public.subpoena
(
    id serial NOT NULL,
    date_of_attendance date NOT NULL,
    date_of_delivery date NOT NULL,
    documents character varying(150) COLLATE pg_catalog."default" NOT NULL,
    reason character varying(150) COLLATE pg_catalog."default" NOT NULL,
    id_user integer NOT NULL,
    military_office_id integer,
    CONSTRAINT subpoena_pkey PRIMARY KEY (id),
    CONSTRAINT fk7d5kkl1y0ykxe4wgfqqtna3mf FOREIGN KEY (military_office_id)
        REFERENCES public.military_office (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk945tkw8pbp2bcwfondarxo2jv FOREIGN KEY (id_user)
        REFERENCES public.citizen (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
