package TheDPFarm.plants;

public class PlantState {

    private state currentState;

    public enum state {
        SEED, SEEDLING, HEALTHY, HARVESTREADY, SICK, DEAD, WEEDINFESTED
    }

    public PlantState(state startState) {
        currentState = startState;
    }

    public state getState() {
        return currentState;
    }

    public void setState(state newState) {
        this.currentState = newState;
    }

    public void advanceState() {
        switch (currentState) {
            case HEALTHY: 
                currentState = state.HARVESTREADY;
                break;
            case SICK:
                currentState = state.DEAD;
                break;
            case SEED:
                currentState = state.SEEDLING;
                break;
            case SEEDLING:
                currentState = state.HEALTHY;
                break;
            case HARVESTREADY:
                currentState = state.DEAD;
                break;
            case WEEDINFESTED:
                currentState = state.DEAD;
                break;
            default:
                break;
        }
    }
}
