package net.xcodersteam.eengineer.components;

import com.badlogic.gdx.graphics.Color;
import net.xcodersteam.eengineer.GirdComponent;

public class Transistor extends GirdComponent {
	Type type;
	public enum Type{
		PnP(Color.MAROON),NpN(Color.MAGENTA);
		public Color color;
        private Type(Color c){
            color = c;
        }
	}
	
	public Transistor(Type type) {
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
