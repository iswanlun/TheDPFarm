package thedpfarm.util;

import java.util.Vector;
import thedpfarm.levels.Farm;

public class TerminalStateManager {

    private Vector<TerminalStateListener> listeners;

    public TerminalStateManager() {
        listeners = new Vector<>();
    }
    
    public void addListeners(TerminalStateListener listener) {
        listeners.add(listener);
    }

    /**
     * If a success defunct occures, notify listeners.
     * @param farm Farm location of event.
     */
    public void notifyDefunct(Farm farm) {
        for (TerminalStateListener l : listeners) {
            l.defunct(farm);
        }
    }

    /**
     * If a success condition occures, notify listeners.
     * @param farm Farm location of event.
     */
    public void notifySuccess(Farm farm) {
        for (TerminalStateListener l : listeners) {
            l.success(farm);
        }
    }
}
