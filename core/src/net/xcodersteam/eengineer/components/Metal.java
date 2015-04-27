package net.xcodersteam.eengineer.components;

import com.badlogic.gdx.graphics.Color;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;

/**
 * Created by fantasyday on 19.04.2015.
 */
public class Metal extends GirdComponent{
    Cell cell;
    public Metal(Cell cell) {
        cell.put(this);
        this.cell = cell;
    }

    @Override
    public Color getColor() {
        Color c = Color.DARK_GRAY;
        if(cell.layers[1] != null)
            c = c.cpy().add(cell.layers[1].getColor()).mul(0.5f);
        return c;
    }

    @Override
    public int getLayer() {
        return 2;
    }

}
