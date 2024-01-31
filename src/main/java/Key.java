package edu.curtin.app;

public class Key extends Decorator {
    private String colour;
    private int colourCode;

    public Key(Square newSquare, String newColour){
        super(newSquare, newColour);
        colour = newColour;
        colourCode = Integer.parseInt(newColour);
    }

    @Override
    public String getColour(){
        return square.getColour() + " " + colour + " ";
    }

    @Override
    public boolean getWin(){
        return square.getWin();
    }

    @Override
    public String toString() {
        String rString;
        if(square.toString().equals("E")){
            rString = "E";
        }
        else{
            rString = "\033[3"+colourCode+"m\u2555\033[m";     
        }
        
        return rString;
    }
}