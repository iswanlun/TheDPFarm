package thedpfarm.util;

import thedpfarm.levels.Farm;

public interface TerminalStateListener {

    public void success(Farm farm);

    public void defunct(Farm farm);
    
}
