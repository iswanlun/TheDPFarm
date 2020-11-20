package thedpfarm.util;

import thedpfarm.animals.Chicken;
import thedpfarm.animals.Cow;
import thedpfarm.animals.Hog;
import thedpfarm.animals.Sheep;
import thedpfarm.animals.AnimalState.State;
import thedpfarm.levels.FarmLevelOne;
import thedpfarm.plants.Corn;
import thedpfarm.plants.Mushrooms;
import thedpfarm.plants.Soybeans;
import thedpfarm.plants.Wheat;
import thedpfarm.util.Acre.AssetType;
import thedpfarm.world.Bank;
import thedpfarm.world.World;

import java.util.Vector;

public class FarmManager {

    private static final int farmPrice = 100000;
    private static final int acrePrice = 5000;
	private static final int defaultFarmSize = 50;

	private SimulationDialog dlg;
	private AcreDirector acreDir;


	public FarmManager(SimulationDialog dlg) {
		this.dlg = dlg;
		this.acreDir = new AcreDirector(dlg);
	}

	public void sellAcres(String string) {
		int toSell = Integer.parseInt(string);
		if (toSell <= World.getFarm().size() - 5) {
			World.getFarm().trimFarm(toSell);
			double bal = Bank.findAccount(World.getFarm().getFarmId()).makeDeposit(toSell * acrePrice);
			dlg.saleMade(bal);
		} else {
			dlg.insufficientAmount(toSell);
		}
	}

	public void buyAcres(String string) {
		int numAcres = Integer.parseInt(string);
		double amount = numAcres * World.getFarm().getTaxRate();
		if(amount < Bank.accountBalance(World.getFarm().getFarmId())) {
			acreDir.createAcres(numAcres, acrePrice);
		} else {
			dlg.insufficientFunds(amount);
		}
	}

	public void addFence(String string) {
	}

	public void addDogs(String string) {
	}

	public void switchFarm(String string) {
		World.setFarm(Integer.parseInt(string));
	}

	public void newFarm() {
        if (World.getNumFarms() == 0) {
			World.addFarm(new FarmLevelOne(defaultFarmSize));
			Bank.addAccount(World.getFarm().getFarmId());
		} else {
			if (Bank.accountBalance(World.getFarm().getFarmId()) > farmPrice + 1000) {
				double newBalance = Bank.findAccount(World.getFarm().getFarmId()).makeWithdrawl(farmPrice);
				World.addFarm(new FarmLevelOne(defaultFarmSize));
				Bank.addAccount(World.getFarm().getFarmId());
				dlg.purchaseMade(newBalance);
			} else {
				dlg.insufficientFunds(farmPrice);
			}
		}
	}

	public void auditCrops() {
		dlg.auditTable();
		World.getFarm().auditCrops(dlg);
		dlg.removeWeedsCost();
	}

	public void auditLivestock() {
		dlg.auditTable();
		World.getFarm().auditLivestock(dlg);
	}

	public void removeWeeds() {
		World.getFarm().removeWeeds();
		Bank.findAccount(World.getFarm().getFarmId()).makeWithdrawl(World.getFarm().weedCost());
		dlg.weedsRemoved();
	}

	public void plant(String type, String amount) {
		int amt = Integer.parseInt(amount);
		AssetType aType = stringToEnumConverter(type);
		double price = amt * assetPriceCalc(aType) * World.getFarm().getTaxRate();

		if(price > Bank.accountBalance(World.getFarm().getFarmId())) {
			dlg.insufficientFunds(price);
			return;
		}

		Vector<Acre> empties = World.getFarm().getEmptyAcres();
		int emptyAcres = empties.size();

		if(amt <= emptyAcres) {
			int counter = 0;
			for (Acre a : empties) {
				acreDir.constructCropAcre(a, stringToEnumConverter(type));
				counter++;
				if (counter == amt) {
					break;
				}
			}
			dlg.purchaseMade(Bank.accountBalance(World.getFarm().getFarmId()));
		} else {
			dlg.insufficientLand(amt);
		}
	}

	public void harvestAcres(String string) {
		AssetType type = stringToEnumConverter(string);
		for (Acre a : World.getFarm().getHarvestAcres(type)) {
			a.harvest(dlg);
		}
	}

	public void collectProducts(String string) {
		AssetType type = stringToEnumConverter(string);
		Vector<Acre> vec = World.getFarm().getCollectAcres(type);
		for (Acre a : vec) {
			a.collect(dlg);
		}
	}

	public void raise(String type, String amount) throws IllegalArgumentException {
		int amt = Integer.parseInt(amount);
		AssetType aType = stringToEnumConverter(type);
		double price = amt * assetPriceCalc(aType) * World.getFarm().getTaxRate();

		if(price > Bank.accountBalance(World.getFarm().getFarmId())) {
			dlg.insufficientFunds(price);
			return;
		}

		Vector<Acre> empties = World.getFarm().getEmptyAcres();
		int emptyAcres = empties.size();

		if(amt <= emptyAcres) {
			int counter = 0;
			for (Acre a : empties) {
				acreDir.constructLivestockAcre(a, stringToEnumConverter(type));
				counter++;
				if (counter == amt) {
					break;
				}
			}
			dlg.purchaseMade(Bank.accountBalance(World.getFarm().getFarmId()));
		} else {
			dlg.insufficientLand(amt);
		}
	}

	private AssetType stringToEnumConverter(String string) throws IllegalArgumentException {
		switch (string) {
			case "corn": return AssetType.CORN;
			case "mushrooms": return AssetType.MUSHROOMS;
			case "soybeans": return AssetType.SOYBEANS;
			case "wheat": return AssetType.WHEAT;
			case "chicken": return AssetType.CHICKEN;
			case "cattle": return AssetType.COW;
			case "hog": return AssetType.HOG;
			case "sheep": return AssetType.SHEEP;
			default: 
				dlg.badParameter(string);
				return null;
		}
	}

	private double assetPriceCalc(AssetType type) {
		switch (type) {
			case CORN: Corn c = new Corn(null, 0);
					   return c.getCostPerAcre();
			case MUSHROOMS: Mushrooms m = new Mushrooms(null, 0);
						return m.getCostPerAcre();
			case SOYBEANS: Soybeans s = new Soybeans(null, 0);
						return s.getCostPerAcre();
			case WHEAT: Wheat w = new Wheat(null, 0);
						return w.getCostPerAcre();
			case CHICKEN: Chicken l = new Chicken(State.HEALTHY, 0);
						return l.getBatchesPerAcre() * l.getBatchPrice();
			case COW: Cow t = new Cow(State.HEALTHY, 0);
						return t.getBatchesPerAcre() * t.getBatchPrice();
			case HOG: Hog h = new Hog(State.HEALTHY, 0);
						return h.getBatchesPerAcre() * h.getBatchPrice();
			case SHEEP: Sheep r = new Sheep(State.HEALTHY, 0);
						return r.getBatchesPerAcre() * r.getBatchPrice();
			default: 
				return 0;
		}
	}
}
