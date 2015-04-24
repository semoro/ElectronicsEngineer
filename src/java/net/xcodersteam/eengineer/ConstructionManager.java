package net.xcodersteam.eengineer;

/**
 * Created by fantasyday on 19.04.2015.
 */
public class ConstructionManager {
    public final Cell[][] construction;
    public final int height;
    public final int width;

    public ConstructionManager(int height, int width) {
        this.height = height;
        this.width = width;
        construction = new Cell[height][width];
    }
    public Cell getCell(int x,int y){
        if(x > 0 && x < width && y > 0 && y < height){
            Cell c = construction[x][y];
            if(c == null){
                c = new Cell();
                construction[x][y] = c;
            }
            return c;
        }
        return null;
    }
}
