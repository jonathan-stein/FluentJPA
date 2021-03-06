create schema if not exists EC;

CREATE TABLE EC.users (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY,
	email varchar(100),
	name varchar(100)
);

CREATE TABLE EC.user_phone_numbers (
    user_id BIGINT,
    phone_number varchar(255)
);

CREATE TABLE EC.user_addresses (
    user_id BIGINT,
    house_number varchar(255),
    street varchar(255),
    city varchar(255),
    country varchar(255),
    state varchar(255),
    zip_code varchar(255)
);
