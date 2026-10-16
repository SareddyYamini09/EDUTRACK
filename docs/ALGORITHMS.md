# EduTrack: Detailed Algorithm Specifications & Viva Guide

This document presents the detailed architectural and theoretical specifications for all 26+ Data Structures and Algorithms implemented from scratch in **EduTrack**.

---

# Module M1: String Algorithms (CO1)

## 1. Knuth-Morris-Pratt (KMP)
- **CO Mapping**: CO1 / Module M1
- **Concept**: Precomputes a Longest Proper Prefix which is also Suffix ($\pi$ / LPS) array in linear time. When a mismatch occurs during searching, the algorithm uses LPS to slide the pattern without ever retreating the text pointer.
- **Input**: Text $T$ of length $n$, Pattern $P$ of length $m$.
- **Output**: List of zero-based starting indices where $P$ appears in $T$.
- **EduTrack Application**: Instant searching for student names, course titles, and departmental keywords.
- **Time Complexity**: Preprocessing $O(m)$, Search $O(n)$, Total $O(n + m)$.
- **Space Complexity**: $O(m)$ for LPS array.
- **Small Example**:
  - Text: `"Data Structures and Algorithms"`, Pattern: `"Data"`
  - LPS Array: `[0, 0, 0, 0]`
  - Match Position: Index `0`.

---

## 2. Z-Function Algorithm
- **CO Mapping**: CO1 / Module M1
- **Concept**: Computes an array $Z$ where $Z[i]$ is the length of the longest substring starting from $S[i]$ that is also a prefix of $S$. Maintains a bounding "Z-box" $[L, R]$ to achieve strict linear time.
- **Input**: String $S$ of length $n$ (formatted as $P + \text{"\$"} + T$).
- **Output**: $Z$-array of length $n$ and starting positions where $Z[i] = m$.
- **EduTrack Application**: Identifying repeated text passages and pattern matches across student assignment submissions.
- **Time Complexity**: $O(n)$ deterministic linear time.
- **Space Complexity**: $O(n)$ auxiliary array.
- **Small Example**:
  - String: `"abracadabra"`, Pattern: `"abra"`
  - $P\$T$: `"abra$abracadabra"`
  - $Z$-values: Match at index `0` and index `7`.

---

## 3. Rabin-Karp Rolling Hash
- **CO Mapping**: CO1 / Module M1
- **Concept**: Treats strings as numbers in base $d$ modulo a large prime $q$. Updates the hash value in $O(1)$ time by subtracting the leading character and adding the trailing character. Performs character checks on hash equality to eliminate collisions.
- **Input**: Text $T$, Pattern $P$, Radix $d = 256$, Modulus $q = 10^9 + 7$.
- **Output**: Matching starting positions.
- **EduTrack Application**: High-speed filtering of course codes (`"DSA301"`, `"AI201"`) and assignment IDs.
- **Time Complexity**: Average $O(n + m)$, Worst-case $O(n \cdot m)$.
- **Space Complexity**: $O(1)$ auxiliary space.
- **Small Example**:
  - Text: `"Course Enrollment in DSA301 and AI201"`, Pattern: `"DSA301"`
  - Modulus: $10^9+7 \implies$ Matched at index `21`.

---

## 4. Aho-Corasick Multi-Pattern Automaton
- **CO Mapping**: CO1 / Module M1
- **Concept**: Builds a Trie containing all dictionary keywords, augments it with BFS failure links pointing to the longest proper suffix that is also a prefix, and constructs output links to emit all matching words simultaneously.
- **Input**: Document text $T$, Dictionary of keywords $\{P_1, P_2, \dots, P_k\}$.
- **Output**: Pairs of $(\text{Keyword}, \text{Position})$ for every occurrence.
- **EduTrack Application**: Multi-keyword indexing of curriculum syllabi and student lecture notes in a single text pass.
- **Time Complexity**: $O(n + \sum m_i + z)$ where $z$ is the number of matches.
- **Space Complexity**: $O(\sum m_i \cdot \Sigma)$ for trie states and transitions.
- **Small Example**:
  - Keywords: `["AI", "Data Structures", "Algorithms"]`
  - Document: `"AI students learn Data Structures and Algorithms."`
  - Matches: `"AI"` at 0, `"Data Structures"` at 17, `"Algorithms"` at 37.

---

# Module M2: Suffix Structures (CO2)

## 5. Suffix Array
- **CO Mapping**: CO2 / Module M2
- **Concept**: An array of integer indices representing all suffixes of text $T$ arranged in lexicographical order. Enables binary search for substrings in $O(m \log n)$.
- **Input**: String $T$ of length $n$.
- **Output**: Array `SA` of size $n$.
- **EduTrack Application**: Digital document indexing and repository search.
- **Time Complexity**: Construction $O(n^2 \log n)$ / $O(n \log^2 n)$, Query $O(m \log n)$.
- **Space Complexity**: $O(n)$.
- **Small Example**:
  - Text: `"banana"`
  - Suffixes: `0: banana`, `1: anana`, `2: nana`, `3: ana`, `4: na`, `5: a`
  - Sorted SA: `[5, 3, 1, 0, 4, 2]` corresponding to `["a", "ana", "anana", "banana", "na", "nana"]`.

---

## 6. SA-IS (Suffix Array Induced Sorting)
- **CO Mapping**: CO2 / Module M2
- **Concept**: Authentic linear-time suffix array construction (Nong, Zhang, Chan 2009). Classifies suffixes into S-type ($s[i] < s[i+1]$) and L-type ($s[i] > s[i+1]$). LMS characters are induced-sorted into bucket ends, LMS substrings are named, and recursive reduction is performed if names are not unique.
- **Input**: Integer/character array $S$ ending with sentinel 0.
- **Output**: Exact Suffix Array in $O(n)$ time.
- **EduTrack Application**: Large-scale textbook corpus indexing and syllabus compression.
- **Time Complexity**: $O(n)$ guaranteed linear time.
- **Space Complexity**: $O(n)$ auxiliary space.
- **Small Example**:
  - Text: `"mmiissiissiippii$"`
  - S/L Types identified $\to$ LMS positions extracted $\to$ Suffix array constructed in $O(n)$.

---

## 7. Kasai's LCP Algorithm
- **CO Mapping**: CO2 / Module M2
- **Concept**: Computes the Longest Common Prefix array in $O(n)$ by traversing suffixes in text order rather than sorted order, leveraging the invariant that $\text{LCP}[\text{rank}[i]] \ge \text{LCP}[\text{rank}[i-1]] - 1$.
- **Input**: Text $T$ and its Suffix Array `SA`.
- **Output**: Array `LCP` of length $n$ where $\text{LCP}[i] = \text{lcp}(SA[i], SA[i-1])$.
- **EduTrack Application**: Detecting plagiarized phrases between assignment submissions.
- **Time Complexity**: $O(n)$ linear time.
- **Space Complexity**: $O(n)$ for `rank` and `LCP` arrays.
- **Small Example**:
  - Suffixes `"ana"` and `"anana"` have common prefix `"ana"`, so $\text{LCP} = 3$.

---

## 8. Suffix Tree
- **CO Mapping**: CO2 / Module M2
- **Concept**: A compressed Trie containing all suffixes of string $T\$$. Edge labels represent substrings defined by start and end indices. Internal nodes represent branching points.
- **Input**: Text $T$ with unique sentinel `$`.
- **Output**: Tree structure supporting substring search in $O(m)$ time.
- **EduTrack Application**: Fast catalog search and prefix/suffix queries across learning materials.
- **Time Complexity**: Construction $O(n^2)$ educational, Query $O(m)$.
- **Space Complexity**: $O(n)$ nodes.

---

## 9. Suffix Automaton (DAWG)
- **CO Mapping**: CO2 / Module M2
- **Concept**: Directed Acyclic Word Graph representing all substrings of $T$ with minimal states. Each state corresponds to an equivalence class of substrings having identical right-extension endpos sets.
- **Input**: Text string $T$.
- **Output**: Automaton states and transition table.
- **EduTrack Application**: Instant $O(m)$ substring existence checks and counting distinct lecture subtopics.
- **Time Complexity**: $O(n)$ construction time, $O(m)$ query.
- **Space Complexity**: At most $2n - 1$ states and $3n - 4$ transitions ($O(n)$ space).

---

# Module M3: Advanced Dynamic Programming (CO3)

## 10. Levenshtein Distance
- **CO Mapping**: CO3 / Module M3
- **Concept**: Wagner-Fischer dynamic programming algorithm computing the minimum operations (Insert, Delete, Substitute) to transform string $S_1$ into $S_2$.
- **Input**: Strings $S_1$ (length $n$), $S_2$ (length $m$).
- **Output**: Minimum integer edit distance and 2D DP matrix.
- **EduTrack Application**: Search query auto-correction (e.g. `"Machne Learing"` $\to$ `"Machine Learning"`).
- **Time Complexity**: $O(n \cdot m)$.
- **Space Complexity**: $O(n \cdot m)$.

---

## 11. Damerau-Levenshtein Distance
- **CO Mapping**: CO3 / Module M3
- **Concept**: Extends Levenshtein distance by allowing adjacent character transpositions (swaps) as an elementary operation at unit cost.
- **Input**: Strings $S_1$ and $S_2$.
- **Output**: Minimum distance accounting for transpositions.
- **EduTrack Application**: Correcting common typing slips in student queries (`"Algoritm"` $\to$ `"Algorithm"`).
- **Time Complexity**: $O(n \cdot m)$.
- **Space Complexity**: $O(n \cdot m)$.

---

## 12. Matrix Chain Multiplication
- **CO Mapping**: CO3 / Module M3
- **Concept**: Dynamic programming algorithm finding the optimal associative parenthesization that minimizes the total scalar multiplications needed to compute a product of matrices.
- **Input**: Dimension array $p$ of size $n+1$ for $n$ pipeline stages.
- **Output**: Minimum multiplication cost and parenthesization string.
- **EduTrack Application**: Optimizing linear algebraic transformations in academic data analytics pipelines ($Student \times Course \times Assignment \times Analytics$).
- **Time Complexity**: $O(n^3)$.
- **Space Complexity**: $O(n^2)$ for cost table $m$ and split table $s$.

---

## 13. Bitmask DP for Feasible Course Selection
- **CO Mapping**: CO3 / Module M3
- **Concept**: Explores subsets of courses represented as integer bitmasks ($0 \le \text{mask} < 2^N$). A state is feasible only if all prerequisite bits are present in the mask. Computes the maximum total academic value achievable under a credit cap.
- **Input**: $N$ courses with credit values, prerequisite bitmasks, and maximum credits $C$.
- **Output**: Optimal feasible bitmask, selected course list, and total score.
- **EduTrack Application**: Student degree planning and semester course enrollment optimization.
- **Time Complexity**: $O(2^N \cdot N)$.
- **Space Complexity**: $O(2^N)$.

---

## 14. Optimal Binary Search Tree (OBST)
- **CO Mapping**: CO3 / Module M3
- **Concept**: Given keys with known access probabilities, constructs a static binary search tree that minimizes expected search time: $\sum p_i \cdot (\text{depth}(k_i) + 1)$.
- **Input**: Sorted keys $K$ and search probabilities $P$.
- **Output**: Expected search cost matrix $e[i][j]$ and root selection matrix.
- **EduTrack Application**: Fast index tree organization for high-frequency course codes.
- **Time Complexity**: $O(n^3)$.
- **Space Complexity**: $O(n^2)$.

---

# Module M4: Network Flow (CO4)

## 15. Maximum Bipartite Matching
- **CO Mapping**: CO4 / Module M4
- **Concept**: Augmenting path algorithm on bipartite graph $G = (U, V, E)$ where $U$ represents faculty and $V$ represents courses. Finds the maximum number of disjoint edges.
- **Input**: Faculty list, Course list, Qualification edges.
- **Output**: Maximum matching mappings.
- **EduTrack Application**: Automated faculty teaching assignments.
- **Time Complexity**: $O(V \cdot E)$.
- **Space Complexity**: $O(V)$.

---

## 16. Ford-Fulkerson Method
- **CO Mapping**: CO4 / Module M4
- **Concept**: Repeatedly finds augmenting paths from source to sink in the residual network using DFS, augmenting flow by the bottleneck capacity until no path exists.
- **Input**: Flow network with capacities, source $s$, sink $t$.
- **Output**: Max flow value and step-by-step augmenting paths.
- **EduTrack Application**: Campus classroom and lecture hall capacity allocation.
- **Time Complexity**: $O(E \cdot \text{max\_flow})$.
- **Space Complexity**: $O(V^2)$ for capacity/residual matrix.

---

## 17. Edmonds-Karp Algorithm
- **CO Mapping**: CO4 / Module M4
- **Concept**: Implementation of Ford-Fulkerson using Breadth-First Search (BFS) to guarantee finding the shortest augmenting path in terms of edge count.
- **Input**: Flow network capacity matrix, source $s$, sink $t$.
- **Output**: Max flow and list of BFS augmenting paths.
- **EduTrack Application**: Workstation distribution in student computer labs.
- **Time Complexity**: $O(V \cdot E^2)$ independent of edge capacities.
- **Space Complexity**: $O(V^2)$.

---

## 18. Dinic's Algorithm
- **CO Mapping**: CO4 / Module M4
- **Concept**: Two-phase algorithm: BFS constructs a level graph; DFS finds a blocking flow along shortest paths using pointer advancement to prune dead ends.
- **Input**: Multi-tier flow network.
- **Output**: Total saturated network flow.
- **EduTrack Application**: Multi-tier scheduling across Departments $\to$ Buildings $\to$ Classrooms $\to$ Student Cohorts.
- **Time Complexity**: $O(V^2 \cdot E)$ general, $O(E \sqrt{V})$ unit networks.
- **Space Complexity**: $O(V + E)$.

---

## 19. König's Theorem
- **CO Mapping**: CO4 / Module M4
- **Concept**: Proves that in any bipartite graph, the size of the maximum matching equals the size of the minimum vertex cover ($|M| = |C|$). Constructs the cover using alternating BFS paths from unmatched vertices in $L$.
- **Input**: Bipartite graph $G = (L, R, E)$.
- **Output**: Maximum Matching $M$, Minimum Vertex Cover $C$, and size equivalence proof.
- **EduTrack Application**: Determining the minimal faculty oversight committee required to supervise all university courses.
- **Time Complexity**: $O(V \cdot E)$.
- **Space Complexity**: $O(V + E)$.

---

# Module M5: NP-Completeness & Approximation (CO5)

## 20. DPLL Educational SAT Solver
- **CO Mapping**: CO5 / Module M5
- **Concept**: Davis-Putnam-Logemann-Loveland (DPLL) backtracking algorithm with unit clause propagation and pure literal elimination for Conjunctive Normal Form (CNF) formulas.
- **Input**: Propositional formula in CNF.
- **Output**: Satisfying truth assignment or UNSAT certificate.
- **EduTrack Application**: Timetable conflict constraint satisfaction and resource booking validation.
- **Time Complexity**: $O(2^V)$ worst-case.
- **Space Complexity**: $O(V + C)$.

---

## 21. 3-SAT Solver
- **CO Mapping**: CO5 / Module M5
- **Concept**: Solves formulas where each clause contains exactly three literals. Fundamental NP-complete problem established by Cook-Levin (1971).
- **Input**: 3-CNF formula.
- **Output**: Truth assignment satisfying all 3-literal clauses.
- **EduTrack Application**: Three-way resource conflict resolution (Room, Timeslot, Invigilator).
- **Time Complexity**: $O(2^n)$ worst-case.
- **Space Complexity**: $O(n + m)$.

---

## 22. 3-SAT to CLIQUE Reduction
- **CO Mapping**: CO5 / Module M5
- **Concept**: Karp (1972) polynomial-time reduction. For a 3-SAT formula with $k$ clauses, creates $3k$ vertices (one for each literal occurrence). Connects two vertices if they belong to different clauses and are non-contradictory. The formula is SAT iff the graph contains a $k$-clique.
- **Input**: 3-CNF formula with $k$ clauses.
- **Output**: Graph $G = (V, E)$ with target clique size $k$.
- **EduTrack Application**: Demonstrating the computational hardness of timetable scheduling.
- **Time Complexity**: $O(k^2)$ reduction time.
- **Space Complexity**: $O(k^2)$ graph representation.

---

## 23. CLIQUE to INDEPENDENT SET Reduction
- **CO Mapping**: CO5 / Module M5
- **Concept**: Graph complement transformation. A subset $S \subseteq V$ is a clique in $G$ if and only if $S$ is an independent set in the complement graph $\overline{G}$.
- **Input**: Graph $G = (V, E)$ and integer $k$.
- **Output**: Complement graph $\overline{G}$.
- **EduTrack Application**: Converting student study groups (cliques) into conflict-free exam timeslots (independent sets).
- **Time Complexity**: $O(V^2)$.
- **Space Complexity**: $O(V^2)$.

---

## 24. INDEPENDENT SET to VERTEX COVER Reduction
- **CO Mapping**: CO5 / Module M5
- **Concept**: Complement set duality: A set $S$ is an independent set in $G = (V, E)$ if and only if its complement $C = V \setminus S$ is a vertex cover of $G$.
- **Input**: Graph vertices $V$, edges $E$, and independent set $S$.
- **Output**: Verified vertex cover $C = V \setminus S$ of size $|V| - |S|$.
- **EduTrack Application**: Deriving minimal examination proctors from student-conflict-free schedules.
- **Time Complexity**: $O(V + E)$.
- **Space Complexity**: $O(V)$.

---

## 25. Vertex Cover 2-Approximation
- **CO Mapping**: CO5 / Module M5
- **Concept**: Gavril-Yannakakis maximal-matching-based 2-approximation algorithm. Greedily selects an uncovered edge, adds both endpoints to the cover, and deletes all incident edges. Guarantees $|C_{approx}| \le 2 \cdot |C_{optimal}|$.
- **Input**: Conflict graph $G = (V, E)$.
- **Output**: Approximate vertex cover $C$ and maximal matching $M$.
- **EduTrack Application**: Fast polynomial-time exam conflict rescheduling.
- **Time Complexity**: $O(V + E)$ linear time.
- **Space Complexity**: $O(V + E)$.

---

# Module M6: Randomized & Parallel Algorithms (CO6)

## 26. Randomized QuickSort
- **CO Mapping**: CO6 / Module M6
- **Concept**: Quicksort with randomized pivot selection. Eliminates susceptibility to adversarial inputs, guaranteeing expected $O(n \log n)$ comparisons regardless of input order.
- **Input**: Array/list of student academic records.
- **Output**: In-place sorted array.
- **EduTrack Application**: Academic merit honor roll generation and ranking GPAs.
- **Time Complexity**: Expected $O(n \log n)$, Worst-case $O(n^2)$ (negligible probability).
- **Space Complexity**: Expected $O(\log n)$ recursion stack.

---

## 27. Reservoir Sampling (Algorithm R)
- **CO Mapping**: CO6 / Module M6
- **Concept**: Selects $k$ random items from an unbounded continuous stream. For the $i$-th arriving element ($i > k$), includes it with probability $k/i$, replacing a randomly chosen reservoir item.
- **Input**: Data stream of unknown size $N$, reservoir size $k$.
- **Output**: Uniform random sample of $k$ items.
- **EduTrack Application**: Auditing live high-frequency student portal telemetry (logins, submissions, quiz clicks).
- **Time Complexity**: $O(N)$ single-pass stream processing.
- **Space Complexity**: Strictly $O(k)$ bounded space.

---

## 28. Miller-Rabin Primality Test
- **CO Mapping**: CO6 / Module M6
- **Concept**: Probabilistic primality test based on Fermat's Little Theorem and non-trivial square roots of 1 modulo $n$. Decomposes $n - 1 = 2^s \cdot d$ and evaluates witness sequences using safe modular exponentiation.
- **Input**: Candidate integer $n$ and witness rounds $k$.
- **Output**: Boolean verdict (`PROBABLY PRIME` or `COMPOSITE`).
- **EduTrack Application**: Cryptographic validation of student diploma serial numbers and tamper-evident identifiers.
- **Time Complexity**: $O(k \log^3 n)$.
- **Space Complexity**: $O(1)$.

---

## 29. Blelloch Work-Efficient Parallel Prefix Scan
- **CO Mapping**: CO6 / Module M6
- **Concept**: Two-phase parallel algorithm on a binary tree: Upsweep (reduce) phase computes partial sums up to the root; Downsweep phase sets the root to zero and passes intermediate sums down to leaves.
- **Input**: Array padded to power of 2.
- **Output**: Exclusive and inclusive prefix sums.
- **EduTrack Application**: Parallel cumulative tracking of student study hours, attendance, and semester credit completion.
- **Time Complexity**: Work $W = O(n)$, Span $S = O(\log n)$.
- **Space Complexity**: $O(n)$.

---

## 30. Parallel Tree Reduce
- **CO Mapping**: CO6 / Module M6
- **Concept**: Divide-and-conquer parallel tree reduction implemented via Java's ForkJoin framework. Splits large arrays into subtasks, computing partial sums, minima, and maxima concurrently.
- **Input**: Array of student assessment marks.
- **Output**: Aggregate statistics (Count, Sum, Mean, Min, Max).
- **EduTrack Application**: Instant cohort analytics computation across large university enrollment datasets.
- **Time Complexity**: Work $W = O(n)$, Span $S = O(\log n)$.
- **Space Complexity**: $O(\log n)$ call stack depth.

---

## 31. Brent's Scheduling Theorem
- **CO Mapping**: CO6 / Module M6
- **Concept**: Theoretical bound on parallel execution time: $T_P \le \frac{W - S}{P} + S$. Quantifies the achievable speedup $S_P = W / T_P$ and processor efficiency $E_P = S_P / P$.
- **Input**: Total Work $W$, Span $S$, Processors $P$.
- **Output**: Theoretical time bound $T_P$, speedup, and efficiency percentage.
- **EduTrack Application**: Server capacity planning and speedup estimation for campus batch computation pipelines.
- **Time Complexity**: $O(1)$.
- **Space Complexity**: $O(1)$.
