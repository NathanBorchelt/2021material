public class PrintBoard{
    public static void main(String args[]){
        printBoard();
    }

    public static void printBoard(){
        int[][] zeroArrays = new int[3][3];
        System.out.println("-------------");
        for(int[] zero : zeroArrays){
            for(int num : zero){
                System.out.printf("| %d ",num);
            }
            System.out.print("|\n");
            System.out.println("-------------");
        }

    }
}
