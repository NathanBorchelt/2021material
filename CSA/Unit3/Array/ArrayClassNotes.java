public class ArrayClassNotes{
    public static void main(String[] args) {
        int[] goals = {1,2,0,3,2,4,2,1,0,2,0,1,3,2};

        System.out.println(goals);
        System.out.println(goals[0]);
        for (int i=0;i<goals.length;i++){
            System.out.print(goals[i]);
        int[] ourGoals = new int[28];
        int[] rando = {1,2,3,4,5,6,7,8,9,10};
        printArray(rando);
        for (int i = 0;i<rando.length;i++){
            rando[i]+=10;
        }
        printArray(rando);
        }
        String[] daysOfTheWeek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        String[] lunch = {"lunch1","lunch2","lunch3","lunch4","Lunch5","lunch6","lunch7"};
        for (int i = 0;i<lunch.length;i++){
            System.out.println(daysOfTheWeek[i]+":  "+lunch[i]);
        }

        for(int g:goals){
            System.out.println(g);
        }
    }
    public static void printArray(int[] listy){
        for (int i=0;i<listy.length;i++){
            System.out.print(listy[i]);
        }
    }
}