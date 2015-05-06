package net.xcodersteam.eengineer;

/**
 * Created by semoro on 04.05.15.
 */
public class PinState {
    public boolean up;
    public int len;

    public PinState( int len) {
        this.len = len;
    }

    public PinState(boolean up, int len) {
        this.up = up;
        this.len = len;
    }
}
