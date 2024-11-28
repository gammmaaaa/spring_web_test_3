-- liquibase formatted sql

-- changeset e_cha:1727702549313-1
CREATE SEQUENCE IF NOT EXISTS role_seq START WITH 1 INCREMENT BY 50;

-- changeset e_cha:1727702549313-3
CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

-- changeset e_cha:1727702549313-4
CREATE TABLE role
(
    id   BIGINT,
    name VARCHAR(20),
    CONSTRAINT pk_role PRIMARY KEY (id)
);

ALTER TABLE role
    ALTER COLUMN id SET DEFAULT nextval('role_seq');

-- changeset e_cha:1727702549313-6
CREATE TABLE user_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (role_id, user_id)
);

-- changeset e_cha:1727702549313-7
CREATE TABLE users
(
    id       BIGINT,
    login    VARCHAR(20),
    email    VARCHAR(50),
    password VARCHAR(120),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ALTER COLUMN id SET DEFAULT nextval('users_seq');

-- changeset e_cha:1727702549313-8
ALTER TABLE users
    ADD CONSTRAINT uc_74165e195b2f7b25de690d14a UNIQUE (email);

-- changeset e_cha:1727702549313-9
ALTER TABLE users
    ADD CONSTRAINT uc_f8d2576e807e2b20b506bf6a3 UNIQUE (login);

-- changeset e_cha:1727702549313-10
ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES role (id);

-- changeset e_cha:1727702549313-11
ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);

