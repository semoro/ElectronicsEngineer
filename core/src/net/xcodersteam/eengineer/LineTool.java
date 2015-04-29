package net.xcodersteam.eengineer;

/**
 * Created by fantasyday on 28.04.2015.
 */
public abstract class LineTool {
    public abstract boolean perform(Cell cell);

    public abstract boolean isLineAble(Cell cell1, Cell cell2);

    public abstract void setConnection(byte b, Cell c);

    public abstract byte getConnection(Cell c);

    public abstract boolean delete(Cell cell);
}
