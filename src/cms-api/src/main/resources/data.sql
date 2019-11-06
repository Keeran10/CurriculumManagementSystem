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

-- id, credits, description,
-- is_active, lab, lect, level, course_name, course_number, outline, title, program_id
INSERT IGNORE INTO course VALUES (1, 4.00, 'Processor structure, Data and Instructions, Instruction Set Processor (ISP) level view of computer hardware, assembly language level use. Memory systems — RAM and disks, hierarchy of memories. I/O organization, I/O devices and their diversity, their interconnection to CPU and Memory. Communication between computers at the physical level. Networks and computers. Lectures: three hours per week. Tutorial: two hours per week. Laboratory: two hours per week.',
1, 2, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 228 may not take this course for credit.', 228, NULL, 'System Hardware', 2, 1);
INSERT IGNORE INTO course VALUES (2, 3.00, 'Internet architecture and protocols. Web applications through clients and servers. Markup languages. Client‑side programming using scripting languages. Static website contents and dynamic page generation through server‑side programming. Preserving state (client‑side) in web applications. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'SOEN', NULL,  287, NULL, 'Web Programming', 2, 1);
INSERT IGNORE INTO course VALUES (3, 3.00, 'Protocol layers and security protocols. Intranets and extranets. Mobile computing. Electronic commerce. Security architectures in open‑network environments. Cryptographic security protocols. Threats, attacks, and vulnerabilities. Security services: confidentiality; authentication; integrity; access control; non‑repudiation; and availability. Security mechanisms: encryption; data‑integrity mechanisms; digital signatures; keyed hashes; access‑control mechanisms; challenge‑response authentication; traffic padding; routing control; and notarization. Key‑management principles. Distributed and embedded firewalls. Security zones. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', NULL, 321, NULL, 'Information Systems Security', 1, 1);
INSERT IGNORE INTO course VALUES (4, 3.00, 'Assertions. Static and dynamic checking. Method specification using preconditions and postconditions. Strengthening and weakening. Design by contract. Hoare logic. Invariants. Class specification using invariants. Software tools for assertion checking and verification. Reliable software development. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'SOEN', NULL,  331, NULL, 'Introduction to Formal Methods for Software Engineering', 2, 1);
INSERT IGNORE INTO course VALUES (5, 3.00, 'previously ortsiles of software engineering. Introduction to software process models. Activities in each phase, including review activities. Working in teams: organization; stages of formation; roles; conflict resolution. Notations used in software documentation. How to review, revise, and improve software documentation. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', 'NOTE: Students who have received credit for COMP 354 may not take this course for credit.',  341, NULL, 'Software Process', 1, 1);
INSERT IGNORE INTO course VALUES (6, 3.00, 'Requirements engineering. Functional and non‑functional requirements. Traceability. Test generation. Formal and informal specifications. Formal specification languages. Reasoning with specifications. Correctness issues. Verification. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', NULL,  342, NULL, 'Software Requirements and Specifications', 1, 1);
INSERT IGNORE INTO course VALUES (7, 3.00, 'From requirements to design to implementation. Planned vs. evolutionary design and refactoring. Model‑driven design and Unified Modelling Language (UML). Structural and behavioural design descriptions and specifications. General and domain‑specific design principles, patterns and idioms. Object‑oriented design concepts such as interfaces vs. abstract types, polymorphism, generics, and delegation vs. subclassing. Introduction to software architecture (styles and view models). Design quality. Design rationale. Design methodologies (e.g. based on responsibility assignment). Test‑driven development. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', NULL, 343, NULL, 'Software Architecture and Design I', 1, 1);
INSERT IGNORE INTO course VALUES (8, 3.00, 'Architectural activities, roles, and deliverables. Architectural view models. Architectural styles (including client‑server, layered, pipes‑and‑filters, event‑based, process control) and frameworks. Architectural analysis and the interplay with requirements elicitation. Notations for expressing architectural designs, structural and behavioural specifications. From architectural design to detailed design. Domain specific architectures and design patterns. Evaluation and performance estimation of designs. Advanced object‑oriented design patterns and idioms. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', NULL, 344, NULL, 'Software Architecture and Design II', 1, 1);
INSERT IGNORE INTO course VALUES (9, 3.00, "Testing strategies. Specification‑based vs. code‑based, black‑box vs. white‑box, functional vs. structural testing; unit, integration, system, acceptance, and regression testing. Verification vs. validation. Test planning, design and artifacts. Introduction to software reliability and quality assurance. Formal verification methods, oracles; static and dynamic program verification. Lectures: three hours per week. Tutorial: one hour per week.",
1, 0, 3, 1, 'SOEN', NULL,  345, NULL, 'Software Testing, Verification and Quality Assurance', 1, 1);
INSERT IGNORE INTO course VALUES (10, 3.00, 'The human side: I/O; memory; and information processing. Interaction: mental models; human error; interaction frameworks and paradigms. Direct manipulation. User interface design: principles; standards; and guidelines. User‑centred design: standards and design rationale; heuristic evaluation; iterative design; and prototyping. Task‑centred design. Rationalized design: usability engineering; dialogue notations; user models; diagrammatic notations; and textual notations. Evaluation: with the user; without the user; quantitative; and qualitative. Implementation support. Help and documentation. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', NULL, 357, NULL, 'User Interface Design', 1, 1);
INSERT IGNORE INTO course VALUES (11, 3.00, 'Organization of large software development. Roles of team members, leaders, managers, stakeholders, and users. Tools for monitoring and controlling a schedule. Financial, organizational, human, and computational resources allocation and control. Project and quality reviews, inspections, and walkthroughs. Risk management. Communication and collaboration. Cause and effects of project failure. Project management via the Internet. Quality assurance and control. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', NULL,  384, NULL, 'Management, Measurement and Quality Control', 1, 1);
INSERT IGNORE INTO course VALUES (12, 3.00, 'Mathematical modelling of dynamical systems; block diagrams; feedback; open and closed loops. Linear differential equations; time domain analysis; free, forced, and total response; steady state and transient response. Laplace transform and inverse transform; second order systems. Transfer functions and stability. Control system design: PID and root locus techniques. Computer simulation of control systems. Applications. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'SOEN', NULL, 385, NULL, 'Control Systems and Applications', 1, 1);
INSERT IGNORE INTO course VALUES (13, 3.50, 'Students work in teams to design and implement a software project from requirements provided by the coordinator. Each team will demonstrate the software and prepare adequate documentation for it. In addition, each student will write an individual report. Lectures: two hours per week. Tutorial: one hour per week. Laboratory: three hours per week.',
1, 3, 2, 1, 'SOEN', NULL,  390, NULL, 'Software Engineering Team Design Project', 1, 1);
-- SOEN 490 needs 75 credits in the program as prerequisite. Not handled yet.
INSERT IGNORE INTO course VALUES (14, 4.00, 'Students work in teams of at least four members to construct a significant software application. The class meets at regular intervals. Team members will give a presentation of their contribution to the project. Lectures: one hour per week. Laboratory: two hours per week. Two terms.',
1, 2, 1, 1, 'SOEN', NULL,  490, NULL, 'Capstone Software Engineering Design Project', 0, 1);
INSERT IGNORE INTO course VALUES (15, 3.50, 'From requirements to design to implementation. Planned vs. evolutionary design and refactoring. Model‑driven design and Unified Modelling Language (UML). Structural and behavioural design descriptions and specifications. General and domain‑specific design principles, patterns and idioms. Object‑oriented design concepts such as interfaces vs. abstract types, polymorphism, generics, and delegation vs. subclassing. Introduction to software architecture (styles and view models). Design quality. Design rationale. Design methodologies (e.g. based on responsibility assignment). Test‑driven development. Lectures: three hours per week. Tutorial: one hour per week.',
0, 0, 3, 2, 'SOEN', NULL, 343, NULL, 'Software Architecture and Design I extreme', 1, 1);

INSERT IGNORE INTO course VALUES (16, 3.00, 'Resultant of force systems; equilibrium of particles and rigid bodies; distributed forces; statically determinate systems; trusses; friction; moments of inertia; virtual work. Shear and bending moment diagrams. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'ENGR', NULL, 242, NULL, 'Statics', 2, 3);
INSERT IGNORE INTO course VALUES (17, 3.00, 'Kinematics of a particle and rigid body; forces and accelerations; work and energy; impulse and momentum; dynamics of a system of particles and rigid bodies, introduction to vibrations. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'ENGR', NULL, 243, NULL, 'Dynamics', 2, 3);
INSERT IGNORE INTO course VALUES (18, 3.75, 'Mechanical behaviour of materials; stress; strain; shear and bending moment diagrams; introduction to inelastic action. Analysis and design of structural and machine elements subjected to axial, torsional, and flexural loadings. Combined stresses and stress transformation. Deflections. Introduction to elastic stability. Lectures: three hours per week. Tutorial: two hours per week. Laboratory: three hours per week, alternate weeks.',
1, 3, 3, 1, 'ENGR', NULL, 244, NULL, 'Mechanics of Materials', 2, 3); -- lab hours is 3 hours alternative weeks !
INSERT IGNORE INTO course VALUES (19, 3.00, 'Basic principles of thermodynamics and their application to various systems composed of pure substances and their homogeneous non-reactive mixtures. Simple power production and utilization cycles. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'ENGR', NULL, 251, NULL, 'Thermodynamics I', 2, 3);
INSERT IGNORE INTO course VALUES (20, 3.00, 'Elements of complex variables. The Laplace transform: Laplace transforms and their properties, solution of linear differential equations with constant coefficients. Further theorems and their applications. The Fourier transform: orthogonal functions, expan­sion of a function in orthogonal functions, the Fourier series, the Fourier integral, the Fourier transform, the convolution theorem. Partial differential equations: physical foundations of partial differential equations, introduction to boundary value problems. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'ENGR', NULL, 311, NULL, 'Transform Calculus and Partial Differential Equations', 2, 3);
INSERT IGNORE INTO course VALUES (21, 3.00, 'Basic concepts and principles of fluid mechanics. Classification of fluid flow. Hydrostatic forces on plane and curved surfaces, buoyancy and stability, fluids in rigid body motion. Mass, momentum, and energy conservation integral equations. Bernoulli equation. Basic concepts of pipe and duct flow. Introduction to Navier-Stokes equations. Similarity and model studies. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'ENGR', NULL, 361, NULL, 'Fluid Mechanics I', 1, 3);

INSERT IGNORE INTO course VALUES (22, 3.50, 'Introduction to graphic language and design — means and techniques. The third and the first angle projections. Orthographic projection of points, lines, planes and solids. Principal and auxiliary views. Views in a given direction. Sectional views. Intersection of lines, planes and solids. Development of surfaces. Drafting practices. Dimensioning, fits and tolerancing. Computer-aided drawing and solid modelling. Working drawings — detail and assembly drawing. Design practice. Machine elements representation. Lectures: three hours per week. Tutorial: two hours per week — includes learning of a CAD software. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', NULL, 211, NULL, 'Mechanical Engineering Drawing', 2, 3);
INSERT IGNORE INTO course VALUES (23, 3.50, 'Writing programs using assignment and sequences. Variables and types. Operators and expressions. Conditional and repetitive statements. Input and output. File access. Functions. Program structure and organization. Pointers and dynamic memory allocation. Introduction to classes and objects. Mechanical and industrial engineering applications. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: one hour per week.',
1, 1, 3, 1, 'MECH', NULL, 215, NULL, 'Programming for Mechanical and Industrial Engineers', 1, 3);
INSERT IGNORE INTO course VALUES (24, 3.00, 'Relationships between properties and internal structure, atomic bonding; molecular, crystalline and amorphous structures, crystalline imperfections and mechanisms of structural change. Microstructures and their development from phase diagrams. Structures and mechanical properties of polymers and ceramics. Thermal, optical, and magnetic properties of materials. Lectures: three hours per week. Tutorial: one hour per week.',
1, 0, 3, 1, 'MECH', NULL, 221, NULL, 'Materials Science', 1, 3);
INSERT IGNORE INTO course VALUES (25, 3.75, 'Fundamentals of manufacturing processes and their limitations, metrology, machine shop practice, safety and health considerations, forming, conventional machining and casting processes, welding and joining, plastic production, and non-conventional machining techniques. Sustainable technologies. Laboratory includes instruction and practice on conventional machine tools and a manufacturing project. Lectures: three hours per week. Tutorial: two hours per week, including industrial visits and field trips to local industries. Laboratory: three hours per week, alternate weeks.',
1, 3, 3, 1, 'MECH', NULL, 311, NULL, 'Manufacturing Processes', 2, 3);
INSERT IGNORE INTO course VALUES (26, 3.50, 'Introduction to engi­neering design and design process. Problem definition, solution formulation, model development and collaboration aspects of design process.The use of drawings and other graphical methods in the process of engineering design. Industrial standards and specifications, design of fits, linear and geometrical tolerances. Design projects based on design philosophies will involve design and selection of many standard machine com­ponents like mechanical drives, cams, clutches, couplings, brakes, seals, fasteners, springs, and bearings. Drawing representation of standard components. Design projects are an integral part of this course. Lectures: three hours per week. Tutorial: two hours per week. Laboratory: 12 hours total',
1, 12, 3, 1, 'MECH', NULL, 313, NULL, 'Machine Drawing and Design', 2, 3);
INSERT IGNORE INTO course VALUES (27, 3.50, 'The service capabilities of alloys and their relationship to microstructure as produced by thermal and mechanical treatments; tensile and torsion tests; elements of dislocation theory; strengthening mechanisms; composite materials. Modes of failure of materials; fracture, fatigue, wear, creep, corrosion, radiation damage. Failure analysis. Material codes; material selection for design. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', NULL, 321, NULL, 'Properties and Failure of Materials', 1, 3);
INSERT IGNORE INTO course VALUES (28, 3.50, 'Introduction to mechanisms; position and displacement; velocity; acceleration; synthesis of linkage; robotics; static force analysis; dynamic force analysis; forward kinematics and inverse kinematics; introduction to gear analysis and gear box design; kinematic analysis of spatial mechanisms. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', NULL, 343, NULL, 'Theory of Machines', 1, 3);
INSERT IGNORE INTO course VALUES (29, 3.00, 'Introduction to machine design; static failure theories; failure of ductile vs. brittle materials under static loading. Fatigue failure theories; fatigue loads; notches and stress concentrations; residual stresses; designing for high cycle fatigue. Design of shafts, keys and couplings. Design of spur gears. Spring design. Design of screws and fasteners. Design of bearings. Case studies. Lectures: three hours per week. Tutorial: two hours per week.',
1, 0, 3, 1, 'MECH', 'NOTE: Students who have received credit for MECH 441 may not take this course for credit.', 344, NULL, 'Machine Element Design', 2, 3);
INSERT IGNORE INTO course VALUES (30, 3.50, 'Brief review of ideal gas processes. Semi-perfect gases and the gas tables. Mixtures of gases, gases and vapours, air conditioning processes. Combustion and combustion equilibrium. Applications of thermo­dynamics to power production and utilization systems: study of basic and advanced cycles for gas compression, internal combustion engines, power from steam, gas turbine cycles, and refrigeration. Real gases. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', "", 351, NULL, 'Thermodynamics II', 1, 3);
INSERT IGNORE INTO course VALUES (31, 3.50, 'Analytical and numerical methods for steady-state and transient heat conduction. Empirical and practical relations for forced- and free-convection heat transfer. Radiation heat exchange between black bodies, and between non-black bodies. Gas radiation. Solar radiation. Effect of radiation on temperature measurement. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', NULL, 352, NULL, 'Heat Transfer I', 1, 3);
INSERT IGNORE INTO course VALUES (32, 3.50, 'Differential analysis of fluid flows, vorticity, stream function, stresses, and strains. Navier-Stokes equations and solutions for parallel flows. Euler’s equations, irrotational and potential flows, plane potential flows. Viscous flows in pipes, laminar and turbulent flows, major and minor losses. Flow over immersed bodies, boundary layers, separation and thickness. Drag, lift and applications. Introduction to compressible flows, speed of sound, Mach cone, and some characteristics of supersonic flows. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', NULL, 361, NULL, 'Fluid Mechanics II', 1, 3);
INSERT IGNORE INTO course VALUES (33, 3.50, 'Dependent sources, voltage and current dividers, voltage and current sources, superposition, Thevenin and Norton equivalent sources, linear and nonlinear circuit analysis. Semiconductors and diodes. Bipolar Junction Transistors (BJT), Field Effect Transistors (FET); amplifiers and switches. Operational amplifiers; circuits and frequency response. Digital logic components and circuits. Digital systems. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', 'NOTE: Students who have received credit for MECH 470 may not take this course for credit. NOTE: Electrical Engineering and Computer Engineering students may not take this course for credit.', 368, NULL, 'Electronics for Mechanical Engineers', 1, 3);
INSERT IGNORE INTO course VALUES (34, 3.50, 'Definition and classification of dynamic systems and components. Modelling of dynamic systems containing individual or mixed mechanical, electrical, fluid and thermal elements. Block diagrams representation and simulation techniques using MATLAB/Simulink. Time domain analysis. Transient and steady-state characteristics of dynamic systems. Linearization. Transfer functions. Introduction to feedback control systems. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', 'NOTE: Students who have received credit for ELEC 370 may not take this course for credit.', 370, NULL, 'Modelling and Analysis of Dynamic Systems', 1, 3);
INSERT IGNORE INTO course VALUES (35, 3.75, 'Stability of linear feedback systems. Root-Locus method. Frequency response concepts. Stability in the frequency domain. Feedback system design using Root Locus techniques. Compensator concepts and configurations. PID-controller design. Simulation and computer-aided controller design using Matlab/Simulink. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: three hours per week, alternate weeks.',
1, 3, 3, 1, 'MECH', 'NOTE: Students who have received credit for ELEC 372 may not take this course for credit.', 371, NULL, 'Analysis and Design of Control Systems', 1, 3);
INSERT IGNORE INTO course VALUES (36, 3.50, 'Transient vibrations under impulsive shock and arbitrary excitation: normal modes, free and forced vibration. Multi-degree of freedom systems, influence coefficients, orthogonality principle, numerical methods. Continuous systems; longitudinal torsional and flexural free and forced vibrations of prismatic bars. Lagrange’s equations. Vibration measurements. Lectures: three hours per week. Tutorial: two hours per week. Laboratory: two hours per week, alternate weeks.',
1, 2, 3, 1, 'MECH', 'NOTE: Students who have received credit for MECH 443 may not take this course for credit', 375, NULL, 'Mechanical Vibrations', 2, 3);
INSERT IGNORE INTO course VALUES (37, 3.00, 'The design process; product cost, quality and time to market, open and concept design problems, problem description. Geometric and type synthesis. Direct and inverse design problems. Material selection and load determination. Mathematical modelling, analysis, and validation. Introduction to Computer-Aided Design and Engineering (CAD and CAE). Product evaluation for performance, tolerance, cost, manufacture, assembly, and other measures. Design documentation. A team-based design project is an intrinsic part of this course. Lectures: three hours per week. Tutorial: two hours per week',
1, 0, 3, 1, 'MECH', NULL, 390, NULL, 'Mechanical Engineering Design Project', 2, 3);
INSERT IGNORE INTO course VALUES (38, 4.00, 'A supervised design, simulation or experimental capstone design project including a preliminary project proposal with complete project plan and a technical report at the end of the fall term; a final report by the group and presentation at the end of the winter term. Lectures: one hour per week, one term. Equivalent laboratory time: three hours per week, two terms.',
1, 3, 1, 1, 'MECH', 'NOTE: Students will work in groups under direct supervision of a faculty member.', 490, NULL, 'Capstone Mechanical Engineering Design Project', 0, 3);
INSERT IGNORE INTO course VALUES (39, 3.50, 'No review of ideal gas processes. Students should be aware of this beforehand. Semi-perfect gases and the gas tables. Mixtures of gases, gases and vapours, air conditioning processes. Combustion and combustion equilibrium. Applications of thermo­dynamics to power production and utilization systems: study of basic and advanced cycles for gas compression, internal combustion engines, power from steam, gas turbine cycles, and refrigeration. Real gases. Lectures: three hours per week. Tutorial: one hour per week. Laboratory: two hours per week, alternate weeks.',
0, 2, 3, 1, 'MECH', "NOTE: Students will not perform any cool experiments unfortunately.", 351, NULL, 'Thermodynamics II', 1, 3);

-- id, is_active, name, number, type, course_id
INSERT IGNORE INTO requisite VALUES (1, 1, "SOEN", 341, "prerequisite", 6);-- soen 342 needs 341
INSERT IGNORE INTO requisite VALUES (2, 1, "SOEN", 341, "prerequisite", 7);-- soen 343 needs 341
INSERT IGNORE INTO requisite VALUES (3, 1, "SOEN", 342, "corequisite", 7);-- soen 343 needs 342
INSERT IGNORE INTO requisite VALUES (4, 1, "SOEN", 343, "prerequisite", 8);-- soen 344 needs 343
INSERT IGNORE INTO requisite VALUES (5, 1, "SOEN", 343, "prerequisite", 9);-- soen 345 needs 343
INSERT IGNORE INTO requisite VALUES (6, 1, "SOEN", 341, "prerequisite", 10);-- soen 357 needs 341
INSERT IGNORE INTO requisite VALUES (7, 0, "SOEN", 341, "prerequisite", 15);-- soen 343 edited needs 341
INSERT IGNORE INTO requisite VALUES (8, 0, "SOEN", 331, "prerequisite", 15);-- soen 343 edited needs 331
INSERT IGNORE INTO requisite VALUES (9, 0, "SOEN", 343, "prerequisite", 10);-- soen 357 edited needs 343 edited

INSERT IGNORE INTO requisite VALUES (10, 1, "ENGR", 213, "prerequisite", 16);
INSERT IGNORE INTO requisite VALUES (11, 1, "PHYS", 204, "prerequisite", 16);
INSERT IGNORE INTO requisite VALUES (12, 1, "MATH", 204, "prerequisite", 16);
INSERT IGNORE INTO requisite VALUES (13, 1, "ENGR", 213, "prerequisite", 17);
INSERT IGNORE INTO requisite VALUES (14, 1, "ENGR", 242, "prerequisite", 17);
INSERT IGNORE INTO requisite VALUES (15, 1, "ENGR", 213, "prerequisite", 18);
INSERT IGNORE INTO requisite VALUES (16, 1, "ENGR", 233, "corequisite", 18);
INSERT IGNORE INTO requisite VALUES (17, 1, "ENGR", 242, "or-prerequisite", 18); -- new type or-prerequisite; you either had to do 242 or 245 as prerequisites
INSERT IGNORE INTO requisite VALUES (18, 1, "ENGR", 245, "or-prerequisite", 18); -- new type or-prerequisite; you either had to do 242 or 245 as prerequisites
INSERT IGNORE INTO requisite VALUES (19, 1, "MATH", 203, "prerequisite", 19);
INSERT IGNORE INTO requisite VALUES (20, 1, "ENGR", 213, "prerequisite", 20);
INSERT IGNORE INTO requisite VALUES (21, 1, "ENGR", 233, "prerequisite", 20);
INSERT IGNORE INTO requisite VALUES (22, 1, "ENGR", 213, "prerequisite", 21);
INSERT IGNORE INTO requisite VALUES (23, 1, "ENGR", 233, "prerequisite", 21);
INSERT IGNORE INTO requisite VALUES (24, 1, "ENGR", 251, "prerequisite", 21);

INSERT IGNORE INTO requisite VALUES (25, 1, "COMP", 354, "equivalent", 5); -- soen 341 = comp 354
INSERT IGNORE INTO requisite VALUES (26, 1, "COMP", 228, "equivalent", 5); -- soen 228 = comp 228

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
INSERT IGNORE INTO requisite VALUES (39, 1, "MECH", 441, "equivalent", 29);
INSERT IGNORE INTO requisite VALUES (40, 1, "ENGR", 251, "prerequisite", 30);
INSERT IGNORE INTO requisite VALUES (41, 1, "ENGR", 311, "prerequisite", 31);
INSERT IGNORE INTO requisite VALUES (42, 1, "ENGR", 361, "prerequisite", 31);
INSERT IGNORE INTO requisite VALUES (43, 1, "ENGR", 361, "prerequisite", 32);
INSERT IGNORE INTO requisite VALUES (44, 1, "PHYS", 205, "prerequisite", 33);
INSERT IGNORE INTO requisite VALUES (45, 1, "ENGR", 311, "corequisite", 33);
INSERT IGNORE INTO requisite VALUES (46, 1, "MECH", 470, "equivalent", 33);
INSERT IGNORE INTO requisite VALUES (47, 1, "PHYS", 205, "prerequisite", 34);
INSERT IGNORE INTO requisite VALUES (48, 1, "ENGR", 213, "prerequisite", 34);
INSERT IGNORE INTO requisite VALUES (49, 1, "ENGR", 311, "corequisite", 34);
INSERT IGNORE INTO requisite VALUES (50, 1, "ENGR", 243, "or-prerequisite", 34);
INSERT IGNORE INTO requisite VALUES (51, 1, "ENGR", 245, "or-prerequisite", 34);
INSERT IGNORE INTO requisite VALUES (52, 1, "ELEC", 370, "equivalent", 34);
INSERT IGNORE INTO requisite VALUES (53, 1, "ENGR", 311, "prerequisite", 35);
INSERT IGNORE INTO requisite VALUES (54, 1, "MECH", 370, "prerequisite", 35);
INSERT IGNORE INTO requisite VALUES (55, 1, "ELEC", 372, "equivalent", 35);
INSERT IGNORE INTO requisite VALUES (56, 1, "AERO", 371, "or-prerequisite", 36);
INSERT IGNORE INTO requisite VALUES (57, 1, "MECH", 370, "or-prerequisite", 36);
INSERT IGNORE INTO requisite VALUES (58, 1, "MECH", 443, "equivalent", 36);
INSERT IGNORE INTO requisite VALUES (59, 1, "ENCS", 282, "prerequisite", 37);
INSERT IGNORE INTO requisite VALUES (60, 1, "MECH", 311, "prerequisite", 37);
INSERT IGNORE INTO requisite VALUES (61, 1, "MECH", 343, "prerequisite", 37);
INSERT IGNORE INTO requisite VALUES (62, 1, "MECH", 344, "corequisite", 37);
INSERT IGNORE INTO requisite VALUES (63, 1, "ENCS", 282, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (64, 1, "ENGR", 301, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (65, 1, "MECH", 344, "prerequisite", 38);
INSERT IGNORE INTO requisite VALUES (66, 1, "MECH", 390, "corequisite", 38);
INSERT IGNORE INTO requisite VALUES (67, 1, "75 credits in the program", 000, "prerequisite", 38);

-- degree_id, course_id
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 1);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 2);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 3);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 4);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 5);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 6);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 7);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 8);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 9);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 10);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 11);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 12);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 13);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 1, 14);
INSERT IGNORE INTO degree_requirement VALUES ("Software Engineering Core", 2, 15);
INSERT IGNORE INTO degree_requirement VALUES ("Electives", 4, 15);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 16);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 17);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 18);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 19);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 20);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 21);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 22);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 23);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 24);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 25);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 26);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 27);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 28);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 29);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 30);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 31);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 32);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 33);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 34);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 35);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 36);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 37);
INSERT IGNORE INTO degree_requirement VALUES ("Mechanical Engineering Core", 5, 38);

-- id, body, section_id, section_title, section_type, department_id, faculty_id
INSERT IGNORE INTO calendar VALUES (1, 'Both major and minor programs in Management Information Systems can be found in the John Molson School of Business Section of the Undergraduate Calendar, §61. The Faculty of Fine Arts and the Department of Computer Science and Software Engineering offer complementary major programs. Students who take the Computer Applications Option (see §71.70.2 above) can also take the Major in Computation Arts and Computer Science (see §71.80, and the Fine Arts Section, §81) or the Joint Major in Mathematics and Statistics and Computer Applications (see §71.85, and the Mathematics and Statistics Section, §31.200).', '71.70.6', 'Programs Related to Computer Science', 'general',  4, 2);
INSERT IGNORE INTO calendar VALUES (2, 'Students employed full‑time in a computer science position during their non‑study terms may have this Industrial Experience listed on their official transcript and student record, provided they successfully complete the Reflective Learning course associated with this work term. Students may only register for these courses with the permission of the Faculty. The Industrial Experience terms COMP 107 and 207 carry no credit value and are used to indicate that the student is on an Industrial Experience term. The COMP 108 and 208 Industrial Experience Reflective Learning courses are worth three credits and are marked on a pass/fail basis. They are above and beyond the credit requirements of the student’s program and are not transferable nor are they included in the full‑ or part‑time assessment status. Students studying for a co‑op work term or CIADI term should not register for these Industrial Experience and Reflective Learning courses.', '71.70.7', 'Industrial Experience and Reflective Learning Courses', 'general', 4, 2);
INSERT IGNORE INTO calendar VALUES (3, "Please note that the current version of the Undergraduate Calendar is up to date as of February 2019.", "71.60", "Engineering Course Descriptions", "", 8, 2);

-- id, pdf_file, department_id
INSERT IGNORE INTO request_package VALUES (1, NULL, 4); -- creating a package for department of CS & SE
INSERT IGNORE INTO request_package VALUES (2, NULL, 8); -- used for pdf generation

-- id, original id, rationale, request type, target id, target type, timestamp, package id, user id
INSERT IGNORE INTO request VALUES (1, 7, "rationale", 2, "resource implications", 15, 2, NULL, 1, 1); -- updating request for the course soen 343
INSERT IGNORE INTO request VALUES (2, 7, "rationale", 3, "resource implications", 15, 2, NULL, 1, 1); -- removing request for the course soen 343
INSERT IGNORE INTO request VALUES (3, 7, "rationale", 1, "resource implications", 15, 2, NULL, 1, 1); -- creating request for the course soen 343
INSERT IGNORE INTO request VALUES (4, 30, "rationale", 2, "resource implications", 39, 2, NULL, 2, 1); -- thermodynamics II updated

-- id, apc, dcc, fcc, gdc, senate, ugdc
INSERT IGNORE INTO approval_pipeline VALUES (1, 4, 1, 2, 0, 5, 3); -- dcc -> fcc -> ugdc -> apc -> senate