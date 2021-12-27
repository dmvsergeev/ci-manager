INSERT INTO "public"."Users"("username","password","name","email","active")
VALUES
('admin','$2a$12$Lg/zmfq.ZAk8WYYbYB/iCe8XAeEHSRFMJIcN62U9gtUOCrHMi5sby','Dmitry Sergeev','dmvsergeev@gmail.com',TRUE),
('user','$2a$12$NxhSkxoXWAgsSgEdRX0k5eyq2Y2C5WcuQ5IlJy.rxeP.lQbNXRd0O','Test User','testuser@gmail.com',TRUE);

INSERT INTO "public"."Roles"("user_id","roles")
VALUES
(1,'"ADMIN","USER"'),
(2,'USER');

INSERT INTO "public"."Apps"("name","url")
VALUES
('yandex mail','https://mail.yandex.ru'),
('flock','https://flock.com'),
('mc','https://online.moysklad.ru/'),
('1c',''),
('amoCRM','https://marialeokidru.amocrm.ru/'),
('site admin',''),
('site stats',''),
('dropbox','');

INSERT INTO "public"."Passwords"("id_app","id_user","password")
VALUES
(1,1,'Ghd234FG'),
(2,1,'GxdDS234FG'),
(3,1,'Vbdfg45fdggdf'),
(4,1,'234sdfsdf45'),
(5,1,'sdFsdfwer4344'),
(6,1,'FGhsdfwt45'),
(7,1,'$#%^FgFG'),
(8,1,'BDF%$4FGfgfg');