CREATE DATABASE assessment_db;
USE assessment_db;

CREATE TABLE subject (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25)
);

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(26),
    score FLOAT,
    subject_id INT,
    FOREIGN KEY (subject_id) REFERENCES subject(id)
);