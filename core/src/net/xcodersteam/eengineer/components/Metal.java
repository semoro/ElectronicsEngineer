package net.xcodersteam.eengineer.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;

/**
 * Created by fantasyday on 19.04.2015.
 */
public class Metal extends GirdComponent {
    public static final long serialVersionUID = -7889906890679865572L;
    Cell cell;
    public Metal(Cell cell) {
        cell.put(this);
        this.cell = cell;
    }
    public Metal(){}

    @Override
    public Color getColor() {
        Color c = Color.DARK_GRAY;
        return c;
    }

    @Override
    public void render(ShapeRenderer renderer, Cell c, int w, int h) {
        renderer.setColor(getColor());
        drawStandartRect(renderer, w, h);
        if(c.layers[1] != null) {
            byte connection = (byte) (this.connection & c.layers[1].connection);
            renderer.setColor(getColor().cpy().add(c.layers[1].getColor()).mul(0.5f));
            int borderTop = -(connection & 1) * 3;
            int borderRight = -(connection >> 1 & 1) * 3;
            int borderBottom = -(connection >> 2 & 1) * 3;
            int borderLeft = -(connection >> 3 & 1) * 3;
            renderer.rect(borderLeft + 2f, borderBottom + 2f, w - borderLeft - borderRight - 4f, h - borderBottom - borderTop - 4f);
        }
    }

    @Override
    public int getLayer() {
        return 2;
    }


}
