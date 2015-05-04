package net.xcodersteam.eengineer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.io.Serializable;
import net.xcodersteam.eengineer.components.Silicon;
import net.xcodersteam.eengineer.components.Transistor;
import net.xcodersteam.eengineer.components.Transistor.Type;

/**
 * Created by fantasyday on 19.04.2015.
 */
public abstract class GirdComponent implements Serializable{
    public byte connection;
    public boolean isPower;
    public abstract Color getColor();
    public abstract int getLayer();
    public void render(ShapeRenderer renderer,Cell c,int w,int h){
        renderer.setColor(getColor());
        drawStandartRect(renderer,w,h);
    }
    public boolean locked=false;

    public GirdComponent lock(boolean b){ this.locked=b; return this;}
    public void renderSecondPass(Batch b,Cell c,int x, int y,int w,int h){}
    public void drawStandartRect(ShapeRenderer r,int w, int h){
        int borderTop = -(connection & 1) * 3;
        int borderRight = -(connection >> 1 & 1) * 3;
        int borderBottom = -(connection >> 2 & 1) * 3;
        int borderLeft = -(connection >> 3 & 1) * 3;
        r.rect(borderLeft + 2f, borderBottom + 2f, w - borderLeft - borderRight - 4f, h - borderBottom - borderTop - 4f);

    }
    
    public void powerOn(Cell[][] cells, int x, int y){
    	powerOn(cells, x, y, true);
    }
    
    public void powerOn(Cell[][] cells, int x, int y, boolean via){
    	if(isPower){
    		return;
    	}
    	if(cells[x][y].via&&via){
    		if(getLayer()==2){
    			cells[x][y].layers[1].powerOn(cells, x, y, false);
    		}else{
    			cells[x][y].layers[2].powerOn(cells, x, y, false);
    		}
    	}
    	if(cells[x][y].layers[getLayer()] instanceof Transistor){
    		Transistor t=((Transistor)cells[x][y].layers[getLayer()]);
    		if(t.type==Type.PnP){
    			if(getIsSiliconPowerOn(cells, x, y, Silicon.Type.N)){
    				setSiliconPowerOn(cells, x, y, Silicon.Type.N);
    			}
    			if(t.isOpened){
    				if(getIsSiliconPowerOn(cells, x, y, Silicon.Type.P)){
        				setSiliconPowerOn(cells, x, y, Silicon.Type.P);
        			}
    			}
    		}else{
    			if(getIsSiliconPowerOn(cells, x, y, Silicon.Type.P)){
    				setSiliconPowerOn(cells, x, y, Silicon.Type.P);
    			}
    			if(t.isOpened){
    				if(getIsSiliconPowerOn(cells, x, y, Silicon.Type.N)){
        				setSiliconPowerOn(cells, x, y, Silicon.Type.N);
        			}
    			}
    		}
    	}
    	isPower=true;
    	if((connection&0b1)>0&&cells[x][y+1]!=null){
    		cells[x][y+1].layers[getLayer()].powerOn(cells, x, y+1);
		}
		if((connection&0b10)>0&&cells[x+1][y]!=null){
			cells[x+1][y].layers[getLayer()].powerOn(cells, x+1, y);
		}
		if((connection&0b100)>0&&cells[x][y-1]!=null){
			cells[x][y-1].layers[getLayer()].powerOn(cells, x, y-1);
		}
		if((connection&0b1000)>0&&cells[x-1][y]!=null){
			cells[x-1][y].layers[getLayer()].powerOn(cells, x-1, y);
		}
    }
    
    public boolean getIsSiliconPowerOn(Cell[][] cells, int x, int y,Silicon.Type type){
    	if((connection&0b1)>0&&cells[x][y+1]!=null){
			if(((Silicon)cells[x][y+1].layers[getLayer()]).type==type&&((Silicon)cells[x][y+1].layers[getLayer()]).isPower){
				return true;
			}
		}
		if((connection&0b10)>0&&cells[x+1][y]!=null){
			if(((Silicon)cells[x+1][y].layers[getLayer()]).type==type&&((Silicon)cells[x+1][y].layers[getLayer()]).isPower){
				return true;
			}
		}
		if((connection&0b100)>0&&cells[x][y-1]!=null){
			if(((Silicon)cells[x][y-1].layers[getLayer()]).type==type&&((Silicon)cells[x][y-1].layers[getLayer()]).isPower){
				return true;
			}
		}
		if((connection&0b1000)>0&&cells[x-1][y]!=null){
			if(((Silicon)cells[x-1][y].layers[getLayer()]).type==type&&((Silicon)cells[x-1][y].layers[getLayer()]).isPower){
				return true;
			}
		}
		return false;
    }
    
    public void setSiliconPowerOn(Cell[][] cells, int x, int y,Silicon.Type type){
    	if((connection&0b1)>0&&cells[x][y+1]!=null){
			if(((Silicon)cells[x][y+1].layers[getLayer()]).type==type){
				cells[x][y+1].layers[getLayer()].powerOn(cells, x, y);
			}
		}
		if((connection&0b10)>0&&cells[x+1][y]!=null){
			if(((Silicon)cells[x+1][y].layers[getLayer()]).type==type){
				cells[x+1][y].layers[getLayer()].powerOn(cells, x, y);
			}
		}
		if((connection&0b100)>0&&cells[x][y-1]!=null){
			if(((Silicon)cells[x][y-1].layers[getLayer()]).type==type){
				cells[x][y-1].layers[getLayer()].powerOn(cells, x, y);
			}
		}
		if((connection&0b1000)>0&&cells[x-1][y]!=null){
			if(((Silicon)cells[x-1][y].layers[getLayer()]).type==type){
				cells[x-1][y].layers[getLayer()].powerOn(cells, x, y);
			}
		}
    }
}
