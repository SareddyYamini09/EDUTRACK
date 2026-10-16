package m6_random_parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Input: Array or list of comparable academic records (e.g. Student GPAs, Assignment Marks)
// Algorithm: Randomized QuickSort with manual random pivot selection and in-place partitioning
// Output: Sorted array in ascending or descending order
// Time Complexity: O(n log n) expected time, O(n^2) worst-case with negligible probability
// Space Complexity: O(log n) expected recursion stack space
// EduTrack Application: Ranking student GPAs, sorting assignment submissions, and ordering activity scores
public class RandomizedQuickSort {

    private static final Random rng = new Random();

    public static class Record<T> {
        public final String id;
        public final String name;
        public final double score;
        public final T data;

        public Record(String id, String name, double score, T data) {
            this.id = id;
            this.name = name;
            this.score = score;
            this.data = data;
        }

        @Override
        public String toString() {
            return String.format("%-8s | %-20s | Score/GPA: %6.2f", id, name, score);
        }
    }

    /**
     * Sorts an array of doubles in-place using randomized quicksort.
     */
    public static void sort(double[] arr, boolean ascending) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1, ascending);
    }

    private static void quickSort(double[] arr, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high, ascending);
            quickSort(arr, low, pi - 1, ascending);
            quickSort(arr, pi + 1, high, ascending);
        }
    }

    private static int randomizedPartition(double[] arr, int low, int high, boolean ascending) {
        // Pick a random pivot index between low and high
        int randomPivot = low + rng.nextInt(high - low + 1);
        swap(arr, randomPivot, high);
        return partition(arr, low, high, ascending);
    }

    private static int partition(double[] arr, int low, int high, boolean ascending) {
        double pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = ascending ? (arr[j] <= pivot) : (arr[j] >= pivot);
            if (condition) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Generic list sorter for EduTrack records.
     */
    public static <T> void sortRecords(List<Record<T>> list, boolean ascending) {
        if (list == null || list.size() <= 1) return;
        quickSortRecords(list, 0, list.size() - 1, ascending);
    }

    private static <T> void quickSortRecords(List<Record<T>> list, int low, int high, boolean ascending) {
        if (low < high) {
            int randomPivot = low + rng.nextInt(high - low + 1);
            swapRecords(list, randomPivot, high);
            int pi = partitionRecords(list, low, high, ascending);
            quickSortRecords(list, low, pi - 1, ascending);
            quickSortRecords(list, pi + 1, high, ascending);
        }
    }

    private static <T> int partitionRecords(List<Record<T>> list, int low, int high, boolean ascending) {
        double pivot = list.get(high).score;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = ascending ? (list.get(j).score <= pivot) : (list.get(j).score >= pivot);
            if (condition) {
                i++;
                swapRecords(list, i, j);
            }
        }
        swapRecords(list, i + 1, high);
        return i + 1;
    }

    private static <T> void swapRecords(List<Record<T>> list, int i, int j) {
        Record<T> temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Demonstrates Randomized QuickSort ranking on student GPA data.
     */
    public static void demonstrate() {
        System.out.println("\n=== Randomized QuickSort Academic Ranking Demonstration ===");
        List<Record<String>> students = new ArrayList<>();
        students.add(new Record<>("STU101", "Aarav Sharma", 3.85, null));
        students.add(new Record<>("STU102", "Yamini Reddy", 3.96, null));
        students.add(new Record<>("STU103", "Rohan Verma", 3.42, null));
        students.add(new Record<>("STU104", "Pooja Hegde", 3.71, null));
        students.add(new Record<>("STU105", "Vikram Malhotra", 3.55, null));
        students.add(new Record<>("STU106", "Ananya Iyer", 3.91, null));
        students.add(new Record<>("STU107", "Karthik Nair", 3.28, null));

        System.out.println("Student Records BEFORE Sorting:");
        for (Record<String> r : students) {
            System.out.println("  " + r);
        }

        // Sort descending to find top academic rankers
        sortRecords(students, false);

        System.out.println("\nStudent Merit Honor Roll AFTER Randomized QuickSort (Descending GPA):");
        int rank = 1;
        for (Record<String> r : students) {
            System.out.printf("  Rank %d | %s%n", rank++, r);
        }
        System.out.println("Complexity: Expected O(n log n) time, O(log n) space.");
    }
}
