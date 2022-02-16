CREATE TABLE IF NOT EXISTS "Users" (
    id char(36) PRIMARY KEY,
    name text,
    email text,
    username varchar(128),
    password text,
    active boolean
);
CREATE UNIQUE INDEX IF NOT EXISTS "Users_username" ON "Users"(username);

CREATE TABLE IF NOT EXISTS "Roles" (
    id char(36) PRIMARY KEY,
    user_id char(36),
    roles text
);

CREATE TABLE IF NOT EXISTS "Apps" (
    id char(36) PRIMARY KEY,
    name varchar(128),
    url text
);
CREATE UNIQUE INDEX IF NOT EXISTS "Apps_name" ON "Apps"(name);

CREATE TABLE IF NOT EXISTS "Passwords" (
    id_app char(36),
    id_user char(36),
    password text,
    PRIMARY KEY (id_app, id_user)
);

CREATE TABLE IF NOT EXISTS "News" (
    id char(36) PRIMARY KEY,
    title text,
    content text
);

CREATE TABLE IF NOT EXISTS "Guides" (
    id char(36) PRIMARY KEY,
    title text,
    content text
);
