package net.xcodersteam.eengineer;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by fantasyday on 19.04.2015.
 */
public abstract class GirdComponent {
    public byte connection;
    public abstract Color getColor();
    public abstract int getLayer();

}
