package net.xcodersteam.eengineer;

/**
 * Created by fantasyday on 18.04.2015.
 */
public class Cell {
    public static final int maxLayers = 3;
    public boolean via;
    public GirdComponent[] layers = new GirdComponent[maxLayers];
    public void put(GirdComponent component){
        layers[component.getLayer()] = component;
    }
}
