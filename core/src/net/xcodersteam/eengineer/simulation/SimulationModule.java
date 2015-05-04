package net.xcodersteam.eengineer.simulation;

import java.util.Map;
import java.util.Map.Entry;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;
import net.xcodersteam.eengineer.components.Pin;
import net.xcodersteam.eengineer.components.Silicon;
import net.xcodersteam.eengineer.components.Transistor;
import net.xcodersteam.eengineer.components.Transistor.Type;

public class SimulationModule {
	Cell[][] cells;
	public SimulationModule(Cell[][] c) {
		cells=c;
	}
	
	public void делайСвоюМагию(Map<Pin,Boolean> map){
		for(Cell[] i:cells){
			for(Cell j:i){
				if(j!=null)
					for(GirdComponent c:j.layers){
						if(c!=null){
							c.isPower=false;
						}
					}
			}
		}
		/*for(Entry<Pin, Boolean> i:map.entrySet()){
			if(i.getValue()){
				i.getKey().powerOn(cells, x, y);
			}
		}*/
		for(int i=0;i<cells.length;i++){
			for(int j=0;j<cells[0].length;j++){
				Cell c=cells[i][j];
				if(c!=null&&c.layers[1]!=null&&(c.layers[1] instanceof Transistor)){
					if(((Transistor)c.layers[1]).type==Type.NpN){
						((Transistor)c.layers[1]).isOpened=c.layers[1].getIsSiliconPowerOn(cells, i, j, Silicon.Type.P);
					}else{
						((Transistor)c.layers[1]).isOpened=!c.layers[1].getIsSiliconPowerOn(cells, i, j, Silicon.Type.N);
					}
				}
			}
		}
	}
}
