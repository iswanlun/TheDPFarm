package thedpfarm.util;

/**
 * This harvest listener is added to every harvestable entity.
 */

public class HarvestListener {

    private SimulationDialog simDlg;

    public HarvestListener(SimulationDialog simDlg) {
        this.simDlg = simDlg;
    }

    public void harvestEvent(HarvestEvent e) {
        simDlg.harvestMsg(e.getAssetType().toString(), e.getFarmId());
    }
}
