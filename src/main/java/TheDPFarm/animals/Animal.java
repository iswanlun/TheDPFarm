
package TheDPFarm.animals;

import TheDPFarm.util.DayAndNightObserver;

public interface Animal extends DayAndNightObserver {

    public AnimalState getState();
    public void setState(AnimalState newState);
    
}
