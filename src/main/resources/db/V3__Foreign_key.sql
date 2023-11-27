ALTER table role_table
    FOREIGN KEY (user_id)
    REFERENCES user_table(user_id),
    UNIQUE (role_name, user_id)
;