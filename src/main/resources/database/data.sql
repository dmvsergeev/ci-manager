TRUNCATE TABLE "public"."Users";
INSERT INTO "public"."Users"("id","username","password","name","email","active")
VALUES
('53839b82-1556-4945-bc59-78bb9434e4ff', 'admin','$2a$12$5SHK6Dxm2NhbXg9PreJkrOQ9Lq0adSOG9EvYghBV/eE29EmkwX3ka','Test Admin','admin@admin.com',TRUE),
('31f7141c-e4b7-4f78-bdd5-db74dec8990c', 'user','$2a$12$NxhSkxoXWAgsSgEdRX0k5eyq2Y2C5WcuQ5IlJy.rxeP.lQbNXRd0O','Test User','user@user.com',TRUE);

TRUNCATE TABLE "public"."Roles";
INSERT INTO "public"."Roles"("id", "user_id","roles")
VALUES
('3', '53839b82-1556-4945-bc59-78bb9434e4ff','ADMIN,USER'),
('4', '31f7141c-e4b7-4f78-bdd5-db74dec8990c','USER');

TRUNCATE TABLE "public"."Apps";
INSERT INTO "public"."Apps"("id", "name","url")
VALUES
('dba97e81-f86a-4cff-bfde-72f203da61c7', 'yandex mail','https://mail.yandex.ru'),
('f5404cc0-6c0b-4a36-8e59-03529f3187a3', 'flock','https://flock.com'),
('8f934952-b2ac-48a8-92ea-6906b227c6a1', 'mc','https://online.moysklad.ru/'),
('93cd4be0-a6ab-427b-902a-aa1b574e1de9', '1c','https://1c.ru'),
('ce90de1f-1aeb-478b-9108-dd6bc4af390a', 'amoCRM','https://amocrm.ru/'),
('223043b3-849e-4f3b-82d7-e8f39df9ddb9', 'site admin','https://localhost'),
('59ac6ebc-9943-4b2d-b00d-3d2a4fd9dcd5', 'site stats','https://localhost'),
('56bac105-5075-4814-a83a-31d5cf480535', 'dropbox','https://localhost');

TRUNCATE TABLE "public"."Passwords";
INSERT INTO "public"."Passwords"("id_app","id_user","password")
VALUES
('dba97e81-f86a-4cff-bfde-72f203da61c7','53839b82-1556-4945-bc59-78bb9434e4ff','Ghd234FG'),
('f5404cc0-6c0b-4a36-8e59-03529f3187a3','53839b82-1556-4945-bc59-78bb9434e4ff','GxdDS234FG'),
('8f934952-b2ac-48a8-92ea-6906b227c6a1','53839b82-1556-4945-bc59-78bb9434e4ff','Vbdfg45fdggdf'),
('93cd4be0-a6ab-427b-902a-aa1b574e1de9','53839b82-1556-4945-bc59-78bb9434e4ff','234sdfsdf45'),
('ce90de1f-1aeb-478b-9108-dd6bc4af390a','53839b82-1556-4945-bc59-78bb9434e4ff','sdFsdfwer4344'),
('223043b3-849e-4f3b-82d7-e8f39df9ddb9','53839b82-1556-4945-bc59-78bb9434e4ff','FGhsdfwt45'),
('59ac6ebc-9943-4b2d-b00d-3d2a4fd9dcd5','53839b82-1556-4945-bc59-78bb9434e4ff','$#%^FgFG'),
('56bac105-5075-4814-a83a-31d5cf480535','53839b82-1556-4945-bc59-78bb9434e4ff','BDF%$4FGfgfg');

TRUNCATE TABLE "public"."News";
INSERT INTO "public"."News"("id","title","content")
VALUES
('d5bdabc4-ee72-4f07-bca8-00f65ea84494','Новость 1','Скоро будет поставка'),
('52008fc6-9a60-4358-a260-5d5f5e83ecfb','Новость еще тестовая','Прошли тесты движка');

TRUNCATE TABLE "public"."Guides";
INSERT INTO "public"."Guides"("id","title","content")
VALUES
('b0103d54-b0ab-46aa-8b9a-c4c0a18ed44b','Как пользоваться амо','ываываываываыв ыавывы');