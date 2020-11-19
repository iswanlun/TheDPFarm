package TheDPFarm.util;

public interface DayAndNightObservable {
    
    public void addObserver(DayAndNightObserver newObserver);
    public void removeObserver(DayAndNightObserver oldObserver);

    public void notifyDayEvent();
    public void notifyNightEvent();

}