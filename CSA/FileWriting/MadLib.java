public class MadLib {
    public static void main(String[] args) {
        BufferWriter madlibOut  = new BufferWriter("madOutText.txt");
        BufferWriter madRefText = new BufferWriter("madRefText.txt");

        java.util.Scanner in = new java.util.Scanner(System.in);

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


        String[] optionStrings = {company, company, pluralNoun, number, nounIng, regNoun1, company, maleName, color,regNoun2, prefix, pastVerb, regNoun2, maleName, adjective, room};
        int i = 0;
        while(madRefText.readString() != null) {
            String line = madRefText.readString();
            if (line.contains("%")){
                line.replace("%", optionStrings[i]);
                i++;
            }
            madlibOut.writeString(line);
        }

        madRefText.saveAndClose();
        madlibOut.saveAndClose();

    }

}
