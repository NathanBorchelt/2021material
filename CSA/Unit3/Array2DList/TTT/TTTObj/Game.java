import java.util.Scanner;
import java.util.Random;
public class Game{
    Scanner in = new Scanner(System.in);
    Random rand = new Random();

    private Board board = new Board();

    boolean win1, win2, tie, p1, vsPC;
    int row,column;

    public static void main(String[] args) {
        
        Game game = new Game();
        game.play();
    }
    public void play(){
        gameMenu();
        while(!win1 && !win2 && !tie){
            //board is an object of the Board class, use this to call it in this function.
            //this is required because the game is ran as an object
            System.out.println(this.board);
            makeMove();
            winning();
        }
        if(win1){
            System.out.println("p1 Win");
        }
        if(win2){
            System.out.println("p2 win");
        }
    }
    public void gameMenu(){
        System.out.println("1: for single player\n2: for two player\n3: for quit\n");
        int menuSelection = in.nextInt();
        if(menuSelection == 1){
            this.vsPC = true;
        }
        else if(menuSelection == 2){
            this.vsPC = false;
        }
        else{
            System.out.println("Shutting down ........... beoooooop");
            System.exit(1);
        }
        System.out.println("");
    }
    public void setPlayer(){
        if(rand.nextInt(2)==1){
            p1=true;
        }
        else{
            p1=false;
        }
    }
    public String getPlayer(){
        if(p1){
            return ("Player 1: ");
        }
        else{
            return ("Player 2: ");
        }
    }
    public void takeMove(){
        if(!p1 && vsPC){
            //this is where comp takes turn
            row = rand.nextInt(3);
            column = rand.nextInt(3);
            //if comp try to tak efilled space
            if(!board.getBoxes()[row][column].getBoxStat().equals(" ")){
                //recursion
                takeMove();
            }
        }
        else{
            //real person
            //print nested conditional
            System.out.println((p1 ? "\nPlayer1:" : "\nPlayer2:")+"\nRow:   ");
            
            row = in.nextInt()-1;
            if (row<0||row>2){
                System.err.println("Illegal move! The number of rows is 1 to 3.");
                takeMove();
            }
            System.out.println("Column:   ");
            column = in.nextInt()-1;
            if (column<0||column>2){
                System.err.println("Illegal move! The number of columns is 1 to 3.");
                takeMove();
            }
            if(!board.getBoxes()[row][column].getBoxStat().equals(" ")){
                System.err.println("That space is already taken");
                takeMove();
            }
        }
    }
    public void makeMove(){
        takeMove();
        if(p1){
            //player2 is O
            board.getBoxes()[row][column].setBoxStat("X");
        }
        else{
            //player1 is X
            board.getBoxes()[row][column].setBoxStat("O");
        }
        p1 = !p1;
    }
    public void winning(){
        for(int i = 0; i<3;i++){
            if((board.getBoxes()[i][0].getBoxStat().equals(board.getBoxes()[i][1].getBoxStat())) && (board.getBoxes()[i][1].getBoxStat().equals(board.getBoxes()[i][2].getBoxStat())) && (!board.getBoxes()[i][0].getBoxStat().equals(" "))){
                if(board.getBoxes()[i][0].getBoxStat().equals("X")){
                    win1 = true;
                    break;
                }
                else{
                    win2 = true;
                    break;
                }
            }
            else if((board.getBoxes()[0][i].getBoxStat().equals(board.getBoxes()[1][i].getBoxStat())) && (board.getBoxes()[1][i].getBoxStat().equals(board.getBoxes()[2][i].getBoxStat())) && (!board.getBoxes()[0][i].getBoxStat().equals(" "))){
                if(board.getBoxes()[0][i].getBoxStat().equals("X")){
                    win1 = true;
                    break;
                }
                else{
                    win2 = true;
                    break;
                }
            }
        }
        if((board.getBoxes()[0][0].getBoxStat().equals(board.getBoxes()[1][1].getBoxStat())) && (board.getBoxes()[1][1].getBoxStat().equals(board.getBoxes()[2][2].getBoxStat())) && (!board.getBoxes()[0][0].getBoxStat().equals(" "))){
            if(board.getBoxes()[0][0].getBoxStat().equals("X")){
                win1 = true;
                break;
            }
            else{
                win2 = true;
                break;
            }
        }
        else if((board.getBoxes()[0][2].getBoxStat().equals(board.getBoxes()[1][1].getBoxStat())) && (board.getBoxes()[1][1].getBoxStat().equals(board.getBoxes()[2][0].getBoxStat())) && (!board.getBoxes()[0][2].getBoxStat().equals(" "))){
            if(board.getBoxes()[0][2].getBoxStat().equals("X")){
                win1 = true;
                break;
            }
            else{
                win2 = true;
                break;
            }
        }
    }
    
}