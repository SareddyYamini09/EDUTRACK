package m6_random_parallel;

import java.util.Random;

// Input: Odd integer n > 2 and number of testing rounds k
// Algorithm: Miller-Rabin Probabilistic Primality Test using Modular Exponentiation
// Output: Boolean (true if n is PROBABLY PRIME, false if n is definitely COMPOSITE)
// Time Complexity: O(k * log^3 n)
// Space Complexity: O(1)
// EduTrack Application: Cryptographic verification of student diploma credentials, tamper-proof ID hashing
public class MillerRabin {

    private static final Random rng = new Random();

    /**
     * Safe modular multiplication (a * b) % mod preventing 64-bit overflow.
     */
    public static long mulMod(long a, long b, long mod) {
        long res = 0;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res + a) % mod;
            }
            a = (a * 2) % mod;
            b >>= 1;
        }
        return res;
    }

    /**
     * Computes (base^exp) % mod using binary exponentiation.
     */
    public static long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = mulMod(res, base, mod);
            }
            base = mulMod(base, base, mod);
            exp >>= 1;
        }
        return res;
    }

    /**
     * Single round Miller-Rabin witness test.
     */
    private static boolean millerTest(long d, long n) {
        // Pick a random witness in [2, n - 2]
        long a = 2 + (long) (rng.nextDouble() * (n - 4));
        long x = power(a, d, n);

        if (x == 1 || x == n - 1) {
            return true;
        }

        while (d != n - 1) {
            x = mulMod(x, x, n);
            d *= 2;

            if (x == n - 1) return true;
            if (x == 1) return false;
        }
        return false;
    }

    /**
     * Tests primality of n using k independent rounds.
     * Error probability <= (1/4)^k.
     */
    public static boolean isPrime(long n, int k) {
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;
        if (n % 2 == 0) return false;

        // Write n - 1 as 2^s * d
        long d = n - 1;
        while (d % 2 == 0) {
            d /= 2;
        }

        for (int i = 0; i < k; i++) {
            if (!millerTest(d, n)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Demonstrates Miller-Rabin primality testing on large IDs.
     */
    public static void demonstrate() {
        System.out.println("\n=== Miller-Rabin Probabilistic Primality Demonstration ===");
        long[] testNumbers = {
                1000000007L,         // Famous large prime
                1000000009L,         // Twin prime
                1000000011L,         // Composite (3 * 333333337)
                2147483647L,         // 8th Mersenne prime (2^31 - 1)
                2147483649L,         // Composite
                999999999989L        // 12-digit prime used in security certificate
        };

        int rounds = 20;
        System.out.println("Testing Candidates with k = " + rounds + " Witness Rounds:");
        System.out.println("Maximum Error Probability <= (1/4)^20 ≈ 9.09 x 10^-13 (Virtually Certain)");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-18s | %-16s | %s%n", "Candidate Number", "Primality Verdict", "Confidence Level");
        System.out.println("------------------------------------------------------------------");

        for (long num : testNumbers) {
            boolean prime = isPrime(num, rounds);
            System.out.printf("%-18d | %-16s | %s%n",
                    num,
                    prime ? "PROBABLY PRIME" : "COMPOSITE",
                    prime ? "> 99.99999999%" : "100% Definite");
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Complexity: O(k * log^3 n)");
    }
}
