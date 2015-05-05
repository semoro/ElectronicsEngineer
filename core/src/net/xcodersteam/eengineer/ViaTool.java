package net.xcodersteam.eengineer;

/**
 * Created by fantasyday on 28.04.2015.
 */
public class ViaTool extends LineTool {
    @Override
    public boolean perform(Cell cell) {
        if(cell.layers[1] != null && cell.layers[2] != null)
            cell.via = true;
        return true;
    }

    @Override
    public boolean isLineAble(Cell cell1, Cell cell2) {
        return false;
    }

    @Override
    public void setConnection(byte b, Cell c) {

    }

    @Override
    public byte getConnection(Cell c) {
        return 0;
    }

    @Override
    public boolean delete(Cell cell) {
        if(cell != null)
            cell.via = false;
        return true;
    }
}
