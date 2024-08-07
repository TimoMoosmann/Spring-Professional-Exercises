DROP TABLE IF EXISTS users;
CREATE TABLE users(
    id SERIAL,
    username TINYTEXT NOT NULL UNIQUE,
    first_name TINYTEXT,
    last_name TINYTEXT,
    created DATETIME
);

DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions(
    id SERIAL,
    sending_user_id INT,
    receiving_user_id INT,
    amount INT,
    reference TINYTEXT NOT NULL,
    created DATETIME
);