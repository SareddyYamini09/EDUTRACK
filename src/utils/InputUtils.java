package utils;

import java.util.Scanner;

/**
 * Utility class for safe and validated console user input.
 * Guarantees that invalid input never crashes the application.
 */
public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads an integer within [min, max] inclusive, repeatedly prompting on error.
     */
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("  [!] Please enter a valid number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input format. Please enter an integer.");
            }
        }
    }

    /**
     * Reads any valid integer.
     */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid number. Please enter an integer.");
            }
        }
    }

    /**
     * Reads a double value within [min, max].
     */
    public static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("  [!] Please enter a value between %.2f and %.2f.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid number. Please enter a valid decimal number.");
            }
        }
    }

    /**
     * Reads a non-empty string from console.
     */
    public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("  [!] Input cannot be empty. Please enter a value.");
        }
    }

    /**
     * Reads a string with a default fallback if the user presses Enter.
     */
    public static String readString(String prompt, String defaultValue) {
        System.out.printf("%s [%s]: ", prompt, defaultValue);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }

    /**
     * Prompts the user to press Enter to continue.
     */
    public static void pause() {
        System.out.print("\nPress [Enter] to continue...");
        scanner.nextLine();
    }
}
