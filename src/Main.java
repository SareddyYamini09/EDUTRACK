import data.DataManager;
import m1_string.*;
import m2_suffix.*;
import m3_dp.*;
import m4_flow.*;
import m5_np.*;
import m6_random_parallel.*;
import model.*;
import services.*;
import utils.DisplayUtils;
import utils.InputUtils;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main application class for EduTrack – Intelligent University Academic Platform.
 * Styled with clean double-line box UI and comprehensive descriptive submenus.
 */
public class Main {
    private static DataManager dataManager;
    private static StudentService studentService;
    private static FacultyService facultyService;
    private static CourseService courseService;
    private static AssignmentService assignmentService;
    private static ExaminationService examinationService;
    private static ResourceService resourceService;
    private static AnalyticsService analyticsService;

    public static void main(String[] args) {
        // Enable UTF-8 for console output so box-drawing characters render properly
        try {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        } catch (Exception ignored) {}

        System.out.println("Initializing EduTrack: Intelligent University Academic Platform...");

        // Initialize DataManager with persistent CSV loading / fallback
        dataManager = DataManager.getInstance();
        dataManager.initialize();

        studentService = new StudentService(dataManager);
        facultyService = new FacultyService(dataManager);
        courseService = new CourseService(dataManager);
        assignmentService = new AssignmentService(dataManager);
        examinationService = new ExaminationService(dataManager);
        resourceService = new ResourceService(dataManager);
        analyticsService = new AnalyticsService(dataManager);

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = InputUtils.readInt("Enter your choice: ", 0, 8);
            switch (choice) {
                case 1 -> handleAcademicRecordsMenu();
                case 2 -> handleAcademicSearchMenu();
                case 3 -> handleDocumentAnalysisMenu();
                case 4 -> handleAcademicPlanningMenu();
                case 5 -> handleResourceAllocationMenu();
                case 6 -> handleConstraintAnalysisMenu();
                case 7 -> handleAnalyticsParallelMenu();
                case 8 -> handleAlgorithmComparisonMenu();
                case 0 -> {
                    System.out.println("\nSaving application data...");
                    dataManager.saveAll();
                    System.out.println("Goodbye! Have a successful review session.");
                    running = false;
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                            EDUTRACK                                ║");
        System.out.println("║           Intelligent University Academic Platform                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Academic Records                                               ║");
        System.out.println("║  2. Academic Search                                                ║");
        System.out.println("║  3. Document Analysis                                              ║");
        System.out.println("║  4. Academic Planning & Optimization                               ║");
        System.out.println("║  5. Resource Allocation                                            ║");
        System.out.println("║  6. Constraint Analysis                                            ║");
        System.out.println("║  7. Analytics & Parallel Processing                                ║");
        System.out.println("║  8. Algorithm Comparison                                           ║");
        System.out.println("║  0. Exit                                                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    // =========================================================================
    // 1. ACADEMIC RECORDS
    // =========================================================================
    private static void handleAcademicRecordsMenu() {
        boolean back = false;
        while (!back) {
            DisplayUtils.printSectionHeader("ACADEMIC RECORDS");
            System.out.println("1. View All Students");
            System.out.println("   -> Display enrolled student records\n");
            System.out.println("2. Search Student");
            System.out.println("   -> Search a student by ID or Name\n");
            System.out.println("3. Merit Honor Roll");
            System.out.println("   -> Rank students by GPA (Randomized QuickSort)\n");
            System.out.println("4. Faculty Directory");
            System.out.println("   -> View faculty profiles and teaching specializations\n");
            System.out.println("5. Course Catalog");
            System.out.println("   -> View offered courses and credit requirements\n");
            System.out.println("6. Assignment Submissions");
            System.out.println("   -> View assignment records and student scores\n");
            System.out.println("7. Examination Timetable");
            System.out.println("   -> View scheduled semester examinations\n");
            System.out.println("8. Campus Resources");
            System.out.println("   -> View classrooms, computer labs, and facilities\n");
            System.out.println("0. Back to Main Menu\n");

            int ch = InputUtils.readInt("Enter your choice: ", 0, 8);
            switch (ch) {
                case 1 -> studentService.displayAllStudents();
                case 2 -> {
                    String query = InputUtils.readString("Enter student name or ID to search: ");
                    studentService.searchStudent(query);
                }
                case 3 -> studentService.rankStudentsByGPA();
                case 4 -> facultyService.displayAllFaculty();
                case 5 -> courseService.displayAllCourses();
                case 6 -> assignmentService.displayAllAssignments();
                case 7 -> examinationService.displayAllExaminations();
                case 8 -> resourceService.displayAllResources();
                case 0 -> back = true;
            }
            if (!back) InputUtils.pause();
        }
    }

    // =========================================================================
    // 2. ACADEMIC SEARCH
    // =========================================================================
    private static void handleAcademicSearchMenu() {
        boolean back = false;
        while (!back) {
            DisplayUtils.printSectionHeader("ACADEMIC SEARCH");
            System.out.println("1. Knuth-Morris-Pratt (KMP) Search");
            System.out.println("   -> Search course titles and student records in O(n+m) time\n");
            System.out.println("2. Z-Function Pattern Matching");
            System.out.println("   -> Prefix-based phrase repetition detection in O(n) time\n");
            System.out.println("3. Rabin-Karp Rolling Hash Search");
            System.out.println("   -> Rapid exact matching for course codes (mod 10^9+7)\n");
            System.out.println("4. Aho-Corasick Multi-Keyword Search");
            System.out.println("   -> Simultaneous multi-pattern extraction across curriculum notes\n");
            System.out.println("5. Typo-Tolerant Search Query");
            System.out.println("   -> Fuzzy student name lookup with Levenshtein typo suggestion\n");
            System.out.println("0. Back to Main Menu\n");

            int ch = InputUtils.readInt("Enter your choice: ", 0, 5);
            switch (ch) {
                case 1 -> {
                    String t = InputUtils.readString("Enter text", "Data Structures and Algorithms for Intelligent Systems");
                    String p = InputUtils.readString("Enter pattern", "Data");
                    KMP.demonstrate(t, p);
                }
                case 2 -> {
                    String t = InputUtils.readString("Enter text", "abracadabra");
                    String p = InputUtils.readString("Enter pattern", "abra");
                    ZFunction.demonstrate(t, p);
                }
                case 3 -> {
                    String t = InputUtils.readString("Enter text", "Course Enrollment in DSA301 and AI201 for Fall 2026");
                    String p = InputUtils.readString("Enter pattern", "DSA301");
                    RabinKarp.demonstrate(t, p);
                }
                case 4 -> {
                    String doc = InputUtils.readString("Enter document", "This lecture covers Artificial Intelligence, Data Structures, and Machine Learning models.");
                    List<String> kw = Arrays.asList("Artificial Intelligence", "Data Structures", "Machine Learning", "Algorithms");
                    AhoCorasick.demonstrate(doc, kw);
                }
                case 5 -> {
                    String query = InputUtils.readString("Enter student name with typo (e.g. Yamin Redy): ");
                    studentService.searchStudent(query);
                }
                case 0 -> back = true;
            }
            if (!back) InputUtils.pause();
        }
    }

    // =========================================================================
    // 3. DOCUMENT ANALYSIS
    // =========================================================================
    private static void handleDocumentAnalysisMenu() {
        boolean back = false;
        while (!back) {
            DisplayUtils.printSectionHeader("DOCUMENT ANALYSIS");
            System.out.println("1. Suffix Array Indexing");
            System.out.println("   -> Lexicographical suffix sorting with binary search\n");
            System.out.println("2. SA-IS Linear-Time Construction");
            System.out.println("   -> Pure O(n) S/L type induced sorting for large academic text\n");
            System.out.println("3. Kasai's LCP Array & Repetitions");
            System.out.println("   -> Longest common phrase and repetition extraction in O(n) time\n");
            System.out.println("4. Cross-Document Plagiarism Check");
            System.out.println("   -> Cross-document similarity analysis between assignments\n");
            System.out.println("5. Suffix Tree (Compressed Trie)");
            System.out.println("   -> Compact suffix tree queries with sentinel termination\n");
            System.out.println("6. Suffix Automaton (DAWG)");
            System.out.println("   -> Minimal state automaton and distinct substring counting\n");
            System.out.println("0. Back to Main Menu\n");

            int ch = InputUtils.readInt("Enter your choice: ", 0, 6);
            switch (ch) {
                case 1 -> {
                    String doc = InputUtils.readString("Enter document", "banana");
                    String q = InputUtils.readString("Enter search query", "ana");
                    SuffixArray.demonstrate(doc, q);
                }
                case 2 -> {
                    String doc = InputUtils.readString("Enter document", "mmiissiissiippii");
                    SAIS.demonstrate(doc);
                }
                case 3 -> {
                    String doc = InputUtils.readString("Enter document", "algorithms_and_structures_and_algorithms");
                    LCPKasai.demonstrate(doc);
                }
                case 4 -> {
                    String id1 = InputUtils.readString("Enter First Assignment ID (e.g. ASN01): ");
                    String id2 = InputUtils.readString("Enter Second Assignment ID (e.g. ASN02): ");
                    assignmentService.checkAssignmentSimilarity(id1, id2);
                }
                case 5 -> {
                    String doc = InputUtils.readString("Enter document", "banana");
                    String q = InputUtils.readString("Enter query", "nan");
                    SuffixTree.demonstrate(doc, q);
                }
                case 6 -> {
                    String doc = InputUtils.readString("Enter document", "university");
                    String q = InputUtils.readString("Enter test word", "ver");
                    SuffixAutomaton.demonstrate(doc, q);
                }
                case 0 -> back = true;
            }
            if (!back) InputUtils.pause();
        }
    }

    // =========================================================================
    // 4. ACADEMIC PLANNING & OPTIMIZATION
    // =========================================================================
    private static void handleAcademicPlanningMenu() {
        boolean back = false;
        while (!back) {
            DisplayUtils.printSectionHeader("ACADEMIC PLANNING & OPTIMIZATION");
            System.out.println("1. Levenshtein Distance Calculation");
            System.out.println("   -> Edit distance DP matrix for student query correction\n");
            System.out.println("2. Damerau-Levenshtein Distance");
            System.out.println("   -> Dynamic programming with adjacent transposition support\n");
            System.out.println("3. Matrix Chain Multiplication (Pipeline Optimization)");
            System.out.println("   -> Optimal parenthesization for analytics data pipelines\n");
            System.out.println("4. Bitmask DP Course Planner");
            System.out.println("   -> Optimal course selection under prerequisite and credit constraints\n");
            System.out.println("5. Optimal Binary Search Tree (OBST)");
            System.out.println("   -> Minimum expected search cost tree based on query frequencies\n");
            System.out.println("0. Back to Main Menu\n");

            int ch = InputUtils.readInt("Enter your choice: ", 0, 5);
            switch (ch) {
                case 1 -> {
                    String s1 = InputUtils.readString("Enter input query", "Machne Learing");
                    String s2 = InputUtils.readString("Enter target query", "Machine Learning");
                    LevenshteinDistance.demonstrate(s1, s2);
                }
                case 2 -> {
                    String s1 = InputUtils.readString("Enter input query", "Algoritm");
                    String s2 = InputUtils.readString("Enter target query", "Algorithm");
                    DamerauLevenshtein.demonstrate(s1, s2);
                }
                case 3 -> MatrixChainMultiplication.demonstrate();
                case 4 -> {
                    int credits = InputUtils.readInt("Enter maximum credit budget for semester (e.g. 12 or 16): ", 3, 24);
                    courseService.planOptimalCourses(credits);
                }
                case 5 -> OptimalBST.demonstrate();
                case 0 -> back = true;
            }
            if (!back) InputUtils.pause();
        }
    }

    // =========================================================================
    // 5. RESOURCE ALLOCATION
    // =========================================================================
    private static void handleResourceAllocationMenu() {
        boolean back = false;
        while (!back) {
            DisplayUtils.printSectionHeader("RESOURCE ALLOCATION");
            System.out.println("1. Faculty-to-Course Bipartite Matching");
            System.out.println("   -> Optimal teaching assignment using augmenting paths\n");
            System.out.println("2. Ford-Fulkerson Capacity Allocation");
            System.out.println("   -> Step-by-step augmenting path flow on residual classroom network\n");
            System.out.println("3. Edmonds-Karp Workstation Routing");
            System.out.println("   -> BFS shortest augmenting paths for lab equipment flow\n");
            System.out.println("4. Dinic's Multi-Tier Max Flow");
            System.out.println("   -> BFS level graph and DFS blocking flow for campus hierarchy\n");
            System.out.println("5. König's Theorem Verification");
            System.out.println("   -> Minimum faculty oversight committee proof (|M| = |C|)\n");
            System.out.println("0. Back to Main Menu\n");

            int ch = InputUtils.readInt("Enter your choice: ", 0, 5);
            switch (ch) {
                case 1 -> facultyService.allocateFacultyToCourses();
                case 2 -> FordFulkerson.demonstrate();
                case 3 -> EdmondsKarp.demonstrate();
                case 4 -> Dinic.demonstrate();
                case 5 -> KonigTheorem.demonstrate();
                case 0 -> back = true;
            }
            if (!back) InputUtils.pause();
        }
    }

    // =========================================================================
    // 6. CONSTRAINT ANALYSIS
    // =========================================================================
    private static void handleConstraintAnalysisMenu() {
        boolean back = false;
        while (!back) {
            DisplayUtils.printSectionHeader("CONSTRAINT ANALYSIS");
            System.out.println("1. Educational DPLL SAT Solver");
            System.out.println("   -> Propositional logic constraint satisfaction with unit propagation\n");
            System.out.println("2. 3-SAT Problem Verification");
            System.out.println("   -> Satisfiability check for 3-literal clause formulas\n");
            System.out.println("3. 3-SAT to CLIQUE Reduction");
            System.out.println("   -> Karp's polynomial reduction to examine timetable hardness\n");
            System.out.println("4. CLIQUE to INDEPENDENT SET Reduction");
            System.out.println("   -> Graph complement transformation for conflict-free groups\n");
            System.out.println("5. INDEPENDENT SET to VERTEX COVER Reduction");
            System.out.println("   -> Duality reduction for minimum exam proctor coverage\n");
            System.out.println("6. Vertex Cover 2-Approximation");
            System.out.println("   -> Maximal-matching-based 2-approximation for exam rescheduling\n");
            System.out.println("7. Exam Conflict Detection & Resolution");
            System.out.println("   -> Automated conflict analysis on live university exam schedule\n");
            System.out.println("0. Back to Main Menu\n");

            int ch = InputUtils.readInt("Enter your choice: ", 0, 7);
            switch (ch) {
                case 1 -> SATSolver.demonstrate();
                case 2 -> ThreeSAT.demonstrate();
                case 3 -> ThreeSATToClique.demonstrate();
                case 4 -> CliqueToIndependentSet.demonstrate();
                case 5 -> IndependentSetToVertexCover.demonstrate();
                case 6 -> VertexCoverApproximation.demonstrate();
                case 7 -> examinationService.detectAndResolveConflicts();
                case 0 -> back = true;
            }
            if (!back) InputUtils.pause();
        }
    }

    // =========================================================================
    // 7. ANALYTICS & PARALLEL PROCESSING
    // =========================================================================
    private static void handleAnalyticsParallelMenu() {
        boolean back = false;
        while (!back) {
            DisplayUtils.printSectionHeader("ANALYTICS & PARALLEL PROCESSING");
            System.out.println("1. Live Academic Analytics Dashboard");
            System.out.println("   -> Real-time university indicators and cohort metrics\n");
            System.out.println("2. Parallel Tree Reduction (ForkJoin)");
            System.out.println("   -> Multithreaded cohort GPA and exam marks aggregation\n");
            System.out.println("3. Blelloch Parallel Prefix Scan");
            System.out.println("   -> Work-efficient parallel cumulative study workload tracking\n");
            System.out.println("4. Reservoir Sampling (Algorithm R)");
            System.out.println("   -> Bounded-memory streaming audit of student portal telemetry\n");
            System.out.println("5. Miller-Rabin Primality Testing");
            System.out.println("   -> 20-round probabilistic test for cryptographic diploma IDs\n");
            System.out.println("6. Brent's Theorem Scheduling Bounds");
            System.out.println("   -> Theoretical speedup, span, and processor efficiency analysis\n");
            System.out.println("0. Back to Main Menu\n");

            int ch = InputUtils.readInt("Enter your choice: ", 0, 6);
            switch (ch) {
                case 1 -> analyticsService.displayDashboard();
                case 2 -> ParallelReduce.demonstrate();
                case 3 -> BlellochScan.demonstrate();
                case 4 -> ReservoirSampling.demonstrate();
                case 5 -> MillerRabin.demonstrate();
                case 6 -> {
                    double w = InputUtils.readDouble("Enter total work W (e.g. 10000): ", 1.0, 1000000.0);
                    double s = InputUtils.readDouble("Enter critical span S (e.g. 50): ", 1.0, w);
                    int p = InputUtils.readInt("Enter number of processors P (e.g. 8): ", 1, 256);
                    BrentTheorem.AnalysisResult res = BrentTheorem.analyze(w, s, p);
                    System.out.printf("Theoretical Time Bound T_%d <= %.2f%n", p, res.theoreticalTime);
                    System.out.printf("Theoretical Speedup S_%d   = %.2fx%n", p, res.speedup);
                    System.out.printf("Processor Efficiency E_%d  = %.2f%%%n", p, res.efficiency * 100.0);
                }
                case 0 -> back = true;
            }
            if (!back) InputUtils.pause();
        }
    }

    // =========================================================================
    // 8. ALGORITHM COMPARISON & FULL DEMO
    // =========================================================================
    private static void handleAlgorithmComparisonMenu() {
        boolean back = false;
        while (!back) {
            DisplayUtils.printSectionHeader("ALGORITHM COMPARISON");
            System.out.println("1. Run Full Demonstration of All 26+ DSA Algorithms");
            System.out.println("   -> Executes comprehensive verification suite with step-by-step trace\n");
            System.out.println("2. String Matching Comparison (KMP vs Z-Function vs Rabin-Karp)");
            System.out.println("   -> Benchmark pattern matching algorithms on identical text\n");
            System.out.println("3. Suffix Indexing Comparison (Suffix Array vs SA-IS vs Suffix Tree)");
            System.out.println("   -> Compare construction methods and memory footprints\n");
            System.out.println("4. Network Flow Comparison (Ford-Fulkerson vs Edmonds-Karp vs Dinic)");
            System.out.println("   -> Compare augmentation steps and saturation rates\n");
            System.out.println("0. Back to Main Menu\n");

            int ch = InputUtils.readInt("Enter your choice: ", 0, 4);
            switch (ch) {
                case 1 -> runFullAlgorithmDemo();
                case 2 -> {
                    System.out.println("\n--- STRING MATCHING COMPARISON ---");
                    String text = "DATA_STRUCTURES_AND_ALGORITHMS_IN_JAVA_UNIVERSITY_PLATFORM";
                    String pattern = "ALGORITHMS";
                    System.out.println("Text: \"" + text + "\" | Pattern: \"" + pattern + "\"");
                    System.out.println("\n1. KMP:");
                    KMP.demonstrate(text, pattern);
                    System.out.println("\n2. Z-Function:");
                    ZFunction.demonstrate(text, pattern);
                    System.out.println("\n3. Rabin-Karp:");
                    RabinKarp.demonstrate(text, pattern);
                }
                case 3 -> {
                    System.out.println("\n--- SUFFIX STRUCTURES COMPARISON ---");
                    String doc = "banana";
                    System.out.println("Document: \"" + doc + "\"");
                    System.out.println("\n1. Suffix Array (Lexicographical Sort):");
                    SuffixArray.demonstrate(doc, "ana");
                    System.out.println("\n2. SA-IS (Linear-Time S/L Induced Sort):");
                    SAIS.demonstrate(doc);
                    System.out.println("\n3. Suffix Tree (Compressed Trie):");
                    SuffixTree.demonstrate(doc, "nan");
                }
                case 4 -> {
                    System.out.println("\n--- NETWORK FLOW ALGORITHMS COMPARISON ---");
                    System.out.println("1. Ford-Fulkerson (DFS Augmentations):");
                    FordFulkerson.demonstrate();
                    System.out.println("\n2. Edmonds-Karp (BFS Shortest Paths):");
                    EdmondsKarp.demonstrate();
                    System.out.println("\n3. Dinic's Algorithm (Level Graph + DFS Blocking Flow):");
                    Dinic.demonstrate();
                }
                case 0 -> back = true;
            }
            if (!back) InputUtils.pause();
        }
    }

    // =========================================================================
    // FULL ALGORITHM DEMONSTRATION RUNNER
    // =========================================================================
    private static void runFullAlgorithmDemo() {
        DisplayUtils.printSectionHeader("EDUTRACK DSA ALGORITHM DEMO");

        System.out.println("\n==========================================");
        System.out.println("M1 – STRING ALGORITHMS");
        System.out.println("------------------------------------------");
        System.out.println("[1/4] Running KMP Pattern Matching...");
        KMP.demonstrate("Data Structures and Algorithms for Intelligent Systems", "Data");
        System.out.println("✓ KMP Completed");

        System.out.println("\n[2/4] Running Z-Function String Matching...");
        ZFunction.demonstrate("abracadabra", "abra");
        System.out.println("✓ Z-Function Completed");

        System.out.println("\n[3/4] Running Rabin-Karp Rolling Hash...");
        RabinKarp.demonstrate("Course Enrollment in DSA301 and AI201 for Fall 2026", "DSA301");
        System.out.println("✓ Rabin-Karp Completed");

        System.out.println("\n[4/4] Running Aho-Corasick Multi-Pattern Search...");
        AhoCorasick.demonstrate("Artificial Intelligence in Modern Data Structures and Machine Learning",
                Arrays.asList("Artificial Intelligence", "Data Structures", "Machine Learning"));
        System.out.println("✓ Aho-Corasick Completed");

        System.out.println("\n==========================================");
        System.out.println("M2 – SUFFIX STRUCTURES");
        System.out.println("------------------------------------------");
        System.out.println("[1/5] Running Suffix Array Construction...");
        SuffixArray.demonstrate("banana", "ana");
        System.out.println("✓ Suffix Array Completed");

        System.out.println("\n[2/5] Running SA-IS Linear-Time Suffix Array...");
        SAIS.demonstrate("mmiissiissiippii");
        System.out.println("✓ SA-IS Completed");

        System.out.println("\n[3/5] Running Kasai LCP Array & Repetitions...");
        LCPKasai.demonstrate("algorithms_and_structures_and_algorithms");
        System.out.println("✓ LCP/Kasai Completed");

        System.out.println("\n[4/5] Running Suffix Tree (Compressed Trie)...");
        SuffixTree.demonstrate("banana", "nan");
        System.out.println("✓ Suffix Tree Completed");

        System.out.println("\n[5/5] Running Suffix Automaton (DAWG)...");
        SuffixAutomaton.demonstrate("university", "ver");
        System.out.println("✓ Suffix Automaton Completed");

        System.out.println("\n==========================================");
        System.out.println("M3 – DYNAMIC PROGRAMMING");
        System.out.println("------------------------------------------");
        System.out.println("[1/5] Running Levenshtein Distance...");
        LevenshteinDistance.demonstrate("Machne Learing", "Machine Learning");
        System.out.println("✓ Levenshtein Completed");

        System.out.println("\n[2/5] Running Damerau-Levenshtein (Transpositions)...");
        DamerauLevenshtein.demonstrate("Algoritm", "Algorithm");
        System.out.println("✓ Damerau-Levenshtein Completed");

        System.out.println("\n[3/5] Running Matrix Chain Multiplication...");
        MatrixChainMultiplication.demonstrate();
        System.out.println("✓ Matrix Chain Completed");

        System.out.println("\n[4/5] Running Bitmask DP Course Planner...");
        BitmaskDP.demonstrate();
        System.out.println("✓ Bitmask DP Completed");

        System.out.println("\n[5/5] Running Optimal Binary Search Tree...");
        OptimalBST.demonstrate();
        System.out.println("✓ Optimal BST Completed");

        System.out.println("\n==========================================");
        System.out.println("M4 – NETWORK FLOW");
        System.out.println("------------------------------------------");
        System.out.println("[1/5] Running Maximum Bipartite Matching...");
        BipartiteMatching.demonstrate();
        System.out.println("✓ Bipartite Matching Completed");

        System.out.println("\n[2/5] Running Ford-Fulkerson Max Flow...");
        FordFulkerson.demonstrate();
        System.out.println("✓ Ford-Fulkerson Completed");

        System.out.println("\n[3/5] Running Edmonds-Karp Shortest Augmenting Path...");
        EdmondsKarp.demonstrate();
        System.out.println("✓ Edmonds-Karp Completed");

        System.out.println("\n[4/5] Running Dinic's Multi-Tier Max Flow...");
        Dinic.demonstrate();
        System.out.println("✓ Dinic Completed");

        System.out.println("\n[5/5] Running König's Theorem Verification...");
        KonigTheorem.demonstrate();
        System.out.println("✓ König's Theorem Completed");

        System.out.println("\n==========================================");
        System.out.println("M5 – NP-COMPLETENESS & APPROXIMATION");
        System.out.println("------------------------------------------");
        System.out.println("[1/6] Running DPLL SAT Solver...");
        SATSolver.demonstrate();
        System.out.println("✓ SAT Solver Completed");

        System.out.println("\n[2/6] Running 3-SAT Satisfiability...");
        ThreeSAT.demonstrate();
        System.out.println("✓ 3-SAT Completed");

        System.out.println("\n[3/6] Running 3-SAT ---> CLIQUE Reduction...");
        ThreeSATToClique.demonstrate();
        System.out.println("✓ 3-SAT → CLIQUE Completed");

        System.out.println("\n[4/6] Running CLIQUE ---> INDEPENDENT SET Reduction...");
        CliqueToIndependentSet.demonstrate();
        System.out.println("✓ CLIQUE → INDEPENDENT SET Completed");

        System.out.println("\n[5/6] Running INDEPENDENT SET ---> VERTEX COVER Reduction...");
        IndependentSetToVertexCover.demonstrate();
        System.out.println("✓ INDEPENDENT SET → VERTEX COVER Completed");

        System.out.println("\n[6/6] Running Vertex Cover 2-Approximation...");
        VertexCoverApproximation.demonstrate();
        System.out.println("✓ Vertex Cover 2-Approximation Completed");

        System.out.println("\n==========================================");
        System.out.println("M6 – RANDOMIZED & PARALLEL ALGORITHMS");
        System.out.println("------------------------------------------");
        System.out.println("[1/6] Running Randomized QuickSort...");
        RandomizedQuickSort.demonstrate();
        System.out.println("✓ Randomized QuickSort Completed");

        System.out.println("\n[2/6] Running Reservoir Sampling (Streaming Telemetry)...");
        ReservoirSampling.demonstrate();
        System.out.println("✓ Reservoir Sampling Completed");

        System.out.println("\n[3/6] Running Miller-Rabin Primality Test...");
        MillerRabin.demonstrate();
        System.out.println("✓ Miller-Rabin Completed");

        System.out.println("\n[4/6] Running Blelloch Work-Efficient Parallel Prefix Scan...");
        BlellochScan.demonstrate();
        System.out.println("✓ Blelloch Scan Completed");

        System.out.println("\n[5/6] Running Parallel Tree Reduction...");
        ParallelReduce.demonstrate();
        System.out.println("✓ Parallel Reduce Completed");

        System.out.println("\n[6/6] Running Brent's Theorem Scheduling...");
        BrentTheorem.demonstrate();
        System.out.println("✓ Brent's Theorem Completed");

        System.out.println("\n==========================================");
        System.out.println("ALL 26+ DSA ALGORITHMS EXECUTED SUCCESSFULLY!");
        System.out.println("==========================================");
        InputUtils.pause();
    }
}
