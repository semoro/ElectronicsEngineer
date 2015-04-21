package net.xcodersteam.eengineer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    public  MainGameScreen () {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        //img = new Texture("badlogic.jpg");
        cm = new ConstructionManager(16, 16);
        new Metal(cm.getCell(0, 0));
        new Silicon(cm.getCell(0, 1), Silicon.Type.P).connection = 1| 4 | 2 | 8;
        cm.getCell(0, 1).via = true;
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
        renderer.end();
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
