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

-- id, desc, active, name, department_id
INSERT IGNORE INTO program VALUES (1, "The Software Engineering program is built on the fundamentals of computer science, an engineering core, and a discipline core in Software Engineering to cover the engineering approach to all phases of the software process and related topics. The curriculum builds on the traditional computer science core topics of computer mathematics, theory, programming methodology, and mainstream applications to provide the computing theory and practice which underlie the discipline. The engineering core covers basic science, professional topics, and introduces the engineering approach to problem solving. The program core in Software Engineering includes advanced programming techniques, software specification, design, architecture, as well as metrics, security, project management, and quality control. The options cover a broad range of advanced topics, from formal methods to distributed systems.",
1, 'Software Engineering', 4);
INSERT IGNORE INTO program VALUES (2, 'The Computer Science program emphasizes fundamentals and techniques that remain relevant and useful for many years after graduation. The program consists of a combination of core courses in computer science, elective courses in computer science and mathematics, and some free electives. The Computer Science Core provides a basic and broad study of theory, mathematical basics, programming methodology, computer architecture, data structures, operating systems, and software engineering. The option courses are designed to provide an integrated yet specialized training in particular application areas of the discipline. Students may choose either the General Program or one of eight options. Each option involves the study of selected advanced elective courses in computer science to provide further depth in computer science and the particular application area. The General Program and each option constitute a 90‑credit program that consists of courses in the following groups: Computer Science Core, Complementary Core, Option‑Specific Courses, Computer Science Electives, Mathematics Electives, and General Electives.',
1, 'Computer Science', 4);
INSERT IGNORE INTO program VALUES (3, 'description...',
1, 'Mechanical Engineering', 8);


-- id, credits, level, name, program_id
INSERT IGNORE INTO degree VALUES (1, 120, 1, 'Bachelor of Software Engineering (BEng)', 1);
INSERT IGNORE INTO degree VALUES (2, 45, 2, 'Master of Software Engineering (MEng)', 1);
INSERT IGNORE INTO degree VALUES (3, 45, 2, 'Master of Software Engineering (MASc)', 1);
INSERT IGNORE INTO degree VALUES (4, 90, 3, 'Doctor of Philosophy (PhD in Software Engineering)', 1);
INSERT IGNORE INTO degree VALUES (5, 120, 1, 'Mechanical Engineering (BEng)', 3);
INSERT IGNORE INTO degree VALUES (6, 90, 1, 'Bachelor of Computer Science (BCompSc)', 2);

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
INSERT IGNORE INTO section VALUES (22, '71.40.1', 22, "course");
INSERT IGNORE INTO section VALUES (23, '71.40.1', 23, "course");
INSERT IGNORE INTO section VALUES (24, '71.40.1', 24, "course");
INSERT IGNORE INTO section VALUES (25, '71.40.1', 25, "course");
INSERT IGNORE INTO section VALUES (26, '71.40.1', 26, "course");
INSERT IGNORE INTO section VALUES (27, '71.40.1', 27, "course");
INSERT IGNORE INTO section VALUES (28, '71.40.1', 28, "course");
INSERT IGNORE INTO section VALUES (29, '71.40.1', 29, "course");
INSERT IGNORE INTO section VALUES (30, '71.40.1', 30, "course");
INSERT IGNORE INTO section VALUES (31, '71.40.1', 31, "course");
INSERT IGNORE INTO section VALUES (32, '71.40.1', 32, "course");
INSERT IGNORE INTO section VALUES (33, '71.40.1', 33, "course");
INSERT IGNORE INTO section VALUES (34, '71.40.1', 34, "course");
INSERT IGNORE INTO section VALUES (35, '71.40.1', 35, "course");
INSERT IGNORE INTO section VALUES (36, '71.40.1', 36, "course");
INSERT IGNORE INTO section VALUES (37, '71.40.1', 37, "course");
INSERT IGNORE INTO section VALUES (38, '71.40.1', 38, "course");
INSERT IGNORE INTO section VALUES (39, '71.40.1', 39, "course");
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

-- id, pdf_file, rejection_rationale, user_id, department_id
INSERT IGNORE INTO request_package VALUES (1, NULL, NULL, 1, 4); -- creating a package for department of CS & SE
INSERT IGNORE INTO request_package VALUES (2, NULL, NULL, 1, 8); -- used for pdf generation
INSERT IGNORE INTO request_package VALUES (3, NULL, NULL, 1, 4); -- used to test package rejection


-- id, original id, rationale, request type, resource, target id, target type, timestamp, title, package id, user id
INSERT IGNORE INTO request VALUES (1, 8, 8, "rationale", 2, "resource implications", 15, 2, NULL, "SOEN344_update", 1, 1); -- updating request for the course soen 343
INSERT IGNORE INTO request VALUES (2, 1, 1, "Course material already offered by a combination of COMP 228 and SOEN 298.", 3, "resource implications", 0, 2, NULL, "SOEN228_removal", 1, 1); -- removing request for the course soen 343
INSERT IGNORE INTO request VALUES (3, 0, 0, "The CEAB visitor and students noted that there was no data course in the core. This is especially problematic given the trend toward big data. This new course is designed to teach software engineering students about modern database systems.", 1, "resource implications", 40, 2, NULL, "SOEN363_new", 1, 1); -- creating request for the course soen 343
INSERT IGNORE INTO request VALUES (4, 30, 30, "The course description is over ten years old and has been updated to reflect modern software engineering.", 2, "None.", 39, 2, NULL, "MECH371_update", 2, 1); -- thermodynamics II updated
INSERT IGNORE INTO request VALUES (5, 1, 1, "None.", 2, "None.", 2, 1, NULL, "SECTION_70.71.9_update", 1, 1); -- thermodynamics II updated

-- id, apc, department_council, department_curriculum_committee, faculty_council, senate, undergraduate_studies_committee
INSERT IGNORE INTO approval_pipeline VALUES (1, 4, 0, 1, 2, 5, 3); -- dcc -> fcc -> ugdc -> apc -> senate

-- pipeline_id, package_id, position
INSERT IGNORE INTO approval_pipeline_request_package VALUES (1, 1, "Department Curriculum Committee", NULL);
INSERT IGNORE INTO approval_pipeline_request_package VALUES (1, 3, "Department Curriculum Committee", NULL);

-- id, first_core, first_paragraph, is_active, second_core, section_id, section_title
INSERT IGNORE INTO section71709 VALUES (1, "Engineering Core" ,"Students registered in the Software Engineering program must complete a minimum of 120 credits during four years of full‑time study. Students may choose either the general program or one of three options: Computer Games; Web Services and Applications; and Real‑Time, Embedded, and Avionics Software. The program consists of the Engineering Core, Software Engineering Core, general program or an option, and electives.", 1, "Software Engineering Core", "70.71.9", "Degree Requirements for the BEng in Software Engineering");
INSERT IGNORE INTO section71709 VALUES (2, "Engineering Core" ,"Students registered in the Software Engineering program must complete a minimum of 120 credits during four years of full‑time study.", 0,"Software Engineering Core", "70.71.9", "Degree Requirements for the BEng in Software Engineering");

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