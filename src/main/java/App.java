package edu.curtin.app;

import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        try(Scanner sc = new Scanner(System.in)){
            char direction;
            boolean win;
            int pRow, pColumn;
            CreateMap map;
            NewMap newMap;
            InputFile textFile;
            List<Integer> colourList = new LinkedList<>();

            if(args.length == 0){
                textFile = new InputFile("input.txt");
            }
            else{
                textFile = new InputFile(args[0]);
            }
            map = new CreateMap(textFile.getEntryList());

            if(map.getErrorMsg().size() != 0){
                System.out.println("\033[2J\033[H");
                System.out.println("Your Input file has invalid fields.");
                System.out.println(map.getErrorMsg());
                System.out.println("Press any key to key to continue.");
                sc.nextLine();
            }
            pRow = map.getPRow();
            pColumn = map.getPColumn();
            newMap = new NewMap(map.getMap(), pRow, pColumn, colourList);
            newMap.calculation(map.getMap()[pRow][pColumn]);
            newMap.displayMap(map.getMap());
            win = newMap.getWin();

            while(!win){
                direction = sc.next().charAt(0);
                newMap.setPlayer(direction);
                win = newMap.getWin();
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Invalid move");
        }
    }
}
