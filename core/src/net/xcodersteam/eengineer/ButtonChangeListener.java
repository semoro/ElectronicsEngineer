package net.xcodersteam.eengineer;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by fantasyday on 24.04.2015.
 */
public class ButtonChangeListener implements EventListener {
    public TextButton button;
    public String text;
    public String alttext;

    public ButtonChangeListener(TextButton button, String text, String alttext) {
        this.button = button;
        this.text = text;
        this.alttext = alttext;
    }

    @Override
    public boolean handle(Event event) {
        if(event instanceof ChangeListener.ChangeEvent)
            if(!button.isChecked())
                button.setText(text);
            else
                button.setText(alttext);
        return true;
    }
}
