CREATE TABLE "Users" (
    id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name text,
    email text,
    username text,
    password text,
    active boolean
);

CREATE UNIQUE INDEX "Users_pkey1" ON "Users"(id int4_ops);

CREATE TABLE "Roles" (
    id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id integer,
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
    id_user integer,
    password text
);

CREATE UNIQUE INDEX "Passwords_pkey1" ON "Passwords"(id int4_ops);