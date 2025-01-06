CREATE TABLE USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE ROLES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE USER_ROLES (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(id),
    FOREIGN KEY (role_id) REFERENCES ROLES(id)
);

INSERT INTO USERS (username, password) VALUES
('admin@javapulses.com', 'admin123'),
('user@javapulses.com', 'user123');

INSERT INTO ROLES (name) VALUES
('ADMIN'),
('USER');

INSERT INTO USER_ROLES (user_id, role_id) VALUES
(1, 1), -- admin@javapulses.com has ADMIN role
(1, 2), -- admin@javapulses.com has USER role
(2, 2); -- user@javapulses.com has USER role