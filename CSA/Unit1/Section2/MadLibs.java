import java.util.Scanner;
public class MadLibs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);//how to get inputs
    //input code
        System.out.print("Name of a company:  "); String company = in.nextLine();//Asking the question than setting up the input, used only in.nextLine because I only use strings
        System.out.print("Plural noun:  "); String pluralNoun = in.nextLine();
        System.out.print("Number:  "); String number = in.nextLine();
        System.out.print("Present tense verb ending in \"ing\":  "); String nounIng = in.nextLine();
        System.out.print("Noun:  "); String regNoun1 = in.nextLine();
        System.out.print("The name of a man or boy:  "); String maleName = in.nextLine();
        System.out.print("Your favorite color:  "); String color = in.nextLine();
        System.out.print("Noun:  "); String regNoun2 = in.nextLine();
        System.out.print("Enter a Prefix:   "); String prefix = in.nextLine();
        System.out.print("Past tense verb:  "); String pastVerb = in.nextLine();
        System.out.print("Adjective:  "); String adjective = in.nextLine();
        System.out.print("Kind of room in a building (such as \"the attic\", \"the hallway\", etc.):  "); String room = in.nextLine();
        //output code
        System.out.printf("\n\nThe Office at %s\n",company);//Used printf so i could easily add in the variables, uned the \n so it is not one massive line
        System.out.printf("At %s, everyone sits in %s that\n",company,pluralNoun);
        System.out.printf("are spaced %s feet away from each other. This\n",number);
        System.out.printf("makes %s on the %s a public\n",nounIng,regNoun1);
        System.out.println("activity, because everyone around can hear\nwhat you are saying. It's a wonder that people\nget any work done with all the noise.");
        System.out.printf("\n\nThere is an emplyee at %s named\n",company);
        System.out.printf("%s, he used to have a %s %s on his\n",maleName,color,regNoun2);
        System.out.printf("desk, but someone %s%s it. After the loss\n",prefix,pastVerb);
        System.out.printf("of his %s. %s started muttering to himself\n",regNoun2,maleName);
        System.out.printf("a lot. He looked really %s. I guess he was\n",adjective);
        System.out.printf("annoying people, because the company moved\nhis desk into the %s",room);
        
        /*
        Stolen from https://oceancat.com/madlibs/office.htm
        The Office at Dundermiflin
At Dundermiflin, everybody sits in cars that
are spaced 15 feet away from each other. This
makes racing on the sandwich a public activity,
because everyone around can hear what you are 
saying. It's a wonder that people get any work 
done with all the noise.

There is an employee at Dundermiflin named Jim. 
He used to have a orange water on his desk, but
somebody started it. After the loss of his water,
Jim started muttering to himself a lot. He looked
really smelly. I guess he was annoying people,
because the company moved his desk into the dinning room.
        */
    }
}