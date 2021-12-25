INSERT INTO "public"."Users"("username","password","active")
VALUES
('admin','$2a$12$Lg/zmfq.ZAk8WYYbYB/iCe8XAeEHSRFMJIcN62U9gtUOCrHMi5sby',TRUE),
('user','$2a$12$NxhSkxoXWAgsSgEdRX0k5eyq2Y2C5WcuQ5IlJy.rxeP.lQbNXRd0O',TRUE);

INSERT INTO "public"."Roles"("user_id","roles")
VALUES
(1,'"ADMIN","USER"'),
(2,'USER');