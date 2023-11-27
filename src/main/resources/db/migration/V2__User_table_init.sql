CREATE TABLE IF NOT EXISTS user_table (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_status VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS role_table (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL
);

