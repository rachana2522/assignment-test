CREATE DATABASE assessment_db; 


 

USE assessment_db;

 

CREATE TABLE Subject (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(255) NOT NULL
);

 

CREATE TABLE Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(255) NOT NULL,
    score FLOAT,
    subject_id INT,
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id)
);
