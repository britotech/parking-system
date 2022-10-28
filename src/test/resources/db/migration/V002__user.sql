CREATE TABLE IF NOT EXISTS public.tb_user
(
    id        uuid         NOT NULL,
    username  varchar(50)  NOT NULL,
    fullname  varchar(150) NOT NULL,
    email     varchar(80)  NOT NULL,
    password  varchar(255) NOT NULL,
    status    varchar(10)  NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT user_uk_username UNIQUE (username),
    CONSTRAINT user_uk_email    UNIQUE (email)
);

ALTER TABLE IF EXISTS public.tb_user OWNER to postgres;

INSERT INTO public.tb_user(id, username, email, fullname, password, status)
VALUES ('cf1c2190-a2cc-43e3-ab13-f26d9a21c90f', 'admin', 'admin@brito.tech', 'Administrator', '$2a$10$goclcAykrE8Hz.TmSGsgeuLdDEhztR3Gdr39WtBSrn7I5URu/qlvi','ACTIVE');

INSERT INTO public.tb_user(id, username, email, fullname, password, status)
VALUES ('b01832b6-592c-49fb-b99d-823a0a3a446e', 'user', 'user@brito.tech', 'User', '$2a$10$goclcAykrE8Hz.TmSGsgeuLdDEhztR3Gdr39WtBSrn7I5URu/qlvi','ACTIVE');
