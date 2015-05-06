package net.xcodersteam.eengineer.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;

/**
 * Created by fantasyday on 19.04.2015.
 */
public class Silicon extends GirdComponent{
    public Type type;
    public enum Type{
        N(Color.RED),P(Color.YELLOW);
        public Color color;
        Type(Color c){
            color = c;
        }
    }
    public Silicon(Type type) {
        this.type = type;
    }
    public Silicon(){}

    @Override
    public Color getColor() {
        return type.color;
    }

    @Override
    public int getLayer() {
        return 1;
    }




}
