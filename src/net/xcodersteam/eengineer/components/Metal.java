package net.xcodersteam.eengineer.components;

import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;

/**
 * Created by fantasyday on 19.04.2015.
 */
public class Metal extends GirdComponent{
    byte connection;
    public Metal(Cell cell) {
        cell.put(this);
    }

    @Override
    public int getLayer() {
        return 2;
    }

}
