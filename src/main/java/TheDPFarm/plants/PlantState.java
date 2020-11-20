package thedpfarm.plants;

public class PlantState {

    private State currentState;

    public enum State {
        SEED, SEEDLING, HEALTHY, HARVESTREADY, SICK, DEAD, WEEDINFESTED
    }

    public PlantState(State startState) {
        currentState = startState;
    }

    public State getState() {
        return currentState;
    }

    public void setState(State newState) {
        this.currentState = newState;
    }

    public void advanceState() {
        switch (currentState) {
            case HEALTHY: 
                currentState = State.HARVESTREADY;
                break;
            case SICK:
                currentState = State.DEAD;
                break;
            case SEED:
                currentState = State.SEEDLING;
                break;
            case SEEDLING:
                currentState = State.HEALTHY;
                break;
            case HARVESTREADY:
                currentState = State.DEAD;
                break;
            case WEEDINFESTED:
                currentState = State.DEAD;
                break;
            default:
                break;
        }
    }
}
