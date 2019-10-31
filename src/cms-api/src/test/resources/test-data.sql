INSERT INTO user (id, email, first_name, last_name, password, user_type) VALUES
(1, 'boris@soen.com', 'Boris', 'Fitzgerald','123', 'admin');

INSERT INTO faculty (id, name) VALUES (2, 'Gina Cody School of Engineering and Computer Science');

INSERT INTO department (id, name, faculty_id) VALUES (4, 'Computer Science & Software Engineering', 2);

INSERT INTO program (id, description, is_active, name, department_id) VALUES  (1, 'description', 1, 'Software Engineering', 4);

INSERT INTO degree (id, credits, level, name, program_id) VALUES (1, 120, 1, 'Bachelor of Software Engineering (BEng)', 1);

INSERT INTO course (id, credits, description, is_active, name, note, number, outline, title, program_id) VALUES (1, 4.00, 'Processor structure...',
1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 228, NULL, 'System Hardware', 1);