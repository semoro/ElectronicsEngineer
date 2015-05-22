package net.xcodersteam.eengineer;

import java.io.Serializable;

/**
 * Created by fantasyday on 18.04.2015.
 */
public class Cell implements Serializable{
    public static final int maxLayers = 3;
    private static final long serialVersionUID = 2353247239130628787L;
    public boolean via;
    public GirdComponent[] layers = new GirdComponent[maxLayers];
    public void put(GirdComponent component){
        layers[component.getLayer()] = component;
    }
}
