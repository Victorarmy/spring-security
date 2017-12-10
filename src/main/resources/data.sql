insert into role(name) VALUES ('ROLE_USER')
insert into role(name) VALUES ('ROLE_MODERATOR')
insert into role(name) VALUES ('ROLE_ADMIN')

insert into user(email, is_locked, login_attempts, password) values('a@a.a', false, 0, 'a')
insert into user_roles(user_id, roles_id) values(1, 3)


insert into user(email, is_locked, login_attempts, password) values('u@u.u', false, 0, 'u')
insert into user_roles(user_id, roles_id) values(2, 1)