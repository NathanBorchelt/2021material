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

    public static boolean checkingSide;
    public static boolean isDoesDoesNot = false;
    public static boolean isInteger = false;
    public static byte atributeType;

    public static boolean computer = false;
    public static byte playerTurn = -127;

    public static PlayerBoard p1Board = new PlayerBoard();
    public static PlayerBoard p2Board = new PlayerBoard();

    public static PlayerBoard playerChars = new PlayerBoard();

    public static ArrayList<String> questionOptions = new ArrayList<String>();

    public static Scanner in = new Scanner(System.in);

    public static String selectionOption;
    public static String doesDoesNotHaveQuestion;

    public static void main(String[] args) {
        Random rand = new Random();

        ArrayList<SWCharacter> avalibleChars = new ArrayList<SWCharacter>();
        ArrayList<Integer> randOptions = new ArrayList<Integer>();
        ArrayList<SWCharacter> p1Initalization  = new ArrayList<SWCharacter>();
        ArrayList<SWCharacter> p2Initalization  = new ArrayList<SWCharacter>();

        String[] optionPrompStrings = {
            "Are they a jedi?",//1
            "Are they a droid?",
            "Are they a humanoid?",
            "Are they a wookie?",
            "Are they part of the Dark Side?",
            "Are they part of the Light Side?",
            "Are they a bounty hunter?",
            "Are they a smuggler?",
            "How many kessel speeds?",//9
            "Are they part of the empire?",
            "Are they a rebel?",
            "Are they part of the Resistance?",
            "Are they part of the First Order?",
            "Are they part of the Separatist?",
            "Are they part of the Galactic Republic?",
            "Are they a ewok?",
            "Are they fluffy?",
            "Are they slimey?",
            "What is their lightsaber color?",//19
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

        questionOptions =  (ArrayList<String>) Arrays.asList(optionPrompStrings)

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
        if(playerTurn%2 == 0) playerChars = p1Board;
        else                  playerChars = p2Board;
        printOptions(questionOptions);
        while(!isInteger){
            System.out.println("Type in the option number.");
            selectionOption = in.nextLine();
            try{
                atributeType = Byte.valueOf(selectionOption);
                if(atributeType <= questionOptions.size()){
                    isInteger = true;
                }
                } catch (Exception e){
                System.out.println("That is not a valid option");
            }
        }
        isInteger = false;
        while(!isDoesDoesNot || (atributeType != 19 || atributeType != 9)){
            System.out.println("Type \"Has\" if they do have the atribute or \"Not\" if they do not.");
            doesDoesNotHaveQuestion = in.nextLine();
            if(doesDoesNotHaveQuestion.equalsIgnoreCase("has")){
                checkingSide = true;
                isDoesDoesNot = true;
            }
            else if(doesDoesNotHaveQuestion.equalsIgnoreCase("not")){
                checkingSide = false;
                isDoesDoesNot = true;
            }
        }
        isDoesDoesNot = false;

        
        if (atributeType != 9 || atributeType != 19){
            for(byte index = 0; index < playerChars.length(); index++){
                if(checkingSide != playerChars.returnChar(index).questionB(atributeType)){
                    playerChars.invalidChar(index);
                }
            }
        }
        else if(atributeType == 9){
            System.out.println("Enter how many Kessel runs has the character done?");
            boolean
            for(byte index = 0; index < playerChars.length(); index++){
                if(checkingSide != playerChars.returnChar(index).questionI(atributeType)){
                    playerChars.invalidChar(index);
                }
            }
    }

    public static void printOptions(ArrayList<String> avalibleOptions){
        for(byte i = 0; i < avalibleOptions.size();i++){
            System.out.println(String.valueOf(i+1)+". "+avalibleOptions.get(i));
        }
    }
}