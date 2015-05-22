package net.xcodersteam.eengineer.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;

public class Transistor extends GirdComponent {
	private static final long serialVersionUID = -4534383815643503088L;
	public Type type;
	public boolean isOpened;
	
	public enum Type{
		PnP(Color.YELLOW.cpy().mul(0.3f).add(Color.RED.cpy().mul(0.5f))),NpN(Color.RED.cpy().mul(0.3f).add(Color.YELLOW.cpy().mul(0.5f)));
		public Color color;
        Type(Color c){
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
