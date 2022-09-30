import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Applicant implements Observer {
    private String fullName;
    private final Map<String, Integer> disciplinesAndScores = new HashMap<>();

    public Applicant(String fullInfo) {
        String[] fullInfo1 = fullInfo.split(",");
        this.fullName = fullInfo1[0];

        for (int i = 1; i < fullInfo1.length; i++) {
            String disciplineWithScore = fullInfo1[i];
            disciplinesAndScores.put(disciplineWithScore.split("-")[0], Integer.valueOf(disciplineWithScore.split("-")[1]));
        }
    }

    public Applicant() {
    }


    protected String getName() {
        return this.fullName;
    }

    protected Map<String, Integer> getDisciplinesAndScores() {
        return this.disciplinesAndScores;
    }

    @Override
    public void handleEvent(Map<Map.Entry<String, Map<String, Integer>>, Map.Entry<String, ArrayList<String>>> sortedApplicants) {

    }

}
