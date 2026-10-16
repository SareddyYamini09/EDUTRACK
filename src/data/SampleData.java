package data;

import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Realistic and rich sample datasets for EduTrack.
 * Contains:
 * - 25 Students
 * - 10 Faculty
 * - 12 Courses
 * - 20 Assignments
 * - 10 Examinations
 * - 12 Resources
 * - 40 Academic Activities
 */
public class SampleData {

    public static List<Student> getInitialStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("STU101", "Aarav Sharma", "CSE", 5, "aarav.sharma@edutrack.edu", 3.85, Arrays.asList("DSA301", "OS301", "AI201")));
        list.add(new Student("STU102", "Yamini Reddy", "AI", 5, "yamini.reddy@edutrack.edu", 3.96, Arrays.asList("AI201", "ML301", "DSA301")));
        list.add(new Student("STU103", "Rohan Verma", "CSE", 3, "rohan.verma@edutrack.edu", 3.42, Arrays.asList("JAVA201", "DBMS201")));
        list.add(new Student("STU104", "Pooja Hegde", "ECE", 5, "pooja.hegde@edutrack.edu", 3.71, Arrays.asList("CN301", "OS301")));
        list.add(new Student("STU105", "Vikram Malhotra", "IT", 7, "vikram.m@edutrack.edu", 3.55, Arrays.asList("CLOUD401", "SE302")));
        list.add(new Student("STU106", "Ananya Iyer", "AI", 3, "ananya.iyer@edutrack.edu", 3.91, Arrays.asList("AI101", "JAVA201")));
        list.add(new Student("STU107", "Karthik Nair", "CSE", 5, "karthik.nair@edutrack.edu", 3.28, Arrays.asList("DSA301", "DBMS201")));
        list.add(new Student("STU108", "Sneha Patel", "EEE", 5, "sneha.patel@edutrack.edu", 3.64, Arrays.asList("CIR301", "CN301")));
        list.add(new Student("STU109", "Rahul Roy", "IT", 3, "rahul.roy@edutrack.edu", 3.15, Arrays.asList("JAVA201", "WEB202")));
        list.add(new Student("STU110", "Meera Joshi", "AI", 5, "meera.joshi@edutrack.edu", 3.88, Arrays.asList("ML301", "AI201", "DSA301")));
        list.add(new Student("STU111", "Aditya Kulkarni", "CSE", 7, "aditya.k@edutrack.edu", 3.78, Arrays.asList("SE302", "CLOUD401")));
        list.add(new Student("STU112", "Divya Menon", "ECE", 3, "divya.m@edutrack.edu", 3.50, Arrays.asList("CN301", "JAVA201")));
        list.add(new Student("STU113", "Siddharth Rao", "CSE", 5, "siddharth.r@edutrack.edu", 3.62, Arrays.asList("DSA301", "OS301")));
        list.add(new Student("STU114", "Priyanka Sen", "IT", 5, "priyanka.s@edutrack.edu", 3.45, Arrays.asList("DBMS201", "WEB202")));
        list.add(new Student("STU115", "Varun Mehta", "AI", 7, "varun.mehta@edutrack.edu", 3.94, Arrays.asList("ML301", "NLP401")));
        list.add(new Student("STU116", "Ishita Gupta", "CSE", 3, "ishita.g@edutrack.edu", 3.69, Arrays.asList("JAVA201", "DSA301")));
        list.add(new Student("STU117", "Gaurav Sen", "IT", 5, "gaurav.sen@edutrack.edu", 3.82, Arrays.asList("DBMS201", "OS301")));
        list.add(new Student("STU118", "Tanvi Bhat", "EEE", 3, "tanvi.bhat@edutrack.edu", 3.39, Arrays.asList("CIR301", "JAVA201")));
        list.add(new Student("STU119", "Nikhil Chopra", "ECE", 7, "nikhil.c@edutrack.edu", 3.58, Arrays.asList("CN301", "CLOUD401")));
        list.add(new Student("STU120", "Ritu Das", "AI", 5, "ritu.das@edutrack.edu", 3.76, Arrays.asList("AI201", "ML301")));
        list.add(new Student("STU121", "Manish Pandey", "CSE", 5, "manish.p@edutrack.edu", 3.31, Arrays.asList("DSA301", "JAVA201")));
        list.add(new Student("STU122", "Kavita Pillai", "IT", 3, "kavita.p@edutrack.edu", 3.73, Arrays.asList("WEB202", "DBMS201")));
        list.add(new Student("STU123", "Arjun Nambiar", "ECE", 5, "arjun.n@edutrack.edu", 3.60, Arrays.asList("CN301", "CIR301")));
        list.add(new Student("STU124", "Swati Chawla", "AI", 7, "swati.c@edutrack.edu", 3.98, Arrays.asList("ML301", "NLP401", "AI201")));
        list.add(new Student("STU125", "Deepak Verma", "CSE", 3, "deepak.v@edutrack.edu", 3.25, Arrays.asList("JAVA201", "DBMS201")));
        return list;
    }

    public static List<Faculty> getInitialFaculty() {
        List<Faculty> list = new ArrayList<>();
        list.add(new Faculty("FAC01", "Dr. Alan Turing", "CSE", "Algorithms & Automata", Arrays.asList("DSA301", "OS301")));
        list.add(new Faculty("FAC02", "Dr. Geoffrey Hinton", "AI", "Deep Learning & Neural Nets", Arrays.asList("AI201", "ML301", "NLP401")));
        list.add(new Faculty("FAC03", "Dr. Edgar Codd", "IT", "Relational Database Systems", Arrays.asList("DBMS201", "WEB202")));
        list.add(new Faculty("FAC04", "Dr. Andrew Tanenbaum", "ECE", "Computer Networks & OS", Arrays.asList("CN301", "OS301")));
        list.add(new Faculty("FAC05", "Dr. James Gosling", "CSE", "Programming & OOP Systems", Arrays.asList("JAVA201", "SE302")));
        list.add(new Faculty("FAC06", "Dr. Fei-Fei Li", "AI", "Computer Vision & AI Systems", Arrays.asList("AI101", "AI201")));
        list.add(new Faculty("FAC07", "Dr. Donald Knuth", "CSE", "Concrete Mathematics & Analysis", Arrays.asList("DSA301", "SE302")));
        list.add(new Faculty("FAC08", "Dr. Claude Shannon", "ECE", "Information Theory & Signals", Arrays.asList("CN301", "CIR301")));
        list.add(new Faculty("FAC09", "Dr. Leslie Lamport", "IT", "Distributed & Cloud Computing", Arrays.asList("CLOUD401", "OS301")));
        list.add(new Faculty("FAC10", "Dr. Nikola Tesla", "EEE", "Electromagnetic Circuits", Arrays.asList("CIR301")));
        return list;
    }

    public static List<Course> getInitialCourses() {
        List<Course> list = new ArrayList<>();
        list.add(new Course("JAVA201", "Object Oriented Java", "CSE", 3, "Dr. James Gosling", Arrays.asList()));
        list.add(new Course("DSA301", "Data Structures & Algorithms", "CSE", 4, "Dr. Alan Turing", Arrays.asList("JAVA201")));
        list.add(new Course("DBMS201", "Database Management Systems", "IT", 3, "Dr. Edgar Codd", Arrays.asList()));
        list.add(new Course("OS301", "Operating Systems Principles", "CSE", 4, "Dr. Andrew Tanenbaum", Arrays.asList("DSA301")));
        list.add(new Course("CN301", "Computer Networks", "ECE", 3, "Dr. Andrew Tanenbaum", Arrays.asList("OS301")));
        list.add(new Course("AI101", "Introduction to Artificial Intelligence", "AI", 3, "Dr. Fei-Fei Li", Arrays.asList()));
        list.add(new Course("AI201", "Advanced Artificial Intelligence", "AI", 4, "Dr. Geoffrey Hinton", Arrays.asList("AI101")));
        list.add(new Course("ML301", "Machine Learning Techniques", "AI", 4, "Dr. Geoffrey Hinton", Arrays.asList("AI201", "DSA301")));
        list.add(new Course("NLP401", "Natural Language Processing", "AI", 3, "Dr. Geoffrey Hinton", Arrays.asList("ML301")));
        list.add(new Course("WEB202", "Full Stack Web Engineering", "IT", 3, "Dr. Edgar Codd", Arrays.asList("JAVA201")));
        list.add(new Course("SE302", "Software Engineering Architecture", "CSE", 3, "Dr. Donald Knuth", Arrays.asList("JAVA201")));
        list.add(new Course("CLOUD401", "Cloud Infrastructure Systems", "IT", 4, "Dr. Leslie Lamport", Arrays.asList("CN301", "OS301")));
        return list;
    }

    public static List<Assignment> getInitialAssignments() {
        List<Assignment> list = new ArrayList<>();
        list.add(new Assignment("ASN01", "DSA301", "Graph Algorithms Implementation", "Implement Dijkstra and Aho-Corasick in Java", "2026-10-01", "STU101", 92.5));
        list.add(new Assignment("ASN02", "DSA301", "Graph Algorithms Implementation", "Implement Dijkstra and Aho-Corasick graph solver", "2026-10-01", "STU102", 98.0));
        list.add(new Assignment("ASN03", "AI201", "Heuristic Search Agents", "Design A-star search heuristic algorithm for maze", "2026-10-05", "STU102", 95.0));
        list.add(new Assignment("ASN04", "ML301", "Neural Network From Scratch", "Gradient descent backpropagation model training", "2026-10-10", "STU102", 97.5));
        list.add(new Assignment("ASN05", "JAVA201", "Polymorphism & Collections", "Design university management hierarchy in Java", "2026-09-25", "STU103", 84.0));
        list.add(new Assignment("ASN06", "DBMS201", "B-Tree Indexing Engine", "Write B-Tree index structure for relational tuples", "2026-10-08", "STU103", 88.0));
        list.add(new Assignment("ASN07", "CN301", "Sliding Window Protocol", "Simulate Go-Back-N protocol with packet drops", "2026-10-12", "STU104", 89.5));
        list.add(new Assignment("ASN08", "OS301", "Process Scheduler Simulation", "Implement Multi-Level Feedback Queue scheduler", "2026-10-15", "STU101", 91.0));
        list.add(new Assignment("ASN09", "CLOUD401", "Kubernetes Deployment", "Configure container orchestration microservice cluster", "2026-10-18", "STU105", 87.0));
        list.add(new Assignment("ASN10", "SE302", "Design Patterns Case Study", "Implement Factory and Observer structural patterns", "2026-10-20", "STU105", 85.5));
        list.add(new Assignment("ASN11", "AI101", "Propositional Logic Solver", "Truth table solver and DPLL satisfiability check", "2026-09-30", "STU106", 94.0));
        list.add(new Assignment("ASN12", "JAVA201", "Polymorphism & Collections", "Design university management hierarchy in Java OOP", "2026-09-25", "STU106", 92.0));
        list.add(new Assignment("ASN13", "DSA301", "Dynamic Programming Suite", "Matrix Chain Multiplication and Levenshtein code", "2026-10-04", "STU107", 78.0));
        list.add(new Assignment("ASN14", "DBMS201", "SQL Query Optimization", "Implement relational algebra query plan optimizer", "2026-10-09", "STU107", 81.5));
        list.add(new Assignment("ASN15", "ML301", "Support Vector Machines", "Quadratic programming dual problem solver implementation", "2026-10-11", "STU110", 96.0));
        list.add(new Assignment("ASN16", "AI201", "Reinforcement Q-Learning", "Markov decision process gridworld Q-learning model", "2026-10-14", "STU110", 94.5));
        list.add(new Assignment("ASN17", "CLOUD401", "Consensus Protocol Raft", "Implement leader election and log replication logic", "2026-10-22", "STU111", 93.0));
        list.add(new Assignment("ASN18", "DSA301", "Network Flow Ford-Fulkerson", "Max flow residual network min-cut implementation", "2026-10-06", "STU113", 89.0));
        list.add(new Assignment("ASN19", "NLP401", "Transformer Attention Layer", "Multi-head scaled dot-product self-attention network", "2026-10-25", "STU115", 99.0));
        list.add(new Assignment("ASN20", "DSA301", "Suffix Array & LCP Engine", "Implement Suffix Array and Kasai LCP algorithm", "2026-10-02", "STU116", 90.0));
        return list;
    }

    public static List<Examination> getInitialExaminations() {
        List<Examination> list = new ArrayList<>();
        list.add(new Examination("EXAM01", "DSA301", "2026-11-10", "09:30 AM", "Hall-A", 120));
        list.add(new Examination("EXAM02", "AI201", "2026-11-11", "09:30 AM", "Hall-B", 80));
        list.add(new Examination("EXAM03", "DBMS201", "2026-11-12", "02:00 PM", "Hall-A", 100));
        list.add(new Examination("EXAM04", "OS301", "2026-11-13", "09:30 AM", "Hall-C", 110));
        list.add(new Examination("EXAM05", "CN301", "2026-11-14", "02:00 PM", "Hall-B", 90));
        list.add(new Examination("EXAM06", "ML301", "2026-11-16", "09:30 AM", "Auditorium", 150));
        list.add(new Examination("EXAM07", "JAVA201", "2026-11-17", "02:00 PM", "Hall-A", 130));
        list.add(new Examination("EXAM08", "CLOUD401", "2026-11-18", "09:30 AM", "Lab-301", 60));
        list.add(new Examination("EXAM09", "NLP401", "2026-11-19", "02:00 PM", "Lab-202", 50));
        list.add(new Examination("EXAM10", "SE302", "2026-11-20", "09:30 AM", "Hall-C", 85));
        return list;
    }

    public static List<Resource> getInitialResources() {
        List<Resource> list = new ArrayList<>();
        list.add(new Resource("RES01", "Auditorium Main", "Lecture Hall", "Block-A", 200, true));
        list.add(new Resource("RES02", "Hall-A Seminar Room", "Classroom", "Block-A", 120, true));
        list.add(new Resource("RES03", "Hall-B Smart Room", "Classroom", "Block-B", 80, true));
        list.add(new Resource("RES04", "Hall-C Tiered Room", "Classroom", "Block-B", 100, false));
        list.add(new Resource("RES05", "Lab-101 Computing Core", "Computer Lab", "Block-C", 60, true));
        list.add(new Resource("RES06", "Lab-202 AI & GPU Cluster", "Specialized Lab", "Block-C", 50, true));
        list.add(new Resource("RES07", "Lab-301 Cloud & Networks", "Computer Lab", "Block-C", 60, true));
        list.add(new Resource("RES08", "Library Discussion Room 1", "Study Room", "Library-2F", 20, true));
        list.add(new Resource("RES09", "Library Discussion Room 2", "Study Room", "Library-2F", 20, false));
        list.add(new Resource("RES10", "Electronics Workshop Lab", "Hardware Lab", "Block-D", 45, true));
        list.add(new Resource("RES11", "Innovation Incubation Bay", "Project Space", "Block-E", 35, true));
        list.add(new Resource("RES12", "Conference Boardroom", "Executive Room", "Admin-1F", 25, true));
        return list;
    }

    public static List<AcademicActivity> getInitialActivities() {
        List<AcademicActivity> list = new ArrayList<>();
        String[] types = {"Portal Login", "Assignment Submission", "Quiz Attempt", "Lecture Video Watched", "Course Material Download", "Forum Discussion Post"};
        list.add(new AcademicActivity("ACT001", "STU102", "Portal Login", "2026-09-01 08:30:00", 1.0));
        list.add(new AcademicActivity("ACT002", "STU102", "Quiz Attempt", "2026-09-01 09:15:00", 98.0));
        list.add(new AcademicActivity("ACT003", "STU101", "Portal Login", "2026-09-01 10:00:00", 1.0));
        list.add(new AcademicActivity("ACT004", "STU101", "Lecture Video Watched", "2026-09-01 10:30:00", 45.0));
        list.add(new AcademicActivity("ACT005", "STU103", "Course Material Download", "2026-09-01 11:20:00", 12.0));
        list.add(new AcademicActivity("ACT006", "STU104", "Portal Login", "2026-09-01 13:00:00", 1.0));
        list.add(new AcademicActivity("ACT007", "STU102", "Assignment Submission", "2026-09-01 14:45:00", 100.0));
        list.add(new AcademicActivity("ACT008", "STU105", "Portal Login", "2026-09-02 08:45:00", 1.0));
        list.add(new AcademicActivity("ACT009", "STU106", "Quiz Attempt", "2026-09-02 09:30:00", 94.0));
        list.add(new AcademicActivity("ACT010", "STU107", "Forum Discussion Post", "2026-09-02 11:00:00", 5.0));
        list.add(new AcademicActivity("ACT011", "STU108", "Portal Login", "2026-09-02 11:30:00", 1.0));
        list.add(new AcademicActivity("ACT012", "STU102", "Forum Discussion Post", "2026-09-02 12:15:00", 10.0));
        list.add(new AcademicActivity("ACT013", "STU109", "Course Material Download", "2026-09-02 14:00:00", 15.0));
        list.add(new AcademicActivity("ACT014", "STU110", "Lecture Video Watched", "2026-09-02 15:30:00", 60.0));
        list.add(new AcademicActivity("ACT015", "STU110", "Assignment Submission", "2026-09-02 17:00:00", 95.0));
        list.add(new AcademicActivity("ACT016", "STU111", "Portal Login", "2026-09-03 08:15:00", 1.0));
        list.add(new AcademicActivity("ACT017", "STU112", "Quiz Attempt", "2026-09-03 09:45:00", 88.0));
        list.add(new AcademicActivity("ACT018", "STU113", "Assignment Submission", "2026-09-03 10:30:00", 90.0));
        list.add(new AcademicActivity("ACT019", "STU114", "Portal Login", "2026-09-03 11:00:00", 1.0));
        list.add(new AcademicActivity("ACT020", "STU115", "Lecture Video Watched", "2026-09-03 12:30:00", 55.0));
        list.add(new AcademicActivity("ACT021", "STU116", "Course Material Download", "2026-09-03 14:15:00", 20.0));
        list.add(new AcademicActivity("ACT022", "STU117", "Portal Login", "2026-09-04 08:30:00", 1.0));
        list.add(new AcademicActivity("ACT023", "STU118", "Forum Discussion Post", "2026-09-04 09:20:00", 8.0));
        list.add(new AcademicActivity("ACT024", "STU119", "Quiz Attempt", "2026-09-04 10:45:00", 85.0));
        list.add(new AcademicActivity("ACT025", "STU120", "Portal Login", "2026-09-04 11:15:00", 1.0));
        list.add(new AcademicActivity("ACT026", "STU121", "Assignment Submission", "2026-09-04 13:00:00", 82.0));
        list.add(new AcademicActivity("ACT027", "STU122", "Course Material Download", "2026-09-04 14:30:00", 18.0));
        list.add(new AcademicActivity("ACT028", "STU123", "Lecture Video Watched", "2026-09-05 08:50:00", 40.0));
        list.add(new AcademicActivity("ACT029", "STU124", "Portal Login", "2026-09-05 09:10:00", 1.0));
        list.add(new AcademicActivity("ACT030", "STU124", "Assignment Submission", "2026-09-05 10:00:00", 100.0));
        list.add(new AcademicActivity("ACT031", "STU124", "Quiz Attempt", "2026-09-05 11:30:00", 99.0));
        list.add(new AcademicActivity("ACT032", "STU125", "Portal Login", "2026-09-05 13:00:00", 1.0));
        list.add(new AcademicActivity("ACT033", "STU101", "Assignment Submission", "2026-09-05 15:30:00", 93.0));
        list.add(new AcademicActivity("ACT034", "STU102", "Lecture Video Watched", "2026-09-06 08:45:00", 70.0));
        list.add(new AcademicActivity("ACT035", "STU103", "Portal Login", "2026-09-06 10:15:00", 1.0));
        list.add(new AcademicActivity("ACT036", "STU104", "Course Material Download", "2026-09-06 11:45:00", 25.0));
        list.add(new AcademicActivity("ACT037", "STU105", "Forum Discussion Post", "2026-09-06 14:00:00", 12.0));
        list.add(new AcademicActivity("ACT038", "STU106", "Portal Login", "2026-09-07 09:00:00", 1.0));
        list.add(new AcademicActivity("ACT039", "STU107", "Quiz Attempt", "2026-09-07 10:30:00", 80.0));
        list.add(new AcademicActivity("ACT040", "STU102", "Course Material Download", "2026-09-07 12:00:00", 30.0));
        return list;
    }
}
