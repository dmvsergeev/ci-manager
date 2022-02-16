CREATE TABLE "Users" (
    id char(36) PRIMARY KEY,
    name text,
    email text,
    username varchar(128),
    password text,
    active boolean
);
CREATE UNIQUE INDEX "Users_username" ON "Users"(username);

CREATE TABLE "Roles" (
    id char(36) PRIMARY KEY,
    user_id char(36),
    roles text
);

CREATE TABLE "Apps" (
    id char(36) PRIMARY KEY,
    name varchar(128),
    url text
);
CREATE UNIQUE INDEX "Apps_name" ON "Apps"(name);

CREATE TABLE "Passwords" (
    id_app char(36),
    id_user char(36),
    password text,
    PRIMARY KEY (id_app, id_user)
);

CREATE TABLE "News" (
    id char(36) PRIMARY KEY,
    title text,
    content text
);

CREATE TABLE "Guides" (
    id char(36) PRIMARY KEY,
    title text,
    content text
);
