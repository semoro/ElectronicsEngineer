package net.xcodersteam.eengineer;

import net.xcodersteam.eengineer.components.Silicon;
import net.xcodersteam.eengineer.components.Transistor;

/**
 * Created by fantasyday on 28.04.2015.
 */
public class NSiliconTool extends LineTool {
    @Override
    public boolean perform(Cell cell) {
        if(cell.layers[1] != null && cell.layers[1] instanceof Silicon && ((Silicon) cell.layers[1]).type != Silicon.Type.N)
            cell.layers[1] = new Transistor(Transistor.Type.PnP);
        else if(cell.layers[1] == null)
            cell.layers[1] = new Silicon(Silicon.Type.N);
        else
            return false;
        return true;
    }

    @Override
    public boolean isLineAble(Cell cell1, Cell cell2) {
        return cell1 != null && cell2!= null && cell1.layers[1] != null && ((cell1.layers[1] instanceof Silicon && ((Silicon) cell1.layers[1]).type == Silicon.Type.N) || ((Transistor) cell1.layers[1]).type == Transistor.Type.PnP);
    }

    @Override
    public void setConnection(byte b, Cell c) {

    }

    @Override
    public byte getConnection(Cell c) {
        return 0;
    }
}
