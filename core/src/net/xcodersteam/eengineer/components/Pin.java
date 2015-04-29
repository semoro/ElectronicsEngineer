package net.xcodersteam.eengineer.components;

import com.badlogic.gdx.graphics.Color;
import net.xcodersteam.eengineer.GirdComponent;

public class Pin extends GirdComponent {
	public PinType pinType;
    public Pin(){}
	
	public enum PinType{
		IN,OUT;
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
