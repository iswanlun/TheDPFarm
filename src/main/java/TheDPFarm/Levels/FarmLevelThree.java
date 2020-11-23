package thedpfarm.levels;

public class FarmLevelThree extends FarmLevelTwo {
    
    private double taxRateThree = 1.1875;

    public FarmLevelThree(Farm farm) {
        super(farm);
    }

    @Override
    public double getTaxRate() {
        return taxRateThree;
    }

    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    public int getPredatorRisk() {
        return (int) Math.floor(
            50 - (25 * (dogCoverage / size()))
        );
    }

    @Override
    public int getWeedRisk() {
        return (int) Math.floor(
            50 - (25 * (groundCoverage / size()))
        );
    }

    @Override
    public boolean addDogs(int amount) {
        dogCoverage += amount;
        return true;
    }

    @Override
    public boolean addGroundCover(int amount) {
        groundCoverage += amount;
        return true;
    }
}
