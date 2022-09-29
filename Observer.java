import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Observer {
    void handleEventBudget(Map.Entry<String, Map<String, Integer>> applicant, Map.Entry<String, ArrayList<String>> faculty, int average);

    void handleEventPaid(Map.Entry<String, Map<String, Integer>> applicant, Map.Entry<String, ArrayList<String>> faculty, int average);
}
