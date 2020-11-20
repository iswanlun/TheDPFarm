
package TheDPFarm.animals;

import TheDPFarm.animals.AnimalState.State;
import TheDPFarm.util.DayAndNightObserver;

public interface Animal extends DayAndNightObserver {

    public State getState();
    public void setState(State newState);
    
}
