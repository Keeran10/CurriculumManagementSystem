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


ALTER DATABASE cms
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE cms;

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


-- id, email, firstname, lastname, password, user_type, department
INSERT IGNORE INTO user VALUES (1, 'boris@soen.com', 'Boris', 'Fitzgerald','123', 'Professor', 4);
INSERT IGNORE INTO user VALUES (2, 'felix@soen.com', 'Felix', 'Rosinante','123', 'Department Curriculum Committee', 8);
INSERT IGNORE INTO user VALUES (3, 'doris@soen.com', 'Doris', 'Fitzgerald','123', 'Faculty Council', 4);
INSERT IGNORE INTO user VALUES (4, 'chad@soen.com', 'Chad', 'Lee','123', 'APC', 8);
INSERT IGNORE INTO user VALUES (5, 'rhonda@soen.com', 'Rhonda', 'Philips','123', 'Department Council', 8);
INSERT IGNORE INTO user VALUES (6, 'billy@soen.com', 'Billy', 'Bob','123', 'UGSC', 8);
INSERT IGNORE INTO user VALUES (7, 'olga@soen.com', 'Olga', 'Hashtag','123', 'Senate', 8);
INSERT IGNORE INTO user VALUES (8, 'admin@soen.com', 'Admin', 'Badminton','123', 'admin', 4);
INSERT IGNORE INTO user VALUES (9, 'mech@soen.com', 'Mech', 'Badminton','123', 'Professor', 8);

-- id, desc, active, name, department_id
INSERT IGNORE INTO program VALUES (1, "The Software Engineering program is built on the fundamentals of computer science, an engineering core, and a discipline core in Software Engineering to cover the engineering approach to all phases of the software process and related topics. The curriculum builds on the traditional computer science core topics of computer mathematics, theory, programming methodology, and mainstream applications to provide the computing theory and practice which underlie the discipline. The engineering core covers basic science, professional topics, and introduces the engineering approach to problem solving. The program core in Software Engineering includes advanced programming techniques, software specification, design, architecture, as well as metrics, security, project management, and quality control. The options cover a broad range of advanced topics, from formal methods to distributed systems.",
1, 'Software Engineering', 4);
INSERT IGNORE INTO program VALUES (2, 'The Computer Science program emphasizes fundamentals and techniques that remain relevant and useful for many years after graduation. The program consists of a combination of core courses in computer science, elective courses in computer science and mathematics, and some free electives. The Computer Science Core provides a basic and broad study of theory, mathematical basics, programming methodology, computer architecture, data structures, operating systems, and software engineering. The option courses are designed to provide an integrated yet specialized training in particular application areas of the discipline. Students may choose either the General Program or one of eight options. Each option involves the study of selected advanced elective courses in computer science to provide further depth in computer science and the particular application area. The General Program and each option constitute a 90‑credit program that consists of courses in the following groups: Computer Science Core, Complementary Core, Option‑Specific Courses, Computer Science Electives, Mathematics Electives, and General Electives.',
1, 'Computer Science', 4);
INSERT IGNORE INTO program VALUES (3, 'The program in Mechanical Engineering consists of the Engineering Core, the Mechanical Engineering Core, and elective credits as shown below. The minimum length of the program is 120 credits.',
1, 'Mechanical Engineering', 8);
INSERT IGNORE INTO program VALUES (4, 'The program in Industrial Engineering consists of the Engineering Core, the Industrial Engineering Core, and elective credits as shown below. Students must select one course from the list of Basic and Natural Science courses as part of the Industrial Engineering Core courses. The minimum length of the program is 120 credits.',
1, 'Industrial Engineering', 8);
INSERT IGNORE INTO program VALUES (5, '', 1, 'Aerospace Engineering', 8);


-- id, credits, level, name, program_id
INSERT IGNORE INTO degree VALUES (1, 120, 1, 'Bachelor of Software Engineering (BEng)', 1);
INSERT IGNORE INTO degree VALUES (2, 45, 2, 'Master of Software Engineering (MEng)', 1);
INSERT IGNORE INTO degree VALUES (3, 45, 2, 'Master of Software Engineering (MASc)', 1);
INSERT IGNORE INTO degree VALUES (4, 90, 3, 'Doctor of Philosophy (PhD in Software Engineering)', 1);
INSERT IGNORE INTO degree VALUES (5, 120, 1, 'Mechanical Engineering (BEng)', 3);
INSERT IGNORE INTO degree VALUES (6, 90, 1, 'Bachelor of Computer Science (BCompSc)', 2);
INSERT IGNORE INTO degree VALUES (7, 120, 1, 'Industrial Engineering (BEng)', 4);
INSERT IGNORE INTO degree VALUES (8, 120, 1, 'Aerospace Engineering (BEng)', 5);

-- id, credits, description,
-- is_active, lab, lect, level, course_name, course_number, outline, title, program_id
INSERT IGNORE INTO course VALUES (1, 4.00, 'Processor structure, Data and Instructions, Instruction Set Processor (ISP) level view of computer hardware, assembly language level use. Memory systems — RAM and disks, hierarchy of memories. I/O organization, I/O devices and their diversity, their interconnection to CPU and Memory. Communication between computers at the physical level. Networks and computers. Lectures: three hours per week. Tutorial: two hours per week. Laboratory: two hours per week.',
1, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 228, 'System Hardware', 2, 1);
INSERT IGNORE INTO course VALUES (2, 3.00, 'Internet architecture and protocols. Web applications through clients and servers. Markup languages. Client‑side programming using scripting languages. Static website contents and dynamic page generation through server‑side programming. Preserving state (client‑side) in web applications. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'SOEN', "",  287, 'Web Programming', 2, 1);
INSERT IGNORE INTO course VALUES (3, 3.00, 'Protocol layers and security protocols. Intranets and extranets. Mobile computing. Electronic commerce. Security architectures in open‑network environments. Cryptographic security protocols. Threats, attacks, and vulnerabilities. Security services: confidentiality; authentication; integrity; access control; non‑repudiation; and availability. Security mechanisms: encryption; data‑integrity mechanisms; digital signatures; keyed hashes; access‑control mechanisms; challenge‑response authentication; traffic padding; routing control; and notarization. Key‑management principles. Distributed and embedded firewalls. Security zones. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', "", 321, 'Information Systems Security', 1, 1);
INSERT IGNORE INTO course VALUES (4, 3.00, 'Assertions. Static and dynamic checking. Method specification using preconditions and postconditions. Strengthening and weakening. Design by contract. Hoare logic. Invariants. Class specification using invariants. Software tools for assertion checking and verification. Reliable software development. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'SOEN', "",  331, 'Introduction to Formal Methods for Software Engineering', 2, 1);
INSERT IGNORE INTO course VALUES (5, 3.00, 'previously ortsiles of software engineering. Introduction to software process models. Activities in each phase, including review activities. Working in teams: organization; stages of formation; roles; conflict resolution. Notations used in software documentation. How to review, revise, and improve software documentation. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 354 may not take this course for credit.',  341, 'Software Process', 1, 1);
INSERT IGNORE INTO course VALUES (6, 3.00, 'Requirements engineering. Functional and non‑functional requirements. Traceability. Test generation. Formal and informal specifications. Formal specification languages. Reasoning with specifications. Correctness issues. Verification. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', "",  342, 'Software Requirements and Specifications', 1, 1);
INSERT IGNORE INTO course VALUES (7, 3.00, 'From requirements to design to implementation. Planned vs. evolutionary design and refactoring. Model‑driven design and Unified Modelling Language (UML). Structural and behavioural design descriptions and specifications. General and domain‑specific design principles, patterns and idioms. Object‑oriented design concepts such as interfaces vs. abstract types, polymorphism, generics, and delegation vs. subclassing. Introduction to software architecture (styles and view models). Design quality. Design rationale. Design methodologies (e.g. based on responsibility assignment). Test‑driven development. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', "", 343, 'Software Architecture and Design I', 1, 1);
INSERT IGNORE INTO course VALUES (8, 3.00, 'Architectural activities, roles, and deliverables. Architectural view models. Architectural styles (including client‑server, layered, pipes‑and‑filters, event‑based, process control) and frameworks. Architectural analysis and the interplay with requirements elicitation. Notations for expressing architectural designs, structural and behavioural specifications. From architectural design to detailed design. Domain specific architectures and design patterns. Evaluation and performance estimation of designs. Advanced object‑oriented design patterns and idioms. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', "", 344, 'Software Architecture and Design II', 1, 1);
INSERT IGNORE INTO course VALUES (9, 3.00, "Testing strategies. Specification‑based vs. code‑based, black‑box vs. white‑box, functional vs. structural testing; unit, integration, system, acceptance, and regression testing. Verification vs. validation. Test planning, design and artifacts. Introduction to software reliability and quality assurance. Formal verification methods, oracles; static and dynamic program verification. Lectures: three hours per week. Tutorial: one hour per week.",
1, 0, 3, 1, 'SOEN', "",  345, 'Software Testing, Verification and Quality Assurance', 1, 1);
INSERT IGNORE INTO course VALUES (10, 3.00, 'The human side: I/O; memory; and information processing. Interaction: mental models; human error; interaction frameworks and paradigms. Direct manipulation. User interface design: principles; standards; and guidelines. User‑centred design: standards and design rationale; heuristic evaluation; iterative design; and prototyping. Task‑centred design. Rationalized design: usability engineering; dialogue notations; user models; diagrammatic notations; and textual notations. Evaluation: with the user; without the user; quantitative; and qualitative. Implementation support. Help and documentation. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', "", 357, 'User Interface Design', 1, 1);
INSERT IGNORE INTO course VALUES (11, 3.00, 'Organization of large software development. Roles of team members, leaders, managers, stakeholders, and users. Tools for monitoring and controlling a schedule. Financial, organizational, human, and computational resources allocation and control. Project and quality reviews, inspections, and walkthroughs. Risk management. Communication and collaboration. Cause and effects of project failure. Project management via the Internet. Quality assurance and control. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', "",  384, 'Management, Measurement and Quality Control', 1, 1);
INSERT IGNORE INTO course VALUES (12, 3.00, 'Mathematical modelling of dynamical systems; block diagrams; feedback; open and closed loops. Linear differential equations; time domain analysis; free, forced, and total response; steady state and transient response. Laplace transform and inverse transform; second order systems. Transfer functions and stability. Control system design: PID and root locus techniques. Computer simulation of control systems. Applications. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', "", 385, 'Control Systems and Applications', 1, 1);
INSERT IGNORE INTO course VALUES (13, 3.50, 'Students work in teams to design and implement a software project from requirements provided by the coordinator. Each team will demonstrate the software and prepare adequate documentation for it. In addition, each student will write an individual report. Lectures: two hours per week. Tutorial: one hour per week. Laboratory: three hours per week.',
1, 3, 2, 1, 'SOEN', "",  390, 'Software Engineering Team Design Project', 1, 1);
-- SOEN 490 needs 75 credits in the program as prerequisite. Not handled yet.
INSERT IGNORE INTO course VALUES (14, 4.00, 'Students work in teams of at least four members to construct a significant software application. The class meets at regular intervals. Team members will give a presentation of their contribution to the project. Lectures: one hour per week. Laboratory: two hours per week. Two terms.',
1, 2, 1, 1, 'SOEN', "",  490, 'Capstone Software Engineering Design Project', 0, 1);
INSERT IGNORE INTO course VALUES (15, 4.00, 'Architectural activities, roles, and deliverables. Architectural view models. Architectural styles (including client‑server, layered, pipes‑and‑filters, event‑based, process control) and frameworks. Architectural analysis and the interplay with requirements elicitation. Notations for expressing architectural designs, structural and behavioural specifications. From architectural design to detailed design. Domain specific architectures and design patterns. Evaluation and performance estimation of designs. Advanced object‑oriented design patterns and idioms. Lectures: three hours per week. Tutorial: one hour per week.',
0, 0, 3, 2, 'SOEN', "", 344, 'Advanced Software Architecture and Design', 1, 1);

INSERT IGNORE INTO course VALUES (16, 3.00, 'Resultant of force systems; equilibrium of particles and rigid bodies; distributed forces; statically determinate systems; trusses; friction; moments of inertia; virtual work. Shear and bending moment diagrams. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'ENGR', "", 242, 'Statics', 2, 3);
INSERT IGNORE INTO course VALUES (17, 3.00, 'Kinematics of a particle and rigid body; forces and accelerations; work and energy; impulse and momentum; dynamics of a system of particles and rigid bodies, introduction to vibrations. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'ENGR', "", 243, 'Dynamics', 2, 3);
INSERT IGNORE INTO course VALUES (18, 3.75, 'Mechanical behaviour of materials; stress; strain; shear and bending moment diagrams; introduction to inelastic action. Analysis and design of structural and machine elements subjected to axial, torsional, and flexural loadings. Combined stresses and stress transformation. Deflections. Introduction to elastic stability. Lectures: three hours per week. Tutorial: two hours per week. Laboratory: three hours per week, alternate weeks.',
1, 3, 3, 1, 'ENGR', "", 244, 'Mechanics of Materials', 2, 3); -- lab hours is 3 hours alternative weeks !
INSERT IGNORE INTO course VALUES (19, 3.00, 'Basic principles of thermodynamics and their application to various systems composed of pure substances and their homogeneous non-reactive mixtures. Simple power production and utilization cycles. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'ENGR', "", 251, 'Thermodynamics I', 2, 3);
INSERT IGNORE INTO course VALUES (20, 3.00, 'Elements of complex variables. The Laplace transform: Laplace transforms and their properties, solution of linear differential equations with constant coefficients. Further theorems and their applications. The Fourier transform: orthogonal functions, expan­sion of a function in orthogonal functions, the Fourier series, the Fourier integral, the Fourier transform, the convolution theorem. Partial differential equations: physical foundations of partial differential equations, introduction to boundary value problems. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'ENGR', "", 311, 'Transform Calculus and Partial Differential Equations', 2, 3);
INSERT IGNORE INTO course VALUES (21, 3.00, 'Basic concepts and principles of fluid mechanics. Classification of fluid flow. Hydrostatic forces on plane and curved surfaces, buoyancy and stability, fluids in rigid body motion. Mass, momentum, and energy conservation integral equations. Bernoulli equation. Basic concepts of pipe and duct flow. Introduction to Navier-Stokes equations. Similarity and model studies. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'ENGR', "", 361, 'Fluid Mechanics I', 1, 3);

INSERT IGNORE INTO course VALUES (22, 3.50, 'Introduction to graphic language and design — means and techniques. The third and the first angle projections. Orthographic projection of points, lines, planes and solids. Principal and auxiliary views. Views in a given direction. Sectional views. Intersection of lines, planes and solids. Development of surfaces. Drafting practices. Dimensioning, fits and tolerancing. Computer-aided drawing and solid modelling. Working drawings — detail and assembly drawing. Design practice. Machine elements representation. Lectures: three hours per week. Tutorial: two hours per week — includes learning of a CAD software. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', "", 211, 'Mechanical Engineering Drawing', 2, 3);
INSERT IGNORE INTO course VALUES (23, 3.50, 'Writing programs using assignment and sequences. Variables and types. Operators and expressions. Conditional and repetitive statements. Input and output. File access. Functions. Program structure and organization. Pointers and dynamic memory allocation. Introduction to classes and objects. Mechanical and industrial engineering applications. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: one hour per week.',
1, 1, 3, 1, 'MECH', "", 215, 'Programming for Mechanical and Industrial Engineers', 1, 3);
INSERT IGNORE INTO course VALUES (24, 3.00, 'Relationships between properties and internal structure, atomic bonding; molecular, crystalline and amorphous structures, crystalline imperfections and mechanisms of structural change. Microstructures and their development from phase diagrams. Structures and mechanical properties of polymers and ceramics. Thermal, optical, and magnetic properties of materials. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'MECH', "", 221, 'Materials Science', 1, 3);
INSERT IGNORE INTO course VALUES (25, 3.75, 'Fundamentals of manufacturing processes and their limitations, metrology, machine shop practice, safety and health considerations, forming, conventional machining and casting processes, welding and joining, plastic production, and non-conventional machining techniques. Sustainable technologies. Laboratory includes instruction and practice on conventional machine tools and a manufacturing project. Lectures: three hours per week. Tutorial: two hours per week, including industrial visits and field trips to local industries. Laboratory: three hours per week, alternate weeks.',
1, 3, 3, 1, 'MECH', "", 311, 'Manufacturing Processes', 2, 3);
INSERT IGNORE INTO course VALUES (26, 3.50, 'Introduction to engi­neering design and design process. Problem definition, solution formulation, model development and collaboration aspects of design process.The use of drawings and other graphical methods in the process of engineering design. Industrial standards and specifications, design of fits, linear and geometrical tolerances. Design projects based on design philosophies will involve design and selection of many standard machine com­ponents like mechanical drives, cams, clutches, couplings, brakes, seals, fasteners, springs, and bearings. Drawing representation of standard components. Design projects are an integral part of this course. Lectures: three hours per week. Tutorial: two hours per week. Laboratory: 12 hours total',
1, 12, 3, 1, 'MECH', "", 313, 'Machine Drawing and Design', 2, 3);
INSERT IGNORE INTO course VALUES (27, 3.50, 'The service capabilities of alloys and their relationship to microstructure as produced by thermal and mechanical treatments; tensile and torsion tests; elements of dislocation theory; strengthening mechanisms; composite materials. Modes of failure of materials; fracture, fatigue, wear, creep, corrosion, radiation damage. Failure analysis. Material codes; material selection for design. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', "", 321, 'Properties and Failure of Materials', 1, 3);
INSERT IGNORE INTO course VALUES (28, 3.50, 'Introduction to mechanisms; position and displacement; velocity; acceleration; synthesis of linkage; robotics; static force analysis; dynamic force analysis; forward kinematics and inverse kinematics; introduction to gear analysis and gear box design; kinematic analysis of spatial mechanisms. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', "", 343, 'Theory of Machines', 1, 3);
INSERT IGNORE INTO course VALUES (29, 3.00, 'Introduction to machine design; static failure theories; failure of ductile vs. brittle materials under static loading. Fatigue failure theories; fatigue loads; notches and stress concentrations; residual stresses; designing for high cycle fatigue. Design of shafts, keys and couplings. Design of spur gears. Spring design. Design of screws and fasteners. Design of bearings. Case studies. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'MECH', 'NOTE: Students who have received credit for MECH 441 may not take this course for credit.', 344, 'Machine Element Design', 2, 3);
INSERT IGNORE INTO course VALUES (30, 3.50, 'Brief review of ideal gas processes. Semi-perfect gases and the gas tables. Mixtures of gases, gases and vapours, air conditioning processes. Combustion and combustion equilibrium. Applications of thermo­dynamics to power production and utilization systems: study of basic and advanced cycles for gas compression, internal combustion engines, power from steam, gas turbine cycles, and refrigeration. Real gases. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', "", 351, 'Thermodynamics II', 1, 3);
INSERT IGNORE INTO course VALUES (31, 3.50, 'Analytical and numerical methods for steady-state and transient heat conduction. Empirical and practical relations for forced- and free-convection heat transfer. Radiation heat exchange between black bodies, and between non-black bodies. Gas radiation. Solar radiation. Effect of radiation on temperature measurement. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', "", 352, 'Heat Transfer I', 1, 3);
INSERT IGNORE INTO course VALUES (32, 3.50, 'Differential analysis of fluid flows, vorticity, stream function, stresses, and strains. Navier-Stokes equations and solutions for parallel flows. Euler’s equations, irrotational and potential flows, plane potential flows. Viscous flows in pipes, laminar and turbulent flows, major and minor losses. Flow over immersed bodies, boundary layers, separation and thickness. Drag, lift and applications. Introduction to compressible flows, speed of sound, Mach cone, and some characteristics of supersonic flows. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', "", 361, 'Fluid Mechanics II', 1, 3);
INSERT IGNORE INTO course VALUES (33, 3.50, 'Dependent sources, voltage and current dividers, voltage and current sources, superposition, Thevenin and Norton equivalent sources, linear and nonlinear circuit analysis. Semiconductors and diodes. Bipolar Junction Transistors (BJT), Field Effect Transistors (FET); amplifiers and switches. Operational amplifiers; circuits and frequency response. Digital logic components and circuits. Digital systems. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', 'NOTE: Students who have received credit for MECH 470 may not take this course for credit. NOTE: Electrical Engineering and Computer Engineering students may not take this course for credit.', 368, 'Electronics for Mechanical Engineers', 1, 3);
INSERT IGNORE INTO course VALUES (34, 3.50, 'Definition and classification of dynamic systems and components. Modelling of dynamic systems containing individual or mixed mechanical, electrical, fluid and thermal elements. Block diagrams representation and simulation techniques using MATLAB/Simulink. Time domain analysis. Transient and steady-state characteristics of dynamic systems. Linearization. Transfer functions. Introduction to feedback control systems. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', 'NOTE: Students who have received credit for ELEC 370 may not take this course for credit.', 370, 'Modelling and Analysis of Dynamic Systems', 1, 3);
INSERT IGNORE INTO course VALUES (35, 3.75, 'Stability of linear feedback systems. Root-Locus method. Frequency response concepts. Stability in the frequency domain. Feedback system design using Root Locus techniques. Compensator concepts and configurations. PID-controller design. Simulation and computer-aided controller design using Matlab/Simulink. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: three hours per week, alternate weeks.',
1, 3, 3, 1, 'MECH', 'NOTE: Students who have received credit for ELEC 372 may not take this course for credit.', 371, 'Analysis and Design of Control Systems', 1, 3);
INSERT IGNORE INTO course VALUES (36, 3.50, 'Transient vibrations under impulsive shock and arbitrary excitation: normal modes, free and forced vibration. Multi-degree of freedom systems, influence coefficients, orthogonality principle, numerical methods. Continuous systems; longitudinal torsional and flexural free and forced vibrations of prismatic bars. Lagrange’s equations. Vibration measurements. Lectures: three hours per week. Tutorial: two hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', 'NOTE: Students who have received credit for MECH 443 may not take this course for credit', 375, 'Mechanical Vibrations', 2, 3);
INSERT IGNORE INTO course VALUES (37, 3.00, 'The design process; product cost, quality and time to market, open and concept design problems, problem description. Geometric and type synthesis. Direct and inverse design problems. Material selection and load determination. Mathematical modelling, analysis, and validation. Introduction to Computer-Aided Design and Engineering (CAD and CAE). Product evaluation for performance, tolerance, cost, manufacture, assembly, and other measures. Design documentation. A team-based design project is an intrinsic part of this course. Lectures: three hours per week. Tutorial: two hours per week',
1, 0, 3, 1, 'MECH', "", 390, 'Mechanical Engineering Design Project', 2, 3);
INSERT IGNORE INTO course VALUES (38, 4.00, 'A supervised design, simulation or experimental capstone design project including a preliminary project proposal with complete project plan and a technical report at the end of the fall term; a final report by the group and presentation at the end of the winter term. Lectures: one hour per week, one term. Equivalent laboratory time: three hours per week, two terms.',
1, 3, 1, 1, 'MECH', 'NOTE: Students will work in groups under direct supervision of a faculty member.', 490, 'Capstone Mechanical Engineering Design Project', 0, 3);
INSERT IGNORE INTO course VALUES (39, 4.00, 'No review of ideal gas processes. Students should be aware of this beforehand. Semi-perfect gases and the gas tables. Mixtures of gases, gases and vapours, air conditioning processes. Combustion and combustion equilibrium. Applications of thermo­dynamics to power production and utilization systems: study of basic and advanced cycles for gas compression, internal combustion engines, power from steam, gas turbine cycles, and refrigeration. Real gases. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
0, 2, 3, 1, 'MECH', "NOTE: Students will not perform any cool experiments unfortunately.", 351, 'Thermodynamics II', 1, 3);

-- new course soen 363
INSERT IGNORE INTO course VALUES (40, 3.00, 'Prerequisite: COMP 352. This course covers the following topics: introduction to the current data ecosystem; relational databases; key-value databases; document databases; column databases; graph databases; RDF stores; parallel and distributed file systems, data processing engines; data stream analytics; and data infrastructure. Lectures: three hours per week. Tutorial: one hour per week',
0, 0, 3, 1, 'SOEN', "", 363, 'Data Systems for Software Engineers', 1, 1);
INSERT IGNORE INTO course VALUES (41, 3.00, 'Prerequisite: COMP 248; MATH 203 or Cegep Mathematics 103 or NYA previously or concurrently; MATH 204 or Cegep Mathematics 105 or NYC previously or concurrently. Levels of system abstraction and von Neumann model. Basics of digital logic design. Data representation and manipulation. Instruction set architecture. Processor internals. Assembly language programming. Memory subsystem and cache management. I/O subsystem. Introduction to network organization and architecture. Lectures: three hours per week. Tutorial: two hours per week.',1, 2, 3, 1, 'COMP', 'NOTE: Students who have received credit for SOEN 228 may not take this course for credit.', 228, 'System Hardware', 1, 2);
INSERT IGNORE INTO course VALUES (42, 3.00, 'Prerequisite: MATH 203 or Cegep Mathematics 103 or NYA; MATH 204 or Cegep Mathematics 105 or NYC. Sets. Propositional logic and predicate calculus. Functions and relations. Elements of number theory. Mathematical reasoning. Proof techniques: direct proof, indirect proof, proof by contradiction, proof by induction. Lectures: three hours per week. Tutorial: two hours per week.',
1, 2, 3, 1, 'COMP', 'NOTE: Students who have received credit for COMP 238 or COEN 231 may not take this course for credit.', 232, 'Mathematics for Computer Science', 1, 2);
INSERT IGNORE INTO course VALUES (43, 3.00, 'Prerequisite: MATH 205 or Cegep Mathematics 203 or NYB. Combinatorics. Axioms of probability. Conditional probability. Discrete and continuous probability distributions. Expectation and moments. Hypothesis testing. Parameter estimation. Correlation and linear regression. Applications to computer science. Lectures: three hours per week. Tutorial: two hours per week.',
1, 2, 3, 1, 'COMP', 'NOTE: Students who have received credit for ENGR 371, STAT 249, STAT 250, COMM 215, MAST 221, MAST 333 may not take this course for credit.
', 233, 'Probability and Statistics for Computer Science', 1, 2);
INSERT IGNORE INTO course VALUES (44, 3.50, 'Prerequisite: MATH 201 or equivalent. Overview of computing systems. Problem solving and algorithms. Introduction to computer programming. Hardware, software and data storage, programming languages, data organization, program design and development. Lectures: three hours per week. Tutorial: one hour per week.
',1, 2, 3, 1, 'COMP', NULL, 248, 'Object‑Oriented Programming I', 1, 2);
INSERT IGNORE INTO course VALUES (45, 3.50, 'Prerequisite: COMP 248; MATH 203 or Cegep Mathematics 103 or NYA; MATH 205 or Cegep Mathematics 203 or NYB previously or concurrently. Design of classes. Inheritance. Polymorphism. Static and dynamic binding. Abstract classes. Exception handling. File I/O. Recursion. Interfaces and inner classes. Graphical user interfaces. Generics. Collections and iterators. Lectures: three hours per week. Tutorial: two hours per week. Laboratory: one hour per week.
',1, 2, 3, 1, 'COMP', NULL, 249, 'Object‑Oriented Programming II', 1, 2);
INSERT IGNORE INTO course VALUES (46, 3.00, 'Prerequisite: COMP 232 or COEN 231; COMP 249 or COEN 244. Finite state automata and regular languages. Push‑down automata and context‑free languages. Pumping lemmas. Applications to parsing. Turing machines. Unde­cidability and decidability. Lectures: three hours per week. Tutorial: one hour per week.
',1, 2, 3, 1, 'COMP', NULL, 335, 'Introduction to Theoretical Computer Science', 1, 2);
INSERT IGNORE INTO course VALUES (47, 4.00, 'Prerequisite: COMP 346. Computer architecture models: control‑flow and data‑flow. Concurrency and locality, data dependency theory. Instruction level parallelism. Instruction scheduling. Pipelined processors. Vector processors. Thread level parallelism. Multiprocessors. Shared memory models. Coherence protocols. Interconnection networks. Performance issues. Advanced topics in contemporary computer architectures. Lectures: three hours per week. Tutorial: one hour per week.',0, 2, 3, 1, 'COMP', 'NOTE: Students who have received credit for COEN 346 may not take this course for credit.', 346, 'Operating Systems', 1, 2);
INSERT IGNORE INTO course VALUES (48, 3.00, 'Prerequisite: COMP 249. Survey of programming paradigms: Imperative, functional, and logic programming. Issues in the design and implementation of programming languages. Declaration models: binding, visibility, and scope. Type systems, including static and dynamic typing. Parameter passing mechanisms. Hybrid language design. Lectures: three hours per week. Tutorial: one hour per week.',1, 2, 3, 1, 'COMP', NULL, 348, 'Principles of Programming Languages', 1, 2);
INSERT IGNORE INTO course VALUES (49, 3.00, 'Prerequisite: COMP 232 previously or concurrently; COMP 249. Abstract data types: stacks and queues, trees, priority queues, dictionaries. Data structures: arrays, linked lists, heaps, hash tables, search trees. Design and analysis of algorithms: asymptotic notation, recursive algorithms, searching and sorting, tree traversal, graph algorithms. Lectures: three hours per week. Tutorial: one hour per week.',1, 2, 3, 1, 'COMP', 'NOTE: Students who have received credit for COEN 352 may not take this course for credit.', 352, 'Data Structures and Algorithms', 1, 2);
INSERT IGNORE INTO course VALUES (50, 4.00, 'Prerequisite: COMP 352; ENCS 282. Software development process models (e.g. linear vs. iterative). Project management; roles, activities and deliverables for each software life cycle phase. Requirements management: analysis, elicitation, and scope. Architecture, design and the mapping of requirements to design and design to implementation. Traceability. Software quality assurance: verification, validation and the role of testing. Maintenance and evolution. Project. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week.',1, 2, 3, 1, 'COMP', 'NOTES: 1. Students who have received credit for SOEN 341 may not take this course for credit.2. Students in the BEng in Software Engineering program may not take this course for credit."', 354, 'Introduction to Software Engineering', 1, 2);
INSERT IGNORE INTO course VALUES (51, 3.00, '',
1, 2, 3, 1, 'ENCS', '', 282, 'Technical Writing and Communication', 1, 2);
INSERT IGNORE INTO course VALUES (52, 3.00, '',
1, 2, 3, 1, 'ENCS', '', 393, 'Social and Ethical Dimensions of Information and Communication Technologies', 1, 2);
INSERT IGNORE INTO course VALUES (53, 3.00, '',
1, 2, 3, 1, 'COMP', '', 339, 'Combinatorics', 1, 2);
INSERT IGNORE INTO course VALUES (54, 3.00, '',
1, 2, 3, 1, 'COMP', '', 361, 'Elementary Numerical Methods', 1, 2);
INSERT IGNORE INTO course VALUES (55, 3.00, '',
1, 2, 3, 1, 'COMP', '', 367, 'Techniques in Symbolic Computation', 1, 2);
INSERT IGNORE INTO course VALUES (56, 3.00, '',
1, 2, 3, 1, 'ENGR', '', 213, 'Applied Ordinary Differential Equations', 1, 2);
INSERT IGNORE INTO course VALUES (57, 3.00, '',
1, 2, 3, 1, 'ENGR', '', 233, 'Applied Advanced Calculus', 1, 2);
INSERT IGNORE INTO course VALUES (58, 3.00, '',
1, 2, 3, 1, 'MAST', '', 218, 'Multivariable Calculus I', 1, 2);
INSERT IGNORE INTO course VALUES (59, 3.00, '',
1, 2, 3, 1, 'MAST', '', 219, 'Multivariable Calculus II', 1, 2);
INSERT IGNORE INTO course VALUES (60, 3.00, '',
1, 2, 3, 1, 'MAST', '', 234, 'Linear Algebra and Applications I', 1, 2);
INSERT IGNORE INTO course VALUES (61, 3.00, '',
1, 2, 3, 1, 'MAST', '', 235, 'Linear Algebra and Applications II', 1, 2);
INSERT IGNORE INTO course VALUES (62, 3.00, '',
1, 2, 3, 1, 'MAST', '', 324, 'Introduction to Optimization', 1, 2);
INSERT IGNORE INTO course VALUES (63, 3.00, '',
1, 2, 3, 1, 'MAST', '', 332, 'Techniques in Symbolic Computation', 1, 2);
INSERT IGNORE INTO course VALUES (64, 3.00, '',
1, 2, 3, 1, 'MAST', '', 334, 'Numerical Analysis', 1, 2);
INSERT IGNORE INTO course VALUES (65, 3.00, '',
1, 2, 3, 1, 'MATH ', '', 251, 'Linear Algebra I', 1, 2);
INSERT IGNORE INTO course VALUES (66, 3.00, '',
1, 2, 3, 1, 'MATH ', '', 252, 'Linear Algebra II', 1, 2);
INSERT IGNORE INTO course VALUES (67, 3.00, '',
1, 2, 3, 1, 'MATH ', '', 339, 'Combinatorics', 1, 2);
INSERT IGNORE INTO course VALUES (68, 3.00, '',
1, 2, 3, 1, 'MATH ', '', 392, 'Elementary Number Theory', 1, 2);
INSERT IGNORE INTO course VALUES (69, 4.00, '',
1, 2, 3, 1, 'COMP', '', 345, 'Advanced Program Design with C++', 1, 2);
INSERT IGNORE INTO course VALUES (70, 4.00, '',
1, 2, 3, 1, 'COMP', '', 353, 'Databases', 1, 2);
INSERT IGNORE INTO course VALUES (71, 4.00, '',
1, 2, 3, 1, 'COMP', '', 371, 'Computer Graphics', 1, 2);
INSERT IGNORE INTO course VALUES (72, 4.00, '',
1, 2, 3, 1, 'COMP', '', 376, 'Introduction to Game Development', 1, 2);
INSERT IGNORE INTO course VALUES (73, 4.00, '',
1, 2, 3, 1, 'COMP', '', 472, 'Artificial Intelligence', 1, 2);
INSERT IGNORE INTO course VALUES (74, 4.00, '',
1, 2, 3, 1, 'COMP', '', 476, 'Advanced Game Development', 1, 2);
INSERT IGNORE INTO course VALUES (75, 4.00, '',
1, 2, 3, 1, 'COMP', '', 477, 'Animation for Computer Games', 1, 2);
INSERT IGNORE INTO course VALUES (76, 4.00, '',
1, 2, 3, 1, 'COMP', '', 445, 'Data Communication and Computer Networks', 1, 2);
INSERT IGNORE INTO course VALUES (77, 4.00, '',
1, 2, 3, 1, 'COMP', '', 479, 'Information Retrieval and Web Search', 1, 2);
INSERT IGNORE INTO course VALUES (78, 3.00, '',
1, 2, 3, 1, 'SOEN', '', 287, 'Web Programming', 1, 2);
INSERT IGNORE INTO course VALUES (79, 3.00, '',
1, 2, 3, 1, 'SOEN', '', 387, 'Web Based Enterprise Application Design', 1, 2);
INSERT IGNORE INTO course VALUES (80, 4.00, '',
1, 2, 3, 1, 'SOEN', '', 423, 'Distributed Systems', 1, 2);
INSERT IGNORE INTO course VALUES (81, 4.00, '',
1, 2, 3, 1, 'SOEN', '', 487, 'Web Services and Applications', 1, 2);
INSERT IGNORE INTO course VALUES (81, 3.00, '',
1, 2, 3, 1, 'COMP', '', 326, 'Computer Architecture', 1, 2);
INSERT IGNORE INTO course VALUES (82, 4.00, '',
1, 2, 3, 1, 'COMP', '', 426, 'Multicore Programming', 1, 2);
INSERT IGNORE INTO course VALUES (83, 4.00, '',
1, 2, 3, 1, 'COMP', '', 428, 'Parallel Programming', 1, 2);
INSERT IGNORE INTO course VALUES (84, 4.00, '',
1, 2, 3, 1, 'SOEN', '', 422, 'Embedded Systems and Software', 1, 2);
INSERT IGNORE INTO course VALUES (85, 3.00, '',
1, 2, 3, 1, 'COMP', '', 465, 'Design and Analysis of Algorithms', 1, 2);
INSERT IGNORE INTO course VALUES (86, 3.00, '',
1, 2, 3, 1, 'ACCO', '', 220, 'Financial and Managerial Accounting', 1, 2);
INSERT IGNORE INTO course VALUES (87, 3.00, '',
1, 2, 3, 1, 'BSTA', '', 445, 'Statistical Software for Data Management and Analysis', 1, 2);
INSERT IGNORE INTO course VALUES (88, 3.00, '',
1, 2, 3, 1, 'BTM', '', 387, 'E-Business', 1, 2);
INSERT IGNORE INTO course VALUES (89, 3.00, '',
1, 2, 3, 1, 'BTM', '', 430, 'Enterprise Resource Planning and Information Technology Integration', 1, 2);
INSERT IGNORE INTO course VALUES (90, 3.00, '',
1, 2, 3, 1, 'COMM', '', 210, 'Contemporary Business Thinking', 1, 2);
INSERT IGNORE INTO course VALUES (91, 3.00, '',
1, 2, 3, 1, 'COMM', '', 222, 'Organizational Behaviour and Theory', 1, 2);
INSERT IGNORE INTO course VALUES (92, 3.00, '',
1, 2, 3, 1, 'COMM', '', 223, 'Marketing Management I', 1, 2);
INSERT IGNORE INTO course VALUES (93, 3.00, '',
1, 2, 3, 1, 'COMM', '', 225, 'Production and Operations Management', 1, 2);
INSERT IGNORE INTO course VALUES (94, 3.00, '',
1, 2, 3, 1, 'COMM', '', 308, 'Introduction to Finance', 1, 2);
INSERT IGNORE INTO course VALUES (95, 3.00, '',
1, 2, 3, 1, 'ECON', '', 201, 'Introduction to Microeconomics', 1, 2);
INSERT IGNORE INTO course VALUES (96, 3.00, '',
1, 2, 3, 1, 'SCOM', '', 361, 'Management Science Models for Operations Management', 1, 2);
INSERT IGNORE INTO course VALUES (97, 3.00, '',
1, 2, 3, 1, 'SCOM', '', 372, 'Supply Chain Planning and Control', 1, 2);
INSERT IGNORE INTO course VALUES (98, 3.00, '',
1, 2, 3, 1, 'BIOL', '', 206, 'Elementary Genetics', 1, 2);
INSERT IGNORE INTO course VALUES (99, 3.00, '',
1, 2, 3, 1, 'BIOL', '', 261, 'Molecular and General Genetics', 1, 2);
INSERT IGNORE INTO course VALUES (100, 3.00, '',
1, 2, 3, 1, 'CHEM', '', 217, 'Introductory Analytical Chemistry I', 1, 2);
INSERT IGNORE INTO course VALUES (101, 3.00, '',
1, 2, 3, 1, 'CHEM', '', 221, 'Introductory Organic Chemistry I', 1, 2);
INSERT IGNORE INTO course VALUES (102, 3.00, '',
1, 2, 3, 1, 'CIVI', '', 231, 'Geology for Civil Engineers', 1, 2);
INSERT IGNORE INTO course VALUES (103, 3.50, '',
1, 2, 3, 1, 'ELEC', '', 321, 'Introduction to Semiconductor Materials and Devices', 1, 2);
INSERT IGNORE INTO course VALUES (104, 3.00, '',
1, 2, 3, 1, 'PHYS', '', 252, 'Optics', 1, 2);
INSERT IGNORE INTO course VALUES (105, 3.00, '',
1, 2, 3, 1, 'PHYS', '', 284, 'Introduction to Astronomy', 1, 2);
INSERT IGNORE INTO course VALUES (106, 3.00, '',
1, 2, 3, 1, 'PHYS', '', 385, 'Astrophysics', 1, 2);
INSERT IGNORE INTO course VALUES (107, 3.50, '',
1, 2, 3, 1, 'AERO', '', 480, 'Flight Control Systems', 1, 2);
INSERT IGNORE INTO course VALUES (108, 3.00, '',
1, 2, 3, 1, 'AERO', '', 482, 'Avionic Navigation Systems', 1, 2);
INSERT IGNORE INTO course VALUES (109, 3.00, '',
1, 2, 3, 1, 'COEN', '', 320, 'Introduction to Real‑Time Systems', 1, 2);
INSERT IGNORE INTO course VALUES (110, 4.00, '',
1, 2, 3, 1, 'COMP', '', 444, 'System Software Design', 1, 2);
INSERT IGNORE INTO course VALUES (111, 4.00, '',
1, 2, 3, 1, 'SOEN', '', 423, 'Distributed Systems', 1, 2);
INSERT IGNORE INTO course VALUES (112, 4.00, '',
1, 2, 3, 1, 'COMP', '', 425, 'Computer Vision', 1, 2);
INSERT IGNORE INTO course VALUES (113, 4.00, '',
1, 2, 3, 1, 'COMP', '', 442, 'Compiler Design', 1, 2);
INSERT IGNORE INTO course VALUES (114, 4.00, '',
1, 2, 3, 1, 'COMP', '', 451, 'Database Design', 1, 2);
INSERT IGNORE INTO course VALUES (115, 4.00, '',
1, 2, 3, 1, 'COMP', '', 473, 'Pattern Recognition', 1, 2);
INSERT IGNORE INTO course VALUES (116, 4.00, '',
1, 2, 3, 1, 'COMP', '', 474, 'Intelligent Systems', 1, 2);
INSERT IGNORE INTO course VALUES (117, 4.00, '',
1, 2, 3, 1, 'COMP', '', 478, 'Image Processing', 1, 2);
INSERT IGNORE INTO course VALUES (118, 1.00, '',
1, 2, 3, 1, 'SOEN', '', 298, 'System Hardware Lab', 1, 2);
INSERT IGNORE INTO course VALUES (119, 3.00, '',
1, 2, 3, 1, 'SOEN', '', 448, 'Management of Evolving Systems', 1, 2);
INSERT IGNORE INTO course VALUES (120, 1.00, '',
1, 2, 3, 1, 'SOEN', '', 491, 'Software Engineering Project', 1, 2);
INSERT IGNORE INTO course VALUES (121, 3.00, '',
1, 2, 3, 1, 'SOEN', '', 498, 'Topics in Software Engineering', 1, 2);
INSERT IGNORE INTO course VALUES (122, 4.00, '',
1, 2, 3, 1, 'SOEN', '', 499, 'Topics in Software Engineering', 1, 2);

INSERT IGNORE INTO course VALUES (124, 3.00, 'History of industrial engineering. Role of industrial engineers. Types of manufacturing and production systems. Material flow systems. Job design and work measurement. Introduction to solution methodologies for problems which relate to the design and operation of integrated production systems of humans, machines, information, and materials. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'INDU', '', 211, 'Introduction to Production and Manufacturing Systems', 1, 4);
INSERT IGNORE INTO course VALUES (125, 3.50, 'Prerequisite: ENGR 371. Modelling techniques in simulation; application of discrete simulation techniques to model industrial systems; random number generation and testing; design of simulation experiments using different simulation languages; output data analysis. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks. Tutorial: one hour per week.',
1, 2, 3, 1, 'INDU', '', 311, 'Simulation of Industrial Systems', 1, 4);
INSERT IGNORE INTO course VALUES (126, 3.00, 'Prerequisite: INDU 323 The systems approach to production. Interrelationships among the component blocks of the system: forecasting, aggregate planning, production, material and capacity planning, operations scheduling. An overview of integrated production planning and control including MRP II, Just in Time manufacturing (JIT). Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'INDU', '', 320, 'Production Engineering', 1, 4);
INSERT IGNORE INTO course VALUES (127, 3.00, 'Prerequisite: INDU 320. Lean fundamentals; lean manufacturing; lean engineering; lean principles, tools and techniques, practices, and implementation; five S’s, process analysis/spaghetti charts, value engineering; value stream mapping; standardized work/standard times; set-up reduction/line balancing; unit manufacturing; cell layout/cellular manufacturing; total productive maintenance; kanban; lean supply chain management; transition-to-lean roadmap; people/organizational issues in the lean enterprise; Six Sigma; TOM; agile manufacturing. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'INDU', 'NOTE: Students who have received credit for INDU 420 may not take this course for credit.', 321, 'Lean Manufacturing', 1, 4);
INSERT IGNORE INTO course VALUES (128, 3.50, 'Prerequisite: ENGR 213, 233; INDU 211. An introduction to deterministic mathematical models with emphasis on linear programming. Applications to production, logistics, and service systems. Computer solution of optimization problems. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'INDU', '', 323, 'Operations Research I', 1, 4);
INSERT IGNORE INTO course VALUES (129, 3.50, 'Prerequisite: INDU 323. Integer programming (IP), including modelling and enumerative algorithms for solving IP problems; post-optimality analysis. Network flows, dynamic programming and non-linear programming. Applications in the design and operation of industrial systems. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'INDU', 'NOTE: Students who have received credit for INDU 430 may not take this course for credit.', 324, 'Operations Research II', 1, 4);
INSERT IGNORE INTO course VALUES (130, 3.00, 'Prerequisite: ENCS 282; ENGR 301 previously or concurrently. Organizational structures, their growth and change. Motivation, leadership, and group behaviour. Design of alternatives for improving organizational performance and effectiveness. Planning, organization and management of engineering projects. Management for total quality. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', '', 330, 'Engineering Management', 0, 4);
INSERT IGNORE INTO course VALUES (131, 3.00, 'Prerequisite: INDU 324. Overview of transportation systems; airlines, railways, ocean liners, cargo, energy transportation and pipelines. Supply chain characterization. Site location. Distribution planning. Vehicle routing. Fleet scheduling. Crew scheduling. Demand management. Replenishment management. Revenue management. Geographic information systems. Real-time network control issues. Project. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', 'NOTE: Students who have received credit for INDU 442 may not take this course for credit.', 342, 'Logistics Network Models', 0, 4);
INSERT IGNORE INTO course VALUES (132, 3.00, 'Prerequisite: ENGR 371. Overview of probability theory; probability distributions; exponential model and Poisson process; discrete-time and continuous-time Markov chains; classification of states; birth and death processes; queuing theory. Application to industrial engineering problems. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'INDU', '', 371, 'Stochastic Models in Industrial Engineering', 1, 4);
INSERT IGNORE INTO course VALUES (133, 3.00, 'Prerequisite: ENGR 371. Importance of quality; total quality management; statistical concepts relevant to process control; control charts for variables and attributes; sampling plans. Introduction to reliability models and acceptance testing; issues of standardization. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', '', 372, 'Quality Control and Reliability', 0, 4);
INSERT IGNORE INTO course VALUES (134, 3.00, 'Prerequisite: MECH 311. Engineering design for the control of workplace hazards. Occupational injuries and diseases. Codes and standards. Workplace Hazardous Materials Information Systems (WHMIS). Hazard evaluation and control. Design criteria. Risk assessment. Safety in the manufacturing environment. Applications in ventilation, air cleaning, noise and vibration. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', '', 410, 'Safety Engineering', 0, 4);
INSERT IGNORE INTO course VALUES (135, 3.50, 'Prerequisite: MECH 311. Concepts and benefits of computer integrated manufacturing (CIM). Design for manufacturing. Computer-aided design, process planning, manufacturing (computer numerical control parts programming), and inspection. Robots in CIM. Production planning and scheduling in CIM. System integration. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'INDU', '', 411, 'Computer Integrated Manufacturing', 0, 4);
INSERT IGNORE INTO course VALUES (136, 3.50, 'Prerequisite: ENGR 371. Elements of anatomy, physiology, and psychology; engineering anthropometry; human capacities and limitations; manual material handling; design of workplaces; human-machines system design; design of controls and displays; shift work. Applications to a manufacturing environment. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'INDU', '', 412, 'Human Factors Engineering', 0, 4);
INSERT IGNORE INTO course VALUES (137, 3.5, 'Prerequisite: INDU 311 previously or concurrently; INDU 320. An introduction to planning and design of production and manufacturing. Facility layout and location. Material handling systems and equipment specifications. Computer-aided facilities planning. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'INDU', '', 421, 'Facilities Design and Material Handling Systems', 1, 4);
INSERT IGNORE INTO course VALUES (138, 3.5, 'Prerequisite: INDU 320. Inventory analysis and control systems; the role of forecasting in controlling inventories; the role of inventories in physical distribution; supply chain management; work in process inventories; inventory in just-in-time manufacturing systems. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'INDU', '', 423, 'Inventory Control', 1, 4);
INSERT IGNORE INTO course VALUES (139, 3.0, 'Prerequisite: MECH 311. Development processes and organizations, product planning, identifying customer needs, product specifications, concept generation, concept selection, concept testing, product architecture, industrial design, design for manufacturing, prototyping robust design, patents and intellectual property. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', 'NOTE: Students who have received credit for AERO 444 may not take this course for credit.', 440, 'Product Design and Development', 0, 4);
INSERT IGNORE INTO course VALUES (140, 3.0, 'Prerequisite: INDU 372. Overview of the Six Sigma concepts and tools. Six Sigma deployment practices: Define, Measure, Analyze, Improve and Control phases (DMAIC). Project development, and the DMAIC problem-solving approach. Project. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', '', 441, 'Introduction to Six Sigma', 0, 4);
INSERT IGNORE INTO course VALUES (141, 3.0, 'Prerequisite: ENGR 371; INDU 320. Introduction to service strategy and operations. Service demand forecasting and development of new services. Service facility location and layout planning. Applications of decision models in service operations and service quality control. Cost analysis, queuing models, risk management and resource allocation models for service decisions. Service outsourcing and supply chain issues. Efficiency and effectiveness issues in different service sectors such as emergency force deployment, municipal resource allocation and health care. Case studies using operations research, operations management, and statistical techniques. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', '', 466, 'Decision Models in Service Sector', 0, 4);
INSERT IGNORE INTO course VALUES (142, 3.5, 'Prerequisite: INDU 372. Statistical experimental design issues such as randomized blocks, factorial designs at two levels, applications on factorial designs, building models, Taguchi methods. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', '', 475, 'Advanced Concepts in Quality Improvement', 0, 4);
INSERT IGNORE INTO course VALUES (143, 3.0, 'Prerequisite: INDU 311, 324. This course uses the case teaching method to train industrial engineering students to analyze real-world situations using the tools of operations research. Students assume the roles of engineering consultants working together to solve a problem posed by the client in each case. As a consequence, students obtain experience dealing with all steps involved in solving a real problem, from identification of stakeholders, problem formulation and identification of data requirements, to model implementation and analysis of results. Students are required to participate in class discussions of the case and to present their solutions in either report or presentation form. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', '', 480, 'Cases in Industrial Engineering', 0, 4);
INSERT IGNORE INTO course VALUES (144, 4.0, 'Prerequisite: 75 credits in the program; ENCS 282; ENGR 301; INDU 421 previously or concurrently. A supervised design, simulation or experimental capstone design project including a preliminary project proposal with complete project plan and a technical report at the end of the fall term; a final report by the group and individual oral presentation at the end of the winter term. Lectures: one hour per week, one term. Equivalent laboratory time: three hours per week, two terms.',
1, 3, 1, 1, 'INDU', 'NOTE: Students will work in groups under direct supervision of a faculty member.', 490, 'Capstone Industrial Engineering Design Project', 0, 4);
INSERT IGNORE INTO course VALUES (145, 3.0, 'Prerequisite: Permission of the Department Chair. This course may be offered in a given year upon the authorization of the Mechanical, Industrial and Aerospace Engineering Department. The course content may vary from offering to offering and will be chosen to complement the elective courses available in the Industrial Engineering program. Lectures: three hours per week.',
1, 0, 3, 1, 'INDU', '', 498, 'Topics in Industrial Engineering', 0, 4);

INSERT IGNORE INTO course VALUES (146, 3, 'Prerequisite: PHYS 204; ENGR 213 previously or concurrently. Forces in a plane and in space, moments of forces, Varignon’s theorem, rigid bodies in equilibrium, free-body diagram. Centroids, centres of gravity. Distributed forces, moments of inertia. Principle of virtual work. Kinematics of particles and rigid bodies. Forces and accelerations; work and energy; impulse and momentum. Kinetics of particles and rigid bodies. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'ENGR', '', 245, 'Mechanical Analysis', 1, 3);

INSERT IGNORE INTO course VALUES (151, 3.00, '',
1, 0, 3, 1, 'GEOL', '', 206, 'Earthquakes, Volcanoes, and Plate Tectonics', 1, 1);
INSERT IGNORE INTO course VALUES (152, 3.00, '',
1, 0, 3, 1, 'GEOL', '', 208, 'The Earth, Moon and the Planets', 1, 1);
INSERT IGNORE INTO course VALUES (153, 3.00, '',
1, 0, 3, 1, 'PHYS', '', 252, 'Optics', 1, 1);
INSERT IGNORE INTO course VALUES (154, 3.00, '',
1, 0, 3, 1, 'PHYS', '', 260, 'Introductory Biophysics', 1, 1);
INSERT IGNORE INTO course VALUES (155, 3.00, '',
1, 0, 3, 1, 'PHYS', '', 270, 'Introduction to Energy and Environment', 1, 1);

INSERT IGNORE INTO course VALUES (158, 3.00, '',
1, 0, 3, 1, 'BSTA', '', 478, 'Data Mining Techniques', 1,1);
INSERT IGNORE INTO course VALUES (159, 3.00, '',
1, 0, 3, 1, 'BTM', '', 430, 'Enterprise Resource Planning and Information Technology Integration', 1, 1);
INSERT IGNORE INTO course VALUES (160, 3.00, '',
1, 0, 3, 1, 'BTM', '', 480, 'Project Management', 1, 1);
INSERT IGNORE INTO course VALUES (161, 1.00, 'Prerequisite: ENCS 282; permission of the Department. Students must submit a report on a topic related to the students’ discipline and approved by the Department. The report must present a review of a current engineering problem, a proposal for a design project, or a current engineering practice.',
1, 0, 0, 1, 'ENGR', 'NOTE: Students who have received credit for ENGR 410 may not take this course for credit.', 411, 'Special Technical Report', 0, 3);
INSERT IGNORE INTO course VALUES (162, 3.00, 'Prerequisite: ENCS 282; minimum 75 credits in the BEng program with a cumulative GPA of 3.00 or better; permission of the Department. Students work on a research project in their area of concentration, selected in consultation with and conducted under the supervision of a faculty member of the Department. The student’s work must culminate in a final report, as well as an oral presentation. Students planning to register for this course should consult with the Department prior to term of planned registration. Intended for students with potential interest in graduate programs.',
1, 0, 0, 1, 'ENGR', 'NOTE: Must be approved by the Department prior to registration.', 412, 'Honours Research Project', 0, 3);
INSERT IGNORE INTO course VALUES (163, 3.00, '',
1, 0, 3, 1, 'MANA', '', 300, 'Entrepreneurship: Launching Your Business', 1, 1);

INSERT IGNORE INTO course VALUES (164, 3.50, 'Prerequisite: ENGR 311; AERO 371 or MECH 370. Unified treatment of measurement of physical quantities; static and dynamic characteristics of instruments — calibration, linearity, precision, accuracy, and bias and sensitivity drift; sources of errors; error analysis; experiment planning; data analysis techniques; principles of transducers; signal generation, acquisition and processing; principles and designs of systems for measurement of position, velocity, acceleration, pressure, force, stress, temperature, flow-rate, proximity detection. The course includes demonstration of various instruments. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', 'NOTE: Students who have received credit for MECH 373 may not take this course for credit.', 411, 'Instrumentation and Measurements', 1, 3);
INSERT IGNORE INTO course VALUES (165, 3.50, 'Prerequisite: MECH 313. Introduction to computational tools in the design process. Introduction to the fundamental approaches to computer-aided geometric modelling, physical modelling and engineering simulations. Establishing functions and functional specifications with emphasis on geometric tolerancing and dimensioning, manufacturing and assembly evaluation. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 412, 'Computer-Aided Mechanical Design', 1, 3);
INSERT IGNORE INTO course VALUES (166, 3.00, 'Prerequisite: MECH 215. Class definitions. Designing classes and member functions. Constructors and destructors. Class libraries and their uses. Input and output. Data abstrac­tion and encapsulation. Introduction to software engineering. Computer graphics and visualization. Numerical methods. Advanced mechanical and industrial engineering applications. This course includes a substantial project. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'MECH', '', 415, 'Advanced Programming for Mechanical and Industrial Engineers', 1, 3);
INSERT IGNORE INTO course VALUES (167, 3.00, 'Prerequisite: ENGR 233, 244; MECH 221. General applications of polymer composite materials in aircraft, aerospace, automobile, marine, recreational, and chemical processing industries. Mechanics of a unidirectional lamina. Transformation of stress, strain, modulus, and compliance. Off-axis engineering constants, shear and normal coupling coefficients. In-plane and flexural stiffness and compliance with different laminates, including cross-ply, angle-ply, quasiisotropic, and general bidirectional laminates. Hygrothermal effects. Strength of laminates and failure criteria. Micromechanics. Lectures: three hours per week.',
1, 0, 3, 1, 'MECH', '', 422, 'Mechanical Behaviour of Polymer Composite Materials', 0, 3);
INSERT IGNORE INTO course VALUES (168, 3.50, 'Prerequisite: MECH 221. Comparative analysis of the various techniques of casting, welding, powder fabrication, finishing, and non-destructive testing. Consideration of the control parameters that are essential to define both automation and robot application. Materials behaviour which determines product micro-structure and properties. Technology and theory of solidification, normalizing, quenching, surface hardening, tempering, aging, and thermomechanical processing for steels, cast irons and Al, Cu, Ni and Ti alloys. Energy conservation, worker safety, quality control, and product liability. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 423, 'Casting, Welding, Heat Treating, and Non-Destructive Testing', 0, 3);
INSERT IGNORE INTO course VALUES (169, 3.50, 'Prerequisite: MECH 311, 343. Introduction to microsystems and devices; mechanical properties of materials used in microsystems; microfabrication and post-processing techniques; sacrificial and structural layers; lithography, deposition and etching; introduction and design of different types of sensors and actuators; micromotors and other microdevices; mechanical design, finite element modelling; design and fabrication of free-standing structures; microbearings; special techniques: double-sided lithography, electrochemical milling, laser machining, LIGA, influence of IC fabrication methods on mechanical properties; application examples in biomedical, industrial, and space technology areas; integration, bonding and packaging of MEMS devices. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks',
1, 2, 3, 1, 'MECH', '', 424, 'MEMS — Design and Fabrication', 0, 3);
INSERT IGNORE INTO course VALUES (170, 3.50, 'Prerequisite: MECH 311. Fibres and resins. Hand lay up. Autoclave curing. Compression molding. Filament winding. Resin transfer molding. Braiding. Injection molding. Cutting. Joining. Thermoset and thermoplastic composites. Polymer Nanocomposites. Process modelling and computer simulation. Non-destructive evaluation techniques. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 425, 'Manufacturing of Composites', 0, 3);
INSERT IGNORE INTO course VALUES (171, 3.00, 'Prerequisite: ENGR 233, 244; MECH 321. Analysis of stresses, strains and deformations in machine elements; non-symmetric bending of beams; shear centre for thin-walled beams; curved beams; torsion of non-circular shafts and tubes; thick wall cylinders; plates and shells; contact elements; stress concentrations; energy methods; failure modes, analysis and prevention; buckling, fracture, fatigue and creep. Lectures: three hours per week.',
1, 0, 3, 1, 'MECH', '', 426, 'Stress and Failure Analysis of Machinery', 0, 3);
INSERT IGNORE INTO course VALUES (172, 3.00, 'Prerequisite: MECH 375. Definition and classification of guided transportation systems. Track characterization: alignment, gage, profile, and cross-level irregularities. Wheel-rail interactions: rolling contact theories, creep forces. Modelling of guided vehicle components: wheel set, suspension, truck and car body configurations, suspension characteristics. Performance evaluation: stability hunting, ride quality. Introduction to ad­vanced vehicles. Lectures: three hours per week.',
1, 0, 3, 1, 'MECH', '', 444, 'Guided Vehicle Systems', 0, 3);
INSERT IGNORE INTO course VALUES (173, 3.50, 'Prerequisite: MECH 343. Mechanics and construction of wheels and tires: rolling resistance, tractive and braking forces, brake system design: components of mechanical, hydraulic and pneumatic brake systems, braking efficiency, antilock braking devices, performance characteristics of road vehicles: transmission design, driving condition diagrams, acceleration, speed and stopping distance, gradability, steering mechanisms: design and kinematics, suspension spring and shock absorbers: anti-roll and anti-pitch devices, chassis and body design considerations. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 447, 'Fundamentals of Vehicle System Design', 0, 3);
INSERT IGNORE INTO course VALUES (174, 3.00, 'Prerequisite: MECH 447 previously or concurrently. Tire-terrain interactions; side-slip, cornering and aligning properties of tires; camber angle and camber torque; estimation of braking/tractive and cornering forces of tires; steady-state handling of road vehicles; steering response and directional stability; handling and directional response of vehicles with multiple steerable axles; handling of articulated vehicles; handling and directional response of tracked and wheeled off-road vehicles; directional response to simultaneous braking and steering. Lectures: three hours per week.',
1, 0, 3, 1, 'MECH', '', 448, 'Vehicle Dynamics', 0, 3);
INSERT IGNORE INTO course VALUES (175, 3.50, 'Prerequisite: MECH 351, 352, 361. Heat exchangers. Condensation and boiling heat transfer. Principles of forced convection. Analysis of free convection from a vertical wall. Correlations for free convection in enclosed spaces. Mass transfer. Special topics of heat transfer. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 452, 'Heat Transfer II', 0, 3);
INSERT IGNORE INTO course VALUES (176, 3.00, 'Prerequisite: MECH 352. Heating and cooling load calculation. Overview of heating and air conditioning systems. Review: Vapour compression refrigeration cycles, refrigerant properties, psychometrics. Performance characteristic of components: evaporators, condensers, compressors, throttling devices (expansion valves, capillary tubes). System performance characteristics: calculation of system operating conditions based on the capacities of its components and outdoor and indoor conditions. Controls: operational, capacity. Computer-aided design methods. Defrosting. Estimation of energy consumption for heating with heat pumps. Fundamentals of refrigerant piping, water piping, and air distribution systems. Experimental methods for system development. Lectures: three hours per week.',
1, 0, 3, 1, 'MECH', '', 453, 'Heating, Ventilation and Air Conditioning Systems', 0, 3);
INSERT IGNORE INTO course VALUES (177, 3.00, 'Prerequisite: MECH 351, 361. Mechanical design of vehicular engines for different applications. Gas exchange and combustion engine processes. Combustion chambers design. Fuels for vehicular engines. Fuel supply, ignition and control systems. Cooling and lubrication of engines. Emissions formation and control. Engines’ operational characteristics — matching with vehicles. Enhancement of engine performance. Engine testing. Environmental impact of vehicular engines on global pollution. Recent developments in energy efficient and “clean” engines. Design or calculation project of vehicular engine. Lectures: three hours per week.',
1, 0, 3, 1, 'MECH', '', 454, 'Vehicular Internal Combustion Engines', 0, 3);
INSERT IGNORE INTO course VALUES (178, 3.75, 'Prerequisite: ENGR 244, 391. Formulation and application of the finite element method to modelling of engineering problems, including stress analysis, vibrations, and heat transfer. Examples illustrating the direct approach, as well as variational and weighted residual methods. Elements and interpolation functions. Meshing effect. Error analysis. One- and two-dimensional boundary value problems. Development of simple programs and direct experience with general purpose packages currently used in industry for design problems. Lectures: three hours per week. Laboratory: three hours per week, alternate weeks.',
1, 3, 3, 1, 'MECH', '', 460, 'Finite Element Analysis', 0, 3);
INSERT IGNORE INTO course VALUES (179, 3.50, 'Prerequisite: MECH 361. Review of one-dimensional compressible flow. Normal and oblique shock waves; Prandtl-Meyer flow; combined effects in one-dimensional flow; non-ideal gas effects; multi-dimensional flow; linearized flow; method of characteristics. Selected experiments in supersonic flow, convergent-divergent nozzles, hydraulic analog and Fanno tube. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 461, 'Gas Dynamics', 0, 3);
INSERT IGNORE INTO course VALUES (180, 3.00, 'Prerequisite: MECH 343, 361; MECH 344, 371 previously or concurrently. This course is designed to cover the theoretical and practical areas pertinent to the operation of wind turbines. Energy in the wind. Aerodynamic drag and lift of turbine blades. Horizontal axis and vertical axis wind turbine designs. Generators. Control systems. Mechanical load analysis: blade, tower, generator and gearbox. Blade and tower design. Turbine braking. Economical, environmental and safety aspects.',
1, 0, 3, 1, 'MECH', '', 462, 'Wind Turbine Engineering', 0, 3);
INSERT IGNORE INTO course VALUES (181, 3.50, 'Prerequisite: ENGR 361; ELEC 372 or MECH 371. Introduction to fluid power; pneumatic devices; fluidic devices; hydraulic system components; hydraulic and electro-hydraulic systems; dynamic performance of fluid power systems; fluid logic. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 463, 'Fluid Power Control', 0, 3);
INSERT IGNORE INTO course VALUES (182, 3.50, 'Prerequisite: ENGR 311; MECH 368. Introduction to the concepts and practices of microcontrollers and their application for the control of electromechanical devices and systems. Study of the internal architecture of microcontrollers; programming in assembly language for specific microcontroller functions and controller algorithms; timing of the microcontroller and interfacing with peripheral devices. Students undertake hands-on project work by controlling the position or speed of a DC motor with a feed-back sensor. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 471, 'Microcontrollers for Mechatronics', 0, 3);
INSERT IGNORE INTO course VALUES (183, 3.50, 'Prerequisite: MECH 215; MECH 371 previously or concurrently. Design and analysis of mechatronic and automation systems. Selection and integration of actuators, sensors, hardware, and software. Computer vision. Programming and software design for mechatronic systems. Modelling and simulation. Design of logic control systems. Finite state machine methods. Feedback control and trajectory generation. Safety logic systems. Case studies including automation systems, mobile robots, and unmanned vehicle systems. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 472, 'Mechatronics and Automation', 0, 3);
INSERT IGNORE INTO course VALUES (184, 3.50, 'Prerequisite: ELEC 372 or MECH 371. Analog and digital controller designs. Analog controllers: lead/lag compensators, pole placement, model matching, two-parameter configuration, plant input/output feedback configuration. Digital controllers: difference equations, Z-transform, stability in the Z-domain, digital implementation of analog controllers, equivalent digital plant method, alias signals, selection of sampling time. Introduction to analog/digital state-space: controllability, observability, state feedback, state estimator. PI and PID controllers. Simulink assignments and project. Hardware laboratory project: analog and digital controller design for motor with inertial plus generator load. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 473, 'Control System Design', 0, 3);
INSERT IGNORE INTO course VALUES (185, 3.75, 'Prerequisite: ELEC 372 or MECH 371. Introduction to mechatronics; basic elements of mechatronic systems. Measurement systems: including principles of measurement systems; sensors and transducers; signal conditioning processes and circuits; filters and data acquisition. Actuation systems: mechanical actuation systems and electrical actuation systems. Controllers: control modes; PID controller; performance measures; introduction to digital controllers and robust control. Modelling and analysis of mechatronic systems; performance measures; frequency response; transient response analysis; stability analysis. Lectures: three hours per week. Laboratory: three hours per week, alternate weeks.',
1, 3, 3, 1, 'MECH', '', 474, 'Mechatronics', 0, 3);
INSERT IGNORE INTO course VALUES (186, 3.00, 'Prerequisite: MECH 313; AERO 390 or MECH 390 previously or concurrently. Generative design is a form-finding process that can mimic nature’s evolutionary approach to design. It can start with design goals and then explore innumerable possible permutations of a solution to find the best option. This course provides fundamental information on generative design and manufacturing in engineering. The core techniques from mathematics to artificial intelligence that are commonly used in the creative industry are discussed. The formal paradigms and algorithms used for generation as well as cloud computing are also covered. Lectures: three hours per week.',
1, 0, 3, 1, 'MECH', '', 476, 'Generative Design and Manufacturing in Engineering', 0, 3);
INSERT IGNORE INTO course VALUES (187, 3.00, 'Prerequisite: Permission of the Department Chair. This course may be offered in a given year upon the authorization of the Mechanical, Industrial and Aerospace Engineering Department. The course content may vary from offering to offering and will be chosen to complement the elective courses available in a given option or options. Lectures: three hours per week.',
1, 0, 3, 1, 'MECH', '', 498, 'Topics in Mechanical Engineering', 0, 3);
INSERT IGNORE INTO course VALUES (199, 3.50, "Prerequisite: MECH 311, 412. Computer aided design and manufacturing (CAD/CAM) hardware and software. Essentials of Computer Numerical Control (CNC) machine tools and systems. Process planning and tooling systems for CNC machining. Theory of CNC programming of sculptured parts. Multi-axis CNC tool path generation. Project using CAD/CAM software; CATIA for complex mechanical parts design and a CNC machine tool to manufacture parts. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.",
1, 2, 3, 1, 'MECH', '', 414, 'Computer Numerically Controller Machining', 0, 3);
INSERT IGNORE INTO course VALUES (200, 3.50, 'Prerequisite: MECH 221. Metal forming: extrusion, forging, rolling, drawing, pressing, compacting; shear line theory, sheet forming limits. Metal cutting, machinability, tooling. Plastics shaping: extrusion, moulding, vacuum forming. Consideration of the mechanical parameters critical for process control and computer applications. Interaction of materials characteristics with processing to define product properties: cold working, annealing, hot working, super plasticity, thermomechanical treatment. Energy conservation, safety, product quality, and liability. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', '', 421, 'Mechanical Shaping of Metals and Plastics', 1, 3);

INSERT IGNORE INTO course VALUES (188, 3.00, 'Prerequisite: ENGR 201. Overview of DoT and other international aviation standards (e.g. FAA), regulations and certification procedures; regulatory areas, namely, pilot training/testing, air traffic procedures, aircraft systems design and airworthiness; development process for new regulations and criteria for certification. Lectures: three hours per week.',
1, 0, 3, 1, 'AERO', 'NOTE: Students who have received credit for ENGR 417 or for this topic under an ENGR 498 number may not take this course for credit.', 417, 'Standards, Regulations and Certification', 0, 5);
INSERT IGNORE INTO course VALUES (189, 3.00, 'Prerequisite: ENGR 243, 311. Aerodynamic loading of elastic airfoils. Phenomenon of divergence. Effect of flexible control surface on divergence of main structure. Divergence of one- and two-dimensional wing models. Phenomenon of flutter. Flutter of two- and three-dimensional wings. Flutter prevention and control. Panel flutter in high speed vehicles, flutter of turbomachine bladings, galloping vortex-induced oscillations, bridge buffeting. Lectures: three hours per week.',
1, 0, 3, 1, 'AERO', 'NOTE: Students who have received credit for MECH 431 may not take this course for credit.', 431, 'Principles of Aeroeslasticity', 0, 5);
INSERT IGNORE INTO course VALUES (190, 3.75, 'Prerequisite: ENGR 311, 391; MECH 361. Introduction to computational methods in fluid dynamics using commercial CFD codes; aspects of geometry modelling, structured and unstructured grid generation, solution strategy, and post-processing; conversion of CAD to CFD models; an overview of basic numerical methods for the Navier-Stokes equations with emphasis on accuracy evaluation and efficiency. Elements of turbulence closure modelling. User-defined function for customized physical models into commercial CFD codes. Lectures: three hours per week. Laboratory: three hours per week, alternate weeks.',
1, 3, 3, 1, 'AERO', '', 455, 'Computational Fluid Dynamics for Aerospace Applications', 0, 5);
INSERT IGNORE INTO course VALUES (191, 3.00, 'Prerequisite: MECH 351, 361. Aircraft design process, preliminary sizing and thrust requirements. Rotary and fixed wing aerodynamics and stability. Helicopter configurations. Structure and fatigue design considerations. Review of the gas turbine cycle and components arrangement. Turbo-propulsion: turboprop, turbofan, turbojet and turboshafts. Energy transfer in turbo­machines: Euler equation, velocity triangles. Dimensional analysis of turbomachines. Flow in turbomachines. Three-dimensional flow in turbomachines. Mechanisms of losses in turbo­machines. Axial-flow turbines and compressors. Centrifugal compressors. Compressor and turbine performance maps; surge and stall. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'AERO', 'NOTE: Students who have received credit for MECH 462 may not take this course for credit.', 462, 'Turbomachinery and Propulsion', 1, 5);
INSERT IGNORE INTO course VALUES (192, 3.00, 'Prerequisite: MECH 361. Flow conservation equations, incompressible Navier-Stokes equations, inviscid irrotational and rotational flows: the Euler equations, the potential and stream function equations. Dynamics of an incompressible inviscid flow field: the Kelvin, Stokes, and Helmholtz theorems. Elementary flows and their superposition, panel method for non-lifting bodies. Airfoil and wing characteristics, aerodynamic forces and moments coefficients. Incompressible flows around thin airfoils, Biot-Savart law, vortex sheets. Incompressible flow around thick airfoils, the panel method for lifting bodies. Incompressible flow around wings, Prandtl’s lifting line theory, induced angle and down-wash, unswept wings, swept wings. Compressible subsonic flow: linearized theory, Prandlt-Glauert equation and other compressibility correction rules, the area rule. Transonic flow: Von Karman’s ransonic small disturbance equation, transonic full potential equation, super-critical airfoils. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'AERO', 'NOTE: Students who have received credit for MECH 464 may not take this course for credit.', 464, 'Aerodynamics', 1, 5);
INSERT IGNORE INTO course VALUES (193, 3.50, 'Prerequisite: AERO 462. Review of turbo-propulsion types and energy transfer in turbomachines. Two- and three-dimensional flow. Lift and drag for airfoils. Cascade tests and correlations. Aerodynamic losses: physics, mechanisms, control of viscous effects. Preliminary and detailed design of turbines and compressors. Structural and thermal design requirements. Failure considerations: creep, fatigue and corrosion. Performance matching. Combustion and gearbox design. Air and oil systems design requirements. Installations and acoustics. Evolution of design. Recent trends in technologies. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'AERO', 'NOTE: Students who have received credit for MECH 465 may not take this course for credit.', 465, 'Gas Turbine Design', 0, 5);
INSERT IGNORE INTO course VALUES (194, 3.50, 'Prerequisite: AERO 371 or ELEC 372 or MECH 371 or SOEN 385. Basic flight control and flight dynamics principles. Aircraft dynamic equations and performance data. Implementation of aircraft control: control surfaces and their operations, development of thrust and its control; autopilot systems, their algorithms, dynamics and interaction problems. Flight instruments, principles of operation and dynamics. Cockpit layouts — basic configuration, ergonomic design, control field forces; advanced concepts in instruments, avionics and displays; HUD; flight management systems, and communication equipment. Introduction to flight simulation: overview of visual, audio and motion simulator systems; advanced concepts in flight simulators. Lectures: three hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'AERO', 'NOTE: Students who have received credit for ELEC 415 or MECH 480 may not take this course for credit.', 480, 'Flight Control Systems', 0, 5);
INSERT IGNORE INTO course VALUES (195, 3.00, 'Prerequisite: ENGR 371 or COMP 233; AERO 371 or ELEC 372 or MECH 370 or SOEN 385. Basics of modern electronic navigation systems, history of air navigation, earth coordinate and mapping systems; basic theory and analysis of modern electronic navigation instrumentation, communication and radar systems, approach aids, airborne systems, transmitters and antenna coverage; noise and losses, target detection, digital processing, display systems and technology; demonstration of avionic systems using flight simulator. Lectures: three hours per week.',
1, 0, 3, 1, 'AERO', 'NOTE: Students who have received credit for ELEC 416 or MECH 482 may not take this course for credit.', 482, 'Avionic Navigation Systems', 0, 5);
INSERT IGNORE INTO course VALUES (196, 3.00, 'Prerequisite: MECH 351, 361. Classification of space propulsion systems; Tsiolkovskj’s equation; ideal rocket and nozzle design; flight performance; basic orbital mechanics; chemical propellant rocket performance analysis; fundamentals of liquid and solid propellant rocket motors; electric, solar, fusion thruster. Lectures: three hours per week.',
1, 0, 3, 1, 'AERO', 'NOTE: Students who have received credit for MECH 485 or for this topic under a MECH 498 number may not take this course for credit.', 485, 'Introduction to Space Systems', 0, 5);
INSERT IGNORE INTO course VALUES (197, 3.00, 'Prerequisite: ENGR 243, 244. Definition of load paths in typical aircraft structures. Derivation of analysis procedures to enable the designer to size preliminary designs. Internal shear flow distributions that balance external loads. Stress analysis of open and closed cell beams; statically indeterminate beams and frames; single and multi cell torque boxes; symmetric heavy fuselage frames. Structural instability of columns, beams, plates and flanges in compression and shear. Centres of twist and flexure; structural warping; margins of safety; concepts of optimum design; lug analysis and mechanical joints; matrix analysis methods leading to the Finite Element method. Stress analysis of thin-walled metallic structures. Lectures: three hours per week.',
1, 0, 3, 1, 'AERO', 'NOTE: Students who have received credit for MECH 486 may not take this course for credit.', 486, 'Aircraft Stress Analysis', 0, 5);
INSERT IGNORE INTO course VALUES (198, 3.00, 'Prerequisite: AERO 486. Design process for aircraft structures. Aero/performance aspects of aircraft structures. Airworthiness and design considerations. Materials. Static, vibratory and aeroelastic loadings. Propulsion-induced loadings. Functions and fabrication of structural components. Design for buckling of aircraft structures: local buckling, instability of stiffened panels, flexural torsional buckling. Design for fracture and fatigue failures. Stress analysis and design of wings, fuselages, stringers, fuselage frames, wing ribs, cut-outs in wings and fuselages, and laminated structures. Design using Finite Element Method. Concept of Optimum Design of Aircraft Structures. Design case studies. Lectures: three hours per week.',
1, 0, 3, 1, 'AERO', 'NOTE: Students who have received credit for MECH 487 may not take this course for credit.', 487, 'Design of Aircraft Structures', 0, 5);

-- id, is_active, name, number, type, course_id
INSERT IGNORE INTO requisite VALUES (1, 1, "SOEN", 341, "prerequisite", 6);-- soen 342 needs 341
INSERT IGNORE INTO requisite VALUES (2, 1, "SOEN", 341, "prerequisite", 7);-- soen 343 needs 341
INSERT IGNORE INTO requisite VALUES (3, 1, "SOEN", 342, "corequisite", 7);-- soen 343 needs 342
INSERT IGNORE INTO requisite VALUES (4, 1, "SOEN", 343, "prerequisite", 8);-- soen 344 needs 343
INSERT IGNORE INTO requisite VALUES (5, 1, "SOEN", 343, "prerequisite", 9);-- soen 345 needs 343
INSERT IGNORE INTO requisite VALUES (6, 1, "SOEN", 341, "prerequisite", 10);-- soen 357 needs 341
INSERT IGNORE INTO requisite VALUES (7, 0, "SOEN", 343, "prerequisite", 15);-- soen 344 edited needs 343
INSERT IGNORE INTO requisite VALUES (8, 0, "SOEN", 384, "prerequisite", 15);-- soen 344 edited needs 384
INSERT IGNORE INTO requisite VALUES (9, 0, "SOEN", 343, "prerequisite", 10);-- soen 357 edited needs 343 edited

INSERT IGNORE INTO requisite VALUES (68, 0, "ENCS", 282, "prerequisite", 11);
INSERT IGNORE INTO requisite VALUES (69, 0, "SOEN", 341, "prerequisite", 11);
INSERT IGNORE INTO requisite VALUES (70, 0, "ENGR", 213, "prerequisite", 12);
INSERT IGNORE INTO requisite VALUES (71, 0, "ENGR", 233, "prerequisite", 12);
INSERT IGNORE INTO requisite VALUES (72, 0, "SOEN", 344, "corequisite", 13);
INSERT IGNORE INTO requisite VALUES (73, 0, "SOEN", 357, "corequisite", 13);
INSERT IGNORE INTO requisite VALUES (74, 0, "SOEN", 390, "prerequisite", 14);
INSERT IGNORE INTO requisite VALUES (75, 0, "75 credits in the program", 344, "prerequisite", 14);

INSERT IGNORE INTO requisite VALUES (10, 1, "ENGR", 213, "prerequisite", 16);
INSERT IGNORE INTO requisite VALUES (11, 1, "PHYS", 204, "prerequisite", 16);
INSERT IGNORE INTO requisite VALUES (12, 1, "MATH", 204, "prerequisite", 16);
INSERT IGNORE INTO requisite VALUES (13, 1, "ENGR", 213, "prerequisite", 17);
INSERT IGNORE INTO requisite VALUES (14, 1, "ENGR", 242, "prerequisite", 17);
INSERT IGNORE INTO requisite VALUES (15, 1, "ENGR", 213, "prerequisite", 18);
INSERT IGNORE INTO requisite VALUES (16, 1, "ENGR", 233, "corequisite", 18);
INSERT IGNORE INTO requisite VALUES (17, 1, "ENGR", 242, "equivalent", 18); -- new type or-prerequisite; you either had to do 242 or 245 as prerequisites
INSERT IGNORE INTO requisite VALUES (18, 1, "ENGR", 245, "equivalent", 18); -- new type or-prerequisite; you either had to do 242 or 245 as prerequisites
INSERT IGNORE INTO requisite VALUES (19, 1, "MATH", 203, "prerequisite", 19);
INSERT IGNORE INTO requisite VALUES (20, 1, "ENGR", 213, "prerequisite", 20);
INSERT IGNORE INTO requisite VALUES (21, 1, "ENGR", 233, "prerequisite", 20);
INSERT IGNORE INTO requisite VALUES (22, 1, "ENGR", 213, "prerequisite", 21);
INSERT IGNORE INTO requisite VALUES (23, 1, "ENGR", 233, "prerequisite", 21);
INSERT IGNORE INTO requisite VALUES (24, 1, "ENGR", 251, "prerequisite", 21);

INSERT IGNORE INTO requisite VALUES (25, 1, "COMP", 354, "antirequisite", 5); -- soen 341 = comp 354
INSERT IGNORE INTO requisite VALUES (26, 1, "COMP", 228, "antirequisite", 5); -- soen 228 = comp 228

INSERT IGNORE INTO requisite VALUES (27, 1, "MATH", 204, "prerequisite", 23);
INSERT IGNORE INTO requisite VALUES (28, 1, "CHEM", 205, "prerequisite", 24);
INSERT IGNORE INTO requisite VALUES (29, 1, "MECH", 313, "prerequisite", 25);
INSERT IGNORE INTO requisite VALUES (30, 1, "MECH", 211, "prerequisite", 26);
INSERT IGNORE INTO requisite VALUES (31, 1, "MECH", 221, "prerequisite", 27);
INSERT IGNORE INTO requisite VALUES (32, 1, "ENGR", 213, "prerequisite", 28);
INSERT IGNORE INTO requisite VALUES (33, 1, "ENGR", 233, "prerequisite", 28);
INSERT IGNORE INTO requisite VALUES (34, 1, "ENGR", 243, "prerequisite", 28);
INSERT IGNORE INTO requisite VALUES (35, 1, "ENGR", 244, "prerequisite", 29);
INSERT IGNORE INTO requisite VALUES (36, 1, "MECH", 313, "prerequisite", 29);
INSERT IGNORE INTO requisite VALUES (37, 1, "MECH", 321, "corequisite", 29);
INSERT IGNORE INTO requisite VALUES (38, 1, "MECH", 343, "corequisite", 29);
INSERT IGNORE INTO requisite VALUES (39, 1, "MECH", 441, "antirequisite", 29);
INSERT IGNORE INTO requisite VALUES (40, 1, "ENGR", 251, "prerequisite", 30);
INSERT IGNORE INTO requisite VALUES (41, 1, "ENGR", 311, "prerequisite", 31);
INSERT IGNORE INTO requisite VALUES (42, 1, "ENGR", 361, "prerequisite", 31);
INSERT IGNORE INTO requisite VALUES (43, 1, "ENGR", 361, "prerequisite", 32);
INSERT IGNORE INTO requisite VALUES (44, 1, "PHYS", 205, "prerequisite", 33);
INSERT IGNORE INTO requisite VALUES (45, 1, "ENGR", 311, "corequisite", 33);
INSERT IGNORE INTO requisite VALUES (46, 1, "MECH", 470, "antirequisite", 33);
INSERT IGNORE INTO requisite VALUES (47, 1, "PHYS", 205, "prerequisite", 34);
INSERT IGNORE INTO requisite VALUES (48, 1, "ENGR", 213, "prerequisite", 34);
INSERT IGNORE INTO requisite VALUES (49, 1, "ENGR", 311, "corequisite", 34);
INSERT IGNORE INTO requisite VALUES (50, 1, "ENGR", 243, "equivalent", 34);
INSERT IGNORE INTO requisite VALUES (51, 1, "ENGR", 245, "equivalent", 34);
INSERT IGNORE INTO requisite VALUES (52, 1, "ELEC", 370, "antirequisite", 34);
INSERT IGNORE INTO requisite VALUES (53, 1, "ENGR", 311, "prerequisite", 35);
INSERT IGNORE INTO requisite VALUES (54, 1, "MECH", 370, "prerequisite", 35);
INSERT IGNORE INTO requisite VALUES (55, 1, "ELEC", 372, "antirequisite", 35);
INSERT IGNORE INTO requisite VALUES (56, 1, "AERO", 371, "equivalent", 36);
INSERT IGNORE INTO requisite VALUES (57, 1, "MECH", 370, "equivalent", 36);
INSERT IGNORE INTO requisite VALUES (58, 1, "MECH", 443, "antirequisite", 36);
INSERT IGNORE INTO requisite VALUES (59, 1, "ENCS", 282, "prerequisite", 37);
INSERT IGNORE INTO requisite VALUES (60, 1, "MECH", 311, "prerequisite", 37);
INSERT IGNORE INTO requisite VALUES (61, 1, "MECH", 343, "prerequisite", 37);
INSERT IGNORE INTO requisite VALUES (62, 1, "MECH", 344, "corequisite", 37);
INSERT IGNORE INTO requisite VALUES (63, 1, "ENCS", 282, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (64, 1, "ENGR", 301, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (65, 1, "MECH", 344, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (66, 1, "MECH", 390, "corequisite", 38);
INSERT IGNORE INTO requisite VALUES (67, 1, "75 credits in the program", 000, "prerequisite", 38);


INSERT IGNORE INTO requisite VALUES (177, 1, "INDU", 323, "prerequisite", 126);
INSERT IGNORE INTO requisite VALUES (178, 1, "INDU", 320, "prerequisite", 127);
INSERT IGNORE INTO requisite VALUES (179, 1, "INDU", 420, "antirequisite", 127);
INSERT IGNORE INTO requisite VALUES (180, 1, "INDU", 211, "prerequisite", 128);
INSERT IGNORE INTO requisite VALUES (181, 1, "ENGR", 213, "prerequisite", 128);
INSERT IGNORE INTO requisite VALUES (182, 1, "ENGR", 233, "prerequisite", 128);
INSERT IGNORE INTO requisite VALUES (183, 1, "INDU", 323, "prerequisite", 129);
INSERT IGNORE INTO requisite VALUES (184, 1, "INDU", 430, "antirequisite", 129);
INSERT IGNORE INTO requisite VALUES (185, 1, "ENCS", 282, "prerequisite", 130);
INSERT IGNORE INTO requisite VALUES (186, 1, "ENGR", 301, "corequisite", 130);
INSERT IGNORE INTO requisite VALUES (187, 1, "INDU", 324, "prerequisite", 131);
INSERT IGNORE INTO requisite VALUES (188, 1, "INDU", 442, "antirequisite", 131);
INSERT IGNORE INTO requisite VALUES (189, 1, "ENGR", 371, "prerequisite", 132);
INSERT IGNORE INTO requisite VALUES (190, 1, "ENGR", 371, "prerequisite", 133);
INSERT IGNORE INTO requisite VALUES (191, 1, "MECH", 311, "prerequisite", 134);
INSERT IGNORE INTO requisite VALUES (192, 1, "MECH", 311, "prerequisite", 135);
INSERT IGNORE INTO requisite VALUES (193, 1, "ENGR", 371, "prerequisite", 136);
INSERT IGNORE INTO requisite VALUES (194, 1, "INDU", 311, "corequisite", 137);
INSERT IGNORE INTO requisite VALUES (195, 1, "INDU", 320, "prerequisite", 137);
INSERT IGNORE INTO requisite VALUES (196, 1, "INDU", 320, "prerequisite", 138);
INSERT IGNORE INTO requisite VALUES (197, 1, "MECH", 311, "prerequisite", 139);
INSERT IGNORE INTO requisite VALUES (198, 1, "AERO", 444, "antirequisite", 139);
INSERT IGNORE INTO requisite VALUES (199, 1, "INDU", 472, "prerequisite", 140);
INSERT IGNORE INTO requisite VALUES (200, 1, "ENGR", 371, "prerequisite", 141);
INSERT IGNORE INTO requisite VALUES (201, 1, "INDU", 320, "prerequisite", 142);
INSERT IGNORE INTO requisite VALUES (202, 1, "INDU", 372, "prerequisite", 142);
INSERT IGNORE INTO requisite VALUES (203, 1, "INDU", 311, "prerequisite", 143);
INSERT IGNORE INTO requisite VALUES (204, 1, "INDU", 324, "prerequisite", 143);
INSERT IGNORE INTO requisite VALUES (205, 1, "75 credits in the program", 000, "prerequisite", 144);
INSERT IGNORE INTO requisite VALUES (206, 1, "Permission of the Department Chair", 000, "prerequisite", 145);

INSERT IGNORE INTO requisite VALUES (207, 1, "PHYS", 204, "prerequisite", 146);
INSERT IGNORE INTO requisite VALUES (208, 1, "ENGR", 213, "corequisite", 146);
INSERT IGNORE INTO requisite VALUES (209, 1, "ENCS", 282, "prerequisite", 158);
INSERT IGNORE INTO requisite VALUES (210, 1, "Permission of the Department", 000, "prerequisite", 163);
INSERT IGNORE INTO requisite VALUES (211, 1, "ENGR", 410, "antirequisite", 163);
INSERT IGNORE INTO requisite VALUES (212, 1, "ENCS", 282, "prerequisite", 162);
INSERT IGNORE INTO requisite VALUES (213, 1, "75 credits in the program", 000, "prerequisite", 162);
INSERT IGNORE INTO requisite VALUES (214, 1, "GPA of 3.0 or better", 000, "prerequisite", 162);
INSERT IGNORE INTO requisite VALUES (215, 1, "Permission of the Department", 000, "prerequisite", 162);

INSERT IGNORE INTO requisite VALUES (216, 1, "ENGR", 371, "prerequisite", 125);

INSERT IGNORE INTO requisite VALUES (217, 1, "MATH", 204, "prerequisite", 23);
INSERT IGNORE INTO requisite VALUES (218, 1, "CHEM", 205, "prerequisite", 24);
INSERT IGNORE INTO requisite VALUES (219, 1, "MECH", 313, "prerequisite", 25);
INSERT IGNORE INTO requisite VALUES (220, 1, "MECH", 211, "prerequisite", 26);
INSERT IGNORE INTO requisite VALUES (221, 1, "MECH", 221, "prerequisite", 27);
INSERT IGNORE INTO requisite VALUES (222, 1, "ENGR", 213, "prerequisite", 28);
INSERT IGNORE INTO requisite VALUES (223, 1, "ENGR", 233, "prerequisite", 28);
INSERT IGNORE INTO requisite VALUES (224, 1, "ENGR", 243, "prerequisite", 28);
INSERT IGNORE INTO requisite VALUES (225, 1, "ENGR", 244, "prerequisite", 29);
INSERT IGNORE INTO requisite VALUES (226, 1, "MECH", 313, "prerequisite", 29);
INSERT IGNORE INTO requisite VALUES (227, 1, "MECH", 321, "prerequisite", 29);
INSERT IGNORE INTO requisite VALUES (228, 1, "MECH", 343, "corequisite", 29);
INSERT IGNORE INTO requisite VALUES (229, 1, "MECH", 441, "antirequisite", 29);
INSERT IGNORE INTO requisite VALUES (230, 1, "ENGR", 251, "prerequisite", 30);
INSERT IGNORE INTO requisite VALUES (231, 1, "ENGR", 311, "prerequisite", 31);
INSERT IGNORE INTO requisite VALUES (232, 1, "ENGR", 361, "prerequisite", 31);
INSERT IGNORE INTO requisite VALUES (233, 1, "ENGR", 361, "prerequisite", 32);
INSERT IGNORE INTO requisite VALUES (234, 1, "PHYS", 205, "prerequisite", 33);
INSERT IGNORE INTO requisite VALUES (235, 1, "ENGR", 311, "corequisite", 33);
INSERT IGNORE INTO requisite VALUES (236, 1, "MECH", 470, "antirequisite", 33);
INSERT IGNORE INTO requisite VALUES (237, 1, "Electrical Engineering and Computer Engineering students", 000, "antirequisite", 33);
INSERT IGNORE INTO requisite VALUES (238, 1, "PHYS", 205, "prerequisite", 34);
INSERT IGNORE INTO requisite VALUES (239, 1, "ENGR", 213, "prerequisite", 34);
INSERT IGNORE INTO requisite VALUES (240, 1, "ENGR", 311, "corequisite", 34);
INSERT IGNORE INTO requisite VALUES (241, 1, "ENGR", 245, "equivalent", 34);
INSERT IGNORE INTO requisite VALUES (242, 1, "ENGR", 243, "equivalent", 34);
INSERT IGNORE INTO requisite VALUES (243, 1, "ELEC", 370, "antirequisite", 34);
INSERT IGNORE INTO requisite VALUES (244, 1, "ENGR", 311, "prerequisite", 35);
INSERT IGNORE INTO requisite VALUES (245, 1, "MECH", 370, "prerequisite", 35);
INSERT IGNORE INTO requisite VALUES (246, 1, "ELEC", 372, "antirequisite", 35);
INSERT IGNORE INTO requisite VALUES (247, 1, "AERO", 371, "equivalent", 36);
INSERT IGNORE INTO requisite VALUES (248, 1, "MECH", 370, "equivalent", 36);
INSERT IGNORE INTO requisite VALUES (249, 1, "MECH", 443, "antirequisite", 36);
INSERT IGNORE INTO requisite VALUES (250, 1, "ENCS", 282, "prerequisite", 37);
INSERT IGNORE INTO requisite VALUES (251, 1, "MECH", 311, "prerequisite", 37);
INSERT IGNORE INTO requisite VALUES (252, 1, "MECH", 343, "prerequisite", 37);
INSERT IGNORE INTO requisite VALUES (253, 1, "MECH", 344, "corequisite", 37);
INSERT IGNORE INTO requisite VALUES (254, 1, "75 credits in the program", 000, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (255, 1, "ENCS", 282, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (256, 1, "ENGR", 301, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (257, 1, "MECH", 344, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (258, 1, "MECH", 390, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (259, 1, "Permission from the Department Chair", 000, "prerequisite", 187);
INSERT IGNORE INTO requisite VALUES (260, 1, "ENGR", 311, "prerequisite", 164);
INSERT IGNORE INTO requisite VALUES (261, 1, "AERO", 371, "equivalent", 164);
INSERT IGNORE INTO requisite VALUES (262, 1, "MECH", 370, "equivalent", 164);
INSERT IGNORE INTO requisite VALUES (263, 1, "MECH", 373, "antirequisite", 164);
INSERT IGNORE INTO requisite VALUES (264, 1, "MECH", 313, "prerequisite", 165);
INSERT IGNORE INTO requisite VALUES (265, 1, "MECH", 311, "prerequisite", 199);
INSERT IGNORE INTO requisite VALUES (266, 1, "MECH", 412, "prerequisite", 199);
INSERT IGNORE INTO requisite VALUES (267, 1, "MECH", 215, "prerequisite", 166);
INSERT IGNORE INTO requisite VALUES (268, 1, "MECH", 221, "prerequisite", 200);
INSERT IGNORE INTO requisite VALUES (269, 1, "ENGR", 233, "prerequisite", 167);
INSERT IGNORE INTO requisite VALUES (270, 1, "ENGR", 244, "prerequisite", 167);
INSERT IGNORE INTO requisite VALUES (271, 1, "MECH", 221, "prerequisite", 167);
INSERT IGNORE INTO requisite VALUES (272, 1, "MECH", 221, "prerequisite", 168);
INSERT IGNORE INTO requisite VALUES (273, 1, "MECH", 311, "prerequisite", 169);
INSERT IGNORE INTO requisite VALUES (274, 1, "MECH", 343, "prerequisite", 169);
INSERT IGNORE INTO requisite VALUES (275, 1, "MECH", 311, "prerequisite", 170);
INSERT IGNORE INTO requisite VALUES (276, 1, "ENGR", 233, "prerequisite", 171);
INSERT IGNORE INTO requisite VALUES (277, 1, "ENGR", 244, "prerequisite", 171);
INSERT IGNORE INTO requisite VALUES (278, 1, "MECH", 321, "prerequisite", 171);
INSERT IGNORE INTO requisite VALUES (279, 1, "MECH", 375, "prerequisite", 172);
INSERT IGNORE INTO requisite VALUES (280, 1, "MECH", 343, "prerequisite", 173);
INSERT IGNORE INTO requisite VALUES (281, 1, "MECH", 447, "prerequisite", 174);
INSERT IGNORE INTO requisite VALUES (282, 1, "MECH", 351, "prerequisite", 175);
INSERT IGNORE INTO requisite VALUES (283, 1, "MECH", 352, "prerequisite", 175);
INSERT IGNORE INTO requisite VALUES (284, 1, "MECH", 361, "prerequisite", 175);
INSERT IGNORE INTO requisite VALUES (285, 1, "MECH", 352, "prerequisite", 176);
INSERT IGNORE INTO requisite VALUES (286, 1, "MECH", 351, "prerequisite", 177);
INSERT IGNORE INTO requisite VALUES (287, 1, "MECH", 361, "prerequisite", 177);
INSERT IGNORE INTO requisite VALUES (288, 1, "ENGR", 244, "prerequisite", 178);
INSERT IGNORE INTO requisite VALUES (289, 1, "ENGR", 391, "prerequisite", 178);
INSERT IGNORE INTO requisite VALUES (290, 1, "MECH", 361, "prerequisite", 179);
INSERT IGNORE INTO requisite VALUES (291, 1, "MECH", 343, "prerequisite", 180);
INSERT IGNORE INTO requisite VALUES (292, 1, "MECH", 361, "prerequisite", 180);
INSERT IGNORE INTO requisite VALUES (293, 1, "MECH", 344, "corequisite", 180);
INSERT IGNORE INTO requisite VALUES (294, 1, "MECH", 371, "corequisite", 180);
INSERT IGNORE INTO requisite VALUES (295, 1, "ENGR", 361, "prerequisite", 181);
INSERT IGNORE INTO requisite VALUES (296, 1, "MECH", 371, "equivalent", 181);
INSERT IGNORE INTO requisite VALUES (297, 1, "ELEC", 372, "equivalent", 181);
INSERT IGNORE INTO requisite VALUES (298, 1, "ENGR", 311, "prerequisite", 182);
INSERT IGNORE INTO requisite VALUES (299, 1, "MECH", 368, "prerequisite", 182);
INSERT IGNORE INTO requisite VALUES (300, 1, "MECH", 215, "prerequisite", 183);
INSERT IGNORE INTO requisite VALUES (301, 1, "MECH", 371, "corequisite", 183);
INSERT IGNORE INTO requisite VALUES (302, 1, "ELEC", 372, "equivalent", 184);
INSERT IGNORE INTO requisite VALUES (303, 1, "MECH", 371, "equivalent", 185);
INSERT IGNORE INTO requisite VALUES (304, 1, "MECH", 313, "prerequisite", 186);
INSERT IGNORE INTO requisite VALUES (305, 1, "AERO", 390, "corequisite", 186);
INSERT IGNORE INTO requisite VALUES (306, 1, "MECH", 390, "corequisite", 186);

-- id, core, course_id, degree_id
INSERT IGNORE INTO degree_requirement VALUES (1, "Software Engineering Core", 1, 1);
INSERT IGNORE INTO degree_requirement VALUES (2, "Software Engineering Core", 2, 1);
INSERT IGNORE INTO degree_requirement VALUES (3, "Software Engineering Core", 3, 1);
INSERT IGNORE INTO degree_requirement VALUES (4, "Software Engineering Core", 4, 1);
INSERT IGNORE INTO degree_requirement VALUES (5, "Software Engineering Core", 5, 1);
INSERT IGNORE INTO degree_requirement VALUES (6, "Software Engineering Core", 6, 1);
INSERT IGNORE INTO degree_requirement VALUES (7, "Software Engineering Core", 7, 1);
INSERT IGNORE INTO degree_requirement VALUES (8, "Software Engineering Core", 8, 1);
INSERT IGNORE INTO degree_requirement VALUES (9, "Software Engineering Core", 9, 1);
INSERT IGNORE INTO degree_requirement VALUES (10, "Software Engineering Core", 10, 1);
INSERT IGNORE INTO degree_requirement VALUES (11, "Software Engineering Core", 11, 1);
INSERT IGNORE INTO degree_requirement VALUES (12, "Software Engineering Core", 12, 1);
INSERT IGNORE INTO degree_requirement VALUES (13, "Software Engineering Core", 13, 1);
INSERT IGNORE INTO degree_requirement VALUES (14, "Software Engineering Core", 14, 1);
INSERT IGNORE INTO degree_requirement VALUES (16, "Computer Science Electives", 15, 1);
INSERT IGNORE INTO degree_requirement VALUES (17, "Mechanical Engineering Core", 16, 5);
INSERT IGNORE INTO degree_requirement VALUES (18, "Mechanical Engineering Core", 17, 5);
INSERT IGNORE INTO degree_requirement VALUES (19, "Mechanical Engineering Core", 18, 5);
INSERT IGNORE INTO degree_requirement VALUES (20, "Mechanical Engineering Core", 19, 5);
INSERT IGNORE INTO degree_requirement VALUES (21, "Mechanical Engineering Core", 20, 5);
INSERT IGNORE INTO degree_requirement VALUES (22, "Mechanical Engineering Core", 21, 5);
INSERT IGNORE INTO degree_requirement VALUES (23, "Mechanical Engineering Core", 22, 5);
INSERT IGNORE INTO degree_requirement VALUES (24, "Mechanical Engineering Core", 23, 5);
INSERT IGNORE INTO degree_requirement VALUES (25, "Mechanical Engineering Core", 24, 5);
INSERT IGNORE INTO degree_requirement VALUES (26, "Mechanical Engineering Core", 25, 5);
INSERT IGNORE INTO degree_requirement VALUES (27, "Mechanical Engineering Core", 26, 5);
INSERT IGNORE INTO degree_requirement VALUES (28, "Mechanical Engineering Core", 27, 5);
INSERT IGNORE INTO degree_requirement VALUES (29, "Mechanical Engineering Core", 28, 5);
INSERT IGNORE INTO degree_requirement VALUES (30, "Mechanical Engineering Core", 29, 5);
INSERT IGNORE INTO degree_requirement VALUES (31, "Mechanical Engineering Core", 30, 5);
INSERT IGNORE INTO degree_requirement VALUES (32, "Mechanical Engineering Core", 31, 5);
INSERT IGNORE INTO degree_requirement VALUES (33, "Mechanical Engineering Core", 32, 5);
INSERT IGNORE INTO degree_requirement VALUES (34, "Mechanical Engineering Core", 33, 5);
INSERT IGNORE INTO degree_requirement VALUES (35, "Mechanical Engineering Core", 34, 5);
INSERT IGNORE INTO degree_requirement VALUES (36, "Mechanical Engineering Core", 35, 5);
INSERT IGNORE INTO degree_requirement VALUES (37, "Mechanical Engineering Core", 36, 5);
INSERT IGNORE INTO degree_requirement VALUES (38, "Mechanical Engineering Core", 37, 5);
INSERT IGNORE INTO degree_requirement VALUES (39, "Mechanical Engineering Core", 38, 5);
INSERT IGNORE INTO degree_requirement VALUES (40, "Mechanical Engineering Core", 39, 5);
INSERT IGNORE INTO degree_requirement VALUES (41, "Software Engineering Core", 40, 1);
INSERT IGNORE INTO degree_requirement VALUES (42, "Computer Science Core", 41, 6);
INSERT IGNORE INTO degree_requirement VALUES (43, "Computer Science Core", 42, 6);
INSERT IGNORE INTO degree_requirement VALUES (44, "Computer Science Core", 43, 6);
INSERT IGNORE INTO degree_requirement VALUES (45, "Computer Science Core", 44, 6);
INSERT IGNORE INTO degree_requirement VALUES (46, "Computer Science Core", 45, 6);
INSERT IGNORE INTO degree_requirement VALUES (47, "Computer Science Core", 46, 6);
INSERT IGNORE INTO degree_requirement VALUES (48, "Computer Science Core", 47, 6);
INSERT IGNORE INTO degree_requirement VALUES (49, "Computer Science Core", 48, 6);
INSERT IGNORE INTO degree_requirement VALUES (50, "Computer Science Core", 49, 6);
INSERT IGNORE INTO degree_requirement VALUES (51, "Computer Science Core", 50, 6);
INSERT IGNORE INTO degree_requirement VALUES (52, "Complementary Core", 51, 6);
INSERT IGNORE INTO degree_requirement VALUES (53, "Complementary Core", 52, 6);
INSERT IGNORE INTO degree_requirement VALUES (54, "Mathematics Electives", 53, 6);
INSERT IGNORE INTO degree_requirement VALUES (55, "Mathematics Electives", 54, 6);
INSERT IGNORE INTO degree_requirement VALUES (56, "Mathematics Electives", 55, 6);
INSERT IGNORE INTO degree_requirement VALUES (57, "Mathematics Electives", 56, 6);
INSERT IGNORE INTO degree_requirement VALUES (58, "Mathematics Electives", 57, 6);
INSERT IGNORE INTO degree_requirement VALUES (59, "Mathematics Electives", 58, 6);
INSERT IGNORE INTO degree_requirement VALUES (60, "Mathematics Electives", 59, 6);
INSERT IGNORE INTO degree_requirement VALUES (61, "Mathematics Electives", 60, 6);
INSERT IGNORE INTO degree_requirement VALUES (62, "Mathematics Electives", 61, 6);
INSERT IGNORE INTO degree_requirement VALUES (63, "Mathematics Electives", 62, 6);
INSERT IGNORE INTO degree_requirement VALUES (64, "Mathematics Electives", 63, 6);
INSERT IGNORE INTO degree_requirement VALUES (65, "Mathematics Electives", 64, 6);
INSERT IGNORE INTO degree_requirement VALUES (66, "Mathematics Electives", 65, 6);
INSERT IGNORE INTO degree_requirement VALUES (67, "Mathematics Electives", 66, 6);
INSERT IGNORE INTO degree_requirement VALUES (68, "Mathematics Electives", 67, 6);
INSERT IGNORE INTO degree_requirement VALUES (69, "Mathematics Electives", 68, 6);
INSERT IGNORE INTO degree_requirement VALUES (70, "Computer Games Electives", 69, 6);
INSERT IGNORE INTO degree_requirement VALUES (71, "Computer Games Electives", 70, 6);
INSERT IGNORE INTO degree_requirement VALUES (72, "Computer Games Electives", 71, 6);
INSERT IGNORE INTO degree_requirement VALUES (73, "Computer Games Electives", 72, 6);
INSERT IGNORE INTO degree_requirement VALUES (74, "Computer Games Electives", 73, 6);
INSERT IGNORE INTO degree_requirement VALUES (75, "Computer Games Electives", 74, 6);
INSERT IGNORE INTO degree_requirement VALUES (76, "Computer Games Electives", 75, 6);
INSERT IGNORE INTO degree_requirement VALUES (77, "Computer Games Electives", 76, 6);
INSERT IGNORE INTO degree_requirement VALUES (78, "Web Services and Applications Electives", 70, 6);
INSERT IGNORE INTO degree_requirement VALUES (79, "Web Services and Applications Electives", 77, 6);
INSERT IGNORE INTO degree_requirement VALUES (80, "Web Services and Applications Electives", 78, 6);
INSERT IGNORE INTO degree_requirement VALUES (81, "Web Services and Applications Electives", 79, 6);
INSERT IGNORE INTO degree_requirement VALUES (82, "Web Services and Applications Electives", 80, 6);
INSERT IGNORE INTO degree_requirement VALUES (83, "Web Services and Applications Electives", 81, 6);
INSERT IGNORE INTO degree_requirement VALUES (84, "Computer Systems Electives", 81, 6);
INSERT IGNORE INTO degree_requirement VALUES (85, "Computer Systems Electives", 69, 6);
INSERT IGNORE INTO degree_requirement VALUES (86, "Computer Systems Electives", 82, 6);
INSERT IGNORE INTO degree_requirement VALUES (87, "Computer Systems Electives", 83, 6);
INSERT IGNORE INTO degree_requirement VALUES (88, "Computer Systems Electives", 76, 6);
INSERT IGNORE INTO degree_requirement VALUES (89, "Computer Systems Electives", 84, 6);
INSERT IGNORE INTO degree_requirement VALUES (90, "Computer Systems Electives", 80, 6);
INSERT IGNORE INTO degree_requirement VALUES (91, "Software Systems Core", 81, 6);
INSERT IGNORE INTO degree_requirement VALUES (92, "Software Systems Core", 53, 6);
INSERT IGNORE INTO degree_requirement VALUES (93, "Software Systems Core", 70, 6);
INSERT IGNORE INTO degree_requirement VALUES (94, "Software Systems Core", 54, 6);
INSERT IGNORE INTO degree_requirement VALUES (95, "Software Systems Core", 76, 6);
INSERT IGNORE INTO degree_requirement VALUES (96, "Software Systems Core", 85, 6);
INSERT IGNORE INTO degree_requirement VALUES (97, "Information Systems Electives", 86, 6);
INSERT IGNORE INTO degree_requirement VALUES (98, "Information Systems Electives", 87, 6);
INSERT IGNORE INTO degree_requirement VALUES (99, "Information Systems Electives", 88, 6);
INSERT IGNORE INTO degree_requirement VALUES (100, "Information Systems Electives", 89, 6);
INSERT IGNORE INTO degree_requirement VALUES (101, "Information Systems Electives", 90, 6);
INSERT IGNORE INTO degree_requirement VALUES (102, "Information Systems Electives", 91, 6);
INSERT IGNORE INTO degree_requirement VALUES (103, "Information Systems Electives", 92, 6);
INSERT IGNORE INTO degree_requirement VALUES (104, "Information Systems Electives", 93, 6);
INSERT IGNORE INTO degree_requirement VALUES (105, "Information Systems Electives", 94, 6);
INSERT IGNORE INTO degree_requirement VALUES (106, "Information Systems Electives", 70, 6);
INSERT IGNORE INTO degree_requirement VALUES (107, "Information Systems Electives", 95, 6);
INSERT IGNORE INTO degree_requirement VALUES (108, "Information Systems Electives", 96, 6);
INSERT IGNORE INTO degree_requirement VALUES (109, "Information Systems Electives", 97, 6);
INSERT IGNORE INTO degree_requirement VALUES (110, "Minor in Computer Science", 41, 6);
INSERT IGNORE INTO degree_requirement VALUES (111, "Minor in Computer Science", 42, 6);
INSERT IGNORE INTO degree_requirement VALUES (112, "Minor in Computer Science", 44, 6);
INSERT IGNORE INTO degree_requirement VALUES (113, "Minor in Computer Science", 45, 6);
INSERT IGNORE INTO degree_requirement VALUES (114, "Minor in Computer Science", 49, 6);
INSERT IGNORE INTO degree_requirement VALUES (115, "Computer Science Group", 42, 1);
INSERT IGNORE INTO degree_requirement VALUES (116, "Computer Science Group", 44, 1);
INSERT IGNORE INTO degree_requirement VALUES (117, "Computer Science Group", 45, 1);
INSERT IGNORE INTO degree_requirement VALUES (118, "Computer Science Group", 46, 1);
INSERT IGNORE INTO degree_requirement VALUES (119, "Computer Science Group", 47, 1);
INSERT IGNORE INTO degree_requirement VALUES (120, "Computer Science Group", 48, 1);
INSERT IGNORE INTO degree_requirement VALUES (121, "Computer Science Group", 49, 1);
INSERT IGNORE INTO degree_requirement VALUES (122, "Basic and Natural Science Courses", 98, 1);
INSERT IGNORE INTO degree_requirement VALUES (123, "Basic and Natural Science Courses", 99, 1);
INSERT IGNORE INTO degree_requirement VALUES (124, "Basic and Natural Science Courses", 100, 1);
INSERT IGNORE INTO degree_requirement VALUES (125, "Basic and Natural Science Courses", 101, 1);
INSERT IGNORE INTO degree_requirement VALUES (126, "Basic and Natural Science Courses", 102, 1);
INSERT IGNORE INTO degree_requirement VALUES (127, "Basic and Natural Science Courses", 103, 1);
INSERT IGNORE INTO degree_requirement VALUES (128, "Basic and Natural Science Courses", 16, 1);
INSERT IGNORE INTO degree_requirement VALUES (129, "Basic and Natural Science Courses", 17, 1);
INSERT IGNORE INTO degree_requirement VALUES (130, "Basic and Natural Science Courses", 19, 1);
INSERT IGNORE INTO degree_requirement VALUES (131, "Basic and Natural Science Courses", 21, 1);
INSERT IGNORE INTO degree_requirement VALUES (132, "Basic and Natural Science Courses", 24, 1);
INSERT IGNORE INTO degree_requirement VALUES (133, "Basic and Natural Science Courses", 104, 1);
INSERT IGNORE INTO degree_requirement VALUES (134, "Basic and Natural Science Courses", 105, 1);
INSERT IGNORE INTO degree_requirement VALUES (135, "Basic and Natural Science Courses", 106, 1);
INSERT IGNORE INTO degree_requirement VALUES (136, "Computer Games (CG) Option", 69, 1);
INSERT IGNORE INTO degree_requirement VALUES (137, "Computer Games (CG) Option", 71, 1);
INSERT IGNORE INTO degree_requirement VALUES (138, "Computer Games (CG) Option", 72, 1);
INSERT IGNORE INTO degree_requirement VALUES (139, "Computer Games (CG) Option", 73, 1);
INSERT IGNORE INTO degree_requirement VALUES (140, "Computer Games (CG) Option", 74, 1);
INSERT IGNORE INTO degree_requirement VALUES (141, "Computer Games (CG) Option", 75, 1);
INSERT IGNORE INTO degree_requirement VALUES (142, "Web Services and Applications (WSA) Option", 70, 1);
INSERT IGNORE INTO degree_requirement VALUES (143, "Web Services and Applications (WSA) Option", 76, 1);
INSERT IGNORE INTO degree_requirement VALUES (144, "Web Services and Applications (WSA) Option", 77, 1);
INSERT IGNORE INTO degree_requirement VALUES (145, "Web Services and Applications (WSA) Option", 79, 1);
INSERT IGNORE INTO degree_requirement VALUES (146, "Web Services and Applications (WSA) Option", 81, 1);
INSERT IGNORE INTO degree_requirement VALUES (147, "Real-Time, Embedded, and Avionics Software (REA) Option", 194, 1);
INSERT IGNORE INTO degree_requirement VALUES (148, "Real-Time, Embedded, and Avionics Software (REA) Option", 195, 1);
INSERT IGNORE INTO degree_requirement VALUES (149, "Real-Time, Embedded, and Avionics Software (REA) Option", 109, 1);
INSERT IGNORE INTO degree_requirement VALUES (150, "Real-Time, Embedded, and Avionics Software (REA) Option", 69, 1);
INSERT IGNORE INTO degree_requirement VALUES (151, "Real-Time, Embedded, and Avionics Software (REA) Option", 110, 1);
INSERT IGNORE INTO degree_requirement VALUES (152, "Real-Time, Embedded, and Avionics Software (REA) Option", 84, 1);
INSERT IGNORE INTO degree_requirement VALUES (153, "Real-Time, Embedded, and Avionics Software (REA) Option", 111, 1);
INSERT IGNORE INTO degree_requirement VALUES (154, "Electives", 69, 1);
INSERT IGNORE INTO degree_requirement VALUES (155, "Electives", 70, 1);
INSERT IGNORE INTO degree_requirement VALUES (156, "Electives", 71, 1);
INSERT IGNORE INTO degree_requirement VALUES (157, "Electives", 112, 1);
INSERT IGNORE INTO degree_requirement VALUES (158, "Electives", 82, 1);
INSERT IGNORE INTO degree_requirement VALUES (159, "Electives", 83, 1);
INSERT IGNORE INTO degree_requirement VALUES (160, "Electives", 113, 1);
INSERT IGNORE INTO degree_requirement VALUES (161, "Electives", 76, 1);
INSERT IGNORE INTO degree_requirement VALUES (162, "Electives", 114, 1);
INSERT IGNORE INTO degree_requirement VALUES (163, "Electives", 85, 1);
INSERT IGNORE INTO degree_requirement VALUES (164, "Electives", 73, 1);
INSERT IGNORE INTO degree_requirement VALUES (165, "Electives", 115, 1);
INSERT IGNORE INTO degree_requirement VALUES (166, "Electives", 116, 1);
INSERT IGNORE INTO degree_requirement VALUES (167, "Electives", 117, 1);
INSERT IGNORE INTO degree_requirement VALUES (168, "Electives", 77, 1);
INSERT IGNORE INTO degree_requirement VALUES (169, "Electives", 118, 1);
INSERT IGNORE INTO degree_requirement VALUES (170, "Electives", 84, 1);
INSERT IGNORE INTO degree_requirement VALUES (171, "Electives", 80, 1);
INSERT IGNORE INTO degree_requirement VALUES (172, "Electives", 119, 1);
INSERT IGNORE INTO degree_requirement VALUES (173, "Electives", 120, 1);
INSERT IGNORE INTO degree_requirement VALUES (174, "Electives", 121, 1);
INSERT IGNORE INTO degree_requirement VALUES (175, "Electives", 122, 1);
INSERT IGNORE INTO degree_requirement VALUES (176, "Electives", 161, 1);

INSERT IGNORE INTO degree_requirement VALUES (178, "Industrial Engineering Core", 19, 7);
INSERT IGNORE INTO degree_requirement VALUES (179, "Industrial Engineering Core", 20, 7);
INSERT IGNORE INTO degree_requirement VALUES (180, "Industrial Engineering Core", 124, 7);
INSERT IGNORE INTO degree_requirement VALUES (181, "Industrial Engineering Core", 125, 7);
INSERT IGNORE INTO degree_requirement VALUES (182, "Industrial Engineering Core", 126, 7);
INSERT IGNORE INTO degree_requirement VALUES (183, "Industrial Engineering Core", 127, 7);
INSERT IGNORE INTO degree_requirement VALUES (184, "Industrial Engineering Core", 128, 7);
INSERT IGNORE INTO degree_requirement VALUES (185, "Industrial Engineering Core", 129, 7);
INSERT IGNORE INTO degree_requirement VALUES (186, "Industrial Engineering Core", 130, 7);
INSERT IGNORE INTO degree_requirement VALUES (187, "Industrial Engineering Core", 131, 7);
INSERT IGNORE INTO degree_requirement VALUES (188, "Industrial Engineering Core", 132, 7);
INSERT IGNORE INTO degree_requirement VALUES (189, "Industrial Engineering Core", 133, 7);
INSERT IGNORE INTO degree_requirement VALUES (190, "Industrial Engineering Core", 135, 7);
INSERT IGNORE INTO degree_requirement VALUES (191, "Industrial Engineering Core", 136, 7);
INSERT IGNORE INTO degree_requirement VALUES (192, "Industrial Engineering Core", 137, 7);
INSERT IGNORE INTO degree_requirement VALUES (193, "Industrial Engineering Core", 138, 7);
INSERT IGNORE INTO degree_requirement VALUES (194, "Industrial Engineering Core", 144, 7);
INSERT IGNORE INTO degree_requirement VALUES (195, "Industrial Engineering Core", 22, 7);
INSERT IGNORE INTO degree_requirement VALUES (196, "Industrial Engineering Core", 23, 7);
INSERT IGNORE INTO degree_requirement VALUES (197, "Industrial Engineering Core", 24, 7);
INSERT IGNORE INTO degree_requirement VALUES (198, "Industrial Engineering Core", 25, 7);
INSERT IGNORE INTO degree_requirement VALUES (199, "Industrial Engineering Core", 26, 7);
INSERT IGNORE INTO degree_requirement VALUES (200, "Indu Science Core", 98, 7);
INSERT IGNORE INTO degree_requirement VALUES (201, "Indu Science Core", 99, 7);
INSERT IGNORE INTO degree_requirement VALUES (202, "Indu Science Core", 100, 7);
INSERT IGNORE INTO degree_requirement VALUES (203, "Indu Science Core", 101, 7);
INSERT IGNORE INTO degree_requirement VALUES (204, "Indu Science Core", 151, 7);
INSERT IGNORE INTO degree_requirement VALUES (205, "Indu Science Core", 152, 7);
INSERT IGNORE INTO degree_requirement VALUES (206, "Indu Science Core", 104, 7);
INSERT IGNORE INTO degree_requirement VALUES (207, "Indu Science Core", 154, 7);
INSERT IGNORE INTO degree_requirement VALUES (208, "Indu Science Core", 155, 7);
INSERT IGNORE INTO degree_requirement VALUES (209, "Indu Science Core", 105, 7);
INSERT IGNORE INTO degree_requirement VALUES (210, "Indu Science Core", 106, 7);
INSERT IGNORE INTO degree_requirement VALUES (211, "Indu Electives", 158, 7);
INSERT IGNORE INTO degree_requirement VALUES (212, "Indu Electives", 159, 7);
INSERT IGNORE INTO degree_requirement VALUES (213, "Indu Electives", 160, 7);
INSERT IGNORE INTO degree_requirement VALUES (214, "Indu Electives", 21, 7);
INSERT IGNORE INTO degree_requirement VALUES (215, "Indu Electives", 136, 7);
INSERT IGNORE INTO degree_requirement VALUES (216, "Indu Electives", 137, 7);
INSERT IGNORE INTO degree_requirement VALUES (217, "Indu Electives", 134, 7);
INSERT IGNORE INTO degree_requirement VALUES (218, "Indu Electives", 139, 7);
INSERT IGNORE INTO degree_requirement VALUES (219, "Indu Electives", 140, 7);
INSERT IGNORE INTO degree_requirement VALUES (220, "Indu Electives", 141, 7);
INSERT IGNORE INTO degree_requirement VALUES (221, "Indu Electives", 142, 7);
INSERT IGNORE INTO degree_requirement VALUES (222, "Indu Electives", 143, 7);
INSERT IGNORE INTO degree_requirement VALUES (223, "Indu Electives", 145, 7);
INSERT IGNORE INTO degree_requirement VALUES (224, "Indu Electives", 163, 7);
INSERT IGNORE INTO degree_requirement VALUES (225, "Indu Electives", 27, 7);
INSERT IGNORE INTO degree_requirement VALUES (226, "Indu Electives", 34, 7);
INSERT IGNORE INTO degree_requirement VALUES (227, "Indu Electives", 35, 7);
INSERT IGNORE INTO degree_requirement VALUES (228, "Indu Electives", 165, 7);
INSERT IGNORE INTO degree_requirement VALUES (229, "Indu Electives", 166, 7);
INSERT IGNORE INTO degree_requirement VALUES (230, "Indu Electives", 200, 7);
INSERT IGNORE INTO degree_requirement VALUES (231, "Indu Electives", 168, 7);
INSERT IGNORE INTO degree_requirement VALUES (232, "Indu Electives", 170, 7);
INSERT IGNORE INTO degree_requirement VALUES (233, "Aerospace", 188, 5);
INSERT IGNORE INTO degree_requirement VALUES (234, "Aerospace", 190, 5);
INSERT IGNORE INTO degree_requirement VALUES (235, "Aerospace", 191, 5);
INSERT IGNORE INTO degree_requirement VALUES (236, "Aerospace", 192, 5);
INSERT IGNORE INTO degree_requirement VALUES (237, "Aerospace", 193, 5);
INSERT IGNORE INTO degree_requirement VALUES (238, "Aerospace", 194, 5);
INSERT IGNORE INTO degree_requirement VALUES (239, "Aerospace", 195, 5);
INSERT IGNORE INTO degree_requirement VALUES (240, "Aerospace", 196, 5);
INSERT IGNORE INTO degree_requirement VALUES (241, "Aerospace", 197, 5);
INSERT IGNORE INTO degree_requirement VALUES (242, "Aerospace", 198, 5);
INSERT IGNORE INTO degree_requirement VALUES (243, "Aerospace", 161, 5);
INSERT IGNORE INTO degree_requirement VALUES (244, "Aerospace", 162, 5);
INSERT IGNORE INTO degree_requirement VALUES (245, "Design and Manufacturing", 161, 5);
INSERT IGNORE INTO degree_requirement VALUES (246, "Design and Manufacturing", 162, 5);
INSERT IGNORE INTO degree_requirement VALUES (247, "Design and Manufacturing", 133, 5);
INSERT IGNORE INTO degree_requirement VALUES (248, "Design and Manufacturing", 134, 5);
INSERT IGNORE INTO degree_requirement VALUES (249, "Design and Manufacturing", 135, 5);
INSERT IGNORE INTO degree_requirement VALUES (250, "Design and Manufacturing", 139, 5);
INSERT IGNORE INTO degree_requirement VALUES (251, "Design and Manufacturing", 165, 5);
INSERT IGNORE INTO degree_requirement VALUES (252, "Design and Manufacturing", 199, 5);
INSERT IGNORE INTO degree_requirement VALUES (253, "Design and Manufacturing", 200, 5);
INSERT IGNORE INTO degree_requirement VALUES (254, "Design and Manufacturing", 167, 5);
INSERT IGNORE INTO degree_requirement VALUES (255, "Design and Manufacturing", 168, 5);
INSERT IGNORE INTO degree_requirement VALUES (256, "Design and Manufacturing", 169, 5);
INSERT IGNORE INTO degree_requirement VALUES (257, "Design and Manufacturing", 170, 5);
INSERT IGNORE INTO degree_requirement VALUES (258, "Design and Manufacturing", 180, 5);
INSERT IGNORE INTO degree_requirement VALUES (259, "Design and Manufacturing", 186, 5);
INSERT IGNORE INTO degree_requirement VALUES (260, "Design and Manufacturing", 187, 5);
INSERT IGNORE INTO degree_requirement VALUES (261, "Systems and Mechatronics", 194, 5);
INSERT IGNORE INTO degree_requirement VALUES (262, "Systems and Mechatronics", 195, 5);
INSERT IGNORE INTO degree_requirement VALUES (263, "Systems and Mechatronics", 161, 5);
INSERT IGNORE INTO degree_requirement VALUES (264, "Systems and Mechatronics", 162, 5);
INSERT IGNORE INTO degree_requirement VALUES (265, "Systems and Mechatronics", 164, 5);
INSERT IGNORE INTO degree_requirement VALUES (266, "Systems and Mechatronics", 166, 5);
INSERT IGNORE INTO degree_requirement VALUES (267, "Systems and Mechatronics", 175, 5);
INSERT IGNORE INTO degree_requirement VALUES (268, "Systems and Mechatronics", 179, 5);
INSERT IGNORE INTO degree_requirement VALUES (269, "Systems and Mechatronics", 180, 5);
INSERT IGNORE INTO degree_requirement VALUES (270, "Systems and Mechatronics", 181, 5);
INSERT IGNORE INTO degree_requirement VALUES (271, "Systems and Mechatronics", 187, 5);
INSERT IGNORE INTO degree_requirement VALUES (272, "Thermo-Fluids and Propulsion", 190, 5);
INSERT IGNORE INTO degree_requirement VALUES (273, "Thermo-Fluids and Propulsion", 191, 5);
INSERT IGNORE INTO degree_requirement VALUES (274, "Thermo-Fluids and Propulsion", 193, 5);
INSERT IGNORE INTO degree_requirement VALUES (275, "Thermo-Fluids and Propulsion", 161, 5);
INSERT IGNORE INTO degree_requirement VALUES (276, "Thermo-Fluids and Propulsion", 162, 5);
INSERT IGNORE INTO degree_requirement VALUES (277, "Thermo-Fluids and Propulsion", 164, 5);
INSERT IGNORE INTO degree_requirement VALUES (278, "Thermo-Fluids and Propulsion", 166, 5);
INSERT IGNORE INTO degree_requirement VALUES (279, "Thermo-Fluids and Propulsion", 175, 5);
INSERT IGNORE INTO degree_requirement VALUES (280, "Thermo-Fluids and Propulsion", 176, 5);
INSERT IGNORE INTO degree_requirement VALUES (281, "Thermo-Fluids and Propulsion", 179, 5);
INSERT IGNORE INTO degree_requirement VALUES (282, "Thermo-Fluids and Propulsion", 180, 5);
INSERT IGNORE INTO degree_requirement VALUES (283, "Thermo-Fluids and Propulsion", 181, 5);
INSERT IGNORE INTO degree_requirement VALUES (284, "Thermo-Fluids and Propulsion", 187, 5);
INSERT IGNORE INTO degree_requirement VALUES (285, "Vehicle Systems", 161, 5);
INSERT IGNORE INTO degree_requirement VALUES (286, "Vehicle Systems", 162, 5);
INSERT IGNORE INTO degree_requirement VALUES (287, "Vehicle Systems", 164, 5);
INSERT IGNORE INTO degree_requirement VALUES (288, "Vehicle Systems", 166, 5);
INSERT IGNORE INTO degree_requirement VALUES (289, "Vehicle Systems", 172, 5);
INSERT IGNORE INTO degree_requirement VALUES (290, "Vehicle Systems", 173, 5);
INSERT IGNORE INTO degree_requirement VALUES (291, "Vehicle Systems", 174, 5);
INSERT IGNORE INTO degree_requirement VALUES (292, "Vehicle Systems", 177, 5);
INSERT IGNORE INTO degree_requirement VALUES (293, "Vehicle Systems", 184, 5);
INSERT IGNORE INTO degree_requirement VALUES (294, "Vehicle Systems", 187, 5);
INSERT IGNORE INTO degree_requirement VALUES (295, "Stress Analysis", 189, 5);
INSERT IGNORE INTO degree_requirement VALUES (296, "Stress Analysis", 197, 5);
INSERT IGNORE INTO degree_requirement VALUES (297, "Stress Analysis", 161, 5);
INSERT IGNORE INTO degree_requirement VALUES (298, "Stress Analysis", 162, 5);
INSERT IGNORE INTO degree_requirement VALUES (299, "Stress Analysis", 164, 5);
INSERT IGNORE INTO degree_requirement VALUES (300, "Stress Analysis", 165, 5);
INSERT IGNORE INTO degree_requirement VALUES (301, "Stress Analysis", 166, 5);
INSERT IGNORE INTO degree_requirement VALUES (302, "Stress Analysis", 167, 5);
INSERT IGNORE INTO degree_requirement VALUES (303, "Stress Analysis", 171, 5);
INSERT IGNORE INTO degree_requirement VALUES (304, "Stress Analysis", 178, 5);
INSERT IGNORE INTO degree_requirement VALUES (305, "Stress Analysis", 187, 5);

-- id, body, section_id, section_title, section_type, department_id, faculty_id
INSERT IGNORE INTO calendar VALUES (1, 'Both major and minor programs in Management Information Systems can be found in the John Molson School of Business Section of the Undergraduate Calendar, §61. The Faculty of Fine Arts and the Department of Computer Science and Software Engineering offer complementary major programs. Students who take the Computer Applications Option (see §71.70.2 above) can also take the Major in Computation Arts and Computer Science (see §71.80, and the Fine Arts Section, §81) or the Joint Major in Mathematics and Statistics and Computer Applications (see §71.85, and the Mathematics and Statistics Section, §31.200).', '71.70.6', 'Programs Related to Computer Science', 'general',  1);
INSERT IGNORE INTO calendar VALUES (2, 'Students employed full‑time in a computer science position during their non‑study terms may have this Industrial Experience listed on their official transcript and student record, provided they successfully complete the Reflective Learning course associated with this work term. Students may only register for these courses with the permission of the Faculty. The Industrial Experience terms COMP 107 and 207 carry no credit value and are used to indicate that the student is on an Industrial Experience term. The COMP 108 and 208 Industrial Experience Reflective Learning courses are worth three credits and are marked on a pass/fail basis. They are above and beyond the credit requirements of the student’s program and are not transferable nor are they included in the full‑ or part‑time assessment status. Students studying for a co‑op work term or CIADI term should not register for these Industrial Experience and Reflective Learning courses.', '71.70.7', 'Industrial Experience and Reflective Learning Courses', 'general', 4);
INSERT IGNORE INTO calendar VALUES (3, "Please note that the current version of the Undergraduate Calendar is up to date as of February 2019.", "71.60", "Engineering Course Descriptions", "", 8);

-- id, section, target_id, target_type
INSERT IGNORE INTO section VALUES (1, '71.70.9', 1, "course");
INSERT IGNORE INTO section VALUES (2, '71.70.9', 2, "course");
INSERT IGNORE INTO section VALUES (3, '71.70.9', 3, "course");
INSERT IGNORE INTO section VALUES (4, '71.70.9', 4, "course");
INSERT IGNORE INTO section VALUES (5, '71.70.9', 5, "course");
INSERT IGNORE INTO section VALUES (6, '71.70.9', 6, "course");
INSERT IGNORE INTO section VALUES (7, '71.70.9', 7, "course");
INSERT IGNORE INTO section VALUES (8, '71.70.9', 8, "course");
INSERT IGNORE INTO section VALUES (9, '71.70.9', 9, "course");
INSERT IGNORE INTO section VALUES (10, '71.70.9', 10, "course");
INSERT IGNORE INTO section VALUES (11, '71.70.9', 11, "course");
INSERT IGNORE INTO section VALUES (12, '71.70.9', 12, "course");
INSERT IGNORE INTO section VALUES (13, '71.70.9', 13, "course");
INSERT IGNORE INTO section VALUES (14, '71.70.9', 14, "course");
INSERT IGNORE INTO section VALUES (15, '71.70.9', 15, "course");
INSERT IGNORE INTO section VALUES (16, '71.20.5', 16, "course");
INSERT IGNORE INTO section VALUES (17, '71.20.5', 17, "course");
INSERT IGNORE INTO section VALUES (18, '71.20.5', 18, "course");
INSERT IGNORE INTO section VALUES (19, '71.20.5', 19, "course");
INSERT IGNORE INTO section VALUES (20, '71.20.5', 20, "course");
INSERT IGNORE INTO section VALUES (21, '71.20.5', 21, "course");
INSERT IGNORE INTO section VALUES (40, '71.70.9', 40, "course");
INSERT IGNORE INTO section VALUES (41, '71.70.10', 1, "course");
INSERT IGNORE INTO section VALUES (42, '71.70.10', 2, "course");
INSERT IGNORE INTO section VALUES (43, '71.70.10', 3, "course");
INSERT IGNORE INTO section VALUES (44, '71.70.10', 4, "course");
INSERT IGNORE INTO section VALUES (45, '71.70.10', 5, "course");
INSERT IGNORE INTO section VALUES (46, '71.70.10', 6, "course");
INSERT IGNORE INTO section VALUES (47, '71.70.10', 7, "course");
INSERT IGNORE INTO section VALUES (48, '71.70.10', 8, "course");
INSERT IGNORE INTO section VALUES (49, '71.70.10', 9, "course");
INSERT IGNORE INTO section VALUES (50, '71.70.10', 10, "course");
INSERT IGNORE INTO section VALUES (51, '71.70.10', 11, "course");
INSERT IGNORE INTO section VALUES (52, '71.70.10', 12, "course");
INSERT IGNORE INTO section VALUES (53, '71.70.10', 13, "course");
INSERT IGNORE INTO section VALUES (54, '71.70.10', 14, "course");
INSERT IGNORE INTO section VALUES (55, '71.70.10', 15, "course");
INSERT IGNORE INTO section VALUES (56, '71.60', 16, "course");
INSERT IGNORE INTO section VALUES (57, '71.60', 17, "course");
INSERT IGNORE INTO section VALUES (58, '71.60', 18, "course");
INSERT IGNORE INTO section VALUES (59, '71.60', 19, "course");
INSERT IGNORE INTO section VALUES (60, '71.60', 20, "course");
INSERT IGNORE INTO section VALUES (61, '71.60', 21, "course");
INSERT IGNORE INTO section VALUES (62, '71.60', 22, "course");
INSERT IGNORE INTO section VALUES (63, '71.60', 23, "course");
INSERT IGNORE INTO section VALUES (64, '71.60', 24, "course");
INSERT IGNORE INTO section VALUES (65, '71.60', 25, "course");
INSERT IGNORE INTO section VALUES (66, '71.60', 26, "course");
INSERT IGNORE INTO section VALUES (67, '71.60', 27, "course");
INSERT IGNORE INTO section VALUES (68, '71.60', 28, "course");
INSERT IGNORE INTO section VALUES (69, '71.60', 29, "course");
INSERT IGNORE INTO section VALUES (70, '71.60', 30, "course");
INSERT IGNORE INTO section VALUES (71, '71.60', 31, "course");
INSERT IGNORE INTO section VALUES (72, '71.60', 32, "course");
INSERT IGNORE INTO section VALUES (73, '71.60', 33, "course");
INSERT IGNORE INTO section VALUES (74, '71.60', 34, "course");
INSERT IGNORE INTO section VALUES (75, '71.60', 35, "course");
INSERT IGNORE INTO section VALUES (76, '71.60', 36, "course");
INSERT IGNORE INTO section VALUES (77, '71.60', 37, "course");
INSERT IGNORE INTO section VALUES (78, '71.60', 38, "course");
INSERT IGNORE INTO section VALUES (79, '71.60', 39, "course");
INSERT IGNORE INTO section VALUES (80, '71.60', 40, "course");
INSERT IGNORE INTO section VALUES (81, '71.70.9', 1, "degree");

INSERT IGNORE INTO section VALUES (82, '71.40.2', 7, "degree");
INSERT IGNORE INTO section VALUES (83, '71.40.2', 120, "course");
INSERT IGNORE INTO section VALUES (84, '71.40.2', 19, "course");
INSERT IGNORE INTO section VALUES (85, '71.40.2', 20, "course");
INSERT IGNORE INTO section VALUES (86, '71.40.2', 98, "course");
INSERT IGNORE INTO section VALUES (87, '71.40.2', 99, "course");
INSERT IGNORE INTO section VALUES (88, '71.40.2', 100, "course");
INSERT IGNORE INTO section VALUES (89, '71.40.2', 101, "course");
INSERT IGNORE INTO section VALUES (90, '71.40.2', 102, "course");
INSERT IGNORE INTO section VALUES (91, '71.40.2', 103, "course");
INSERT IGNORE INTO section VALUES (92, '71.40.2', 104, "course");
INSERT IGNORE INTO section VALUES (93, '71.40.2', 105, "course");
INSERT IGNORE INTO section VALUES (94, '71.40.2', 105, "course");
INSERT IGNORE INTO section VALUES (95, '71.40.2', 106, "course");
INSERT IGNORE INTO section VALUES (96, '71.40.2', 107, "course");
INSERT IGNORE INTO section VALUES (97, '71.40.2', 109, "course");
INSERT IGNORE INTO section VALUES (98, '71.40.2', 110, "course");
INSERT IGNORE INTO section VALUES (99, '71.40.2', 112, "course");
INSERT IGNORE INTO section VALUES (100, '71.40.2', 118, "course");
INSERT IGNORE INTO section VALUES (101, '71.40.2', 22, "course");
INSERT IGNORE INTO section VALUES (102, '71.40.2', 23, "course");
INSERT IGNORE INTO section VALUES (103, '71.40.2', 24, "course");
INSERT IGNORE INTO section VALUES (104, '71.40.2', 25, "course");
INSERT IGNORE INTO section VALUES (105, '71.40.2', 26, "course");
INSERT IGNORE INTO section VALUES (106, '71.40.2', 122, "course");
INSERT IGNORE INTO section VALUES (107, '71.40.2', 161, "course");
INSERT IGNORE INTO section VALUES (108, '71.40.2', 124, "course");
INSERT IGNORE INTO section VALUES (109, '71.40.2', 125, "course");
INSERT IGNORE INTO section VALUES (110, '71.40.2', 126, "course");
INSERT IGNORE INTO section VALUES (111, '71.40.2', 127, "course");
INSERT IGNORE INTO section VALUES (112, '71.40.2', 128, "course");
INSERT IGNORE INTO section VALUES (113, '71.40.2', 129, "course");
INSERT IGNORE INTO section VALUES (114, '71.40.2', 130, "course");
INSERT IGNORE INTO section VALUES (115, '71.40.2', 131, "course");
INSERT IGNORE INTO section VALUES (116, '71.40.2', 132, "course");
INSERT IGNORE INTO section VALUES (117, '71.40.2', 133, "course");
INSERT IGNORE INTO section VALUES (118, '71.40.2', 134, "course");
INSERT IGNORE INTO section VALUES (119, '71.40.2', 135, "course");
INSERT IGNORE INTO section VALUES (120, '71.40.2', 136, "course");
INSERT IGNORE INTO section VALUES (121, '71.40.2', 137, "course");
INSERT IGNORE INTO section VALUES (122, '71.40.2', 108, "course");
INSERT IGNORE INTO section VALUES (123, '71.40.2', 111, "course");
INSERT IGNORE INTO section VALUES (124, '71.40.2', 113, "course");
INSERT IGNORE INTO section VALUES (125, '71.40.2', 114, "course");
INSERT IGNORE INTO section VALUES (126, '71.40.2', 115, "course");
INSERT IGNORE INTO section VALUES (127, '71.40.2', 116, "course");
INSERT IGNORE INTO section VALUES (128, '71.40.2', 117, "course");
INSERT IGNORE INTO section VALUES (129, '71.40.2', 119, "course");
INSERT IGNORE INTO section VALUES (130, '71.40.2', 27, "course");
INSERT IGNORE INTO section VALUES (131, '71.40.2', 34, "course");
INSERT IGNORE INTO section VALUES (132, '71.40.2', 35, "course");
INSERT IGNORE INTO section VALUES (133, '71.40.1', 5, "degree");
INSERT IGNORE INTO section VALUES (134, '71.40.1', 16, "course");
INSERT IGNORE INTO section VALUES (135, '71.40.1', 17, "course");
INSERT IGNORE INTO section VALUES (136, '71.40.1', 18, "course");
INSERT IGNORE INTO section VALUES (137, '71.40.1', 19, "course");
INSERT IGNORE INTO section VALUES (138, '71.40.1', 20, "course");
INSERT IGNORE INTO section VALUES (139, '71.40.1', 21, "course");
INSERT IGNORE INTO section VALUES (140, '71.40.1', 22, "course");
INSERT IGNORE INTO section VALUES (141, '71.40.1', 23, "course");
INSERT IGNORE INTO section VALUES (142, '71.40.1', 24, "course");
INSERT IGNORE INTO section VALUES (143, '71.40.1', 25, "course");
INSERT IGNORE INTO section VALUES (144, '71.40.1', 26, "course");
INSERT IGNORE INTO section VALUES (145, '71.40.1', 27, "course");
INSERT IGNORE INTO section VALUES (146, '71.40.1', 28, "course");
INSERT IGNORE INTO section VALUES (147, '71.40.1', 29, "course");
INSERT IGNORE INTO section VALUES (148, '71.40.1', 30, "course");
INSERT IGNORE INTO section VALUES (149, '71.40.1', 31, "course");
INSERT IGNORE INTO section VALUES (150, '71.40.1', 32, "course");
INSERT IGNORE INTO section VALUES (151, '71.40.1', 33, "course");
INSERT IGNORE INTO section VALUES (152, '71.40.1', 34, "course");
INSERT IGNORE INTO section VALUES (153, '71.40.1', 35, "course");
INSERT IGNORE INTO section VALUES (154, '71.40.1', 36, "course");
INSERT IGNORE INTO section VALUES (155, '71.40.1', 37, "course");
INSERT IGNORE INTO section VALUES (156, '71.40.1', 38, "course");
INSERT IGNORE INTO section VALUES (157, '71.40.1', 133, "course");
INSERT IGNORE INTO section VALUES (158, '71.40.1', 134, "course");
INSERT IGNORE INTO section VALUES (159, '71.40.1', 135, "course");
INSERT IGNORE INTO section VALUES (160, '71.40.1', 139, "course");
INSERT IGNORE INTO section VALUES (161, '71.40.1', 164, "course");
INSERT IGNORE INTO section VALUES (162, '71.40.1', 165, "course");
INSERT IGNORE INTO section VALUES (163, '71.40.1', 166, "course");
INSERT IGNORE INTO section VALUES (164, '71.40.1', 167, "course");
INSERT IGNORE INTO section VALUES (165, '71.40.1', 168, "course");
INSERT IGNORE INTO section VALUES (166, '71.40.1', 169, "course");
INSERT IGNORE INTO section VALUES (167, '71.40.1', 170, "course");
INSERT IGNORE INTO section VALUES (168, '71.40.1', 171, "course");
INSERT IGNORE INTO section VALUES (169, '71.40.1', 172, "course");
INSERT IGNORE INTO section VALUES (170, '71.40.1', 173, "course");
INSERT IGNORE INTO section VALUES (171, '71.40.1', 174, "course");
INSERT IGNORE INTO section VALUES (172, '71.40.1', 175, "course");
INSERT IGNORE INTO section VALUES (173, '71.40.1', 176, "course");
INSERT IGNORE INTO section VALUES (174, '71.40.1', 177, "course");
INSERT IGNORE INTO section VALUES (175, '71.40.1', 178, "course");
INSERT IGNORE INTO section VALUES (176, '71.40.1', 179, "course");
INSERT IGNORE INTO section VALUES (177, '71.40.1', 180, "course");
INSERT IGNORE INTO section VALUES (178, '71.40.1', 181, "course");
INSERT IGNORE INTO section VALUES (179, '71.40.1', 182, "course");
INSERT IGNORE INTO section VALUES (180, '71.40.1', 183, "course");
INSERT IGNORE INTO section VALUES (181, '71.40.1', 184, "course");
INSERT IGNORE INTO section VALUES (182, '71.40.1', 185, "course");
INSERT IGNORE INTO section VALUES (183, '71.40.1', 186, "course");
INSERT IGNORE INTO section VALUES (184, '71.40.1', 187, "course");
INSERT IGNORE INTO section VALUES (185, '71.40.1', 188, "course");
INSERT IGNORE INTO section VALUES (186, '71.40.1', 189, "course");
INSERT IGNORE INTO section VALUES (187, '71.40.1', 190, "course");
INSERT IGNORE INTO section VALUES (188, '71.40.1', 191, "course");
INSERT IGNORE INTO section VALUES (189, '71.40.1', 192, "course");
INSERT IGNORE INTO section VALUES (190, '71.40.1', 193, "course");
INSERT IGNORE INTO section VALUES (191, '71.40.1', 194, "course");
INSERT IGNORE INTO section VALUES (192, '71.40.1', 195, "course");
INSERT IGNORE INTO section VALUES (193, '71.40.1', 196, "course");
INSERT IGNORE INTO section VALUES (194, '71.40.1', 197, "course");
INSERT IGNORE INTO section VALUES (195, '71.40.1', 198, "course");
INSERT IGNORE INTO section VALUES (196, '71.40.1', 199, "course");
INSERT IGNORE INTO section VALUES (197, '71.40.1', 200, "course");
INSERT IGNORE INTO section VALUES (198, '71.40.2', 165, "course");
INSERT IGNORE INTO section VALUES (199, '71.40.2', 166, "course");
INSERT IGNORE INTO section VALUES (200, '71.40.2', 168, "course");
INSERT IGNORE INTO section VALUES (201, '71.40.2', 170, "course");
INSERT IGNORE INTO section VALUES (202, '71.40.2', 200, "course");
INSERT IGNORE INTO section VALUES (203, '71.40.2', 98, "course");
INSERT IGNORE INTO section VALUES (204, '71.40.2', 99, "course");
INSERT IGNORE INTO section VALUES (205, '71.40.2', 100, "course");
INSERT IGNORE INTO section VALUES (206, '71.40.2', 101, "course");
INSERT IGNORE INTO section VALUES (207, '71.40.2', 104, "course");
INSERT IGNORE INTO section VALUES (208, '71.40.2', 105, "course");
INSERT IGNORE INTO section VALUES (209, '71.40.2', 106, "course");
INSERT IGNORE INTO section VALUES (210, '71.40.2', 151, "course");
INSERT IGNORE INTO section VALUES (211, '71.40.2', 152, "course");
INSERT IGNORE INTO section VALUES (212, '71.40.2', 153, "course");
INSERT IGNORE INTO section VALUES (213, '71.40.2', 154, "course");
INSERT IGNORE INTO section VALUES (214, '71.40.2', 155, "course");

-- id, pdf_file, rejection_rationale, user_id, department_id
INSERT IGNORE INTO request_package VALUES (1, NULL, NULL, 1, 4); -- creating a package for department of CS & SE
INSERT IGNORE INTO request_package VALUES (2, NULL, NULL, 1, 8); -- used for pdf generation
INSERT IGNORE INTO request_package VALUES (3, NULL, NULL, 1, 4); -- used to test package rejection


-- id, original id, rationale, request type, resource, target id, target type, timestamp, title, package id, user id
INSERT IGNORE INTO request VALUES (1, 8, "rationale", 2, "resource implications", 15, 2, NULL, "SOEN344_update", 1, 1); -- updating request for the course soen 343
INSERT IGNORE INTO request VALUES (2, 1, "Course material already offered by a combination of COMP 228 and SOEN 298.", 3, "resource implications", 0, 2, NULL, "SOEN228_removal", 1, 1); -- removing request for the course soen 343
INSERT IGNORE INTO request VALUES (3, 0, "The CEAB visitor and students noted that there was no data course in the core. This is especially problematic given the trend toward big data. This new course is designed to teach software engineering students about modern database systems.", 1, "resource implications", 40, 2, NULL, "SOEN363_new", 1, 1); -- creating request for the course soen 343
INSERT IGNORE INTO request VALUES (4, 30, "The course description is over ten years old and has been updated to reflect modern software engineering.", 2, "None.", 39, 2, NULL, "MECH371_update", 2, 1); -- thermodynamics II updated

-- id, apc, department_council, department_curriculum_committee, faculty_council, senate, undergraduate_studies_committee
INSERT IGNORE INTO approval_pipeline VALUES (1, 4, 0, 1, 2, 5, 3); -- dcc -> fcc -> ugdc -> apc -> senate

-- pipeline_id, package_id, position
INSERT IGNORE INTO approval_pipeline_request_package VALUES (1, 1, "Department Curriculum Committee", NULL);
INSERT IGNORE INTO approval_pipeline_request_package VALUES (1, 3, "Department Curriculum Committee", NULL);

-- id, first_core, first_paragraph, is_active, second_core, section_id, section_title
INSERT IGNORE INTO section71709 VALUES (1, "Real-Time, Embedded, and Avionics Software (REA) Option",
"General Program","Engineering Core" ,
"Students registered in the Software Engineering program must complete a minimum of 120 credits during four years of full‑time study. Students may choose either the general program or one of three options: Computer Games; Web Services and Applications; and Real‑Time, Embedded, and Avionics Software. The program consists of the Engineering Core, Software Engineering Core, general program or an option, and electives.",
 "Basic and Natural Science Courses",1,
"Electives", "Software Engineering Core", "Two Basic and Natural Science courses must be selected from the following, including at least one course marked *:",
 "71.70.9", "Degree Requirements for the BEng in Software Engineering",
 "Web Services and Applications (WSA) Option", "Computer Games (CG) Option",
 "Computer Science Group","Students must complete at least 16 credits chosen from the electives list.

Options
Students must complete at least 16 credits with a minimum of 15 credits from one of the options listed below, including all the courses marked *, and at least one course marked **, and the remainder chosen from the electives list.");
--INSERT IGNORE INTO section71709 VALUES (2, "Engineering Core" ,"Students registered in the Software Engineering program must complete a minimum of 120 credits during four years of full‑time study.", 0,"Software Engineering Core", "70.71.9", "Degree Requirements for the BEng in Software Engineering");

-- id, first_paragraph, is_Active, section_id, section_title
INSERT IGNORE INTO section71701 VALUES (1,"The Computer Science program emphasizes fundamentals and techniques that remain relevant and useful for many years after graduation. The program consists of a combination of core courses in computer science, elective courses in computer science and mathematics, and some free electives. The Computer Science Core provides a basic and broad study of theory, mathematical basics, programming methodology, computer architecture, data structures, operating systems, and software engineering. The option courses are designed to provide an integrated yet specialized training in particular application areas of the discipline. Students may choose either the General Program or one of eight options. Each option involves the study of selected advanced elective courses in computer science to provide further depth in computer science and the particular application area.
The General Program and each option constitute a 90‑credit program that consists of courses in the following groups: Computer Science Core, Complementary Core, Option‑Specific Courses, Computer Science Electives, Mathematics Electives, and General Electives.

The General Program is a major in Computer Science that emphasizes an exposure to a breadth of topics in Computer Science.
The Computer Games option is a major in Computer Science that deals with the design and implementation of computer games, and the tools and techniques that are useful in developing software for computer games.
The Web Services and Applications option is a major in Computer Science that deals with the analysis, design, and implementation of services and applications delivered over the web.
The Computer Systems option is a major in Computer Science that focuses on state‑of‑the‑art hardware and software platforms and on the tools and techniques necessary to develop software on such platforms.
The Software Systems option is a major in Computer Science that gives a firm grounding in diverse tools and techniques required for a wide variety of software systems.
The Information Systems option combines a major in Computer Science with approximately a third of the credits from the John Molson School of Business to create a program focusing on business applications of computer systems.
The Computer Applications option combines a major in Computer Science with a minor in a discipline of the student’s choice.
The Computation Arts option combines a major in Computer Science with a major in Fine Arts specializing in the design of interactive multimedia.
The Mathematics and Statistics option combines a major in Computer Science with a major in Mathematics and Statistics.
There is an honours program corresponding to the General Program and each option (see §71.70.4). In addition, all programs are offered in the co‑operative format, with alternating study and work terms, for a limited number of students with suitable qualifications (see §24).",1,"71.70.1"," Curriculum for the Degree of Bachelor of/Baccalaureate in Computer Science");

-- id, first_paragraph, section_id, section_title
INSERT IGNORE INTO section71702 VALUES (1, "Computer Games Electives", " 	Computer Science Core	33.00
 	Complementary Core	6.00
 	Web Services and Applications Electives	22.00
 	Computer Science Electives	8.00
 	Mathematics Electives	6.00
 	General Electives	15.00
 	 	_____
 	 	90.00",
		"Computation Arts Option", "Computer Systems Option", "Students must complete six courses (22 credits) from the following list of courses, including all the courses marked *.
",
"Information Systems Option", " 	Computer Science Core	33.00
 	Complementary Core	6.00
 	Computer Science Electives	18.00
 	Mathematics Electives	6.00
 	Minor*	27.00
 	 	_____
 	 	90.00", "General Electives", " 	Computer Science Core	33.00
 	Complementary Core	6.00
 	Computer Science Electives*	30.00
 	Mathematics Electives	6.00
 	General Electives	15.00
 	 	_____
 	 	90.00
*Note: Maximum of 12 credits from any one of Computer Games Electives, Web Services and Applications Electives, Computer Systems Electives, or Software Systems Core.
", "Computer Science Core", "To be recommended for the degree of BCompSc, students must satisfactorily complete an approved program of at least 90 credits comprising the courses of the Computer Science Core and those courses specified for their particular program in accordance with the graduation requirements of §71.10.5.
Students may not register for a 400‑level course before completing all of the 200‑level Computer Science Core courses of their program.
The Gina Cody School of Engineering and Computer Science is committed to ensuring that its students possess good writing skills. Hence, every student in an undergraduate degree program is required to demonstrate competence in writing English or French prior to graduation.
All students admitted to the Gina Cody School of Engineering and Computer Science must meet the writing skills requirement as outlined in §71.20.7 (Writing Skills Requirement).
If a student has satisfied the writing skills requirement prior to transferring to the Gina Cody School of Engineering and Computer Science, that student is deemed to have satisfied the writing skills requirement.
Newly admitted students are strongly encouraged to meet the requirement very early in their program (fall term of first year for students starting in September or winter term of first year for students starting in January) to avoid the risk of delayed graduation should remedial work prove necessary. Students who are required to take ESL courses should meet the Faculty writing skills requirements in the term following completion of their ESL courses.
Students registered in the Computer Science program must complete a minimum of 90 credits. The program offers the General Program and eight options (see §71.70.1). All options consist of the Computer Science Core (33 credits), the Complementary Core (6 credits), Option‑Specific Courses, Computer Science Electives, Mathematics Electives, and General Electives.
", "Software Systems Core", "Students must complete 10 courses (31 credits) from the following list of courses, including all the courses marked *.
", "Mathematics Electives", "General Electives must be chosen from the following list:

Computer Science Electives as mentioned above.
Mathematics Electives as mentioned above.
General Education Electives found in §71.110.
Basic and Natural Science Courses list found in §71.70.9.
A course outside this list may qualify as a General Elective only with prior written permission on an GCS Student Request form, obtainable from the Office of Student Academic Services in the Gina Cody School of Engineering and Computer Science.
", 1, "Mathematics and Statistics Option", "Web Services and Applications Option", "Students must complete six courses (22 credits) from the following list of courses, including all the courses marked *.
", "Complementary Core", "Computer Science Electives must be chosen from the following list:

All COMP courses with numbers 325 or higher.
SOEN 287, 321, 331, 387, 422, 423, 487.
COMP and SOEN courses with numbers between 6000 and 6951 (maximum of eight credits, and with permission from the Department).
In every option, any credits exceeding the required number of Computer Science Elective credits will accrue towards the General Elective credits.
", "71.70.2 ", "Degree Requirements", "Computer Applications Option", "See §71.85 for details.",
"Computer Games Option","Students must complete six courses (24 credits) from the following list of courses, including all the courses marked *.
", "Information Systems Electives", "See §71.80 for details.",
"General Program", " 	Computer Science Core	33.00
 	Complementary Core	6.00
 	Computer Games Electives	24.00
 	Computer Science Electives	6.00
 	Mathematics Electives*	6.00
 	General Electives or Minor in Game Design**	15.00
 	 	_____
 	 	90.00
*Note: Students must take COMP 361 as part of their Mathematics Electives.
**Note: A maximum of 15 credits from the Minor in Game Design (see §81.90) may be counted towards the General Electives.
", "Web Services and Applications Electives", " 	Computer Science Core	33.00
 	Complementary Core	6.00
 	Computer Systems Electives	22.00
 	Computer Science Electives	8.00
 	Mathematics Electives	6.00
 	General Electives	15.00
 	 	_____
 	 	90.00", "Computer Science Electives", "In every option, any credits exceeding the required number of Mathematics Elective credits will accrue towards the General Elective credits.
*Students cannot receive credit for both COMP 339 and MATH 339; COMP 361 and MAST 334; COMP 367 and MAST 332.
", "Software Systems Option", " 	Computer Science Core	33.00
 	Complementary Core	6.00
 	Information Systems Electives	31.00
 	Computer Science Electives	14.00
 	Mathematics Electives	6.00
 	 	_____
 	 	90.00","Computer Systems Electives", "	Computer Science Core	33.00
 	Complementary Core	6.00
 	Software Systems Core	20.00
 	Computer Science Electives	13.00
 	Mathematics Electives	6.00
 	General Electives	12.00
 	 	_____
 	 	90.00");


-- id, first_paragraph, is_Active, section_id, section_title
INSERT IGNORE INTO section71703 VALUES (1, "Students admitted to an Extended Credit Program (ECP) under the provisions of Sections 13.3.2 or 13.8.1 must successfully complete a minimum of 120 credits including:

90	Program requirements as set out in Section 71.70.2
9	MATH 2033, 2043, 2053
6	Chosen from courses in Humanities or Social Sciences as noted in Section 71.110. ESL courses and courses that focus on the acquisition of a language may not be used to meet this requirement.
15	ECP elective credits chosen from the following lists, depending on the student’s program:
a)	General Program, and Computer Applications, Computer Games, Software Systems, and Web Services and Applications
 	Options:
 	15 elective credits chosen from outside the Gina Cody School of Engineering and Computer Science (see Note).
b)	Computation Arts Option:
 	15 elective credits chosen from outside the Gina Cody School of Engineering and Computer Science and the Department of Design and Computation Arts (see Note).
c)	Information Systems Option:
 	15 elective credits chosen from outside the John Molson School of Business and the Department of Computer Science and Software Engineering (see Note).
d)	Mathematics and Statistics Option:
 	15 elective credits chosen from outside the Gina Cody School of Engineering and Computer Science and the Department of Mathematics and Statistics (see Note).
e)	Computer Systems Option:
 	CHEM 2053
 	PHYS 2043, 2053
 	and 6 elective credits chosen from outside the Gina Cody School of Engineering and Computer Science (see Note).
Note: ECP elective credits may be chosen as follows:
   •   General Education Electives found in §71.110.
   •   Basic and Natural Science Courses found in §71.70.9.
   •   Courses not included in the above lists may be taken with prior approval of the undergraduate program director.",1, "71.70.3", "Extended Credit Program");


-- id, first_core, first_paragraph, is_Active,  second_paragraph, section_id, section_title
INSERT IGNORE INTO section71704 VALUES (1, "Course Requirements for Honours Programs", "Students should refer to §16.2.4 of the Calendar for academic regulations for the honours program. The following regulations are additional requirements for the Honours BCompSc program.

Applications to enter an honours program must be submitted to the Office of the Associate Dean (Student Academic Services) at least three months before the start of the term in which the student wishes to enter an honours program.
Students must complete at least 30 credits towards their degree before entering an honours program.
Students who are required to withdraw from an honours program may continue in the regular program of their option or General Program provided they are in acceptable or conditional standing according to the academic regulations in §71.10.3.", 1, "Honours students must fulfill the requirements of their option. In addition, to receive an honours degree:

The student must have a final graduation GPA of at least 3.30.
Students must successfully complete the course COMP 490 as one of the Computer Science electives for their option.
For students in the General Program, and the Computer Games, Computer Systems, and Web Services and Applications Options, at least six of the General Electives credits must be chosen from the list of Computer Science Electives with at least two of the following: COMP 339, COMP 465, and COMP or SOEN courses with a number between 6000 and 6951 not marked with (*).
For students in the Software Systems Option, at least six of the General Electives credits must be chosen from the list of Computer Science Electives with at least one of the following: SOEN 331, and COMP or SOEN courses with a number between 6000 and 6951 not marked with (*).", "71.70.4", "Honours Program");


-- id, first_Core, first_paragraph, is_Active,  section_id, section_title
INSERT IGNORE INTO section71705 VALUES (1,"Minor in Computer Science","Students who require any of the above courses as part of their major should replace these courses with elective courses chosen from the list of Computer Science Electives.",1,"71.70.5"," Minor in Computer Science");

-- id, first_paragraph, is_Active,  section_id, section_title
INSERT IGNORE INTO section71706 VALUES (1, "Both major and minor programs in Management Information Systems can be found in the John Molson School of Business Section of the Undergraduate Calendar, §61.
The Faculty of Fine Arts and the Department of Computer Science and Software Engineering offer complementary major programs. Students who take the Computer Applications Option (see §71.70.2 above) can also take the Major in Computation Arts and Computer Science (see §71.80, and the Fine Arts Section, §81) or the Joint Major in Mathematics and Statistics and Computer Applications (see §71.85, and the Mathematics and Statistics Section, §31.200).",1,"71.70.6","Programs Related to Computer Science");

-- id, first_paragraph, is_Active, section_id, section_title
INSERT IGNORE INTO section71707 VALUES (1, "Students employed full‑time in a computer science position during their non‑study terms may have this Industrial Experience listed on their official transcript and student record, provided they successfully complete the Reflective Learning course associated with this work term.
Industrial Experience work terms will be coded as COMP 107 and 207, and the associated Reflective Learning courses will be coded as COMP 108 and 208 respectively.
Students may only register for these courses with the permission of the Faculty.
The Industrial Experience terms COMP 107 and 207 carry no credit value and are used to indicate that the student is on an Industrial Experience term.
The COMP 108 and 208 Industrial Experience Reflective Learning courses are worth three credits and are marked on a pass/fail basis. They are above and beyond the credit requirements of the student’s program and are not transferable nor are they included in the full‑ or part‑time assessment status.
Students studying for a co‑op work term or CIADI term should not register for these Industrial Experience and Reflective Learning courses.",1, "71.70.7", "Industrial Experience and Reflective Learning Courses");

-- id, first_paragraph, is_Active, section_id, section_title
INSERT IGNORE INTO section71708 VALUES (1, "The Software Engineering program is built on the fundamentals of computer science, an engineering core, and a discipline core in Software Engineering to cover the engineering approach to all phases of the software process and related topics.
The curriculum builds on the traditional computer science core topics of computer mathematics, theory, programming methodology, and mainstream applications to provide the computing theory and practice which underlie the discipline. The engineering core covers basic science, professional topics, and introduces the engineering approach to problem solving. The program core in Software Engineering includes advanced programming techniques, software specification, design, architecture, as well as metrics, security, project management, and quality control. The options cover a broad range of advanced topics, from formal methods to distributed systems.

Extended Credit Program
The requirements of the Extended Credit Program (ECP) are set out in Section 71.20.2.", 1, "71.70.8", "Curriculum for the Degree of BEng in Software Engineering");

-- id, first_paragraph, is_Active,  section_id, section_title
INSERT IGNORE INTO section717010 VALUES (1, "Students from outside the Gina Cody School of Engineering and Computer Science who are not registered in a Computer Science program may not take more than five COMP courses numbered higher than 212.
Students from outside the Gina Cody School of Engineering and Computer Science who are registered for the Minor in Computer Science may not take more than 30 credits of COMP courses numbered higher than 212.
Computer Science
Software Engineering",1, "71.70.10", "Course Descriptions");

-- id, electives_description, electives_header, fifth_option, first_core, first_option, fourth_option, intro_paragraph, is_active, second_core, second_option, section_id, section_title, sixth_option, third_option
INSERT IGNORE INTO section71401 VALUES (1, "Students in the Mechanical Engineering program must complete at least 15.25 elective credits from the list of courses below. Courses are listed in groups to facilitate the selection of courses in a particular area of the field.", "Electives", "Vehicle Systems", "Engineering Core", "Aerospace", "Thermo-Fluids and Propulsion", "The program in Mechanical Engineering consists of the Engineering Core, the Mechanical Engineering Core, and elective credits as shown below. The minimum length of the program is 120 credits.", 1, "Mechanical Engineering Core", "Design and Manufacturing", "71.40.1", "Course Requirements (BEng in Mechanical Engineering)", "Stress Analysis", "Systems and Mechatronics");

-- id, electives_description, electives_header, first_core, intro_paragraph, is_active, science_core_header, science_description, science_header, second_core, section_id, section_title
INSERT IGNORE INTO section71402 VALUES (1, "Students must complete a minimum of 14.25 credits from the following courses, including at least three INDU courses and with no more than one of the courses marked *. With permission of the Department, students may take one technical elective course from another program or Faculty.", "Electives", "Engineering Core", "The program in Industrial Engineering consists of the Engineering Core, the Industrial Engineering Core, and elective credits as shown below. Students must select one course from the list of Basic and Natural Science courses as part of the Industrial Engineering Core courses. The minimum length of the program is 120 credits.", 1, "Indu Science Core", "Students must complete one course from the following list:", "Basic and Natural Science Courses",
"Industrial Engineering Core", "71.40.2", "Course Requirements (BEng in Industrial Engineering)");
