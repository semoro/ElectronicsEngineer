package net.xcodersteam.eengineer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.io.Serializable;

/**
 * Created by fantasyday on 19.04.2015.
 */
public abstract class GirdComponent implements Serializable{
    public byte connection;
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
}
