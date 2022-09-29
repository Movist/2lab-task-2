import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Applicants extends Applicant {

    private Map<String, Map<String, Integer>> applicantsList = new HashMap<>();

    public Applicants(Path pathOfApplicants) {//USE HASH MAP
        super();
        try {
            List<String> lines = Files.readAllLines(pathOfApplicants, StandardCharsets.UTF_8);
            for (int line = 0; line < lines.size(); line++) {
                Applicant applicant = new Applicant(lines.get(line));
                applicantsList.put(applicant.getName(), applicant.getDisciplinesAndScores());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Applicants() {

    }

    protected Map<String, Map<String, Integer>> getApplicantsList() {
        return applicantsList;
    }

    public void printApplicants() {
        int i = 1;
        for (Map.Entry<String, Map<String, Integer>> entry : applicantsList.entrySet()) {
            System.out.print("Студент " + i + ":" + entry.getKey());
            for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                System.out.print(". " + innerEntry.getKey() + " - " + innerEntry.getValue());
            }
            System.out.println();
            i++;
        }
    }
}
