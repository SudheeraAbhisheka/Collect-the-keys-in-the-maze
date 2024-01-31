package edu.curtin.app;

public class Empty implements Square {
    @Override
    public String getMessage(){
        return "";
    }

    @Override
    public String getColour(){
        return " ";
    }
    
    @Override
    public boolean getWin(){
        return false;
    }

    @Override
    public String toString() {
        return " ";
    }
}
