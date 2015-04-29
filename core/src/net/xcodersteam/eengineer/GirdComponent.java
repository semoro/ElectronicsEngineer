package net.xcodersteam.eengineer;

import com.badlogic.gdx.graphics.Color;

import java.io.Serializable;

/**
 * Created by fantasyday on 19.04.2015.
 */
public abstract class GirdComponent implements Serializable{
    public byte connection;
    public abstract Color getColor();
    public abstract int getLayer();

}
