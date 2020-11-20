
package TheDPFarm.animals;

import TheDPFarm.animals.AnimalState.state;
import TheDPFarm.util.DayAndNightObserver;

public interface Animal extends DayAndNightObserver {

    public state getState();
    public void setState(state newState);
    
}
