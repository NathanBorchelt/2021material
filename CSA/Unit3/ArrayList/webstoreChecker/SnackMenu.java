import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class SnackMenu{
    private ArrayList<Snack> snackMenu = new ArrayList<Snack>();

    public SnackMenu(){
        try{
            Scanner sc = new Scanner(new File("SnackServerPull.txt"));
            while(sc.hasNextLine()){
                String temp = sc.nextLine().trim();
                String[] tokens = temp.split("\t");
                int id = Integer.valueOf(tokens[0]);
                String name = tokens[1];
                double cost = Double.valueOf(tokens[2]);
                int quantity = Integer.valueOf(tokens[3]);
                String sURL = tokens[4];

                snackMenu.add(new Snack(id, name, quantity, cost, sURL));
            }
        }
        catch (Exception e){
            System.out.println("IDK, error reading, check the file");
        }
    }

    public ArrayList<Snack> getSnacks(){
        return snackMenu;
    }
}