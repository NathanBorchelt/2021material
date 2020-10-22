import java.util.Scanner;

public class ChooseYourPath{
    public static void main(String[] args) { 
    String initialOptions;
    String iO1 = ""; String iO2 = ""; 
    String usefulItems = "dtclcwfd";
    String allItems = usefulItems + "bkcpnj"; 
    Boolean selectedValidItem1 = false;
    Boolean selectedValidItem2 = false;  
    String generalInput;
    Scanner in = new Scanner(System.in);
    while((selectedValidItem1 == selectedValidItem2)){
        System.out.println("You are stranded on an island, and you can only have two of the things listed below, what do you choose:");
        System.out.println("Duct Tape (dt)\nBooks (bk)\nFood (fd)\nClothes (cl)\nCleen Water (cw)\nCellphone (cp)\nYour Neighbor, Joe (nj)");
        System.out.print("\nPLease enter the two seperated by a space: "); initialOptions = in.nextLine();
        iO1 = initialOptions.substring(0,2);
        iO2 = initialOptions.substring(initialOptions.lastIndexOf(" ")+1);
        if (allItems.indexOf(iO1) != -1 && allItems.indexOf(iO2) != -1){
            selectedValidItem1 = true;
        }}
        if (iO1.equals("dt") || iO2.equals("dt")){
            System.out.println("You are able to use the duct tape to craft a simple hut to protect yourself from the elements.");
        }
        else{
            System.out.println("You can see a cave. What do you do?");
        }
        if(iO1.equals("cl") || iO2.equals("cl")){
            System.out.println("As night falls, you throw on extra layers and stay warm during the night.");
        }
        else{
            System.out.println("As night falls, you find some leaves. What do you do?");
        }
        if(iO1.equals("cw") || iO2.equals("cw")){
            System.out.println("You feel thirsty, you pull out one of the water bottles you found and take a drink.");
            }
        else{
            System.out.println("You see some berries, and some rabbits eating them. What do you do?\nEat a Rabbit (er)\nEat the Barries (eb)");
            generalInput = in.nextLine();
            if (generalInput.equals("er")) {
                System.out.println("You went for a rabbit, and ate it raw right there.");
                }
            System.out.println("You eventually keel over due to sickness and go to sleep, never to wake up.");
            }
                System.out.println("Because you picked " + ((usefulItems.indexOf(iO1) != -1) && (usefulItems.indexOf(iO2) != -1) ? "two" : "one")+ " of useless items, you did not make it off the island");
    in.close();
    }
}