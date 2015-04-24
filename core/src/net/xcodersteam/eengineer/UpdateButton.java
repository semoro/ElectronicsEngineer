package net.xcodersteam.eengineer;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by fantasyday on 24.04.2015.
 */
public class UpdateButton extends TextButton {
    public UpdateButton(String text, Skin skin) {
        super(text, skin);
    }

    public UpdateButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public UpdateButton(String text, TextButtonStyle style) {
        super(text, style);
    }
}
