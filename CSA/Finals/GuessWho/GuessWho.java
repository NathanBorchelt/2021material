import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//https://www.journaldev.com/709/java-read-file-line-by-line
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class GuessWho{
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<SWCharacter> avalibleChars = new ArrayList<SWCharacter>();
        ArrayList<Integer> randOptions1 = new ArrayList<Integer>();
        ArrayList<Integer> randOptions2 = new ArrayList<Integer>();
        ArrayList<SWCharacter> player1Board  = new ArrayList<SWCharacter>();
        ArrayList<SWCharacter> player2Board  = new ArrayList<SWCharacter>();

        String[] optionPrompStrings = {"Are they a jedi?",
            "Are they a droid?",
            "Are they a humanoid?",
            "Are they a wookie?",
            "Are they part of the Dark Side?",
            "Are they part of the Light Side?",
            "Are they a bountyHunter?",
            "Are they a smuggler?",
            "How kesselSpeed?",
            "Are they part of the empire?",
            "Are they a rebel?",
            "Are they part of the Resistance?",
            "Are they part of the First Order?",
            "Are they part of the Separatist?",
            "Are they part of the Galactic Republic?",
            "Are they a ewok?",
            "fluffy?",
            "slimey?",
            "lightsaberColor?",
            "tall?",
            "short?",
            "Are they a pilot?",
            "annoying?",
            "cute?",
            "bandersFav?",
            "gotButtWhooped?",
            "insideATauntaun?",
            "stillLiving?",
            "lostALimb?",
            "Are they a Space Balls character?"}

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
        while(randOptions1.size()<(Math.round((avalibleChars.size()/2)))){
            potCharIndex = rand.nextInt(avalibleChars.size());
            if(!randOptions1.contains(potCharIndex)){
                randOptions1.add(potCharIndex);
            }
        }
        while(randOptions2.size()<(Math.round((avalibleChars.size()/2)))){
            potCharIndex = rand.nextInt(avalibleChars.size());
            if(!randOptions2.contains(potCharIndex)){
                randOptions2.add(potCharIndex);
            }
        }
        for(int i = 0; i < randOptions1.size();i++){
            player1Board.add(avalibleChars.get(randOptions1.get(i)));
            player2Board.add(avalibleChars.get(randOptions2.get(i)));
        }
        /*
        for(int i = 0; i < player1Board.length; i++){
            System.out.println("Player 1: "+ player1Board[i]+"\nPlayer 2: "+player2Board[i]);
        }
        */

        

    }
}