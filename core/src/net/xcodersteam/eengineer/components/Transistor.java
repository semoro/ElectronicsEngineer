package net.xcodersteam.eengineer.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;

public class Transistor extends GirdComponent {
	public Type type;
	public boolean isOpened;
	
	public enum Type{
		PnP(Color.MAROON),NpN(Color.MAGENTA);
		public Color color;
        private Type(Color c){
            color = c;
        }
	}
    public Transistor(){}
	
	public Transistor(Type type) {
        this.type = type;
        if(type==Type.PnP){
        	isOpened=true;
        }
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
