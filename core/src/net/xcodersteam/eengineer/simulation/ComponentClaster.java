package net.xcodersteam.eengineer.simulation;

import java.util.ArrayList;
import java.util.List;

public class ComponentClaster {
	public List<ComponentGate> outs=new ArrayList();

	public void changeState(){
		for(ComponentGate i:outs){
			i.changeState();
		}
	}
}
