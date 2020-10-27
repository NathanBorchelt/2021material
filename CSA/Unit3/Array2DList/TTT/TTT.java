import java.util.Scanner;
public class TTT {

    //check for valid input, so no letters and between the correct range
    public static int getValidInt(String prompt) {
        Scanner in = new Scanner(System.in);
        // done: :since it is only used here, only create it in here
        System.out.println(prompt);
        String input = in.nextLine();
        int num = 0;
        while(true){
            //Try to change String to int,
            try{
                num = Integer.valueOf(input);// *String to int
                if (num < 0 || num > 2){
                    System.out.println("Must be between 0 and 2");
                    continue;
                }
            }
            //if it doesn't, print it is invalid
            catch(Exception e){
                System.out.println("Invalid Input");
                continue;
            }
        return num;
        }
    }
 
    //check the rows for winner
    
    public static boolean checkRows(int[][] A){
        for (int i = 0;i<A.length;i++){
            if(A[i][0]==A[i][1] && A[i][1]==A[i][2] && A[i][0] != 0){
                return true;
            }
        }
        return false;
    }

    //check the cols for winner
    
    public static boolean checkCols(int[][] A){
        for (int i = 0;i<A.length;i++){
            if(A[0][i]==A[1][i] && A[1][i]==A[2][i] && A[0][i] != 0){
                return true;
            }
        }
    return false;
    }
    //check the diagonals for winner
    
    public static boolean checkDiags(int[][] A){
        if(A[0][0]==A[1][1] && A[1][1]==A[2][2] && A[1][1] != 0){
            return true;
            }
        else if(A[0][2]==A[1][1] && A[1][1]==A[2][0] && A[1][1] != 0){
            return true;
            }
        else{
            return false;
        }
    }
    //check for any of the rows, cols, and diagonals are true
    
    public static boolean checkHit(int[][] A){
        if(checkCols(A) || checkRows(A) || checkDiags(A)){
            return true;
        }
        return false;
    }
    //is the spot you chose free?
    
    public static boolean isFree (int[][] A, int row, int col) {
        if(A[row][col] == 0){
            return true;
        }
        return false;
    }
    
    public static boolean getWinner(String turnPrompt, int[][] A, int playerNumber) {
        System.out.println(turnPrompt);
        int row,col=0;
        while(true){
            row = getValidInt("Enter row:  ");
            col = getValidInt("Enter col:  ");
            if(isFree(A,row,col)){
                break;
            }
            System.out.printf("[%d,%d] is already filled!\n",row,col);
        }
        A[row][col]=playerNumber;
        return checkHit(A);
    }

    //print the board
    
    public static void printBoard(int[][] A) {
        System.out.println("-------------");
        for(int[] row : A){
            for(int num : row){
                System.out.printf("| %d ",num);
            } System.out.println("|\n-------------");
        }
    }

    //main class
    
    public static void main(String[] args) {
    
        
    
        int[][] grid = new int[3][3];
    
        int foundWinner = 1;
        int turns = 1;
        while(turns < 10){	//getWinner will determine if main loop is broken
            //player1
            if(turns%2==1){
                if(getWinner("Player1 Turn",grid,1)){
                    foundWinner=1;
                    System.out.println("Player1 Wins!");
                    printBoard(grid);
                }
            }
            //player2
            else{
                if(getWinner("Player2 Turn",grid,2)){
                    foundWinner=2;
                    System.out.println("Player2 Wins!");
                    printBoard(grid);
                }
            }
            turns++;
            printBoard(grid);
    
        }
    }    
}
    
    