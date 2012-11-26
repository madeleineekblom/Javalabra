package GameLogic;

import java.util.*;




/**
 *
 * @author Madeleine Ekblom
 */
public class HighScore {
    
    private HashMap<String,Integer> scores = new HashMap<String, Integer>();
    private ArrayList<Integer> times = new ArrayList<Integer>();
    
    public int getSlowestTime() {
        if (times.isEmpty()) {
            return 0;
        }
        int t = times.get(0);
        for (int i = 1; i < times.size(); i++) {
            if (times.get(i) > t) {
                t = times.get(i);
            }
        }
        return t;
    }
}
