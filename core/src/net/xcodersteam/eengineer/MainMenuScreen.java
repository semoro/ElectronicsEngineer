package net.xcodersteam.eengineer;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by semoro on 04.05.15.
 */
public class MainMenuScreen implements Screen {
    ShapeRenderer shapeRenderer;

    public MainMenuScreen() {
        shapeRenderer=new ShapeRenderer();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.valueOf("00FF00"));
        shapeRenderer.rect(10,10,10,10);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
