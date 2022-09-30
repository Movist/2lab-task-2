import com.itextpdf.text.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class infoApplicants {
    public static void main(String[] args) throws IOException, DocumentException {
        Path pathOfFaculties = Paths.get("faculties-info.txt");
        Path pathOfApplicants = Paths.get("applicants-info.txt");
        Faculties faculties = new Faculties(pathOfFaculties);
        Applicants applicants = new Applicants(pathOfApplicants);
        faculties.addApplicant("Шалагинова Надежда Владимировна,Русский-100,Математика-100,Физика-100,Информатика-100,Химия-100,Английский-100,Общество-100,Биология-100");
        faculties.removeApplicant("Абайдулин Максим Маратович");

        faculties.setSortedApplicants();
        faculties.eventSortedApplicants("pdf");//save to pdf or txt?
    }
}
