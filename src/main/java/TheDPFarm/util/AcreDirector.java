package thedpfarm.util;

import thedpfarm.util.Acre.AssetType;
import thedpfarm.util.Acre.UsageType;
import thedpfarm.world.Bank;
import thedpfarm.world.World;

public class AcreDirector {

    private AcreBuilder cropBuilder;
    private AcreBuilder livestockBuilder;

    public AcreDirector(SimulationDialog dlg) {
        cropBuilder = new CropBuilder(dlg);
        livestockBuilder = new Livestockbuilder(dlg);
    }

    public void constructCropAcre(Acre acre, AssetType aType) {
        try {
            cropBuilder.buildAcre(acre, aType);
        } catch (FinanceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void constructLivestockAcre(Acre acre, AssetType aType) {
        try {
            livestockBuilder.buildAcre(acre, aType);
        } catch (FinanceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createAcres(int acres, double price) {
        Bank.findAccount(World.getFarm().getFarmId()).makeWithdrawl(price);
        for (int i = 0; i < price; i++) {
            World.getFarm().expandFarm(new Acre(UsageType.EMPTY));
        }
    }
}
