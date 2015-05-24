package net.xcodersteam.eengineer.components;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.xcodersteam.eengineer.*;

import java.util.LinkedList;
import java.util.List;

public class Pin extends Metal {
    private static final long serialVersionUID = 9176901718169794977L;
    public PinType pinType;
	public String name;
    public Pin(String name){ this.name=name;  }
	public Pin(){
        locked=true;
        states=new PinStateList();
        testPinsState= new PinStateList();
    }
	public enum PinType{
		IN,OUT,VCC
	}
	
	@Override
	public Color getColor() {
		return Color.DARK_GRAY;
	}

    public transient PinStateList states=new PinStateList();
    public transient PinStateList testPinsState=new PinStateList();
    public boolean getState(int time){
        if(pinType==PinType.VCC) {
            return true;
        }else if(pinType==PinType.OUT) {
            return false;
        }else if(pinType==PinType.IN){
            return states.getState(time);
        }
        return false;
    }

	@Override
	public int getLayer() {
		return 2;
	}
    @Override
    public void renderSecondPass(Batch b, Cell c, int x, int y, int w, int h) {
        super.renderSecondPass(b, c, x, y, w, h);
        MainGameScreen.font.draw(b, name, x+5, y+h);
    }
}
