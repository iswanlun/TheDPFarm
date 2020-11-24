package thedpfarm.levels;

public class FarmLevelTwo extends FarmLevelOne {

    private double taxRateTwo = 1.164;

    public FarmLevelTwo(Farm farm) {
        super(farm);
    }
    
    @Override
    public double getTaxRate() {
        return taxRateTwo;
    }

    @Override
    public int getLevel() {
        return 2;
    }

    @Override
    public int getPredatorRisk() {
        return (int) Math.floor(
            30 + (20 * (dogCoverage / size()))
        );
    }

    @Override
    public int getWeedRisk() {
        return 50;
    }

    @Override
    public boolean addDogs(int amount) {
        dogCoverage += amount;
        return true;
    }

}
