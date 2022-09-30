import java.util.ArrayList;
import java.util.Map;

public interface Observer {
    void handleEvent(Map<Map.Entry<String, Map<String, Integer>>, Map.Entry<String, ArrayList<String>>> sortedApplicants);
}
