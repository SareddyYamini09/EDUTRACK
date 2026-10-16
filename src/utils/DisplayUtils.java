package utils;

import java.util.List;

/**
 * Utility class for formatting console outputs, tables, and section headers.
 * Styled cleanly for terminal presentation.
 */
public class DisplayUtils {

    public static void printSectionHeader(String title) {
        int width = 70;
        System.out.println("\n" + "=".repeat(width));
        int pad = Math.max(0, (width - title.length()) / 2);
        System.out.println(" ".repeat(pad) + title.toUpperCase());
        System.out.println("=".repeat(width) + "\n");
    }

    public static void printSubHeader(String title) {
        printSectionHeader(title);
    }

    public static void printHeader(String title) {
        printSectionHeader(title);
    }

    public static void printCard(String title, String coMapping, String timeComplexity, String spaceComplexity, String application) {
        // Intentionally left blank to match clean terminal application layout
    }

    public static void printSuccess(String message) {
        System.out.println("  [+] " + message);
    }

    public static void printInfo(String message) {
        System.out.println("  [*] " + message);
    }

    public static void printWarning(String message) {
        System.out.println("  [!] " + message);
    }

    public static void printTable(String[] headers, List<String[]> rows) {
        if (headers == null || headers.length == 0) return;
        int[] colWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            colWidths[i] = headers[i].length();
        }
        for (String[] row : rows) {
            for (int i = 0; i < row.length && i < colWidths.length; i++) {
                if (row[i] != null && row[i].length() > colWidths[i]) {
                    colWidths[i] = row[i].length();
                }
            }
        }

        StringBuilder line = new StringBuilder("+");
        for (int w : colWidths) {
            line.append("-".repeat(w + 2)).append("+");
        }
        System.out.println(line);

        StringBuilder head = new StringBuilder("|");
        for (int i = 0; i < headers.length; i++) {
            head.append(String.format(" %-" + colWidths[i] + "s |", headers[i]));
        }
        System.out.println(head);
        System.out.println(line);

        for (String[] row : rows) {
            StringBuilder rowStr = new StringBuilder("|");
            for (int i = 0; i < colWidths.length; i++) {
                String val = (i < row.length && row[i] != null) ? row[i] : "";
                rowStr.append(String.format(" %-" + colWidths[i] + "s |", val));
            }
            System.out.println(rowStr);
        }
        System.out.println(line);
    }
}
