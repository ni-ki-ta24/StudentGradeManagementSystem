-- Student Grade Management System
-- Database Setup Script

CREATE DATABASE IF NOT EXISTS student_grade_system;

USE student_grade_system;


-- Students Table
CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    course VARCHAR(100),
    semester INT
);


-- Grades Table
CREATE TABLE IF NOT EXISTS grades (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    subject VARCHAR(100) NOT NULL,
    marks DOUBLE NOT NULL,

    FOREIGN KEY (student_id)
        REFERENCES students(id)
);


-- Sample Student
INSERT INTO students
(name, email, phone, course, semester)
VALUES
('Nikita Chavan',
 'nikita@example.com',
 '9876543210',
 'Computer Engineering',
 5);


-- Sample Grades
INSERT INTO grades
(student_id, subject, marks)
VALUES
(1, 'Java', 85),
(1, 'DBMS', 78),
(1, 'Python', 92),
(1, 'Mathematics', 81);


-- Verify Students
SELECT * FROM students;


-- Verify Grades
SELECT * FROM grades;


-- Student Grade Report
SELECT
    s.id,
    s.name,
    s.course,
    s.semester,
    g.subject,
    g.marks
FROM students s
JOIN grades g
ON s.id = g.student_id;