package net.xcodersteam.eengineer;

import java.io.Serializable;

/**
 * Created by fantasyday on 18.04.2015.
 */
public class Cell implements Serializable{
    public static final int maxLayers = 3;
    public boolean via;
    public GirdComponent[] layers = new GirdComponent[maxLayers];
    public void put(GirdComponent component){
        layers[component.getLayer()] = component;
    }
}
