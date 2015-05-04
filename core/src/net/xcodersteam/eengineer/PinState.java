package net.xcodersteam.eengineer;

/**
 * Created by semoro on 04.05.15.
 */
public class PinState {
    enum State{
        UP,DOWN;
    }
    public State s;
    public int len;

    public PinState(State s, int len) {
        this.s = s;
        this.len = len;
    }
}
