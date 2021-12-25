CREATE TABLE "Users" (
    id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
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