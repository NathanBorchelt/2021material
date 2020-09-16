import java.util.Scanner;
public class LoopSP {
    public static void main(String[] args) {
        //howMany();
        //reverse();
        //removeEveryOther();
        removeVowels();
        //removeDoubleSpace();
        //dyslexia();
        //duplicateChars();
        //anagrams();
    }
    public static void howMany(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string");
        String input = sc.nextLine();
        String pattern = "aaa";
        int howMany = 0;
        for ( int i = 0; i < input.length() - pattern.length() + 1; i++){
            //for loop to iterate through the string
            String currSeq = input.substring(i, i + pattern.length());
            if (currSeq.equals(pattern)){
                howMany++;
            }
        }
        System.out.println("There were " + howMany + " substrings matching " + pattern);
    }
    public static void reverse(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String: ");
        String input = sc.nextLine();
        
        String output = "";
        
        for (int i = input.length(); i > 0; i--)
        {
          output += input.substring(i - 1, i);
          /*i starts at 5, for the word hello, substring ALWAYS runs 
          throught the object from left to right 
          substring(start char, end char) does not return the end char*/
        }
        
        System.out.println(output);
        sc.close();
      }
    public static void removeEveryOther(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String: ");
        String input = sc.nextLine();
        String output = "";
        //i+=2 makes it do every other
        //if we wanted el instead of hlo from hello, change in i to one
        for (int i = 0; i < input.length(); i += 2){output += input.substring(i,i+1);}
        System.out.println(output);
        sc.close();
        }
    public static void removeVowels(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String: ");
        String input = sc.nextLine();
        String output = "";
        boolean yVowel = true;

        for (int i = 0;i<input.length();i++){
            String x = input.substring(i, i+1);
            if(x.equals("a") || x.equals("e") || x.equals("i") || x.equals("o") || x.equals("u"))
            {yVowel = false;}
            if((x.equals("y") && (yVowel == false)) || (!(x.equals("a") || x.equals("e") || x.equals("i") || x.equals("o") || x.equals("u") || (x.equals("y") && yVowel == true))))
            {output+=x;}
        }
        System.out.println(output);
        sc.close();
    }
    public static void removeDoubleSpace(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String: ");
        String input = sc.nextLine();
        String output = "";
        String pattern = "  ";
        int howMany = 0;
        while(input.indexOf(" ") != -1){
            //for loop to iterate through the string
            input = input.substring(0, input.indexOf("  ")) + //space space
            input.substring(input.indexOf("  ")+1);
            }
    }
}