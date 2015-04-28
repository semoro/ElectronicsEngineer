package net.xcodersteam.eengineer.simulation;

import java.util.List;
import net.xcodersteam.eengineer.Cell;
import net.xcodersteam.eengineer.GirdComponent;
import net.xcodersteam.eengineer.components.Pin;
import net.xcodersteam.eengineer.components.Pin.PinType;
import net.xcodersteam.eengineer.components.Transistor;

public class SimulationModule {
	List<Pin> pins;
	Cell[][] cells;
	ComponentGate[][] gates;
	
	public SimulationModule(Cell[][] c) {
		cells=c;
		gates=new ComponentGate[c.length][c[0].length];
		Cell q=null;
		for(Cell[] i:c){
			for(Cell j:i){
				if(j!=null&&j.layers[2]instanceof Pin&&(((Pin)j.layers[2]).pinType==PinType.IN)){
					q=j;
					break;
				}
			}
		}
		if(q==null){
			throw new RuntimeException("Нет точки входа схемы");
		}
		
	}
	
	public void clasterizeSilicon(int x,int y,ComponentClaster claster, int pSide){
		clasterizeSilicon(x,y,false,claster,pSide);
	}
	
	public void clasterizeSilicon(int x,int y,boolean first,ComponentClaster claster, int pSide){
		if(cells[x][y]==null){
			return;
		}
		GirdComponent from=cells[x][y].layers[1];
		if(from!=null){
			if(from instanceof Transistor){
				if(gates[x][y]==null){
					ComponentGate gate=new ComponentGate();
					gates[x][y]=gate;
				}
				
				return;
			}
			if((from.connection&0b1)>0&&pSide!=2){
				clasterizeSilicon(x,y+1,claster,0);
			}
			if((from.connection&0b01)>0&&pSide!=3){
				clasterizeSilicon(x+1,y,claster,1);			
			}
			if((from.connection&0b001)>0&&pSide!=0){
				clasterizeSilicon(x,y-1,claster,2);
			}
			if((from.connection&0b0001)>0&&pSide!=1){
				clasterizeSilicon(x-1,y,claster,3);
			}
			if(!first&&cells[x][y].via){
				clasterizeMetal(x,y,true,claster,4);
			}
		}
	}
	
	public void clasterizeMetal(int x,int y,ComponentClaster claster, int pSide){
		clasterizeMetal(x,y,false,claster,pSide);
	}
	
	public void clasterizeMetal(int x,int y,boolean first,ComponentClaster claster, int pSide){
		if(cells[x][y]==null){
			return;
		}
		GirdComponent from=cells[x][y].layers[2];
		if(from!=null){
			if((from.connection&0b1)>0&&pSide!=2){
				clasterizeMetal(x,y+1,claster,0);
			}
			if((from.connection&0b01)>0&&pSide!=3){
				clasterizeMetal(x+1,y,claster,1);			
			}
			if((from.connection&0b001)>0&&pSide!=0){
				clasterizeMetal(x,y-1,claster,2);
			}
			if((from.connection&0b0001)>0&&pSide!=1){
				clasterizeMetal(x-1,y,claster,3);
			}
			if(!first&&cells[x][y].via){
				clasterizeSilicon(x,y,true,claster,4);
			}
		}
	}
}
