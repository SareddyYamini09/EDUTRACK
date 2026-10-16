# EduTrack – Intelligent University Academic Platform

EduTrack is an intelligent university academic platform built in pure standard Java that unifies academic administration with authentic, production-grade Data Structures and Algorithms across Modules M1 to M6 (CO1–CO6).

---

## Key Features

1. **Student Management**:
   - Registered student profiles with GPA, department, semester, and course enrollment.
   - Exact and substring search using **KMP (Knuth-Morris-Pratt)**.
   - Typo-tolerant search query suggestion using **Levenshtein Distance**.
   - Academic merit honor roll ranking using in-place **Randomized QuickSort**.

2. **Faculty Management**:
   - Faculty member directory with departmental specializations.
   - Automated teaching assignments via **Maximum Bipartite Matching**.
   - Formal verification of minimum oversight committees using **König's Theorem** ($|Matching| = |Vertex Cover|$).

3. **Course Management**:
   - Academic course catalog with prerequisite dependency trees.
   - Fast course title and code search via **KMP** and **Rabin-Karp Rolling Hash**.
   - Optimal semester course selection under prerequisite and credit constraints using **Bitmask Dynamic Programming**.

4. **Assignment Submissions & Academic Integrity**:
   - Student submission tracking and scoring.
   - Multi-topic keyword extraction using the **Aho-Corasick Multi-Pattern Automaton**.
   - Localized plagiarism and document similarity detection using **Suffix Arrays** and **Kasai's LCP Algorithm**.

5. **Examination Management & Conflict Resolution**:
   - Examination timetable scheduling across campus halls.
   - Conflict graph construction identifying overlapping examinations and student clashes.
   - Minimal exam rescheduling set derivation via **Vertex Cover 2-Approximation**.
   - Timetable slot satisfiability validation via educational **DPLL SAT Solver**.

6. **Campus Resource & Capacity Allocation**:
   - Campus classrooms, computing labs, and seminar rooms.
   - Multi-tier seat and slot allocation across departments and buildings using **Dinic's Algorithm** and **Ford-Fulkerson Max Flow**.

7. **Academic Analytics Dashboard**:
   - Cohort average GPA and exam mark aggregation using **Parallel Tree Reduction (ForkJoin)**.
   - Cumulative student study hours and activity trend analysis via **Blelloch Work-Efficient Parallel Prefix Scan**.
   - Live stream telemetry auditing using **Reservoir Sampling (Algorithm R)**.
   - Processor scheduling speedup bounds and capacity planning using **Brent's Theorem**.

8. **Automated Algorithm Demonstration Mode**:
   - Dedicated runner executing all 26+ DSA algorithms sequentially with diagnostic inputs, steps, and outputs.

---

## Technologies Used

- **Programming Language**: Java (Java 17 or newer, tested on Java 25)
- **Standard Library**: Java Standard Edition (SE) exclusively
- **Dependencies**: None (Zero external DSA libraries, zero paid APIs)
- **Data Storage**: Comma-Separated Values (CSV) with automatic fallback to built-in datasets
- **User Interface**: Interactive Console Menu System with resilient input validation

---

## Project Structure

```
EduTrack/
├── src/
│   ├── Main.java                               # Interactive console application entry point
│   ├── model/                                  # Domain models
│   │   ├── Student.java
│   │   ├── Faculty.java
│   │   ├── Course.java
│   │   ├── Assignment.java
│   │   ├── Examination.java
│   │   ├── Resource.java
│   │   └── AcademicActivity.java
│   ├── data/                                   # Data persistence and mock generation
│   │   ├── DataManager.java
│   │   └── SampleData.java
│   ├── utils/                                  # System utilities
│   │   ├── InputUtils.java
│   │   ├── DisplayUtils.java
│   │   └── FileUtils.java
│   ├── services/                               # Business logic & algorithm integration
│   │   ├── StudentService.java
│   │   ├── FacultyService.java
│   │   ├── CourseService.java
│   │   ├── AssignmentService.java
│   │   ├── ExaminationService.java
│   │   ├── ResourceService.java
│   │   └── AnalyticsService.java
│   ├── m1_string/                              # M1 / CO1: String Algorithms
│   │   ├── KMP.java
│   │   ├── ZFunction.java
│   │   ├── RabinKarp.java
│   │   └── AhoCorasick.java
│   ├── m2_suffix/                              # M2 / CO2: Suffix Structures
│   │   ├── SuffixArray.java
│   │   ├── SAIS.java
│   │   ├── LCPKasai.java
│   │   ├── SuffixTree.java
│   │   └── SuffixAutomaton.java
│   ├── m3_dp/                                  # M3 / CO3: Dynamic Programming
│   │   ├── LevenshteinDistance.java
│   │   ├── DamerauLevenshtein.java
│   │   ├── MatrixChainMultiplication.java
│   │   ├── BitmaskDP.java
│   │   └── OptimalBST.java
│   ├── m4_flow/                                # M4 / CO4: Network Flow
│   │   ├── BipartiteMatching.java
│   │   ├── FordFulkerson.java
│   │   ├── EdmondsKarp.java
│   │   ├── Dinic.java
│   │   └── KonigTheorem.java
│   ├── m5_np/                                  # M5 / CO5: NP-Completeness & Approximation
│   │   ├── SATSolver.java
│   │   ├── ThreeSAT.java
│   │   ├── ThreeSATToClique.java
│   │   ├── CliqueToIndependentSet.java
│   │   ├── IndependentSetToVertexCover.java
│   │   └── VertexCoverApproximation.java
│   └── m6_random_parallel/                     # M6 / CO6: Randomized & Parallel
│       ├── RandomizedQuickSort.java
│       ├── ReservoirSampling.java
│       ├── MillerRabin.java
│       ├── BlellochScan.java
│       ├── ParallelReduce.java
│       └── BrentTheorem.java
├── data/                                       # Persistent CSV datasets
│   ├── students.csv
│   ├── faculty.csv
│   ├── courses.csv
│   ├── assignments.csv
│   ├── examinations.csv
│   ├── resources.csv
│   └── activities.csv
├── docs/                                       # Comprehensive documentation
│   ├── README.md
│   ├── PROJECT_REPORT.md
│   ├── ALGORITHMS.md
│   ├── TEST_CASES.md
│   └── COMPLEXITY_ANALYSIS.md
└── README.md
```

---

## M1–M6 Algorithm Mapping

| Module / CO | Algorithms Implemented | EduTrack Real-World Integration |
| :--- | :--- | :--- |
| **M1: String Algorithms** | KMP, Z-Function, Rabin-Karp, Aho-Corasick | Student & course catalog lookup, multi-topic syllabus keyword search |
| **M2: Suffix Structures** | Suffix Array, SA-IS ($O(n)$), Kasai LCP, Suffix Tree, Suffix Automaton | Localized plagiarism analysis, syllabus deduplication, distinct term counting |
| **M3: Dynamic Programming** | Levenshtein, Damerau-Levenshtein, Matrix Chain, Bitmask DP, Optimal BST | Typo auto-correction, analytics pipeline planning, prerequisite course selection |
| **M4: Network Flow** | Bipartite Matching, Ford-Fulkerson, Edmonds-Karp, Dinic, König's Theorem | Faculty course assignments, multi-tier classroom and workstation flow routing |
| **M5: NP-Completeness** | DPLL SAT, 3-SAT, 3-SAT $\to$ CLIQUE, CLIQUE $\to$ IS, IS $\to$ VC, VC 2-Approx | Exam conflict minimization, invigilator coverage, timetable satisfiability |
| **M6: Randomized & Parallel** | Randomized QuickSort, Reservoir Sampling, Miller-Rabin, Blelloch Scan, Parallel Reduce, Brent's Theorem | GPA merit rankings, live telemetry stream audits, cryptographic IDs, cohort analytics |

---

## How to Compile

### Windows (PowerShell)
```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
```

### Windows (Command Prompt / CMD)
```cmd
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
del sources.txt
```

### Linux / macOS (Bash)
```bash
javac -d out $(find src -name "*.java")
```

---

## How to Run

### Option A: Windows Command Prompt (CMD) - Recommended
```cmd
cd /d E:\DSA3\EduTrack
chcp 65001
java -Dfile.encoding=UTF-8 -cp out Main
```
*(Or simply double-click **`run.bat`** in the project folder!)*

### Option B: Windows PowerShell
```powershell
cd E:\DSA3\EduTrack
java "-Dfile.encoding=UTF-8" -cp out Main
```

### Option C: Linux / macOS (Bash)
```bash
java -cp out Main
```

---

## Sample Screens

### Main Menu
```text
╔════════════════════════════════════════════════════════════════════╗
║                            EDUTRACK                                ║
║           Intelligent University Academic Platform                 ║
╠════════════════════════════════════════════════════════════════════╣
║  1. Academic Records                                               ║
║  2. Academic Search                                                ║
║  3. Document Analysis                                              ║
║  4. Academic Planning & Optimization                               ║
║  5. Resource Allocation                                            ║
║  6. Constraint Analysis                                            ║
║  7. Analytics & Parallel Processing                                ║
║  8. Algorithm Comparison                                           ║
║  0. Exit                                                           ║
╚════════════════════════════════════════════════════════════════════╝
```

### Academic Analytics Dashboard
```
===========================================================================
  Total Students Registered:   25         Total Faculty Members:     10
  Total Courses Offered:       12         Total Scheduled Exams:     10
  Total Assignment Records:    20         Campus Resources Count:   12
  Total Academic Activities:   40        
---------------------------------------------------------------------------
  Average University GPA:      3.63       Highest Recorded GPA:       3.98
  Minimum Recorded GPA:        3.15       GPA Standard Deviation:     [Computed]
  Average Assignment Score:    90.75      Highest Assignment Mark:    99.00
  Most Active Student:         Yamini Reddy (STU102) (6 logged activities)
  Most Popular Course:         JAVA201 (8 students enrolled)
===========================================================================
```

---

## Complexity Summary

For detailed Big-O analysis across all algorithms, see [`docs/COMPLEXITY_ANALYSIS.md`](docs/COMPLEXITY_ANALYSIS.md).

- **Linear-Time String Processing**: KMP, Z-Function, SA-IS, Kasai LCP, and Suffix Automaton operate in $O(n)$ time.
- **Dynamic Programming**: Levenshtein ($O(nm)$), Matrix Chain ($O(n^3)$), Bitmask DP ($O(2^N \cdot N)$ for $N \le 12$ runs in $< 5$ ms), Optimal BST ($O(n^3)$).
- **Network Flow**: Dinic ($O(V^2 E)$) and Bipartite Matching ($O(VE)$).
- **Parallel Algorithms**: Blelloch Scan and Parallel Reduce achieve Work $O(n)$ with Critical Span $O(\log n)$.

---

## Documentation Index

- **[Project Report (24 Sections)](docs/PROJECT_REPORT.md)**: Formal academic thesis-level documentation.
- **[Algorithm Guide (All 26 Algorithms)](docs/ALGORITHMS.md)**: Input/output, pseudocode, and viva walkthroughs.
- **[Complexity Analysis Matrix](docs/COMPLEXITY_ANALYSIS.md)**: Master Big-O tables and formal runtime proofs.
- **[Test Cases Suite (35 Cases)](docs/TEST_CASES.md)**: Comprehensive scenario definitions and validation status.

---

## Future Enhancements

- **Web Dashboard**: Adding a Spring-free embedded micro-server with a lightweight frontend.
- **Distributed Computing**: Extending Parallel Reduce to multi-node clusters using Java RMI / Socket streams.
- **Integer Linear Programming**: Combining SAT solvers with Branch-and-Bound algorithms for multi-term graduation path optimization.
