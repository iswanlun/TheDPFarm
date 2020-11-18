package TheDPFarm.plants;

public abstract class PlantState {

    private state currentState;

    enum state {
        Healthy, Sick, Seed, Seedling, ReadyForHarvest, Dead
    }

    public state getState() {
        return currentState;
    }

    public void setState(state newState) {
        this.currentState = newState;
    }

    public void advanceState() {
        switch (currentState) {
            case Healthy: 
                currentState = state.ReadyForHarvest;
                break;
            case Sick:
                currentState = state.Dead;
                break;
            case Seed:
                currentState = state.Seedling;
                break;
            case Seedling:
                currentState = state.Healthy;
            case ReadyForHarvest:
                currentState = state.Dead;
            default:
                currentState = state.Dead;
                break;
        }
    }
}
