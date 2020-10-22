import java.net.URI;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;


public class BorcheltWebstoreInfo {
    static SnackMenu snackMenu = new SnackMenu();
    static ArrayList<Snack> snacks = snackMenu.getSnacks();
    public static void main(String[] args) {
        for(Snack s:snacks){
            System.out.println(s.getName()+ " " +s.getId()+" "+s.getQuantity()+" "+s.getCost()+" "+s.getsURL());
        }
        Scanner in = new Scanner(System.in);
        String input = "";
        System.out.println("Type 'q' to quit.");
        while (!input.equals("q")){
            input = in.nextLine();
            for (int i=0;i < snacks.size();i++){
                if(snacks.get(i).equals(input)){
                    System.out.println(snacks.get(i).getCost() +"  "+snacks.get(i).getQuantity());
                    try{
                    URI uri = new URI(snacks.get(i).getsURL());
                    java.awt.Desktop.getDesktop().browse(uri);
                    System.out.println("Web Page Opened in Browser");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        in.close();
    }
}
