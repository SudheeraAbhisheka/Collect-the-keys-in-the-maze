package edu.curtin.app;

public abstract class Decorator implements Square {
    protected Square square;
    protected String colour;

    public Decorator(Square newSquare, String newColour) {
        this.square = newSquare;
        this.colour = newColour;
    }

    @Override
    public String getMessage(){
        return square.getMessage();
    }

    @Override
    public String getColour(){
        return square.getColour();
    }
    
    @Override
    public boolean getWin(){
        return false;
    }
}
