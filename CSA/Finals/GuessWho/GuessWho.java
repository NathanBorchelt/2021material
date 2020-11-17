import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//https://www.journaldev.com/709/java-read-file-line-by-line
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.util.Collections;


public class GuessWho{

    public static boolean computer = false;
    public static byte playerTurn = -127;

    public static PlayerBoard p1Board = new PlayerBoard();
    public static PlayerBoard p2Board = new PlayerBoard();

    public static ArrayList<String> playerOptions = new ArrayList<String>();

    public static ArrayList<String> p1QuestionOptions = new ArrayList<String>();
    public static ArrayList<String> p2QuestionOptions = new ArrayList<String>();

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Random rand = new Random();

        ArrayList<SWCharacter> avalibleChars = new ArrayList<SWCharacter>();
        ArrayList<Integer> randOptions = new ArrayList<Integer>();
        ArrayList<SWCharacter> p1Initalization  = new ArrayList<SWCharacter>();
        ArrayList<SWCharacter> p2Initalization  = new ArrayList<SWCharacter>();

        String[] optionPrompStrings = {
            "Are they a jedi?",
            "Are they a droid?",
            "Are they a humanoid?",
            "Are they a wookie?",
            "Are they part of the Dark Side?",
            "Are they part of the Light Side?",
            "Are they a bounty hunter?",
            "Are they a smuggler?",
            "How many kessel speeds?",
            "Are they part of the empire?",
            "Are they a rebel?",
            "Are they part of the Resistance?",
            "Are they part of the First Order?",
            "Are they part of the Separatist?",
            "Are they part of the Galactic Republic?",
            "Are they a ewok?",
            "Are they fluffy?",
            "Are they slimey?",
            "What is their lightsaber color?",
            "Are they tall?",
            "Are they short?",
            "Are they a pilot?",
            "Are they annoying?",
            "Are they cute?",
            "Are they Banders Fav?",
            "Have they gotten their butt whooped?",
            "Have they been inside a tauntaun?",
            "Are they still living?",
            "Have they lost a limb?",
            "Are they a Space Balls character?"};

        p1QuestionOptions =  (ArrayList<String>) Arrays.asList(optionPrompStrings)
        Collections.copy(p2QuestionOptions, p1QuestionOptions);

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
        while(randOptions.size()<(Math.round((avalibleChars.size()/2)))){
            potCharIndex = rand.nextInt(avalibleChars.size());
            if(!randOptions.contains(potCharIndex)){
                randOptions.add(potCharIndex);
            }
        }
        for(int i = 0; i < randOptions.size();i++){
            p1Initalization.add(avalibleChars.get(randOptions.get(i)));
            p2Initalization.add(avalibleChars.get(randOptions.get(i)));
        }

        Collections.shuffle(p2Initalization);
        /*
        for(int i = 0; i < p1Initalization.length; i++){
            System.out.println("Player 1: "+ p1Initalization[i]+"\nPlayer 2: "+p2Initalization[i]);
        }
        */
        p1Board.fillBoard(p1Initalization);
        p2Board.fillBoard(p2Initalization);

       
    }

    public static void testCharacteristic(){
        if(playerTurn%2 == 0) playerOptions = p1QuestionOptions;
        else                  playerOptions = p2QuestionOptions;
        printOptions(playerOptions);
        
    }

    public static void printOptions(ArrayList<String> avalibleOptions){
        for(byte i = 0; i < avalibleOptions.size();i++){
            System.out.println(String.valueOf(i+1)+". "+avalibleOptions.get(i));
        }
    }
}