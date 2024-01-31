package edu.curtin.app;

public class Message extends Decorator {
    private String message;
    private String sym;

    public Message(Square newSquare, String newMessage){
        super(newSquare, newMessage);
        message = newMessage;
        sym = newSquare.toString();
    }

    @Override
    public String getMessage(){
        return square.getMessage() + message + "\n";
    }

    @Override
    public boolean getWin(){
        return square.getWin();
    }

    @Override
    public String toString() {
        return sym;
    }
}