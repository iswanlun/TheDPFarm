

package TheDPFarm.animals;

public class AnimalState {

    private state currentState;

    public enum state {
        HEALTHY, COLLECTREADY, HARVESTREADY, SICK, DEAD, EATEN
    }
    
    public state getState() {
        return currentState;
    }
    
    public void setState(state newState) {
        currentState = newState;
    }

}
