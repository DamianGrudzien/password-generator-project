ALTER table role_table
ADD CONSTRAINT fk_user_id
FOREIGN KEY (role_id)
REFERENCES user_table (user_id);