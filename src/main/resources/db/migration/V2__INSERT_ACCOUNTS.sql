insert into accounts (username, encoded_password) values
('user1','$2a$10$BRMZmPOOaLp5ksyMZMY8rOCphXq8xZtgcsi8svVIeSQnEVMp4LY0a'),
('user2', '$2a$10$BRMZmPOOaLp5ksyMZMY8rOCphXq8xZtgcsi8svVIeSQnEVMp4LY0a');


insert into authorities (authority_role) values
('ROLE_USER'),
('ROLE_ADMIN');


insert into accounts_authorities(account_id, authority_id) values
(1,1),
(2,1);

