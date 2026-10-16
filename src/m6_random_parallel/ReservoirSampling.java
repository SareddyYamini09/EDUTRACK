package m6_random_parallel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

// Input: High-volume continuous data stream of unknown size N, target sample size k
// Algorithm: Reservoir Sampling (Algorithm R by Waterman & Knuth)
// Output: Exactly k randomly selected items where every item has identical probability k/N of selection
// Time Complexity: O(N) linear time processing one element at a time
// Space Complexity: O(k) space bounded strictly by reservoir size
// EduTrack Application: Live telemetry auditing of high-frequency student interactions (logins, downloads, video pauses)
public class ReservoirSampling {

    private static final Random rng = new Random();

    /**
     * Selects k items uniformly at random from an input stream/iterable.
     */
    public static <T> List<T> sample(Iterable<T> stream, int k) {
        List<T> reservoir = new ArrayList<>(k);
        Iterator<T> it = stream.iterator();

        // Step 1: Fill the reservoir with the first k elements
        int count = 0;
        while (it.hasNext() && count < k) {
            reservoir.add(it.next());
            count++;
        }

        // Step 2: For each subsequent i-th element (0-indexed count >= k)
        while (it.hasNext()) {
            T item = it.next();
            count++; // count is now the 1-based index of this item

            // Pick a random integer j in [0, count - 1]
            int j = rng.nextInt(count);

            // If j falls within the reservoir capacity [0, k-1], replace element j
            if (j < k) {
                reservoir.set(j, item);
            }
        }
        return reservoir;
    }

    /**
     * Demonstrates Reservoir Sampling on live student interaction stream.
     */
    public static void demonstrate() {
        System.out.println("\n=== Reservoir Sampling Streaming Telemetry Demonstration ===");
        int k = 4;

        // Simulated high-volume incoming activity event stream
        List<String> activityStream = List.of(
                "Event 01: [STU102] Portal Login at 08:30",
                "Event 02: [STU102] Quiz Attempt at 09:15",
                "Event 03: [STU101] Lecture Video Watched at 10:00",
                "Event 04: [STU103] Syllabus Download at 10:45",
                "Event 05: [STU104] Discussion Forum Post at 11:10",
                "Event 06: [STU105] Assignment Upload at 11:40",
                "Event 07: [STU106] Practice Problem Solved at 12:15",
                "Event 08: [STU107] Portal Login at 13:00",
                "Event 09: [STU108] Grade Report Checked at 13:30",
                "Event 10: [STU109] Video Rewatched at 14:00",
                "Event 11: [STU110] Cloud Lab Launched at 14:45",
                "Event 12: [STU111] Quiz Retake at 15:30"
        );

        System.out.println("Incoming Activity Stream Length N = " + activityStream.size());
        System.out.println("Target Sample Reservoir Size k = " + k);
        System.out.printf("Mathematical Guarantee: Each incoming event has exact uniform probability k/N = %d/%d = %.2f%%%n",
                k, activityStream.size(), (k * 100.0 / activityStream.size()));

        List<String> sampled = sample(activityStream, k);

        System.out.println("\nSelected Random Reservoir Sample (Audited Interactions):");
        for (int i = 0; i < sampled.size(); i++) {
            System.out.printf("  Sample [%d]: %s%n", i + 1, sampled.get(i));
        }
        System.out.println("Complexity: O(N) stream time, O(k) bounded memory.");
    }
}
