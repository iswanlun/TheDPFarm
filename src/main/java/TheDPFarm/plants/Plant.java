
package TheDPFarm.plants;

import TheDPFarm.util.DayAndNightObserver;

public interface Plant extends DayAndNightObserver {

    public PlantState getState();
    public void setState(PlantState newState);
    
    public String getType();

}
