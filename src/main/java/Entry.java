package edu.curtin.app;

import java.util.*;

public class Entry{
    private int nOfRows;
    private int nOfColumns;
    private String type;
    private int rowNumber;
    private int columnNumber;
    private List<String> message;
    private String colour;

    public void setFirstRow(int fR){
        nOfRows = fR;
    }

    public void setFirstColumn(int fC){
        nOfColumns = fC;
    }

    public void setType(String typ){
        type = typ;
    }

    public void setRNum(int rNum){
        rowNumber = rNum;
    }

    public void setCNum(int cNum){
        columnNumber = cNum;
    }

    public void setMessage(List<String> msg){
        message = msg;
    }

    public void setColour(String col){
        colour = col;
    }

// ********************************************************** //

    public int getNRows(){
        return nOfRows;
    }

    public int getNColumns(){
        return nOfColumns;
    }

    public String getType(){
        return type;
    }

    public int getRowNumber(){
        return rowNumber;
    }

    public int getColumnNumber(){
        return columnNumber;
    }

    public List<String> getMessage(){
        return message;
    }

    public String getColour(){
        return colour;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %s, %d, %d, %d\n", nOfRows, nOfColumns, type, rowNumber, columnNumber, colour);
    }
}