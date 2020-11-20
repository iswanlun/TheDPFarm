package TheDPFarm.util;

public class CollectionListener {

    private SimulationDialog simDlg;

    public CollectionListener(SimulationDialog simDlg) {
        this.simDlg = simDlg;
    }

    public void collectionEvent(CollectionEvent e) {
        simDlg.collectMsg(e.getAssetType().toString(), e.getFarmId());
    }
}
