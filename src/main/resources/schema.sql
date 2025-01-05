CREATE TABLE USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL
);

INSERT INTO USERS (username, password, roles) VALUES
('admin@javapulses.com', 'admin123', 'ADMIN,USER'),
('user@javapulses.com', 'user123', 'USER');


CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR(255) NOT NULL
);
