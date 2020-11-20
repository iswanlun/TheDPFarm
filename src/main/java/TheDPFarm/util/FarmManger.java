package TheDPFarm.util;

import java.util.Vector;

import TheDPFarm.util.Acre.AssetType;
import TheDPFarm.levels.FarmLevelOne;
import TheDPFarm.world.Bank;
import TheDPFarm.world.World;

public class FarmManger {

    private final int farmPrice = 100000;
    private final int acrePrice = 2000;
	private final int defaultFarmSize = 50;

	private SimulationDialog dlg;
	private AcreDirector acreDir;
	private HarvestListener harvestListener;
	private CollectionListener collectionListener;


	public FarmManger(SimulationDialog dlg) {
		this.dlg = dlg;
		harvestListener = new HarvestListener(dlg);
		collectionListener = new CollectionListener(dlg);
		this.acreDir = new AcreDirector(harvestListener, collectionListener);
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
	}

	public void plant(String type, String amount) throws IllegalArgumentException {
		int amt = Integer.parseInt(amount);
		double price = amt * acrePrice * World.getFarm().getTaxRate();

		if(price > Bank.accountBalance(World.getFarm().getFarmId())) {
			dlg.insufficientFunds(price);
			return;
		}

		Vector<Acre> empties = World.getFarm().getEmptyAcres();
		int emptyAcres = empties.size();

		if(amt <= emptyAcres) {
			for (Acre a : empties) {
				acreDir.constructCropAcre(a, stringToEnumConverter(type));
			}
			dlg.purchaseMade(Bank.accountBalance(World.getFarm().getFarmId()));
		} else {
			dlg.insufficientLand(amt);
		}
	}

	public void harvestCrops(String string) {
	}

	public void removeWeeds() {
	}

	public void auditLivestock() {
	}

	public void raise(String type, String amount) throws IllegalArgumentException {
		int amt = Integer.parseInt(amount);
		double price = amt * acrePrice * World.getFarm().getTaxRate();

		if(price > Bank.accountBalance(World.getFarm().getFarmId())) {
			dlg.insufficientFunds(price);
			return;
		}

		Vector<Acre> empties = World.getFarm().getEmptyAcres();
		int emptyAcres = empties.size();

		if(amt <= emptyAcres) {
			for (Acre a : empties) {
				acreDir.constructLivestockAcre(a, stringToEnumConverter(type));
			}
			dlg.purchaseMade(Bank.accountBalance(World.getFarm().getFarmId()));
		} else {
			dlg.insufficientLand(amt);
		}
	}

	public void harvestLivestock(String string) {
	}

	public void collectProducts(String string) {
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
				throw new IllegalArgumentException("Invalid type found.");
		}
	}
}
