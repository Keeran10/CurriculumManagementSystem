ALTER DATABASE cms
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE cms;
-- id, email, firstname, lastname, password, user_type
INSERT IGNORE INTO user VALUES (1, 'boris@soen.com', 'Boris', 'Fitzgerald','123', 'admin');
INSERT IGNORE INTO user VALUES (2, 'felix@soen.com', 'Felix', 'Rosinante','123', 'admin');

-- id, name
INSERT IGNORE INTO faculty VALUES (1, 'Faculty of Arts and Science');
INSERT IGNORE INTO faculty VALUES (2, 'Gina Cody School of Engineering and Computer Science');
INSERT IGNORE INTO faculty VALUES (3, 'Faculty of Fine Arts');
INSERT IGNORE INTO faculty VALUES (4, 'John Molson School of Business');
INSERT IGNORE INTO faculty VALUES (5, 'School of Graduate Studies');

-- id, name, faculty_id
INSERT IGNORE INTO department VALUES (1, 'Building, Civil and Environmental Engineering', 2);
INSERT IGNORE INTO department VALUES (2, 'Centre for Engineering in Society', 2);
INSERT IGNORE INTO department VALUES (3, 'Chemical and Materials Engineering', 2);
INSERT IGNORE INTO department VALUES (4, 'Computer Science & Software Engineering', 2);
INSERT IGNORE INTO department VALUES (5, 'Concordia Institute for Information Systems Engineering (CIISE)', 2);
INSERT IGNORE INTO department VALUES (6, 'Concordia Institute for Aerospace Design and Innovation', 2);
INSERT IGNORE INTO department VALUES (7, 'Electrical and Computer Engineering', 2);
INSERT IGNORE INTO department VALUES (8, 'Mechanical, Industrial and Aerospace Engineering', 2);

-- id, desc, active, name, department_id
INSERT IGNORE INTO program VALUES (1, 'description', 1, 'Software Engineering', 4);
INSERT IGNORE INTO program VALUES (2, 'description', 1, 'Computer Science', 4);

-- id, credits, level, name, program_id
INSERT IGNORE INTO degree VALUES (1, 120, 1, 'Bachelor of Software Engineering (BEng)', 1);
INSERT IGNORE INTO degree VALUES (2, 45, 2, 'Master of Software Engineering (MEng)', 1);
INSERT IGNORE INTO degree VALUES (3, 45, 2, 'Master of Software Engineering (MASc)', 1);
INSERT IGNORE INTO degree VALUES (4, 90, 3, 'Doctor of Philosophy (PhD in Software Engineering)', 1);

-- id, credits, description, is_active, lab_hours, lecture_hours, level, course_name, course_number, outline, title, tutorial_hours, program_id
INSERT IGNORE INTO course VALUES (1, 4.00, 'Processor structure, Data and Instructions, Instruction Set Processor (ISP) level view of computer hardware, assembly language level use. Memory systems — RAM and disks, hierarchy of memories. I/O organization, I/O devices and their diversity, their interconnection to CPU and Memory. Communication between computers at the physical level. Networks and computers.', 1, 2, 3, 1, 'SOEN', 228, NULL, 'System Hardware', 2, 1);
INSERT IGNORE INTO course VALUES (2, 3.00, 'Internet architecture and protocols. Web applications through clients and servers. Markup languages. Client‑side programming using scripting languages. Static website contents and dynamic page generation through server‑side programming. Preserving state (client‑side) in web applications.', 1, 0, 3, 1, 'SOEN', 287, NULL, 'Web Programming', 2, 1);
INSERT IGNORE INTO course VALUES (3, 3.00, 'Protocol layers and security protocols. Intranets and extranets. Mobile computing. Electronic commerce. Security architectures in open‑network environments. Cryptographic security protocols. Threats, attacks, and vulnerabilities. Security services: confidentiality; authentication; integrity; access control; non‑repudiation; and availability. Security mechanisms: encryption; data‑integrity mechanisms; digital signatures; keyed hashes; access‑control mechanisms; challenge‑response authentication; traffic padding; routing control; and notarization. Key‑management principles. Distributed and embedded firewalls. Security zones.', 1, 0, 3, 1, 'SOEN', 321, NULL, 'Information Systems Security', 1, 1);
INSERT IGNORE INTO course VALUES (4, 3.00, 'Assertions. Static and dynamic checking. Method specification using preconditions and postconditions. Strengthening and weakening. Design by contract. Hoare logic. Invariants. Class specification using invariants. Software tools for assertion checking and verification. Reliable software development.', 1, 0, 3, 1, 'SOEN', 331, NULL, 'Introduction to Formal Methods for Software Engineering', 2, 1);
INSERT IGNORE INTO course VALUES (5, 3.00, 'previously ortsiles of software engineering. Introduction to software process models. Activities in each phase, including review activities. Working in teams: organization; stages of formation; roles; conflict resolution. Notations used in software documentation. How to review, revise, and improve software documentation.', 1, 0, 3, 1, 'SOEN', 341, NULL, 'Software Process', 1, 1);
INSERT IGNORE INTO course VALUES (6, 3.00, 'Requirements engineering. Functional and non‑functional requirements. Traceability. Test generation. Formal and informal specifications. Formal specification languages. Reasoning with specifications. Correctness issues. Verification.', 1, 0, 3, 1, 'SOEN', 342, NULL, 'Software Requirements and Specifications', 1, 1);
INSERT IGNORE INTO course VALUES (7, 3.00, 'From requirements to design to implementation. Planned vs. evolutionary design and refactoring. Model‑driven design and Unified Modelling Language (UML). Structural and behavioural design descriptions and specifications. General and domain‑specific design principles, patterns and idioms. Object‑oriented design concepts such as interfaces vs. abstract types, polymorphism, generics, and delegation vs. subclassing. Introduction to software architecture (styles and view models). Design quality. Design rationale. Design methodologies (e.g. based on responsibility assignment). Test‑driven development.', 1, 0, 3, 1, 'SOEN', 343, NULL, 'Software Architecture and Design I', 1, 1);
INSERT IGNORE INTO course VALUES (8, 3.00, 'Architectural activities, roles, and deliverables. Architectural view models. Architectural styles (including client‑server, layered, pipes‑and‑filters, event‑based, process control) and frameworks. Architectural analysis and the interplay with requirements elicitation. Notations for expressing architectural designs, structural and behavioural specifications. From architectural design to detailed design. Domain specific architectures and design patterns. Evaluation and performance estimation of designs. Advanced object‑oriented design patterns and idioms.', 1, 0, 3, 1, 'SOEN', 344, NULL, 'Software Architecture and Design II', 1, 1);
INSERT IGNORE INTO course VALUES (9, 3.00, 'Testing strategies. Specification‑based vs. code‑based, black‑box vs. white‑box, functional vs. structural testing; unit, integration, system, acceptance, and regression testing. Verification vs. validation. Test planning, design and artifacts. Introduction to software reliability and quality assurance. Formal verification methods, oracles; static and dynamic program verification.', 1, 0, 3, 1, 'SOEN', 345, NULL, 'Software Testing, Verification and Quality Assurance', 1, 1);
INSERT IGNORE INTO course VALUES (10, 3.00, 'The human side: I/O; memory; and information processing. Interaction: mental models; human error; interaction frameworks and paradigms. Direct manipulation. User interface design: principles; standards; and guidelines. User‑centred design: standards and design rationale; heuristic evaluation; iterative design; and prototyping. Task‑centred design. Rationalized design: usability engineering; dialogue notations; user models; diagrammatic notations; and textual notations. Evaluation: with the user; without the user; quantitative; and qualitative. Implementation support. Help and documentation.', 1, 0, 3, 1, 'SOEN', 357, NULL, 'User Interface Design', 1, 1);
INSERT IGNORE INTO course VALUES (11, 3.00, 'Organization of large software development. Roles of team members, leaders, managers, stakeholders, and users. Tools for monitoring and controlling a schedule. Financial, organizational, human, and computational resources allocation and control. Project and quality reviews, inspections, and walkthroughs. Risk management. Communication and collaboration. Cause and effects of project failure. Project management via the Internet. Quality assurance and control.', 1, 0, 3, 1, 'SOEN', 384, NULL, 'Management, Measurement and Quality Control', 1, 1);
INSERT IGNORE INTO course VALUES (12, 3.00, 'Mathematical modelling of dynamical systems; block diagrams; feedback; open and closed loops. Linear differential equations; time domain analysis; free, forced, and total response; steady state and transient response. Laplace transform and inverse transform; second order systems. Transfer functions and stability. Control system design: PID and root locus techniques. Computer simulation of control systems. Applications.', 1, 0, 3, 1, 'SOEN', 385, NULL, 'Control Systems and Applications', 1, 1);
INSERT IGNORE INTO course VALUES (13, 3.50, 'Students work in teams to design and implement a software project from requirements provided by the coordinator. Each team will demonstrate the software and prepare adequate documentation for it. In addition, each student will write an individual report.', 1, 3, 2, 1, 'SOEN', 390, NULL, 'Software Engineering Team Design Project', 1, 1);
INSERT IGNORE INTO course VALUES (14, 4.00, 'Students work in teams of at least four members to construct a significant software application. The class meets at regular intervals. Team members will give a presentation of their contribution to the project.', 1, 2, 1, 1, 'SOEN', 490, NULL, 'Capstone Software Engineering Design Project', 0, 1);
INSERT IGNORE INTO course VALUES (15, 3.50, 'From requirements to design to implementation. Planned vs. evolutionary design and refactoring. Model‑driven design and Unified Modelling Language (UML). Structural and behavioural design descriptions and specifications. General and domain‑specific design principles, patterns and idioms. Object‑oriented design concepts such as interfaces vs. abstract types, polymorphism, generics, and delegation vs. subclassing. Introduction to software architecture (styles and view models). Design quality. Design rationale. Design methodologies (e.g. based on responsibility assignment). Test‑driven development.', 0, 0, 3, 2, 'SOEN', 343, NULL, 'Software Architecture and Design I extreme', 1, 1);

-- id, active, req_course_id, type (1=pre, 2=con, 3=anti, 4=equal), course_id
INSERT IGNORE INTO requisite VALUES (1, 1, 5, 1, 6);-- soen 342 needs 341
INSERT IGNORE INTO requisite VALUES (2, 1, 5, 1, 7);-- soen 343 needs 341
INSERT IGNORE INTO requisite VALUES (3, 1, 6, 2, 7);-- soen 343 needs 342
INSERT IGNORE INTO requisite VALUES (4, 1, 7, 1, 8);-- soen 344 needs 343
INSERT IGNORE INTO requisite VALUES (5, 1, 7, 2, 9);-- soen 345 needs 343
INSERT IGNORE INTO requisite VALUES (6, 1, 5, 1, 10);-- soen 357 needs 341
INSERT IGNORE INTO requisite VALUES (7, 0, 5, 1, 15);-- soen 343 edited needs 341
INSERT IGNORE INTO requisite VALUES (8, 0, 4, 2, 15);-- soen 343 edited needs 331
INSERT IGNORE INTO requisite VALUES (9, 0, 15, 2, 10);-- soen 357 edited needs 343 edited

-- degree_id, course_id
INSERT IGNORE INTO required_course VALUES (1, 1);
INSERT IGNORE INTO required_course VALUES (1, 2);
INSERT IGNORE INTO required_course VALUES (1, 3);
INSERT IGNORE INTO required_course VALUES (1, 4);
INSERT IGNORE INTO required_course VALUES (1, 5);
INSERT IGNORE INTO required_course VALUES (1, 6);
INSERT IGNORE INTO required_course VALUES (1, 7);
INSERT IGNORE INTO required_course VALUES (1, 8);
INSERT IGNORE INTO required_course VALUES (1, 9);
INSERT IGNORE INTO required_course VALUES (1, 10);
INSERT IGNORE INTO required_course VALUES (1, 11);
INSERT IGNORE INTO required_course VALUES (1, 12);
INSERT IGNORE INTO required_course VALUES (1, 13);
INSERT IGNORE INTO required_course VALUES (1, 14);

-- id, body, section_id, section_title, section_type, department_id, faculty_id
INSERT IGNORE INTO calendar VALUES(1, 'Both major and minor programs in Management Information Systems can be found in the John Molson School of Business Section of the Undergraduate Calendar, §61. The Faculty of Fine Arts and the Department of Computer Science and Software Engineering offer complementary major programs. Students who take the Computer Applications Option (see §71.70.2 above) can also take the Major in Computation Arts and Computer Science (see §71.80, and the Fine Arts Section, §81) or the Joint Major in Mathematics and Statistics and Computer Applications (see §71.85, and the Mathematics and Statistics Section, §31.200).', '71.70.6', 'Programs Related to Computer Science', 'general',  2, 4);
INSERT IGNORE INTO calendar VALUES(2, 'Students employed full‑time in a computer science position during their non‑study terms may have this Industrial Experience listed on their official transcript and student record, provided they successfully complete the Reflective Learning course associated with this work term. Students may only register for these courses with the permission of the Faculty. The Industrial Experience terms COMP 107 and 207 carry no credit value and are used to indicate that the student is on an Industrial Experience term. The COMP 108 and 208 Industrial Experience Reflective Learning courses are worth three credits and are marked on a pass/fail basis. They are above and beyond the credit requirements of the student’s program and are not transferable nor are they included in the full‑ or part‑time assessment status. Students studying for a co‑op work term or CIADI term should not register for these Industrial Experience and Reflective Learning courses.', '71.70.7', 'Industrial Experience and Reflective Learning Courses', 'general', 2, 4);

-- id, user id
INSERT IGNORE INTO package VALUES (1, 1); -- creating a package for user 1

-- id, original id, request type, target id, target type, timestamp, package id, user id
INSERT IGNORE INTO request VALUES (1, 7, 2, 15, 2, NULL, 1, 1); -- updating request for the course soen 343
INSERT IGNORE INTO request VALUES (2, 7, 3, 15, 2, NULL, 1, 1); -- removing request for the course soen 343
INSERT IGNORE INTO request VALUES (3, 7, 1, 15, 2, NULL, 1, 1); -- creating request for the course soen 343

-- id, apc, dcc, fcc, gdc, senate, ugdc
INSERT IGNORE INTO approval_pipeline VALUES (1, 4, 1, 2, 0, 5, 3); -- dcc -> fcc -> ugdc -> apc -> senate