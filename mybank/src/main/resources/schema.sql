DROP TABLE users;

CREATE TABLE users(
    id SERIAL,
    username TINYTEXT NOT NULL UNIQUE,
    first_name TINYTEXT,
    last_name TINYTEXT,
    created DATETIME
);

--CREATE TABLE IF NOT EXISTS transactions (
--    SERIAL id,
--    sending_user_id,
--    receiving_user_id,
--    amount,
--    created,
--    reference,
--    slogan
--);