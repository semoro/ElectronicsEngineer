package net.xcodersteam.eengineer.components;

import com.badlogic.gdx.graphics.Color;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;

/**
 * Created by fantasyday on 19.04.2015.
 */
public class Metal extends GirdComponent{
    public Metal(Cell cell) {
        cell.put(this);
    }

    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }

    @Override
    public int getLayer() {
        return 2;
    }

}
