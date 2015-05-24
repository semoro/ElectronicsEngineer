package net.xcodersteam.eengineer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;

import java.io.File;
import java.io.IOException;

public class EEngineerGame extends Game {

    File select;
    public EEngineerGame(File select) {
        this.select=select;
    }

    @Override
	public void create(){
        try {
            this.setScreen(new MainGameScreen(select));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
