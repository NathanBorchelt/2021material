import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//https://www.journaldev.com/709/java-read-file-line-by-line
import java.util.ArrayList;
import java.util.Scanner;

public class GuessWho{
    public static void main(String[] args) {
        ArrayList<SWCharacter> avalibleChars = new ArrayList<SWCharacter>();
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

        for(int i = 0; i < avalibleChars.size(); i++){
            System.out.println(avalibleChars.get(i).getName());
        }
    }
}