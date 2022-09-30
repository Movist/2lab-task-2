public interface Observed {
    public void addApplicant(String applicant);

    public void removeApplicant(String applicant);

    public void notifyObservers();
}
