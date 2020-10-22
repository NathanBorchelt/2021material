public class Array2dNotes{
    public static void main(String[] args) {
        /*
        System.out.println("Array 2D Practive");
        String board[][] = { {"close", "quarter"},
                            {"moon", "rock"},
                            {"band", "stand"},
                            {"out", "shine"} }; 
        //is a 2x4 array using columnxrow nuumbering system
        //this is not dynamic, no apending
        //cannot remove items, but can be set to null
        for(String[] row:board){
            System.out.println();
            for(String column:row){
                //System.out.println(column);
                System.out.print(column+" ");
            }
        }*/
        String ticTacToe[][] =  {{"1","2","3"},
                                 {"4","5","6"},
                                 {"7","8","9"}};
        printBoard(ticTacToe);
        
    }

    public static void printBoard(String[][] board){
        for(int r = 0; r<board.length; r++){
            for(int c = 0; c<board[r].length; c++){
                if (c<board[r].length-1){
                    System.out.print(board[r][c] + " | ");
                }
                else{
                    System.out.print(board[r][c]);
                }
            }
            if(r<board.length-1){
                System.out.println();
                System.out.println("----------");
            }
        }
    }
}