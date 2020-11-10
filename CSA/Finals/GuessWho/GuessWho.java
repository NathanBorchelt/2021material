import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//https://www.journaldev.com/709/java-read-file-line-by-line
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GuessWho{
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<SWCharacter> avalibleChars = new ArrayList<SWCharacter>();
        ArrayList<Integer> randOptions = new ArrayList<Integer>();
        SWCharacter[] player1Board  = new SWCharacter[25];
        SWCharacter[] player2Board  = new SWCharacter[25];
        try{
            //https://www.journaldev.com/709/java-read-file-line-by-line
            BufferedReader allFile = new BufferedReader(new FileReader("SWData.txt"));
            String fileLine = allFile.readLine();
            while(fileLine != null){
                //System.out.println(fileLine);
                SWCharacter swchar = new SWCharacter(fileLine);
                avalibleChars.add(swchar);
                fileLine = allFile.readLine();
            }
            allFile.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
/*
        for(int i = 0; i < avalibleChars.size(); i++){
            System.out.println(avalibleChars.get(i).getName());
        }
        */
        int potCharIndex;
        while(randOptions.size()<(avalibleChars.size()/2)){
            potCharIndex = rand.nextInt(avalibleChars.size());
            if(!randOptions.contains(potCharIndex)){
                randOptions.add(potCharIndex);
            }
        }
        for(int i = 0; i < randOptions.size();i++){
            player1Board[i] = avalibleChars.get(randOptions.get(i));
            player2Board[i] = avalibleChars.get(randOptions.get(i));
        }
        /*
        for(int i = 0; i < player1Board.length; i++){
            System.out.println("Player 1: "+ player1Board[i]+"\nPlayer 2: "+player2Board[i]);
        }
        */

        

    }
}