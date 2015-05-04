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
import net.xcodersteam.eengineer.PinState;

import java.util.LinkedList;
import java.util.List;

public class Pin extends GirdComponent {
	public PinType pinType;
	String name;
    public Pin(String name){ this.name=name;  }
	public Pin(){
        locked=true;
    }
	public enum PinType{
		IN,OUT,VCC;
	}
	
	@Override
	public Color getColor() {
		return Color.DARK_GRAY;
	}

    public List<PinState> states=new LinkedList<>();

    public boolean getState(int time){
        if(pinType==PinType.VCC) {
            return true;
        }else if(pinType==PinType.OUT) {
            return false;
        }else if(pinType==PinType.IN){
            int ok = 0;
            for (PinState state : states) {
                ok += state.len;
                if (ok > time)
                    return state.up;
            }
            return false;
        }
        return false;
    }

	@Override
	public int getLayer() {
		return 2;
	}
    transient GlyphLayout g;
    @Override
    public void renderSecondPass(Batch b, Cell c, int x, int y, int w, int h) {
        super.renderSecondPass(b, c, x, y, w, h);

        if(g==null)
            g=MainGameScreen.font.draw(b, name, x+5, y);
        else
            MainGameScreen.font.draw(b, name, x+5, y+g.height/2+h/2);
    }
}
