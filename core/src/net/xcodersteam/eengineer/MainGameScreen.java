package net.xcodersteam.eengineer;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import net.xcodersteam.eengineer.components.Metal;
import net.xcodersteam.eengineer.components.Silicon;

/**
 * Created by fantasyday on 21.04.2015.
 */
public class MainGameScreen implements Screen{
    @Override
    public void show() {

    }
    ConstructionManager cm;
    SpriteBatch batch;
    //Texture img;
    ShapeRenderer renderer;
    TextButton buttonP;
    TextButton buttonC;
    TextButton buttonM;
    TextButton viabutton;
    Stage stage;
    public  MainGameScreen () {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        //img = new Texture("badlogic.jpg");
        cm = new ConstructionManager(16, 16);
        stage = new Stage();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("bender.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        buttonP = new TextButton(new String(), style);
        buttonP.setText("Silicon P");
        buttonP.setX(Gdx.graphics.getWidth() - cellSize * 3);
        buttonP.setY(Gdx.graphics.getHeight() - cellSize * 3);
        buttonP.setWidth(cellSize * 3);
        buttonP.setHeight(cellSize * 3);
        buttonC = new TextButton(new String(), style);
        buttonC.setText("Silicon S");
        buttonC.setX(Gdx.graphics.getWidth() - cellSize * 3);
        buttonC.setY(Gdx.graphics.getHeight() - cellSize * 6);
        buttonC.setWidth(cellSize * 3);
        buttonC.setHeight(cellSize * 3);
        buttonM = new TextButton(new String(), style);
        buttonM.setText("Metal");
        buttonM.setX(Gdx.graphics.getWidth() - cellSize * 3);
        buttonM.setY(Gdx.graphics.getHeight() - cellSize * 9);
        buttonM.setWidth(cellSize * 3);
        buttonM.setHeight(cellSize * 3);
        viabutton = new TextButton(new String(), style);
        viabutton.setText("Add via");
        viabutton.setX(Gdx.graphics.getWidth() - cellSize * 3);
        viabutton.setY(Gdx.graphics.getHeight() - cellSize * 12);
        viabutton.setWidth(cellSize * 3);
        viabutton.setHeight(cellSize * 3);
        stage.addActor(buttonP);
        stage.addActor(buttonC);
        stage.addActor(buttonM);
        stage.addActor(viabutton);
        buttonP.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (event.toString().equals("touchDown")) {
                    if (buttonP.getText().toString().equals("P - type"))
                        buttonP.setText("Silicon P");
                    else
                        buttonP.setText("P - type");
                }
                return true;
            }
        });
        buttonC.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (event.toString().equals("touchDown")){
                    if (buttonC.getText().toString().equals("S - type"))
                        buttonC.setText("Silicon S");
                    else
                        buttonC.setText("S - type");
                }
                return true;
            }
        });
        buttonM.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (event.toString().equals("touchDown")){
                    if (buttonM.getText().toString().equals("Set metal"))
                        buttonM.setText("Metal");
                    else
                        buttonM.setText("Set metal");
                }
                return true;
            }
        });
        viabutton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (event.toString().equals("touchDown")){
                    System.out.println("Kek");
                    if (viabutton.getText().toString().equals("Via"))
                        viabutton.setText("Add via");
                    else
                        viabutton.setText("Via");
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            public Cell getCellAt(int screenX, int screenY) {
                return cm.getCell((screenX - 100) / (cellSize + 1), (Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1));
            }
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int b) {
                if(buttonP.getText().toString().equals("P - type")){
                    switch(b) {
                        case Input.Buttons.LEFT: Cell cell = getCellAt(screenX, screenY);
                            if (cell != null)
                                new Silicon(cell, Silicon.Type.P);
                            break;
                        case Input.Buttons.RIGHT:
                            try{
                                cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].layers[1] = null;
                                if(cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].via)
                                    cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].via = false;
                            } catch (Exception e) {

                            }
                    }
                }
                if(buttonC.getText().toString().equals("S - type")){
                    switch (b) {
                        case Input.Buttons.LEFT: Cell cell = getCellAt(screenX, screenY);
                            if (cell != null)
                                new Silicon(cell, Silicon.Type.N);
                            break;
                        case Input.Buttons.RIGHT:
                            try{
                                cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].layers[1] = null;
                                if(cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].via)
                                    cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].via = false;
                            } catch (Exception e){

                            }
                    }
                }
                if(buttonM.getText().toString().equals("Set metal")){
                    switch (b) {
                        case Input.Buttons.LEFT: Cell cell = getCellAt(screenX, screenY);
                            if(cell != null)
                                new Metal(cell);
                            break;
                        case Input.Buttons.RIGHT:
                            try{
                                cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].layers[2] = null;
                            } catch (Exception e){

                            }
                    }
                }
                if(viabutton.getText().toString().equals("Via")){
                    switch (b) {
                        case Input.Buttons.LEFT: Cell cell = getCellAt(screenX, screenY);
                            if (cell != null && ((cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].layers[1] != null) || (cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].layers[2] != null)))
                                cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].via = true;
                            break;
                        case Input.Buttons.RIGHT:
                            try{
                                cm.construction[(screenX - 100) / (cellSize + 1)][(Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1)].via = false;
                            } catch (Exception e){

                            }
                    }
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        }));
    }

    private final int cellSize = 20;
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.begin();
        //batch.draw(img, 0, 0);
        //batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setAutoShapeType(true);
        renderConstruction(100, 100);
        renderGui();
        renderer.end();
        stage.act(delta);
        stage.draw();
    }
    public void renderGui(){
        renderer.setColor(Color.RED);
        renderer.rect(Gdx.graphics.getWidth() - cellSize * 3, Gdx.graphics.getHeight() - cellSize * 3, cellSize * 3, cellSize * 3);
        renderer.setColor(Color.YELLOW);
        renderer.rect(Gdx.graphics.getWidth() - cellSize * 3, Gdx.graphics.getHeight() - cellSize * 6 - 1, cellSize * 3, cellSize * 3);
        renderer.setColor(Color.DARK_GRAY);
        renderer.rect(Gdx.graphics.getWidth() - cellSize * 3, Gdx.graphics.getHeight() - cellSize * 9 - 2, cellSize * 3, cellSize * 3);
        renderer.setColor(Color.NAVY);
        renderer.rect(Gdx.graphics.getWidth() - cellSize * 3, Gdx.graphics.getHeight() - cellSize * 12 - 3, cellSize * 3, cellSize * 3);
    }
    public void renderConstruction(float sx, float sy){
        renderer.translate(sx, sy, 0);
        for(int x = 0; x < cm.width; x++){
            for(int y = 0; y < cm.height; y++){
                renderer.setColor(Color.valueOf("CCCCCC"));
                renderer.rect(x * (cellSize + 1), y * (cellSize + 1), cellSize, cellSize);
                if(cm.construction[x][y] != null) {
                    for (GirdComponent component : cm.construction[x][y].layers) {
                        if (component == null)
                            continue;
                        renderer.setColor(component.getColor());
                        int borderTop = -(component.connection & 1) * 3;
                        int borderRight = -(component.connection >> 1 & 1) * 3;
                        int borderBottom = -(component.connection >> 2 & 1) * 3;
                        int borderLeft = -(component.connection >> 3 & 1) * 3;
                        renderer.rect(x * (cellSize + 1) + borderLeft + 2f, y * (cellSize + 1) + borderBottom + 2f, cellSize - borderLeft - borderRight - 4f, cellSize - borderBottom - borderTop - 4f);
                    }
                    if(cm.construction[x][y].via){
                        renderer.setColor(Color.BLACK);
                        renderer.set(ShapeRenderer.ShapeType.Line);
                        renderer.circle(x * (cellSize + 1) + cellSize / 2, y * (cellSize + 1) + cellSize / 2, 7f);
                        renderer.set(ShapeRenderer.ShapeType.Filled);
                    }
                }
            }
        }
        renderer.translate(-sx, -sy, 0);
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
