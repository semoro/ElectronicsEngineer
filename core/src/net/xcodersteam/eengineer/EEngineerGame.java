package net.xcodersteam.eengineer;

import com.badlogic.gdx.Game;

import java.io.IOException;

public class EEngineerGame extends Game {
    public EEngineerGame() {
    }

    @Override
	public void create(){
        try {
            this.setScreen(new MainGameScreen());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
