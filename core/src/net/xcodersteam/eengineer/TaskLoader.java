package net.xcodersteam.eengineer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import net.xcodersteam.eengineer.components.Metal;
import net.xcodersteam.eengineer.components.Pin;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by semoro on 04.05.15.
 */
public class TaskLoader {
    File task;
    public String description;

    private PinStateList loadStates(JsonValue pinDef){
        PinStateList psL=new PinStateList();
        for (JsonValue jv:pinDef.get("state")){
            PinState ps=new PinState(jv.asInt());
            ps.up=jv.name.equalsIgnoreCase("1");
            psL.add(ps);
        }
        return psL;
    }
    public TaskLoader(File task){
        this.task=task;
    }
    public ConstructionManager init() throws FileNotFoundException {
        JsonReader jr=new JsonReader();
        JsonValue root= jr.parse(new FileInputStream(task));
        int width=root.getInt("w");
        int height=root.getInt("h");
        description = root.getString("desc", "Нет описания");
        ConstructionManager cm=new ConstructionManager(height,width);
        JsonValue pins=root.get("pins");
        pins.forEach(pinDef -> {

            Pin p=new Pin(pinDef.name);
            if(pinDef.name.equalsIgnoreCase("VCC"))
                p.pinType= Pin.PinType.VCC;
            else
                p.pinType= Pin.PinType.valueOf(pinDef.getString("type"));
            createPin(cm,p,pinDef.getInt("x"),pinDef.getInt("y"));
            if(p.pinType!= Pin.PinType.VCC) {

                p.states=loadStates(pinDef);
            }
            MainGameScreen.instance.pins.add(p);
        });

        return cm;
    }

    public void save(File out,ConstructionManager cm) throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(out));
        oos.writeUTF(task.getPath());
        cm.save(oos);
        oos.close();
    }

    private void createPin(ConstructionManager cm,Pin p ,int x, int y){
        cm.getCell(x,y).put(p);
        p.locked=true;
        p.connection=2|1;
        new Metal(cm.getCell(x,y+1)).lock(true).connection=4|2;
        new Metal(cm.getCell(x+1,y)).lock(true).connection=8|1;
        new Metal(cm.getCell(x+1,y+1)).lock(true).connection=4|8;
    }


    public ConstructionManager load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(task));
        String path=ois.readUTF();
        task=new File(path);
        JsonReader jr=new JsonReader();
        JsonValue root= jr.parse(new FileInputStream(task));
        int width=root.getInt("w");
        int height=root.getInt("h");
        description = root.getString("desc", "Нет описания");
        ConstructionManager cm=new ConstructionManager(height,width);
        JsonValue pins=root.get("pins");
        cm.load(ois);
        ois.close();
        pins.forEach(pinDef -> {
            Pin p = new Pin(pinDef.name);
            if (pinDef.name.equalsIgnoreCase("VCC"))
                p.pinType = Pin.PinType.VCC;
            else
                p.pinType = Pin.PinType.valueOf(pinDef.getString("type"));
            Cell c=cm.getCell(pinDef.getInt("x"), pinDef.getInt("y"));
            p.connection=c.layers[2].connection;
            p.locked=true;
            c.put(p);
            if (p.pinType != Pin.PinType.VCC) {
                p.states=loadStates(pinDef);
            }

            MainGameScreen.instance.pins.add(p);
        });

        return cm;
    }

}
