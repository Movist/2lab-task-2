import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Faculty implements Observed {
    //прошедшие абитуриенты на специальность
    private String facultyName;
    private String firstDiscipline;
    private String secondDiscipline;
    private String thirdDiscipline;
    private String minimumScoreValue;
    private String passingScore;

    public Faculty(Path pathOfFaculties) {

    }

    public Faculty(String fullInfo) {
        String[] fullInfo1 = fullInfo.split(",");
        this.facultyName = fullInfo1[0];
        this.firstDiscipline = fullInfo1[1];
        this.secondDiscipline = fullInfo1[2];
        this.thirdDiscipline = fullInfo1[3];
        this.minimumScoreValue = fullInfo1[4];
        this.passingScore = fullInfo1[5];

    }

    public Faculty() {

    }


    @Override
    public void addApplicants(String applicant) {

    }

    @Override
    public void removeApplicants(String applicant) {

    }

    @Override
    public void addFaculty(String faculty) {

    }


    @Override
    public void removeFaculty(String faculty) {

    }


    @Override
    public void notifyObservers() {

    }


    protected String getFacultyName() {
        return this.facultyName;
    }

    protected String getFirstDiscipline() {
        return this.firstDiscipline;
    }

    protected String getSecondDiscipline() {
        return this.secondDiscipline;
    }

    protected String getThirdDiscipline() {
        return this.thirdDiscipline;
    }

    protected String getMinimumScoreValue() {
        return this.minimumScoreValue;
    }

    protected String getPassingScore() {
        return this.passingScore;
    }
}
