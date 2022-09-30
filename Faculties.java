import com.itextpdf.text.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class Faculties extends Faculty {

    Map<String, ArrayList<String>> sortedApplicants = new HashMap<>();
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

    public void setSortedApplicants() {
        Map<String, Map<String, Integer>> applicantsList = Applicants.getApplicantsList();
        for (Map.Entry<String, Map<String, Integer>> applicant : applicantsList.entrySet()) {
            ArrayList<String> fcts = new ArrayList<>();
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
                            fcts.add(faculty.getKey());
                        } else {
                            fcts.add(faculty.getKey());
                        }
                    }
                }
                sortedApplicants.put(applicant.getKey(), fcts);
            }
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

    public void eventSortedApplicants(String question) throws IOException, DocumentException {
        for (Map.Entry<String, ArrayList<String>> applicant : sortedApplicants.entrySet()) {
            String name = applicant.getKey();
            if (question == "txt") {
                Path path = Path.of("C:\\Users\\Shtigun\\Desktop\\5 сем\\Java\\Labs\\lab 2_task2\\mails\\" + name + ".txt");
                FileOutputStream fileOutputStream = new FileOutputStream(path.toFile()); //save txt
                if (applicant.getValue().size() == 0) {
                    String data = applicant.getKey() + ", вы не прошли ни на одну специальность по минимальным баллам";
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.close();
                } else {
                    String data = applicant.getKey() + ", вы прошли на специальности:" + applicant.getValue();
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.close();
                }
            }

            if (question == "pdf") {
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                PDType0Font font = PDType0Font.load(document, new File("resources/dejavusans.ttf"));
                contentStream.setFont(font, 16);
                if (applicant.getValue().size() == 0) {
                    String data = applicant.getKey() + ", вы не прошли ни на одну специальность по минимальным баллам";
                    contentStream.beginText();
                    contentStream.setLeading(14.5f);
                    contentStream.newLineAtOffset(25, 725);
                    contentStream.showText(data);
                    contentStream.endText();
                    contentStream.close();
                    document.save("C:\\Users\\Shtigun\\Desktop\\5 сем\\Java\\Labs\\lab 2_task2\\mails\\" + name + ".pdf");
                    document.close();
                } else {
                    String data = applicant.getKey();
                    contentStream.beginText();
                    contentStream.setLeading(14.5f);
                    contentStream.newLineAtOffset(25, 725);
                    contentStream.showText(data);
                    contentStream.newLine();
                    contentStream.showText("вы прошли на специальности:");
                    contentStream.newLine();
                    for (int i = 0; i < applicant.getValue().size(); i++) {
                        contentStream.showText(String.valueOf(applicant.getValue().get(i)));
                        contentStream.newLine();
                    }
                    contentStream.endText();
                    contentStream.close();
                    document.save("C:\\Users\\Shtigun\\Desktop\\5 сем\\Java\\Labs\\lab 2_task2\\mails\\" + name + ".pdf");
                    document.close();
                }

            }
        }
    }

    @Override
    public void addApplicant(String applicantData) {
        Applicants applicant = new Applicants(applicantData);
        /*applicant.applicantsList.put(applicant.getName(), applicant.getDisciplinesAndScores());*/
    }

    @Override
    public void removeApplicant(String applicantName) {
        Applicants.applicantsList.remove(applicantName);
    }
}
