DROP DATABASE IF EXISTS projeto2;

CREATE DATABASE projeto2;

USE projeto2;

CREATE TABLE permission(
	id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE actuation_area(
	id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE actuation_level(
	id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE collaborator(
    id INT NOT NULL AUTO_INCREMENT,
    permission_id INT NOT NULL,
    actuation_area_id INT NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (permission_id) REFERENCES permission(id),
    FOREIGN KEY (actuation_area_id) REFERENCES actuation_area(id)
);

CREATE TABLE career_path(
	id INT NOT NULL AUTO_INCREMENT,
    collaborator_id INT NOT NULL,
    actuation_level_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (collaborator_id) REFERENCES collaborator(id),
    FOREIGN KEY (actuation_level_id) REFERENCES actuation_level(id)
);

CREATE TABLE checkpoint(
	id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    actuation_area_id INT NOT NULL,
    actuation_level_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (actuation_area_id) REFERENCES actuation_area(id),
    FOREIGN KEY (actuation_level_id) REFERENCES actuation_level(id)
);

CREATE TABLE checkpoint_status(
	id INT NOT NULL AUTO_INCREMENT,
    career_path_id INT NOT NULL,
    checkpoint_id INT NOT NULL,
    progress ENUM("NOT_DONE", "IN_PROGRESS", "DONE") NOT NULL DEFAULT "NOT_DONE",
    PRIMARY KEY (id),
    FOREIGN KEY (career_path_id) REFERENCES career_path(id),
    FOREIGN KEY (checkpoint_id) REFERENCES checkpoint(id)
);

INSERT INTO permission (`description`) VALUES ("ADMIN");
INSERT INTO permission (`description`) VALUES ("EMPLOYEE");

INSERT INTO projeto2.actuation_area (`description`) VALUES ("MANAGER");

INSERT INTO projeto2.collaborator (`permission_id`, `actuation_area_id`, `name`, `email`, `password`) VALUES (1, 1, "Admin UFRN", "admin@ufrn.edu.br", "123");
