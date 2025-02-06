CREATE TABLE IF NOT EXISTS user_table (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_status VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS role_table (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_table(id),
    Unique (role_name, user_id)
);

