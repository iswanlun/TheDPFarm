

package TheDPFarm.animals;

public class AnimalState {

    private state currentState;

    public enum state {
        Healthy, Sick, HarvestReady, SlaughterReady
    }
    
    public state getState() {
        return currentState;
    }
    
    public void setState(state newState) {
        currentState = newState;
    }
}
