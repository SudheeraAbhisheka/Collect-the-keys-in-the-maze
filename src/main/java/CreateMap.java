package edu.curtin.app;

import java.util.*;

public class CreateMap{
    private Object[][] map;
    private int nR;
    private int nC;
    private int pRow;
    private int pColumn;
    private Empty emp;
    private List<String> errorMsg;

    public CreateMap(List<Entry> entries){
        int numR;
        int numC;
        Entry firstEntry;
        String errString;
        boolean validStart = false;
        Entry defaultStart;

        errorMsg = new LinkedList<>();

        emp = new Empty();

        firstEntry = entries.remove(0);

        nR = firstEntry.getNRows();
        nC = firstEntry.getNColumns();

        numR = nR*2+1;
        numC = nC*4+1;

        map = new Object[numR][numC];

        for(int i = 0; i < numR; i++){
            for(int j = 0; j < numC; j++){
                map[i][j] = emp;
            }
        }

        for(int i = 0; i < numR; i++){
            map[i][0] = '|';
            map[i][numC-1] = '|';

            for(int j = 0; j < numC; j++){
                map[0][j] = '\u2500';
                map[numR-1][j] = '\u2500';
            }
        }

        map[0][0] = '\u250c';
        map[0][numC-1] = '\u2510';
        map[numR-1][0] = '\u2514';
        map[numR-1][numC-1] = '\u2518';

        for(Entry entry : entries){
            if(entry.getRowNumber() < nR && entry.getColumnNumber() < nC){
                if(entry.getType().equals("S")){
                    if(validStart){
                        errString = entry.getType() + " " + entry.getRowNumber() + " " + entry.getColumnNumber();
                        errorMsg.add(errString);
                    }
                    else{
                       makingMap(entry); 
                       validStart = true;
                    }
                }
                else{
                    makingMap(entry);
                }
            }
            else{
                errString = entry.getType() + " " + entry.getRowNumber() + " " + entry.getColumnNumber();
                errorMsg.add(errString);
            }
        }
        if(!validStart){
            defaultStart = new Entry();
            defaultStart.setType("S");
            defaultStart.setRNum(0);
            defaultStart.setCNum(0);
            makingMap(defaultStart);

            errString = "A valid start position has not been found.";
            errorMsg.add(errString);
        }
        reAdjMap();
    }

    private void makingMap(Entry entry){
        String type;
        int rNum;
        int cNum;
        String colour;
        int colourCode = 0;
        List<String> listM;
        int j;
        String message = "";
        
        type = entry.getType();
        rNum = entry.getRowNumber()*2+1;
        cNum = entry.getColumnNumber()*4+2;
        colour = entry.getColour();
        listM = entry.getMessage();

        if(colour != null){
            colourCode = Integer.parseInt(colour);
        }

        if(type.equals("WV")){
            vWalls(entry.getRowNumber(), entry.getColumnNumber());
        }
        else if(type.equals("WH")){
            hWalls(entry.getRowNumber(), entry.getColumnNumber());
        }
        else if(type.equals("DV")){
            if(colourCode >= 0 && colourCode <= 6){
                vDoors(entry.getRowNumber(), entry.getColumnNumber(), colourCode);
            }
        }
        else if(type.equals("DH")){
            if(colourCode >= 0 && colourCode <= 6){
                hDoors(entry.getRowNumber(), entry.getColumnNumber(), colourCode);
            }
        }
        else if(type.equals("S")){
            map[rNum][cNum] = new Start(); 
            pRow = rNum;
            pColumn = cNum;
        }
        else if(type.equals("E")){
            map[rNum][cNum] = new End((Square)map[rNum][cNum], " ");
        }
        else if(type.equals("K")){
            map[rNum][cNum] = new Key((Square)map[rNum][cNum], colour);
        }
        else if(type.equals("M")){
            j =  listM.size();

            for(int i = 0; i < j; i++){
                message = message + listM.get(i) + " ";
            }
            map[rNum][cNum] = new Message((Square)map[rNum][cNum], message);
        }
    }

    private void vWalls(int pR, int pC){
        if(pR == 0){
            map[0][pC*4] = '\u252c';
            map[1][pC*4] = '|';
            map[2][pC*4] = '|';
        }
        else if(nR-1 == pR){
            map[pR*2+2][pC*4] = '\u2534';
            map[pR*2+1][pC*4] = '|';
            map[pR*2][pC*4] = '|';
        }
        else{
            map[pR*2+2][pC*4] = '|';
            map[pR*2+1][pC*4] = '|';
            map[pR*2][pC*4] = '|';
        }
    }

    private void hWalls(int pR, int pC){
        if(pC == 0){
            map[pR*2][0] = '\u251c';
            map[pR*2][1] = '\u2500';
            map[pR*2][2] = '\u2500';
            map[pR*2][3] = '\u2500';
            map[pR*2][4] = '\u2500';
        }
        else if(nC-1 == pC){
            map[pR*2][pC*4+4] = '\u2524';
            map[pR*2][pC*4+3] = '\u2500';
            map[pR*2][pC*4+2] = '\u2500';
            map[pR*2][pC*4+1] = '\u2500';
            map[pR*2][pC*4] = '\u2500';
        }
        else{
            map[pR*2][pC*4+4] = '\u2500';
            map[pR*2][pC*4+3] = '\u2500';
            map[pR*2][pC*4+2] = '\u2500';
            map[pR*2][pC*4+1] = '\u2500';
            map[pR*2][pC*4] = '\u2500';
        }
    }

    private void vDoors(int pR, int pC, int colourCode){
        if(pR == 0){
            map[0][pC*4] = '\u252c';
            map[1][pC*4] = "\033[3"+colourCode+"m\u2592\033[m";
            map[2][pC*4] = '|';
        }
        else if(nR-1 == pR){
            map[pR*2+2][pC*4] = '\u2534';
            map[pR*2+1][pC*4] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][pC*4] = '|';
        }
        else{
            map[pR*2+2][pC*4] = '|';
            map[pR*2+1][pC*4] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][pC*4] = '|';
        }
    }

    private void hDoors(int pR, int pC, int colourCode){
        if(pC == 0){
            map[pR*2][0] = '\u251c';
            map[pR*2][1] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][2] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][3] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][4] = '\u2500';
        }
        else if(nC-1 == pC){
            map[pR*2][pC*4+4] = '\u2524';
            map[pR*2][pC*4+3] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][pC*4+2] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][pC*4+1] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][pC*4] = '\u2500';
        }
        else{
            map[pR*2][pC*4+4] = '\u2500';
            map[pR*2][pC*4+3] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][pC*4+2] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][pC*4+1] = "\033[3"+colourCode+"m\u2592\033[m";
            map[pR*2][pC*4] = '\u2500';
        }
    }

    private void reAdjMap(){
        boolean left, right, up, down;

        for(int i = 1; i <= nR - 1; i++){
            for(int j = 1; j <= nC - 1; j++){
                if(!map[i*2-1][j*4].equals(emp)){
                    left = false;
                    right = false;
                    down = false;

                    if(!map[i*2][j*4-1].equals(emp)){
                        left = true;                        
                    }
                    if(!map[i*2][j*4+1].equals(emp)){
                        right = true;                         
                    }    
                    if(!map[i*2+1][j*4].equals(emp)){
                        down = true;
                    }         
              
                    if(left && right && down){
                        map[i*2][j*4] = '\u253c';
                    }
                    else if(left && down){
                        map[i*2][j*4] = '\u2524';                        
                    }
                    else if(right && down){
                        map[i*2][j*4] = '\u251c';                        
                    }
                    else if(left && right){
                        map[i*2][j*4] = '\u2534';
                    }
                    else if(left){
                        map[i*2][j*4] = '\u2518';
                    }     
                    else if(right){
                        map[i*2][j*4] = '\u2514';
                    }            
                }
            }  
        } 

        for(int i = 1; i <= nR - 1; i++){
            for(int j = 1; j <= nC - 1; j++){
                if(!map[i*2+1][j*4].equals(emp)){
                    left = false;
                    right = false;
                    up = false;

                    if(!map[i*2][j*4-1].equals(emp)){
                        left = true;
                    }
                    if(!map[i*2][j*4+1].equals(emp)){
                        right = true;
                    }
                    if(!map[i*2-1][j*4].equals(emp)){
                        up = true;
                    }

                    if(left && right && up){
                        map[i*2][j*4] = '\u253c';
                    }
                    else if(left && up){
                        map[i*2][j*4] = '\u2524';                        
                    }
                    else if(right && up){
                        map[i*2][j*4] = '\u251c';                        
                    } 
                    else if(left && right){
                        map[i*2][j*4] = '\u252c';
                    }
                    else if(left){
                        map[i*2][j*4] = '\u2510';
                    }
                    else if(right){
                        map[i*2][j*4] = '\u250c';
                    }
                }
            }  
        }
    }

    public Object[][] getMap(){
        return map;
    }

    public int getPRow(){
        return pRow;
    }

    public int getPColumn(){
        return pColumn;
    }

    public List<String> getErrorMsg(){
        return errorMsg;
    }
}