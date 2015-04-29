package net.xcodersteam.eengineer;

import java.io.*;
import java.util.Arrays;

/**
 * Created by fantasyday on 19.04.2015.
 */
public class ConstructionManager {
    public final Cell[][] construction;
    public final int height;
    public final int width;
    public ConstructionManager(File file) throws IOException, ClassNotFoundException {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            width = ois.readInt();
            height = ois.readInt();
            construction = new Cell[width][height];
            while(ois.available() > 0){
                int x = ois.readInt();
                int y = ois.readInt();
                construction[x][y] = (Cell) ois.readObject();
            }
    }
    public ConstructionManager(int height, int width) {
        this.height = height;
        this.width = width;
        construction = new Cell[width][height];
    }
    public Cell getCell(int x,int y){
        if(x >= 0 && x < width && y >= 0 && y < height){
            Cell c = construction[x][y];
            if(c == null){
                c = new Cell();
                construction[x][y] = c;
            }
            return c;
        }
        return null;
    }
    public static boolean isArrayEmpty(Object[] i){
        if(i == null)
            return true;
        else {
            for(Object o : i){
                if(o != null)
                    return false;
            }
        }
        return true;
    }
    public void cleanUp(){
        int utechka = 0;
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(construction[x][y] != null && isArrayEmpty(construction[x][y].layers)){
                    construction[x][y] = null;
                    utechka++;
                }
            }
        }
        if(utechka > 0)
            System.out.println(utechka);
    }
    public void cleanUp(int x, int y){
        if(construction[x][y] != null && isArrayEmpty(construction[x][y].layers)){
            construction[x][y] = null;
        }
    }
    public void save(File output) throws IOException {
        cleanUp();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        oos.writeInt(width);
        oos.writeInt(height);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(construction[x][y] != null){
                    oos.writeInt(x);
                    oos.writeInt(y);
                    oos.writeObject(construction[x][y]);
                }
            }
        }
    }
}
