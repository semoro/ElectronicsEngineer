package net.xcodersteam.eengineer;

import java.util.LinkedList;

/**
 * Created by semoro on 05.05.15.
 */
public class PinStateList extends LinkedList<PinState> {

    private int len;
    public int getLen(){
        return len;
    }

    public boolean getState(int len){
        if(len>this.len)
            return false;
        int t=0;
        for(PinState ps : this){
            t+=ps.len;
            if(t>len){
                return ps.up;
            }
        }
        return false;
    }


    @Override
    public boolean add(PinState pinState) {

        len+=pinState.len;
        if(size()>0 && get(size()-1).up==pinState.up) {
            get(size()-1).len+=pinState.len;
            return true;
        }else {
            return super.add(pinState);
        }

    }
}
