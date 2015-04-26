package net.xcodersteam.eengineer.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ComponentClaster {
	public List<ComponentGate> outs=new ArrayList();

	public void changeState(){
		for(ComponentGate i:outs){
			i.changeState();
		}
	}
}
