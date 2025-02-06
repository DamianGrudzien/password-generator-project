CREATE TABLE IF NOT EXISTS logs_table (
    id SERIAL PRIMARY KEY,
    logs VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    log_time timestamp NOT NULL
);
