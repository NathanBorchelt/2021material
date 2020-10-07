import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//https://www.dotnetperls.com/line-count-java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BorcheltBellringer {
    public static void main(String[] args) {
        String[] checkNames = { "Hunter", "Robert", "Rob", "Robbie", "Bob", "Bobby", "Bobbie", "Zach", "Zachary",
                "Zack", "Luke", "Lu", "Pat", "Patrick", "Andrew", "Andy", "Nate", "Nathan", "Nathaniel", "Reese",
                "Caleb", "Nick", "Nic", "Nicholas", "Nicholi", "Nikoli" };

        int numLines = countLines();

        ArrayList<String> arrListNames = new ArrayList<String>();

        for (String n : checkNames) {
            arrListNames.add(n);
        }
        for (int i = 0; i < arrListNames.size(); i++) {
            try {
                Scanner sc = new Scanner(new File("childrensbooksearly1900s.txt"));
                for (int j = 0; j < numLines; j++) {
                    String temp = sc.nextLine().trim();
                    if (temp.equals(arrListNames.get(i))) {
                        System.out.println(arrListNames.get(i) + " is in the text file");
                        break;
                    }
                    if (j == numLines - 1) {
                        System.out.println(arrListNames.get(i) + " is not in the list");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading or parsing childrensbooksearly1900s.txt" + e);
            }
        }
    }

    public static int countLines() throws IOException {

        int lineCount = 0;

        // Get BufferedReader.
        BufferedReader reader = new BufferedReader(new FileReader("\\Name.txt"));

        // Call readLine to count lines.
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            lineCount++;
        }
        reader.close();

        // Display the line count.
        return lineCount;
    }   
}
