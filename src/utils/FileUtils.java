package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Robust CSV and file operations utility using standard Java I/O.
 */
public class FileUtils {

    /**
     * Reads a CSV file and returns a list of string arrays.
     * Skips blank lines and comment lines starting with #.
     */
    public static List<String[]> readCsv(String filePath) {
        List<String[]> records = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return records;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                records.add(parseCsvLine(line));
            }
        } catch (IOException e) {
            System.err.println("  [!] Error reading file " + filePath + ": " + e.getMessage());
        }
        return records;
    }

    /**
     * Writes records to a CSV file. Creates parent directories if needed.
     */
    public static boolean writeCsv(String filePath, List<String[]> records) {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            for (String[] row : records) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    String val = row[i] != null ? row[i] : "";
                    if (val.contains(",") || val.contains("\"") || val.contains("\n")) {
                        val = "\"" + val.replace("\"", "\"\"") + "\"";
                    }
                    sb.append(val);
                    if (i < row.length - 1) {
                        sb.append(",");
                    }
                }
                bw.write(sb.toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("  [!] Error writing file " + filePath + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Parses a single CSV line with quote-handling support.
     */
    public static String[] parseCsvLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '\"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '\"') {
                    sb.append('\"');
                    i++; // skip escaped quote
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString().trim());
        return tokens.toArray(new String[0]);
    }
}
