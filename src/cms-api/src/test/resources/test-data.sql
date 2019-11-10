INSERT INTO user (id, email, first_name, last_name, password, user_type) VALUES
(1, 'boris@soen.com', 'Boris', 'Fitzgerald','123', 'admin');

INSERT INTO faculty (id, name) VALUES (2, 'Gina Cody School of Engineering and Computer Science');

INSERT INTO department (id, name, faculty_id) VALUES (4, 'Computer Science & Software Engineering', 2);

INSERT INTO program (id, description, is_active, name, department_id) VALUES  (1, 'description', 1, 'Software Engineering', 4);

INSERT INTO degree (id, credits, level, name, program_id) VALUES (1, 120, 1, 'Bachelor of Software Engineering (BEng)', 1);

INSERT INTO course (id, credits, description, is_active, lab_hours, lecture_hours, level, name, note, number, outline, title, tutorial_hours, program_id) VALUES (1, 4.00, 'Processor structure...',
1, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 228, NULL, 'System Hardware', 2, 1);

INSERT INTO request_package (id, department_id) VALUES (1, 4);

INSERT INTO approval_pipeline (id, apc, department_council, department_curriculum_committee, faculty_council, senate, undergraduate_studies_committee) VALUES (1, 4, 0, 1, 2, 5, 3);

INSERT INTO approval_pipeline_request_package (pipeline_id, package_id, position) VALUES (1, 1, 'Department Curriculum Committee');