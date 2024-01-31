package edu.curtin.app;

public class End extends Decorator {
    public End(Square newSquare, String newColour){
        super(newSquare, newColour);
    }

    @Override
    public String getMessage(){
        return square.getMessage() + "\nYOU WIN!";
    }

    @Override
    public boolean getWin(){
        return true;
    }

    @Override
    public String toString() {
        return "E";
    }
}
