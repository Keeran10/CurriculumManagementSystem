-- MIT License
--
-- Copyright (c) 2019 teamCMS
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy
-- of this software and associated documentation files (the "Software"), to deal
-- in the Software without restriction, including without limitation the rights
-- to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the Software is
-- furnished to do so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in all
-- copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
-- SOFTWARE.

INSERT INTO faculty (id, name) VALUES (2, 'Gina Cody School of Engineering and Computer Science');

INSERT INTO department (id, name, faculty_id) VALUES (4, 'Computer Science & Software Engineering', 2);

INSERT INTO user (id, email, first_name, last_name, password, user_type, department_id) VALUES
(1, 'boris@soen.com', 'Boris', 'Fitzgerald','123', 'admin', 4);

INSERT INTO program (id, description, is_active, name, department_id) VALUES  (1, 'description', 1, 'Software Engineering', 4);

INSERT INTO degree (id, credits, level, name, program_id) VALUES (1, 120, 1, 'Bachelor of Software Engineering (BEng)', 1);

INSERT INTO course (id, credits, description, is_active, lab_hours, lecture_hours, level, name, note, number, outline, title, tutorial_hours, program_id) VALUES (1, 4.00, 'Processor structure...',
1, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 228, NULL, 'System Hardware', 2, 1);

INSERT INTO request_package (id, user_id, department_id) VALUES (1, 1, 4);

INSERT INTO approval_pipeline (id, apc, department_council, department_curriculum_committee, faculty_council, senate, undergraduate_studies_committee) VALUES (1, 4, 0, 1, 2, 5, 3);

INSERT INTO approval_pipeline_request_package (pipeline_id, package_id, position) VALUES (1, 1, 'Department Curriculum Committee');