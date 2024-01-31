package edu.curtin.app;

import java.util.*;

public class NewMap{
    private int pRow;
    private int pColumn;
    private String message;
    private Object[][] map;
    private List<Integer> colourList;
    private boolean win = false;

    public NewMap(Object[][] newMap, int playerRow, int playerColumn, List<Integer> newList){    
        pRow = playerRow;
        pColumn = playerColumn;
        map = newMap;
        colourList = newList;
    }

    private void north(){
        boolean greenLight = true;
        int i;

        if(map[pRow-1][pColumn].equals('\u2500')){
            throw new IllegalArgumentException("There is a wall");
        }
        else{
            for(int k = 0; k <= 6; k++){
                if(map[pRow-1][pColumn].equals("\033[3"+k+"m\u2592\033[m")){
                    greenLight = false;
                }
            }
            if(greenLight == false){
                i = 0;
                while(i < colourList.size()){
                    if(map[pRow-1][pColumn].equals("\033[3"+colourList.get(i)+"m\u2592\033[m")){
                        greenLight = true;
                    }
                    i++;
                }
            }
        }
        if(greenLight){
            if(!map[pRow-2][pColumn].equals(' ')){
                calculation(map[pRow-2][pColumn]);
            }
            map[pRow-2][pColumn] = 'P';
            map[pRow][pColumn] = ' ';
            pRow = pRow - 2;
            displayMap(map);
        }
        else{
            displayMap(map);
            System.out.println("Find the correct key and come back.");
        }
    }

    private void south(){
        boolean greenLight = true;
        int i;

        if(map[pRow+1][pColumn].equals('\u2500')){
            throw new IllegalArgumentException("There is a wall");
        }
        else{
            for(int k = 0; k <= 6; k++){
                if(map[pRow+1][pColumn].equals("\033[3"+k+"m\u2592\033[m")){
                    greenLight = false;
                }
            }
            if(greenLight == false){
                i = 0;
                while(i < colourList.size()){
                    if(map[pRow+1][pColumn].equals("\033[3"+colourList.get(i)+"m\u2592\033[m")){
                        greenLight = true;
                    }
                    i++;
                }
            }
        }
        if(greenLight){
            if(!map[pRow+2][pColumn].equals(' ')){
                calculation(map[pRow+2][pColumn]);
            }

            map[pRow+2][pColumn] = 'P';
            map[pRow][pColumn] = ' ';
            pRow = pRow + 2;
            displayMap(map);
        }
        else{
            displayMap(map);
            System.out.println("Find the correct key and come back.");
        }
    }

    private void west(){
        boolean greenLight = true;
        int i;

        if(map[pRow][pColumn-2].equals('|')){
            throw new IllegalArgumentException("There is a wall");
        }
        else{
            for(int k = 0; k <= 6; k++){
                if(map[pRow][pColumn-2].equals("\033[3"+k+"m\u2592\033[m")){
                    greenLight = false;
                }
            }
            if(greenLight == false){
                i = 0;
                while(i < colourList.size()){
                    if(map[pRow][pColumn-2].equals("\033[3"+colourList.get(i)+"m\u2592\033[m")){
                        greenLight = true;
                    }
                    i++;
                }
            }
        }

        if(greenLight){
            if(!map[pRow][pColumn-4].equals(' ')){
                calculation(map[pRow][pColumn-4]);
            }
            map[pRow][pColumn-4] = 'P';
            map[pRow][pColumn] = ' ';
            pColumn = pColumn - 4;
            displayMap(map);
        }
        else{
            displayMap(map);
            System.out.println("Find the correct key and come back.");
        }
    }

    private void east(){
        boolean greenLight = true;
        int i;

        if(map[pRow][pColumn+2].equals('|')){
            throw new IllegalArgumentException("There is a wall");
        }
        else{
            for(int k = 0; k <= 6; k++){
                if(map[pRow][pColumn+2].equals("\033[3"+k+"m\u2592\033[m")){
                    greenLight = false;
                }
            }
            if(greenLight == false){
                i = 0;
                while(i < colourList.size()){
                    if(map[pRow][pColumn+2].equals("\033[3"+colourList.get(i)+"m\u2592\033[m")){
                        greenLight = true;
                    }

                    i++;
                }
            }
        }
        if(greenLight){
            if(!map[pRow][pColumn+4].equals(' ')){
                calculation(map[pRow][pColumn+4]);
            }
            map[pRow][pColumn+4] = 'P';
            map[pRow][pColumn] = ' ';
            pColumn = pColumn + 4;
            displayMap(map);
        }
        else{
            displayMap(map);
            System.out.println("Find the correct key and come back.");
        }
    }

    public void setPlayer(char direction){
        if(direction == 'n'){
            north();
        }
        else if(direction == 's'){
            south();
        }
        else if(direction == 'w'){
            west();
        }
        else if(direction == 'e'){
            east();
        }
        message = "";
    }

    public void displayMap(Object[][] maze){
        System.out.println("\033[2J\033[H");

        for(Object[] i : maze){
            for(Object j: i){
                System.out.print(j);
            }
            System.out.println("");
        }
        for(int colourCode : colourList){
            System.out.print("\033[3"+colourCode+"m\u2555\033[m" + " ");
        }
        
        System.out.println("");

        System.out.println(message);
    }

    public void calculation(Object cell){
        String colour;
        String[] splitLine;
        int lineLength;

        win = ((Square)cell).getWin();

        message = ((Square)cell).getMessage();

        colour = ((Square)cell).getColour();
        splitLine = colour.split(" ");

        lineLength = splitLine.length;
        for(int i = 0; i < lineLength; i++){
            if(!splitLine[i].equals("")){
                colourList.add(Integer.parseInt(splitLine[i]));
            }
        }
    }

    public boolean getWin(){
        return win;
    }
}