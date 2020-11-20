
package thedpfarm.animals;

import thedpfarm.animals.AnimalState.State;
import thedpfarm.util.DayAndNightObserver;

public interface Animal extends DayAndNightObserver {

    public State getState();

    public void setState(State newState);
    
}
