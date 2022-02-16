INSERT INTO "public"."Users"("id","username","password","name","email","active")
VALUES
('1', 'admin','$2a$12$Lg/zmfq.ZAk8WYYbYB/iCe8XAeEHSRFMJIcN62U9gtUOCrHMi5sby','Dmitry Sergeev','dmvsergeev@gmail.com',TRUE),
('2', 'user','$2a$12$NxhSkxoXWAgsSgEdRX0k5eyq2Y2C5WcuQ5IlJy.rxeP.lQbNXRd0O','Test User','testuser@gmail.com',TRUE);

INSERT INTO "public"."Roles"("id", "user_id","roles")
VALUES
('3', '1','ADMIN'),
('4', '2','USER');

INSERT INTO "public"."Apps"("id", "name","url")
VALUES
('5', 'yandex mail','https://mail.yandex.ru'),
('6', 'flock','https://flock.com'),
('7', 'mc','https://online.moysklad.ru/'),
('8', '1c','https://1c.ru'),
('9', 'amoCRM','https://amocrm.ru/'),
('10', 'site admin','https://localhost'),
('11', 'site stats','https://localhost'),
('12', 'dropbox','https://localhost');

INSERT INTO "public"."Passwords"("id_app","id_user","password")
VALUES
('5','1','Ghd234FG'),
('6','1','GxdDS234FG'),
('7','1','Vbdfg45fdggdf'),
('8','1','234sdfsdf45'),
('9','1','sdFsdfwer4344'),
('10','1','FGhsdfwt45'),
('11','1','$#%^FgFG'),
('12','1','BDF%$4FGfgfg');

INSERT INTO "public"."News"("id","title","content")
VALUES
('13','Новость 1','Скоро будет поставка'),
('14','Новость еще тестовая','Прошли тесты движка');

INSERT INTO "public"."Guides"("id","title","content")
VALUES
('15','Как пользоваться амо','ываываываываыв ыавывы');