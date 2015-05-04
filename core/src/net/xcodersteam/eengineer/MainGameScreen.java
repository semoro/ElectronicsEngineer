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
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import net.xcodersteam.eengineer.components.Pin;

import java.io.File;
import java.io.IOException;

/**
 * Created by fantasyday on 21.04.2015.
 */

public class MainGameScreen implements Screen {
    @Override
    public void show() {

    }

    public static MainGameScreen instance;
    ConstructionManager cm;
    public SpriteBatch stageBatch;
    ShapeRenderer renderer;
    TextButton buttonP;
    TextButton buttonC;
    TextButton buttonM;
    TextButton viabutton;
    TextButton close;
    Stage stage;
    ButtonGroup group;
    public static BitmapFont font;
    public static int x = 100;
    public static int y = 100;

    public void setButton(TextButton button, float x) {
        group.add(button);
        button.setWidth(cellSize * 3);
        button.setHeight(cellSize * 3);
        button.setX(Gdx.graphics.getWidth() - cellSize * 3);
        button.setY(Gdx.graphics.getHeight() - cellSize * x - 35);
        stage.addActor(button);
    }

    public MainGameScreen() throws IOException, ClassNotFoundException {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("bender.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        font = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        stageBatch = new SpriteBatch();
        renderer = new ShapeRenderer();
        File save = new File("current.sv");
        if (save.exists()) {
            cm = new ConstructionManager(save);
        } else {
            cm = new ConstructionManager(16, 16);
        }
        cm.getCell(2,2).put(new Pin("VCC"));
        stage = new Stage();

        style.font = font;
        group = new ButtonGroup();
        buttonP = new TextButton(new String(), style);
        buttonP.setText("Silicon P");
        buttonP.setUserObject(new PSiliconTool());
        setButton(buttonP, 3);
        buttonC = new TextButton(new String(), style);
        buttonC.setText("Silicon N");
        buttonC.setUserObject(new NSiliconTool());
        setButton(buttonC, 6);
        buttonM = new TextButton(new String(), style);
        buttonM.setText("Metal");
        buttonM.setUserObject(new MetalTool());
        setButton(buttonM, 9);
        viabutton = new TextButton(new String(), style);
        viabutton.setText("Add via");
        viabutton.setUserObject(new ViaTool());
        setButton(viabutton, 12);
        close = new TextButton(new String(), style);
        close.setText("");
        close.setWidth(35);
        close.setHeight(35);
        close.setX(Gdx.graphics.getWidth() - 35);
        close.setY(Gdx.graphics.getHeight() - 35);
        stage.addActor(close);
        group.uncheckAll();
        buttonP.addListener(new ButtonChangeListener(buttonP, "Silicon P", "P - type"));
        buttonC.addListener(new ButtonChangeListener(buttonC, "Silicon N", "N - type"));
        buttonM.addListener(new ButtonChangeListener(buttonM, "Metal", "Set metal"));
        viabutton.addListener(new ButtonChangeListener(viabutton, "Add via", "Via"));
        close.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (event.toString().equals("touchDown")) {
                    try {
                        cm.save(new File("current.sv"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Gdx.app.exit();
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new MainInputProcessor()));
        instance = this;
    }

    private final int cellSize = 20;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(235f / 255f, 218f / 255f, 159f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setAutoShapeType(true);
        renderConstruction(x, y);
        renderGui();
        renderer.end();
        stageBatch.begin();
        renderSecondPass(x,y);

        stageBatch.end();
        stage.act(delta);
        stage.draw();
    }

    public void renderGui() {
        int val = 1;
        renderer.setColor(Color.valueOf("9cf964"));
        renderer.rect(0, Gdx.graphics.getHeight() - 35, Gdx.graphics.getWidth(), 35);
        renderer.setColor(Color.RED);
        renderer.rect(Gdx.graphics.getWidth() - 35, Gdx.graphics.getHeight() - 35, 35, 35);
        renderer.setColor(Color.BLACK);
        renderer.rectLine(Gdx.graphics.getWidth() - 30, Gdx.graphics.getHeight() - 30, Gdx.graphics.getWidth() - 5, Gdx.graphics.getHeight() - 5, 2f);
        renderer.rectLine(Gdx.graphics.getWidth() - 30, Gdx.graphics.getHeight() - 5, Gdx.graphics.getWidth() - 5, Gdx.graphics.getHeight() - 30, 2f);
        renderer.setColor(Color.BLACK);
        renderer.rect(Gdx.graphics.getWidth() - cellSize * 3 - 1, Gdx.graphics.getHeight() - 35 - val + 1, cellSize * 3 + 1, -cellSize * 12 - 5);
        renderer.setColor(Color.RED);
        renderer.rect(Gdx.graphics.getWidth() - cellSize * 3, Gdx.graphics.getHeight() - cellSize * 3 * val - 35 - val++, cellSize * 3, cellSize * 3);
        renderer.setColor(Color.YELLOW);
        renderer.rect(Gdx.graphics.getWidth() - cellSize * 3, Gdx.graphics.getHeight() - cellSize * 3 * val - 35 - val++, cellSize * 3, cellSize * 3);
        renderer.setColor(Color.DARK_GRAY);
        renderer.rect(Gdx.graphics.getWidth() - cellSize * 3, Gdx.graphics.getHeight() - cellSize * 3 * val - 35 - val++, cellSize * 3, cellSize * 3);
        renderer.setColor(Color.NAVY);
        renderer.rect(Gdx.graphics.getWidth() - cellSize * 3, Gdx.graphics.getHeight() - cellSize * 3 * val - 35 - val++, cellSize * 3, cellSize * 3);
    }

    public void renderSecondPass(int sx, int sy) {

        for (int x = 0; x < cm.width; x++) {
            for (int y = 0; y < cm.height; y++) {

                if (cm.construction[x][y] != null) {
                    for (GirdComponent component : cm.construction[x][y].layers) {
                        if (component == null)
                            continue;
                        component.renderSecondPass(stageBatch, cm.construction[x][y],x*(cellSize+1)+sx,y*(cellSize+1)+sy, cellSize, cellSize);
                    }
                }

            }

        }

    }

    public void renderConstruction(float sx, float sy) {
        renderer.translate(sx, sy, 0);
        renderer.setColor(Color.BLACK);
        renderer.rect(-1, -1, cm.width * (cellSize + 1) + 1, cm.height * (cellSize + 1) + 1);
        for (int x = 0; x < cm.width; x++) {
            for (int y = 0; y < cm.height; y++) {
                renderer.setColor(Color.valueOf("CCCCCC"));
                renderer.rect(0, 0, cellSize, cellSize);
                if (cm.construction[x][y] != null) {
                    for (GirdComponent component : cm.construction[x][y].layers) {
                        if (component == null)
                            continue;
                        component.render(renderer, cm.construction[x][y], cellSize, cellSize);
                    }
                    if (cm.construction[x][y].via) {
                        renderer.setColor(Color.BLACK);
                        renderer.set(ShapeRenderer.ShapeType.Line);
                        renderer.circle(0,0, 7f);
                        renderer.set(ShapeRenderer.ShapeType.Filled);
                    }
                }
                renderer.translate(0, cellSize + 1, 0);
            }
            renderer.translate(cellSize + 1, -(cellSize + 1) * cm.height, 0);
        }

        renderer.translate(-(cellSize + 1) * cm.width, 0, 0);
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


    class MainInputProcessor implements InputProcessor {
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

        public int getCellX(int screenX) {
            return (screenX - 100) / (cellSize + 1);
        }

        public int getCellY(int screenY) {
            return (Gdx.graphics.getHeight() - screenY - 100) / (cellSize + 1);
        }

        public void deleteConnection(int cellX, int cellY, LineTool tool) {
            try {
                Cell cell = cm.construction[cellX][cellY];
                if (tool.isLineAble(cm.construction[cellX][cellY + 1], cell)) {
                    tool.setConnection((byte) (-5 & tool.getConnection(cm.construction[cellX][cellY + 1])), cm.construction[cellX][cellY + 1]);
                }
                if (tool.isLineAble(cm.construction[cellX][cellY - 1], cell)) {
                    tool.setConnection((byte) (-2 & tool.getConnection(cm.construction[cellX][cellY - 1])), cm.construction[cellX][cellY - 1]);
                }
                if (tool.isLineAble(cm.construction[cellX + 1][cellY], cell)) {
                    tool.setConnection((byte) (-9 & tool.getConnection(cm.construction[cellX + 1][cellY])), cm.construction[cellX + 1][cellY]);
                }
                if (tool.isLineAble(cm.construction[cellX - 1][cellY], cell)) {
                    tool.setConnection((byte) (-3 & tool.getConnection(cm.construction[cellX - 1][cellY])), cm.construction[cellX - 1][cellY]);
                }
            } catch (Exception e) {

            }
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int b) {
            try {
                click = b;
                Cell c = cm.getCell(getCellX(screenX), getCellY(screenY));
                if (c != null) {
                    if (click == Input.Buttons.LEFT)
                        ((LineTool) group.getChecked().getUserObject()).perform(c);
                    else if (click == Input.Buttons.RIGHT) {
                        deleteConnection(getCellX(screenX), getCellY(screenY), (LineTool) group.getChecked().getUserObject());
                        ((LineTool) group.getChecked().getUserObject()).delete(c);
                        cm.cleanUp(getCellX(screenX), getCellY(screenY));
                    }
                }
                lastScreenX = screenX;
                lastScreenY = screenY;
                lastCellX = getCellX(screenX);
                lastCellY = getCellY(screenY);
            } catch (Exception e) {
            }
            return true;
        }

        int click;

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        public int lastScreenX;
        public int lastScreenY;
        public int lastCellY;
        public int lastCellX;

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            int deltaX = getCellX(screenX) - lastCellX;
            int deltaY = getCellY(screenY) - lastCellY;
            if (Math.abs(deltaX) > 0 || Math.abs(deltaY) > 0) {
                //int deltaX = screenX - lastScreenX;
                //int deltaY = screenY - lastScreenY;
                try {
                    byte dir = 0;
                    if (Math.abs(deltaX) == 1 || Math.abs(deltaY) == 1) {
                        if (Math.abs(deltaX) > Math.abs(deltaY)) {
                            if (deltaX > 0)
                                dir = 2;
                            else
                                dir = 8;
                        } else if (Math.abs(deltaX) < Math.abs(deltaY)) {
                            if (deltaY > 0)
                                dir = 1;
                            else
                                dir = 4;
                        }
                    }
                    Cell lastCell = cm.construction[lastCellX][lastCellY];
                    LineTool tool = (LineTool) group.getChecked().getUserObject();
                    if (click == Input.Buttons.LEFT) {
                        if (lastCell != null) {
                            Cell cell = cm.getCell(getCellX(screenX), getCellY(screenY));
                            if (cell != null) {
                                tool.perform(cell);
                                if (tool.isLineAble(lastCell, getCellAt(lastScreenX, lastScreenY))) {
                                    tool.setConnection((byte) (dir | tool.getConnection(lastCell)), lastCell);
                                    tool.setConnection((byte) ((dir << 2 > 8 ? dir >> 2 : dir << 2) | tool.getConnection(cell)), cell);
                                }
                            }
                        }
                    } else if (click == Input.Buttons.RIGHT) {
                        Cell cell = getCellAt(screenX, screenY);
                        int cellX = getCellX(screenX);
                        int cellY = getCellY(screenY);
                        deleteConnection(cellX, cellY, tool);
                        tool.delete(cell);
                        cm.cleanUp(cellX, cellY);
                    }
                } catch (Exception e) {
                }
                lastCellX = getCellX(screenX);
                lastCellY = getCellY(screenY);
            }
            lastScreenX = screenX;
            lastScreenY = screenY;
            return true;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
