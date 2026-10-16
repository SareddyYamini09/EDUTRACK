# EduTrack: Comprehensive Time & Space Complexity Analysis

This document provides a formal asymptotic complexity analysis for all Data Structures and Algorithms implemented within the **EduTrack** Intelligent University Academic Platform, categorized across Modules M1 through M6 (Course Outcomes CO1 through CO6).

---

## 1. Master Algorithm Complexity Matrix

| Module | Algorithm | Time Complexity (Best) | Time Complexity (Average) | Time Complexity (Worst) | Space Complexity | Primary EduTrack Application |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **M1** | **KMP (Knuth-Morris-Pratt)** | $\Omega(m)$ | $\Theta(n + m)$ | $O(n + m)$ | $O(m)$ | Course title & student search |
| **M1** | **Z-Function Algorithm** | $\Omega(n)$ | $\Theta(n)$ | $O(n)$ | $O(n)$ | Assignment repeated phrase search |
| **M1** | **Rabin-Karp Rolling Hash** | $\Omega(n + m)$ | $\Theta(n + m)$ | $O(n \cdot m)$ | $O(1)$ | Course code & assignment ID lookup |
| **M1** | **Aho-Corasick Automaton** | $\Omega(n + \sum m_i)$ | $\Theta(n + \sum m_i + z)$ | $O(n + \sum m_i + z)$ | $O(\sum m_i \cdot \Sigma)$ | Multi-keyword curriculum indexing |
| **M2** | **Suffix Array (Sorted)** | $\Omega(n \log n)$ | $\Theta(n^2 \log n)$ | $O(n^2 \log n)$ | $O(n)$ | Digital note repository indexing |
| **M2** | **SA-IS Linear Construction** | $\Omega(n)$ | $\Theta(n)$ | $O(n)$ | $O(n)$ | Large textbook & transcript indexing |
| **M2** | **LCP Array (Kasai)** | $\Omega(n)$ | $\Theta(n)$ | $O(n)$ | $O(n)$ | Plagiarism & phrase repetition detection |
| **M2** | **Suffix Tree** | $\Omega(m)$ query | $\Theta(m)$ query | $O(m)$ query | $O(n)$ nodes | Instant catalog substring search |
| **M2** | **Suffix Automaton (DAWG)** | $\Omega(n)$ build, $O(m)$ | $\Theta(n)$ build, $O(m)$ | $O(n)$ build, $O(m)$ | $O(n)$ states | Distinct terms counting & search |
| **M3** | **Levenshtein Distance** | $\Omega(n \cdot m)$ | $\Theta(n \cdot m)$ | $O(n \cdot m)$ | $O(n \cdot m)$ | Search typo auto-correction |
| **M3** | **Damerau-Levenshtein** | $\Omega(n \cdot m)$ | $\Theta(n \cdot m)$ | $O(n \cdot m)$ | $O(n \cdot m)$ | Typographical transposition handling |
| **M3** | **Matrix Chain Multipl.** | $\Omega(n^3)$ | $\Theta(n^3)$ | $O(n^3)$ | $O(n^2)$ | Academic analytics pipeline planning |
| **M3** | **Bitmask DP Course Select** | $\Omega(2^N \cdot N)$ | $\Theta(2^N \cdot N)$ | $O(2^N \cdot N)$ | $O(2^N)$ | Prerequisite & credit optimization |
| **M3** | **Optimal BST (OBST)** | $\Omega(n^3)$ | $\Theta(n^3)$ | $O(n^3)$ | $O(n^2)$ | Key course access frequency tree |
| **M4** | **Bipartite Matching** | $\Omega(V + E)$ | $\Theta(V \cdot E)$ | $O(V \cdot E)$ | $O(V)$ | Faculty-to-course allocation |
| **M4** | **Ford-Fulkerson (DFS)** | $\Omega(E)$ | $\Theta(E \cdot |f|)$ | $O(E \cdot \text{max\_flow})$ | Classroom seat capacity allocation |
| **M4** | **Edmonds-Karp (BFS)** | $\Omega(V \cdot E)$ | $\Theta(V \cdot E^2)$ | $O(V \cdot E^2)$ | $O(V^2)$ | Workstation lab equipment routing |
| **M4** | **Dinic's Algorithm** | $\Omega(V \cdot E)$ | $\Theta(V^2 \cdot E)$ | $O(V^2 \cdot E)$ | $O(V + E)$ | Multi-tier campus resource scheduling |
| **M4** | **König's Theorem** | $\Omega(V + E)$ | $\Theta(V \cdot E)$ | $O(V \cdot E)$ | $O(V + E)$ | Minimum faculty oversight committee |
| **M5** | **DPLL SAT Solver** | $\Omega(V + C)$ | Exponential | $O(2^V)$ | $O(V + C)$ | Timetable constraint satisfaction |
| **M5** | **3-SAT Solver** | $\Omega(m)$ | Exponential | $O(2^n)$ | $O(n + m)$ | 3-way conflict constraint resolution |
| **M5** | **3-SAT $\to$ CLIQUE** | $\Omega(k^2)$ | $\Theta(k^2)$ | $O(k^2)$ | $O(k^2)$ | Hardness proof of room scheduling |
| **M5** | **CLIQUE $\to$ Indep. Set** | $\Omega(V^2)$ | $\Theta(V^2)$ | $O(V^2)$ | $O(V^2)$ | Conflict graph complement conversion |
| **M5** | **Indep. Set $\to$ Vert. Cover** | $\Omega(V + E)$ | $\Theta(V + E)$ | $O(V + E)$ | $O(V)$ | Exam invigilation duality mapping |
| **M5** | **Vertex Cover 2-Approx** | $\Omega(E)$ | $\Theta(V + E)$ | $O(V + E)$ | $O(V + E)$ | Fast exam conflict rescheduling |
| **M6** | **Randomized QuickSort** | $\Omega(n \log n)$ | $\Theta(n \log n)$ | $O(n^2)$ | $O(\log n)$ | Student GPA merit honor roll |
| **M6** | **Reservoir Sampling** | $\Omega(N)$ | $\Theta(N)$ | $O(N)$ | $O(k)$ | Streaming audit of student telemetry |
| **M6** | **Miller-Rabin Primality** | $\Omega(k \log n)$ | $\Theta(k \log^3 n)$ | $O(k \log^3 n)$ | $O(1)$ | Cryptographic student ID verification |
| **M6** | **Blelloch Parallel Scan** | $\Omega(\log n)$ span | $\Theta(n)$ work, $O(\log n)$ span | $O(n)$ work, $O(\log n)$ span | $O(n)$ | Cumulative study hours progression |
| **M6** | **Parallel Tree Reduce** | $\Omega(\log n)$ span | $\Theta(n)$ work, $O(\log n)$ span | $O(n)$ work, $O(\log n)$ span | $O(\log n)$ | Cohort GPA and marks distribution |
| **M6** | **Brent's Theorem** | $\Omega(1)$ | $\Theta(1)$ | $O(1)$ | $O(1)$ | Processor capacity & speedup bounds |

---

## 2. In-Depth Module Analysis

### 2.1 Module M1: String Algorithms (CO1)
1. **Knuth-Morris-Pratt (KMP)**:
   - **Preprocessing**: Computing the LPS array examines each character at most twice because the index `len` increases by 1 at most $m$ times and can only decrease. Preprocessing takes $\Theta(m)$ time and $O(m)$ auxiliary space.
   - **Search**: The text pointer $i$ never retreats; it strictly advances from $0$ to $n-1$. The pattern pointer $j$ falls back using LPS. Total comparisons are bounded by $2n$, yielding $O(n)$ search time.
   - **Total**: $O(n + m)$ deterministic time.

2. **Z-Function**:
   - Maintains an explicit interval $[L, R]$ (the rightmost Z-box). For each position $i$:
     - If $i > R$, characters are compared directly starting at index 0.
     - If $i \le R$, $Z[i]$ is initialized to $\min(R - i + 1, Z[i - L])$. Any further character matches strictly advance $R$.
   - Since $R$ increases monotonically up to $n-1$, the total number of character matches outside known boxes is at most $n$. Total time is strictly $O(n)$ with $O(n)$ space.

3. **Rabin-Karp**:
   - Uses Horner's rule to compute initial rolling hashes in $O(m)$.
   - Sliding the window updates the rolling hash in $O(1)$ arithmetic operations:
     $$H_{new} = \left(d \cdot (H_{old} - T[i] \cdot d^{m-1}) + T[i+m]\right) \pmod q$$
   - Spurious hits are checked character-by-character in $O(m)$. On university strings with prime $q = 10^9+7$, collision probability is $< 10^{-6}$, giving $O(n + m)$ average time.

4. **Aho-Corasick**:
   - Constructs a keyword Trie in $O(\sum m_i)$ time.
   - Computes failure transitions level-by-level via Breadth-First Search (BFS) in $O(\sum m_i \cdot \Sigma)$ time where $\Sigma$ is alphabet size.
   - Text parsing advances one character per step; failure transitions do not consume characters but their amortized steps are bounded by the trie depth. Total runtime is $O(n + \sum m_i + z)$ where $z$ is the number of match occurrences.

---

### 2.2 Module M2: Suffix Structures (CO2)
1. **SA-IS (Suffix Array Induced Sorting)**:
   - Classifies characters into S-type and L-type in $O(n)$ time.
   - Identifies LMS (Leftmost S-type) characters. There are at most $n/2$ LMS characters.
   - Performs induced sorting via bucket pointers in $O(n)$ time.
   - Recursively solves the reduced problem on LMS substrings:
     $$T(n) \le T(n/2) + O(n) \implies T(n) = O(n)$$
   - Strictly linear $O(n)$ time and $O(n)$ space, optimal for large academic indexing.

2. **Kasai's LCP Algorithm**:
   - Inverts the suffix array into a `rank` array in $O(n)$.
   - Leverages the key mathematical property:
     $$\text{LCP}[\text{rank}[i]] \ge \text{LCP}[\text{rank}[i-1]] - 1$$
   - Height variable $h$ starts at 0, decreases at most once per iteration ($n$ times total), and can never exceed $n$. Hence, $h$ can increase at most $2n$ times.
   - Total time is strictly $O(n)$ with $O(n)$ space.

3. **Suffix Automaton (DAWG)**:
   - Constructs an online state machine state-by-state.
   - Blumer et al. proved that for a string of length $n$, the number of states is at most $2n - 1$ and the number of transitions is at most $3n - 4$.
   - Each extension takes amortized $O(1)$ time, yielding $O(n)$ total construction time and $O(n)$ space.

---

### 2.3 Module M3: Advanced Dynamic Programming (CO3)
1. **Levenshtein & Damerau-Levenshtein**:
   - Constructs an $(n+1) \times (m+1)$ dynamic programming matrix.
   - Each cell $(i, j)$ requires constant $O(1)$ lookups among 3 or 4 neighboring cells.
   - Total time: $\Theta(n \cdot m)$. Space: $\Theta(n \cdot m)$.

2. **Matrix Chain Multiplication (MCM)**:
   - Evaluates sub-chains of length $l \in [2, n]$.
   - For a chain of length $l$ from $i$ to $j$, evaluates $k \in [i, j-1]$ split points:
     $$T(n) = \sum_{l=2}^n (n - l + 1)(l - 1) = \Theta(n^3)$$
   - Table storage requires $n \times n$ entries for both $m[i][j]$ and $s[i][j]$, taking $\Theta(n^2)$ space.

3. **Bitmask DP**:
   - There are $2^N$ possible course subset states.
   - For each mask, the algorithm iterates through $N$ candidate courses to verify prerequisite masks and credit budgets:
     $$T(N) = O(2^N \cdot N)$$
   - For $N = 12$, $2^{12} \cdot 12 = 4096 \cdot 12 = 49,152$ operations, executing instantaneously in $< 5$ milliseconds.

4. **Optimal Binary Search Tree (OBST)**:
   - Fills upper-triangular matrices $e[i][j]$ and $w[i][j]$ for keys $1 \le i \le j \le n$.
   - For each sub-problem of size $l$, tests $r \in [i, j]$ as potential root:
     $$T(n) = \sum_{l=1}^n \sum_{i=1}^{n-l+1} \sum_{r=i}^{i+l-1} O(1) = \Theta(n^3)$$
   - Space complexity is $\Theta(n^2)$.

---

### 2.4 Module M4: Network Flow (CO4)
1. **Bipartite Matching (Augmenting Paths)**:
   - Each augmentation path search takes $O(E)$ via DFS.
   - The matching size is bounded by $|V|/2$.
   - Total time: $O(V \cdot E)$.

2. **Ford-Fulkerson**:
   - Each DFS traversal finds an augmenting path in $O(E)$.
   - In integer networks, each augmentation increases flow by at least 1 unit.
   - Total time: $O(E \cdot \text{max\_flow})$.

3. **Edmonds-Karp**:
   - Uses BFS to find the shortest augmenting path (measured in number of edges).
   - Each augmentation increases the distance from source to some bottleneck edge. A critical edge can become critical at most $|V|/2$ times.
   - Total time: $O(V \cdot E^2)$ independent of capacity values.

4. **Dinic's Algorithm**:
   - Divides execution into phases. In each phase:
     1. BFS constructs the level graph in $O(E)$.
     2. DFS finds a blocking flow using pointer advancement in $O(V \cdot E)$.
   - There are at most $V - 1$ phases because the distance to sink increases strictly after each phase.
   - Total time: $O(V^2 \cdot E)$. On unit capacity networks: $O(E \sqrt{V})$.

5. **König's Theorem**:
   - Relies on one maximum bipartite matching computation: $O(V \cdot E)$.
   - Alternating BFS from unmatched left vertices runs in $O(V + E)$.
   - Partitioning into $(L \setminus Z) \cup (R \cap Z)$ takes $O(V)$. Total time: $O(V \cdot E)$.

---

### 2.5 Module M5: NP-Completeness & Reductions (CO5)
1. **DPLL SAT & 3-SAT**:
   - Backtracking search over $V$ boolean variables.
   - Without pruning: $O(2^V \cdot C)$.
   - Unit clause propagation and pure literal elimination prune exponential subtrees, achieving sub-exponential performance on typical academic timetabling formulas.

2. **Reductions (3-SAT $\to$ CLIQUE $\to$ Independent Set $\to$ Vertex Cover)**:
   - 3-SAT $\to$ CLIQUE: Creates $3k$ vertices and compares pairs in different clauses: $\binom{3k}{2} = O(k^2)$ time and space.
   - CLIQUE $\to$ Independent Set: Graph complement inspection examines all $\binom{V}{2}$ edges: $O(V^2)$ time and space.
   - Independent Set $\to$ Vertex Cover: Complement set subtraction $C = V \setminus S$ takes $O(V + E)$ time.
   - All reductions run in verified polynomial time.

3. **Vertex Cover 2-Approximation**:
   - Finds a maximal matching by inspecting each edge once: $O(E)$.
   - Endpoints are added to cover in $O(1)$ per edge.
   - Total runtime: $O(V + E)$.
   - **Approximation Bound**: If maximal matching has $M$ edges, $|C_{approx}| = 2M$. Any valid vertex cover must include at least one endpoint from each disjoint edge in $M$, so $|C_{opt}| \ge M$. Thus:
     $$\frac{|C_{approx}|}{|C_{opt}|} \le \frac{2M}{M} = 2.0$$

---

### 2.6 Module M6: Randomized & Parallel Algorithms (CO6)
1. **Randomized QuickSort**:
   - Random pivot selection guarantees that any two elements are compared with probability at most $2 / (j - i + 1)$.
   - Expected comparisons:
     $$E[C] = \sum_{i=1}^n \sum_{j=i+1}^n \frac{2}{j - i + 1} = 2n \ln n + \Theta(n) = \Theta(n \log n)$$
   - Worst case $O(n^2)$ occurs only if the random number generator consistently picks extreme elements, probability $< 2^{-n}$.

2. **Reservoir Sampling (Algorithm R)**:
   - Processes each stream element in $O(1)$ time.
   - **Induction Proof**: Probability that $i$-th element is in reservoir after $N$ items:
     - At arrival step $i$, it is included with probability $k/i$.
     - At each step $j > i$, probability of not being replaced is $1 - (k/j) \cdot (1/k) = (j - 1)/j$.
     - Cumulative probability:
       $$P = \frac{k}{i} \times \frac{i}{i+1} \times \frac{i+1}{i+2} \times \cdots \times \frac{N-1}{N} = \frac{k}{N}$$
   - Space is bounded by $O(k)$.

3. **Miller-Rabin Primality**:
   - Decomposes $n - 1 = 2^s \cdot d$ in $O(\log n)$.
   - Modular exponentiation runs in $O(\log n)$ multiplications using bitwise arithmetic.
   - For $k$ independent rounds, false-positive probability is at most $(1/4)^k$. For $k = 20$, error probability $< 10^{-12}$.
   - Total time: $O(k \log^3 n)$.

4. **Blelloch Parallel Scan**:
   - Arrays are padded to $m = 2^{\lceil \log_2 n \rceil}$.
   - Upsweep Phase: $\log_2 m$ steps, step $d$ does $m / 2^{d+1}$ additions. Total work: $\sum_{d=0}^{\log m - 1} m / 2^{d+1} = m - 1 = O(n)$.
   - Downsweep Phase: $\log_2 m$ steps, identical work $m - 1 = O(n)$.
   - Span (critical path depth) is $2 \log_2 m = O(\log n)$. Work-efficient parallel algorithm.

5. **Parallel Tree Reduce**:
   - Binary reduction tree has depth $\lceil \log_2 n \rceil$.
   - Total work: $W = n - 1 = O(n)$.
   - Span: $S = O(\log n)$.
   - Speedup on $P$ cores: $S_P \approx \min(P, n / \log n)$.

6. **Brent's Theorem**:
   - Evaluates theoretical schedule:
     $$T_P \le \frac{W - S}{P} + S$$
   - As $P \to \infty$, $T_P \to S$ (the critical path limit).
   - Analytical computation runs in $O(1)$ time and $O(1)$ space.
