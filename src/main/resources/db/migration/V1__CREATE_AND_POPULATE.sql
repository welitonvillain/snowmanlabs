CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    level VARCHAR(50) NOT NULL
);

CREATE TABLE movies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    release_year INT NOT NULL,
    format VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    duration INT NOT NULL,
    available BOOLEAN NOT NULL
);

CREATE TABLE series (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    release_year INT NOT NULL,
    format VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    season INT NOT NULL,
    episodes INT NOT NULL,
    available BOOLEAN NOT NULL
);

CREATE TABLE rentals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_id BIGINT NOT NULL,
    item_type VARCHAR(50) NOT NULL,
    username VARCHAR(100) NOT NULL,
    rental_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP
);

INSERT INTO movies (title, release_year, format, genre, duration, available) VALUES
    ('The Matrix', 1999, 'DIGITAL', 'SCIENCE_FICTION', 5400, TRUE),
    ('The Godfather', 1972, 'PHYSICAL', 'DRAMA', 3600, FALSE),
    ('Interstellar', 2014, 'DIGITAL', 'SCIENCE_FICTION', 6600, FALSE);

INSERT INTO users (username, password, email, level) VALUES
    ('weliton.villain', 'password123', 'dev.weliton.villain@gmail.com', 'USER'),
    ('snow.man', 'password456', 'snow.man@email.com', 'ADMIN');

INSERT INTO rentals (item_id, item_type, username, rental_date, return_date) VALUES
    (1, 'MOVIE', 'weliton.villain', '2024-11-01 10:00:00', '2024-11-05 12:00:00'),
    (2, 'MOVIE', 'weliton.villain', '2024-11-01 10:00:00', NULL),
    (3, 'MOVIE', 'snow.man', '2024-11-10 15:30:00', NULL);

INSERT INTO series (title, release_year, format, genre, season, episodes, available) VALUES
    ('Breaking Bad', 2008, 'DIGITAL', 'DRAMA', 5, 62, TRUE),
    ('Stranger Things', 2016, 'DIGITAL', 'SCIENCE_FICTION', 4, 34, TRUE),
    ('Game of Thrones', 2011, 'DIGITAL', 'FANTASY', 8, 73, TRUE),
    ('The Office', 2005, 'PHYSICAL', 'COMEDY', 9, 201, TRUE);