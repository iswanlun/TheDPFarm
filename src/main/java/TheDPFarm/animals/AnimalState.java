

package TheDPFarm.animals;

public class AnimalState {

    private State currentState;

    public enum State {
        HEALTHY, COLLECTREADY, HARVESTREADY, SICK, DEAD, EATEN
    }
    
    public State getState() {
        return currentState;
    }
    
    public void setState(State newState) {
        currentState = newState;
    }

}
