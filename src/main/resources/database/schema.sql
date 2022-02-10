CREATE TABLE "Users" (
    id char(36) PRIMARY KEY,
    name text,
    email text,
    username text,
    password text,
    active boolean
);

CREATE UNIQUE INDEX "Users_pkey1" ON "Users"(id);

CREATE TABLE "Roles" (
    id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id character(36),
    roles text
);

CREATE UNIQUE INDEX "Roles_pkey1" ON "Roles"(id int4_ops);

CREATE TABLE "Apps" (
    id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name text,
    url text
);

CREATE UNIQUE INDEX "Apps_pkey1" ON "Apps"(id int4_ops);

CREATE TABLE "Passwords" (
    id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_app integer,
    id_user character(36),
    password text
);

CREATE UNIQUE INDEX "Passwords_pkey1" ON "Passwords"(id int4_ops);

CREATE UNIQUE INDEX "Users_username" ON "Users"(username text_ops);

CREATE TABLE "News" (
    id char(36) PRIMARY KEY,
    title text,
    content text,
    img text
);

CREATE UNIQUE INDEX "News_pkey1" ON "News"(id);

CREATE TABLE "Guides" (
    id char(36) PRIMARY KEY,
    title text,
    content text
);

CREATE UNIQUE INDEX "Guides_pkey1" ON "Guides"(id);