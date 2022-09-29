public interface Observed {
    public void addApplicants(String applicant);

    public void removeApplicants(String applicant);
    public void addFaculty(String faculty);
    public void removeFaculty(String faculty);


    public void notifyObservers();
}
