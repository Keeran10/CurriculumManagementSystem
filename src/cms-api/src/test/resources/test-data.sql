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

INSERT INTO course (id, credits, description, is_active, lab_hours, lecture_hours, level, name, note, number, title, tutorial_hours, program_id)
VALUES (1, 4.00, 'Processor structure...',
1, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 228, 'System Hardware', 2, 1);

INSERT INTO course (id, credits, description, is_active, lab_hours, lecture_hours, level, name, note, number, title, tutorial_hours, program_id)
VALUES (2, 4.00, 'Brocessor structure...',
0, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 228, 'System Hardware', 2, 1);

INSERT INTO course (id, credits, description, is_active, lab_hours, lecture_hours, level, name, note, number, title, tutorial_hours, program_id)
VALUES (3, 4.00, 'Crocessor structure...',
1, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 228, 'System Hardware', 2, 1);

INSERT INTO course (id, credits, description, is_active, lab_hours, lecture_hours, level, name, note, number, title, tutorial_hours, program_id)
VALUES (4, 4.00, 'Drocessor structure...',
1, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 228, 'System Hardware', 2, 1);

INSERT INTO course (id, credits, description, is_active, lab_hours, lecture_hours, level, name, note, number, title, tutorial_hours, program_id)
VALUES (5, 4.00, 'Drocessor structure...',
1, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 344, 'Software Architecture', 2, 1);

INSERT INTO course (id, credits, description, is_active, lab_hours, lecture_hours, level, name, note, number, title, tutorial_hours, program_id)
VALUES (6, 4.00, 'Drocessor structure...',
0, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 344, 'Software Architecture', 2, 1);


INSERT INTO degree_requirement (id, core, course_id, degree_id) VALUES (1, 'Software Engineering Core', 1, 1);
INSERT INTO degree_requirement (id, core, course_id, degree_id) VALUES (2, 'Software Engineering Core', 2, 1);
INSERT INTO degree_requirement (id, core, course_id, degree_id) VALUES (3, 'Software Engineering Core', 3, 1);
INSERT INTO degree_requirement (id, core, course_id, degree_id) VALUES (4, 'Software Engineering Core', 4, 1);
INSERT INTO degree_requirement (id, core, course_id, degree_id) VALUES (5, 'Software Engineering Core', 5, 1);
INSERT INTO degree_requirement (id, core, course_id, degree_id) VALUES (6, 'Software Engineering Core', 6, 1);

INSERT INTO request_package (id, user_id, department_id) VALUES (1, 1, 4);


INSERT INTO request (id, origin_id, original_id, request_type, target_id, target_type, title, package_id, user_id) VALUES (1, 1, 1, 2, 2, 2, 'SOEN344_update', 1, 1);
INSERT INTO request (id, origin_id, original_id, request_type, target_id, target_type, title, package_id, user_id) VALUES (2, 0, 0, 1, 3, 2, 'SOEN344_update', 1, 1);
INSERT INTO request (id, origin_id, original_id, request_type, target_id, target_type, title, package_id, user_id) VALUES (3, 4, 4, 3, 0, 2, 'SOEN344_update', 1, 1);
INSERT INTO request (id, origin_id, original_id, request_type, target_id, target_type, title, package_id, user_id) VALUES (4, 5, 5, 2, 6, 2, 'SOEN344_update', 1, 1);

INSERT INTO approval_pipeline (id, apc, department_council, department_curriculum_committee, faculty_council, senate, undergraduate_studies_committee) VALUES (1, 4, 0, 1, 2, 5, 3);

INSERT INTO approval_pipeline_request_package (pipeline_id, package_id, position) VALUES (1, 1, 'Department Curriculum Committee');

-- id, first_core, first_paragraph, is_active, second_core, section_id, section_title
INSERT INTO section70719 (id, first_core, first_paragraph, is_active, second_core, section_id, section_title) VALUES
(1, 'Engineering Core', 'Students registered in the Software Engineering program must complete a minimum of 120 credits.',
1, 'Software Engineering Core', '70.71.9', 'Degree Requirements for the BEng in Software Engineering');