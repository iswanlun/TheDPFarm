package TheDPFarm.util;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * This class will contain a timer that for every so many ossilations will trigger either
 * a day or a night event.
 */

public class DayNightManager extends TimerTask implements DayAndNightObservable {

    private ArrayList<DayAndNightObserver> observers = new ArrayList<>();

    private boolean cyclePosition = true; //true is day and false is night
    
    public DayNightManager() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 2000, 2000);
        //timer.scheduleAtFixedRate(this, 20000, 20000);
    }

    public void run() {
        //System.out.println("[DEBUG] : Day night switch occured.");
        if(cyclePosition) {
            notifyNightEvent();
        } else {
            notifyDayEvent();
        }
        cyclePosition = !cyclePosition;
    }
    
    @Override
    public void addObserver(DayAndNightObserver newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void removeObserver(DayAndNightObserver oldObserver) {
        observers.remove(oldObserver);
    }

    @Override
    public void notifyDayEvent() {
        for (DayAndNightObserver o : observers) {
            o.notifyDay();
        }
    }

    @Override
    public void notifyNightEvent() {
        for (DayAndNightObserver o : observers) {
            o.notifyNight();
        }
    }

    @Override
    public boolean getTime() {
        return cyclePosition;
    }

}
