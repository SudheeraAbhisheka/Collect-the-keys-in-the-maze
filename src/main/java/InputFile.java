package edu.curtin.app;

import java.util.*;
import java.io.*;

public class InputFile{
    private List<Entry> entries = new LinkedList<>();

    public InputFile(String pFileName){
        readFile(pFileName);
    }

    private void readFile(String pFileName){ 
        int lineNum;
        String line;
        try(            
            FileInputStream fileStream  = new FileInputStream(pFileName);
            InputStreamReader rdr       = new InputStreamReader(fileStream);
            BufferedReader bufRdr       = new BufferedReader(rdr);
            ){
            lineNum    = 0;
            line       = bufRdr.readLine();
            while(line != null){
                lineNum++;
                if(lineNum == 1){
                    firstRow(line);
                    line = bufRdr.readLine();
                }
                else{
                    processLine(line);
                    line = bufRdr.readLine();
                }
            }
        }
        catch(IOException errorDetails){
            System.out.println("Error in fileProcessing: " + errorDetails.getMessage());
        }
    }

    private void processLine(String csvRow){
        String[] splitLine;
        splitLine = csvRow.split(" ");
        int lineLength = splitLine.length;

        Entry entry = new Entry();

        entry.setType(splitLine[0]);
        entry.setRNum(Integer.parseInt(splitLine[1]));
        entry.setCNum(Integer.parseInt(splitLine[2]));

        if(lineLength > 3){
            if(splitLine[0].equals("M")){
                List<String> message = new LinkedList<>();

                for(int i = 3; i < lineLength; i++)
                {
                    message.add(splitLine[i]);
                    entry.setMessage(message);
                } 
            }
            if(splitLine[0].equals("DV") || splitLine[0].equals("DH") || splitLine[0].equals("K")){
                entry.setColour(splitLine[3]);
            }
        }    
        entries.add(entry);
    }

    private void firstRow(String csvRow){
        String[] splitLine;
        
        splitLine = csvRow.split(" ");
        Entry entry = new Entry();

        entry.setFirstRow(Integer.parseInt(splitLine[0]));
        entry.setFirstColumn(Integer.parseInt(splitLine[1]));

        entries.add(entry);
    }

    public List<Entry> getEntryList(){
        return entries;
    }
}
