package net.xcodersteam.eengineer.components;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;
import net.xcodersteam.eengineer.MainGameScreen;

public class Pin extends GirdComponent {
	public PinType pinType;
	String name;
    public Pin(String name){this(); this.name=name;  }
	public Pin(){

    }
	public enum PinType{
		IN,OUT,VCC;
	}
	
	@Override
	public Color getColor() {
		return Color.DARK_GRAY;
	}
	
	@Override
	public int getLayer() {
		return 2;
	}

    GlyphLayout g;
    @Override
    public void renderSecondPass(Batch b, Cell c, int x, int y, int w, int h) {
        super.renderSecondPass(b, c, x, y, w, h);

        if(g==null)
            g=MainGameScreen.font.draw(b, name, x, y);
        else
            MainGameScreen.font.draw(b, name, x-g.width/2+w/2, y+g.height/2+h/2);
    }
}
