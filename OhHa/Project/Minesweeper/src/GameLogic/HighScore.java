package GameLogic;

import java.util.*;




/**
 *
 * @author Madeleine Ekblom
 */
public class HighScore {
    
    private HashMap<Integer, String> scores = new HashMap<Integer, String>();
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
    
    private void removeTime() {
        if (times.size() < 5) {
            return;
        }
        int t = getSlowestTime(); // What happens if there are two results with the same name and time???
        times.remove(t);
        scores.remove(t);
        
    }
    
    public void addTime(String name, int time) {  
        removeTime();
        scores.put(time, name);
        times.add(time);
    } 
}
