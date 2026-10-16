package services;

import data.DataManager;
import m1_string.AhoCorasick;
import m1_string.ZFunction;
import m2_suffix.LCPKasai;
import model.Assignment;
import utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service managing Student Assignments with integrated DSA algorithms:
 * - Aho-Corasick & Z-Function for multi-keyword and phrase pattern search
 * - Suffix Array & Kasai LCP for document similarity and plagiarism detection
 */
public class AssignmentService {
    private final DataManager dataManager;

    public AssignmentService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Assignment> getAllAssignments() {
        return dataManager.getAssignments();
    }

    public void displayAllAssignments() {
        List<Assignment> assignments = getAllAssignments();
        DisplayUtils.printSubHeader("Assignment Submissions Directory (" + assignments.size() + ")");
        String[] headers = {"ID", "Course", "Title", "Student", "Marks", "Date", "Description Preview"};
        List<String[]> rows = new ArrayList<>();
        for (Assignment a : assignments) {
            String desc = a.getDescription();
            if (desc.length() > 30) desc = desc.substring(0, 27) + "...";
            rows.add(new String[]{
                    a.getAssignmentId(), a.getCourseCode(), a.getTitle(),
                    a.getStudentId(), String.format("%.1f", a.getMarks()),
                    a.getSubmissionDate(), desc
            });
        }
        DisplayUtils.printTable(headers, rows);
    }

    public void searchKeywordsInAssignments(List<String> keywords) {
        DisplayUtils.printCard("Multi-Keyword Search via Aho-Corasick", "M1 / CO1",
                "O(n + sum(m_i) + z)", "O(sum(m_i))", "Simultaneous Keyword Extraction from Submissions");

        List<Assignment> assignments = getAllAssignments();
        AhoCorasick ac = new AhoCorasick(keywords);

        System.out.println("Searching Keywords: " + keywords);
        int totalMatches = 0;

        for (Assignment a : assignments) {
            String text = a.getTitle() + " " + a.getDescription();
            List<AhoCorasick.MatchResult> matches = ac.search(text);
            if (!matches.isEmpty()) {
                totalMatches += matches.size();
                System.out.printf("  * [%s] Course: %s | Student: %s%n", a.getAssignmentId(), a.getCourseCode(), a.getStudentId());
                for (AhoCorasick.MatchResult mr : matches) {
                    System.out.printf("      -> Found \"%s\" at char position %d%n", mr.keyword, mr.position);
                }
            }
        }
        System.out.println("Total keyword occurrences located: " + totalMatches);
    }

    public void checkAssignmentSimilarity(String id1, String id2) {
        DisplayUtils.printCard("Plagiarism Detection via Suffix Array & Kasai LCP", "M2 / CO2",
                "O(n) Suffix Array + O(n) LCP", "O(n)", "Assignment Submission Similarity Analysis");

        Assignment a1 = null, a2 = null;
        for (Assignment a : getAllAssignments()) {
            if (a.getAssignmentId().equalsIgnoreCase(id1)) a1 = a;
            if (a.getAssignmentId().equalsIgnoreCase(id2)) a2 = a;
        }

        if (a1 == null || a2 == null) {
            DisplayUtils.printWarning("One or both assignment IDs could not be found!");
            return;
        }

        String doc1 = a1.getTitle() + " " + a1.getDescription();
        String doc2 = a2.getTitle() + " " + a2.getDescription();

        System.out.println("Document 1 [" + a1.getAssignmentId() + "]: \"" + doc1 + "\"");
        System.out.println("Document 2 [" + a2.getAssignmentId() + "]: \"" + doc2 + "\"");

        String commonSubstring = LCPKasai.findLongestCommonSubstring(doc1, doc2);

        System.out.println("\n--- Cross-Document Similarity Analysis ---");
        System.out.println("Longest Common Extracted Phrase: \"" + commonSubstring + "\"");
        System.out.println("Matched Phrase Length:            " + commonSubstring.length() + " characters");

        double similarityScore = (commonSubstring.length() * 200.0) / (doc1.length() + doc2.length());
        System.out.printf("Estimated Text Overlap Metric:   %.2f%%%n", Math.min(100.0, similarityScore));
        if (similarityScore > 50.0) {
            DisplayUtils.printWarning("High textual similarity detected. Flagged for academic integrity review!");
        } else {
            DisplayUtils.printSuccess("Similarity within standard academic threshold.");
        }
    }

    public void addAssignment(Assignment assignment) {
        for (Assignment a : dataManager.getAssignments()) {
            if (a.getAssignmentId().equalsIgnoreCase(assignment.getAssignmentId())) {
                DisplayUtils.printWarning("Assignment ID " + assignment.getAssignmentId() + " already exists!");
                return;
            }
        }
        dataManager.getAssignments().add(assignment);
        dataManager.saveAssignments();
        DisplayUtils.printSuccess("Assignment " + assignment.getAssignmentId() + " added successfully.");
    }
}
