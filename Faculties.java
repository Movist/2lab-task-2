import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Faculties extends Faculty {

    //все абитуриенты
    private Map<String, ArrayList<String>> facultiesList = new HashMap<>();

    public Faculties(Path pathOfFaculties) {
        super(pathOfFaculties);
        try {
            List<String> lines = Files.readAllLines(pathOfFaculties, StandardCharsets.UTF_8);
            for (int line = 0; line < lines.size(); line++) {
                Faculty faculty = new Faculty(lines.get(line));
                ArrayList<String> facultyData = new ArrayList<>();
                facultyData.add(faculty.getFirstDiscipline());
                facultyData.add(faculty.getSecondDiscipline());
                facultyData.add(faculty.getThirdDiscipline());
                facultyData.add(faculty.getMinimumScoreValue());
                facultyData.add(faculty.getPassingScore());
                facultiesList.put(faculty.getFacultyName(), facultyData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSortedApplicants(Applicants applicants) {
        Map<String, Map<String, Integer>> applicantsList = applicants.getApplicantsList();

        for (Map.Entry<String, Map<String, Integer>> applicant : applicantsList.entrySet()) {
            for (Map.Entry<String, ArrayList<String>> faculty : facultiesList.entrySet()) {
                int isMatch = 0;
                int average = 0;
                for (Map.Entry<String, Integer> innerEntry : applicant.getValue().entrySet()) {
                    ArrayList<String> str = faculty.getValue();
                    for (int j = 0; j < 3; j++) {
                        String temp = str.get(j);
                        String temp2 = String.valueOf(innerEntry.getKey());
                        if (temp2.equals(temp)) {
                            isMatch++;
                            average += innerEntry.getValue();
                        }
                    }
                }
                if (isMatch == 3) {
                    if (Integer.parseInt(faculty.getValue().get(3)) < average) {
                        if (Integer.parseInt(faculty.getValue().get(4)) < average) {
                            applicants.handleEventBudget(applicant, faculty, average);
                        } else {
                            applicants.handleEventPaid(applicant, faculty, average);

                        }
                    }
                }
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        }
    }

    public void printFaculties() {
        for (Map.Entry<String, ArrayList<String>> entry : facultiesList.entrySet()) {
            System.out.print(entry.getKey() + " ");
            for (String innerEntry : entry.getValue()) {
                System.out.print(innerEntry + " ");
            }
            System.out.println();
        }
    }


    @Override
    public void addApplicants(String applicant) {
        /*sortedApplicants.add(applicant);*/
    }

    @Override
    public void removeApplicants(String applicant) {
        /*sortedStudents.remove(applicant);*/
    }

    @Override
    public void addFaculty(String faculty) {
        /*listOfFaculties.add(faculty);*/
    }

    @Override
    public void removeFaculty(String faculty) {
        /*listOfFaculties.remove(faculty);*/
    }
}
