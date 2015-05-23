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
		PnP(Color.RED),NpN(Color.YELLOW);
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
    public void render(ShapeRenderer renderer, Cell c, int w, int h) {
        super.render(renderer, c, w, h);
        if(this.type == Type.NpN)
            renderer.setColor(Color.RED);
        else
            renderer.setColor(Color.YELLOW);
        renderer.rectLine(10, 4, 10, 16, 2);
        renderer.rectLine(4, 10, 16, 10, 2);
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
