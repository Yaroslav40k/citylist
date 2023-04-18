CREATE TABLE city (
      id SERIAL PRIMARY KEY,
      name VARCHAR(255),
      photo_link VARCHAR(255)
);

INSERT INTO city (id, name, photo_link) VALUES (1, 'Barcelona', 'https://upload.wikimedia.org/wikipedia/barcelona.jpg');
INSERT INTO city (id, name, photo_link) VALUES (2, 'New York', 'https://upload.wikimedia.org/wikipedia/york.jpg');
INSERT INTO city (id, name, photo_link) VALUES (3, 'Istanbul', 'https://upload.wikimedia.org/wikipedia/istanbul.jpg');


CREATE TABLE "user" (
      id SERIAL PRIMARY KEY,
      name VARCHAR(255),
      password VARCHAR(255),
      role VARCHAR(255)
);

INSERT INTO "user" (name, password, role) VALUES ('fabius.bile', '$2a$12$pqOjSNWQqsTKl8YTEb5oi.9WPU7FZDBSceGUnXsq3gcAzQFtIBOSO', 'ALLOW_EDIT');