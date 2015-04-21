package net.xcodersteam.eengineer.components;

import com.badlogic.gdx.graphics.Color;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;

/**
 * Created by fantasyday on 19.04.2015.
 */
public class Silicon extends GirdComponent{
    public Type type;
    public enum Type{
        N(Color.YELLOW),P(Color.RED),NP(Color.MAGENTA),PN(Color.MAROON);
        public Color color;
        private Type(Color c){
            color = c;
        }
        public Type combine(Type type1){
            return Type.valueOf(this.name() + type1.name());
        }
    }
    public Silicon(Cell cell, Type type) {
        Silicon sc = (Silicon) cell.layers[getLayer()];
        if(sc != null && sc.type != null){
            type = sc.type.combine(type);
        }
        cell.put(this);
        this.type = type;
    }

    @Override
    public Color getColor() {
        return type.color;
    }

    @Override
    public int getLayer() {
        return 1;
    }
}
