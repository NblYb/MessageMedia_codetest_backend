create table authorities (
    id int unsigned not null auto_increment,
    authority_role varchar(30),
    primary key(id)
);

create table accounts (
    id int unsigned not null auto_increment,
    username varchar(30),
    encoded_password varchar(100),
    primary key(id)
);

create table accounts_authorities (
    account_id int unsigned not null,
    authority_id int unsigned not null,
    CONSTRAINT accounts_authorities_accounts foreign key (account_id) references accounts(id),
	CONSTRAINT accounts_authorities_authorities foreign key (authority_id) references authorities(id),
	CONSTRAINT product_store_unique UNIQUE (account_id, authority_id)
);

CREATE TABLE messages
(
    id int unsigned not null auto_increment,
    account_id int unsigned not null,
    destination_number integer not null,
    message varchar(255) not null,
    message_time integer not null,
    message_status BOOLEAN not null,
    constraint messages_accounts foreign key (account_id) references accounts(id),
    primary key(id)
);