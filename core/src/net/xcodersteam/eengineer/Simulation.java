package net.xcodersteam.eengineer;

import net.xcodersteam.eengineer.components.Pin;
import net.xcodersteam.eengineer.components.Silicon;
import net.xcodersteam.eengineer.components.Transistor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by semoro on 06.05.15.
 */
public class Simulation {
    private final ConstructionManager cm;
    private final List<Pin> pins;
    private final int outputsCount;
    private boolean running=false;
    public final int length;
    private float validPercent=0f;
    public int time=0;
    public int valid=0;

    public Simulation( MainGameScreen in){
        this.cm=in.cm;
        this.pins=in.pins;
        outputsCount= (int) pins.stream().filter(p->p.pinType== Pin.PinType.OUT).count();
        length=in.pins.stream().map(p->p.states.getLen()).reduce(0,Math::max);
    }

    public float getValidPercent(){
        return validPercent;
    }

    public boolean isRunning(){
        return running;
    }
    private void end(){

        stop();
    }

    public void reset(){
        cm.cleanUp();
        pins.forEach(p -> {
            p.testPinsState.clear();
        });
        valid=0;
        time=0;
    }
    public void stop(){
        for (Cell[] i : cm.construction) {
            for (Cell j : i) {
                if (j != null) {
                    for (GirdComponent c : j.layers) {
                        if (c != null) {
                            c.isPower = false;
                        }
                    }
                }
            }
        }
        running=false;
    }
    public void start(){
        reset();
        running=true;
    }
    public void simulate() {
        if(time==length) {
            end();
            return;
        }
        for (Cell[] i : cm.construction) {
            for (Cell j : i) {
                if (j != null) {
                    for (GirdComponent c : j.layers) {
                        if (c != null) {
                            c.isPower = false;
                        }
                    }
                }
            }
        }
        for (int x = 0; x < cm.width; x++)
            for (int y = 0; y < cm.height; y++)
                if (cm.construction[x][y] != null && cm.construction[x][y].layers[2] instanceof Pin && ((Pin) cm.construction[x][y].layers[2]).getState(time))
                    cm.construction[x][y].layers[2].powerOn(cm.construction, x, y);

        for (int i = 0; i < cm.construction.length; i++) {
            for (int j = 0; j < cm.construction[0].length; j++) {
                Cell c = cm.construction[i][j];
                if (c != null && c.layers[1] != null && (c.layers[1] instanceof Transistor)) {
                    if (((Transistor) c.layers[1]).type == Transistor.Type.NpN) {
                        ((Transistor) c.layers[1]).isOpened = c.layers[1].getIsSiliconPowerOn(cm.construction, i, j, Silicon.Type.P);
                    } else {
                        ((Transistor) c.layers[1]).isOpened = !c.layers[1].getIsSiliconPowerOn(cm.construction, i, j, Silicon.Type.N);
                    }
                }
            }
        }

        pins.stream().filter(p -> p.pinType == Pin.PinType.OUT).forEach(p -> {
            if (p.isPower == p.states.getState(time))
                valid++;
            p.testPinsState.add(new PinState(p.isPower, 1));
        });
        validPercent=((float)valid/length)/outputsCount * 100f;
        time++;
    }

}
