import java.nio.file.Path;
import java.nio.file.Paths;

public class infoApplicants {
    public static void main(String[] args) {
        Path pathOfFaculties = Paths.get("faculties-info.txt");
        Path pathOfApplicants = Paths.get("applicants-info.txt");
        Faculties faculties = new Faculties(pathOfFaculties);
        Applicants applicants = new Applicants(pathOfApplicants);
        faculties.setSortedApplicants(applicants);

    }
}
