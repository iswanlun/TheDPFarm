/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TheDPFarm;

import org.junit.Test;

import thedpfarm.util.FarmManager;
import thedpfarm.util.SimulationDialog;
import thedpfarm.world.Bank;
import thedpfarm.world.World;

import static org.junit.Assert.*;

import org.junit.Before;

public class AppTest {

    SimulationDialog dlg;
    FarmManager farmManager;

    @Before public void setupTesting() {
        dlg = new SimulationDialog();
        farmManager = new FarmManager(dlg);
    }

    @Test public void farmLevelsTest() {

        farmManager.newFarm();
        
        Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(100001);
        int level = 2;
        assertEquals(level, World.getFarm().getLevel());

        Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(100001);
        level = 3;
        assertEquals(level, World.getFarm().getLevel());

        farmManager.expellFarm(World.getFarm().getFarmId());
    }

    @Test public void farmIdTest() {

        farmManager.newFarm();

        int expectedId = 21111927;
        World.getFarm().setId(expectedId);

        assertEquals(expectedId, World.getFarm().getFarmId());

        farmManager.expellFarm(World.getFarm().getFarmId());
    }

    @Test public void farmSizeTest() {
        
        farmManager.newFarm();

        int expectedDefault = 50;

        assertEquals(expectedDefault, World.getFarm().size());

        farmManager.expellFarm(World.getFarm().getFarmId());
    }

    @Test public void expandingTheFarmTest() {
        
        farmManager.newFarm();

        int firstExpansion = 100;

        farmManager.buyAcres("50");

        assertEquals(firstExpansion, World.getFarm().size());

        Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(300000);

        int secondExpansion = 150;

        farmManager.buyAcres("50");

        assertEquals(secondExpansion, World.getFarm().size());

        farmManager.expellFarm(World.getFarm().getFarmId());
    }

    @Test public void sellingAcresTest() {

        farmManager.newFarm();

        farmManager.buyAcres("50");

        farmManager.sellAcres("60");

        int expectedSize = 40;
        double expectedBalance = 319250; //Tax included

        assertEquals(expectedSize, World.getFarm().size());
        assertEquals((Double) expectedBalance, (Double) Bank.accountBalance(World.getFarm().getFarmId()));
        
        farmManager.expellFarm(World.getFarm().getFarmId());
    }

    @Test public void specificAcreTest() {
        
    }

    @Test public void taxRateTest() {
        farmManager.newFarm();

        double taxRate = 1.123;
        assertEquals((Double) taxRate, (Double) World.getFarm().getTaxRate());
        
        Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(100001);

        taxRate = 1.164;
        assertEquals((Double) taxRate, (Double) World.getFarm().getTaxRate());

        Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(100001);

        taxRate = 1.1875;
        assertEquals((Double) taxRate, (Double) World.getFarm().getTaxRate());

        farmManager.expellFarm(World.getFarm().getFarmId());
    }

    @Test public void emptyAcreTest() {

        farmManager.newFarm();

        int expectedEmpty = 50;

        int actualEmpty = World.getFarm().getEmptyAcres().size();

        assertEquals(expectedEmpty, actualEmpty);
        
        farmManager.expellFarm(World.getFarm().getFarmId());
    }

    @Test public void harvestAcresTest() {
        
    }

    @Test public void collectAcresTest() {
        
    }

    @Test public void cropAndLivestockAcresTest() {
        
    }

    @Test public void weedCostTest() {
        
    }

    @Test public void Test() {
        
    }
}
