import java.util.Random
/** 
 * A game board of NxM board of tiles.
 * 
 *  @author PLTW
 * @version 2.0
 */

/** 
 * A Board class for concentration
 */
public class Board
{  
  private static String[] tileValues = {"lion", "lion",
                                        "penguin", "penguin",
                                        "dolphin", "dolphin",
                                        "fox", "fox",
                                        "monkey", "monkey",
                                        "turtle", "turtle"}; 
  private Tile[][] gameboard = new Tile[3][4];

  /**  
   * Constructor for the game. Creates the 2D gameboard
   * by populating it with card values
   * 
   */
  public Board(){
    /* your code here */ 
    //step 14 in 3.8.2
    short tileCount = (short) (tileValues.length-1);
    /*
    for(short i = 0; i < gameboard.length;i++){
        for(short j = 0; j < gameboard[0].length;j++){
            gameboard[i][j] = new Tile(tileValues[tileCount]);
            tileCount++;
        }
     */   
    //System.out.println(java.util.Arrays.toString(gameboard[0]));
    //System.out.println(java.util.Arrays.toString(gameboard[1]));
    //System.out.println(java.util.Arrays.toString(gameboard[2]));
    Random rand = new Random();
    for(short i = 0;i<gameboard.length;i++){
      for(short j = 0; j<gameboard[i].length;j++){
        short randSet = (short) rand.nextInt(tileCount);
        gameboard[i][j] = new Tile(tileValues[randSet]);
        tileValues[randSet] = tileValues[tileCount];
        tileCount--;
      }
    }
}

    /* 
   * Returns a string representation of the board, getting the state of
   * each tile. If the tile is showing, displays its value, 
   * otherwise displays it as hidden.
   * 
   * Precondition: gameboard is populated with tiles
   * 
   * @return a string represetation of the board
   */
  public String toString(Tile[][] gameboard)
  {
    String value = "";
    for(Tile[] row : gameboard){
      for(Tile item : row){
        // done  is visable ?
        if(item.isShowingValue()){
          value+=item.getValue();
        }
        else{
          value+=item.getHidden();
        }
        // done tab for spacing
        value+="\t";
      }
      value+="\n";
    }
 
    return value;
  }

  /* 
   * Determines if the board is full of tiles that have all been matched,
   * indicating the game is over.
   * 
   * Precondition: gameboard is populated with tiles
   * 
   * @return true if all tiles have been matched, false otherwse
   */
  public boolean allTilesMatch()
  {

    // done return false unless all are true
    for(Tile[] row : gameboard){
      for(Tile item : row){
        if(!item.matched()){
          return false;
        }
      }
    }
    return true;
  }

  /* 
   * Sets the tile to show its value (like a playing card face up)
   * 
   * Preconditions:
   *   gameboard is populated with tiles,
   *   row values must be in the range of 0 to gameboard.length,
   *   column values must be in the range of 0 to gameboard[0].length
   * 
   * @param row the row value of Tile
   * @param column the column value of Tile
   */
  public void showValue (int row, int column){  
    gameboard[row][column].show();
  }  

  /** 
   * Checks if the Tiles in the two locations match.
   * 
   * If Tiles match, show Tiles in matched state and return a "matched" message
   * If Tiles do not match, re-hide Tiles (turn face down).
   * 
   * Preconditions:
   *   gameboard is populated with Tiles,
   *   row values must be in the range of 0 to gameboard.length,
   *   column values must be in the range of 0 to gameboard[0].length
   * 
   * @param row1 the row value of Tile 1
   * @param col1 the column value of Tile 1
   * @param row2 the row value of Tile 2
   * @param col2 the column vlue of Tile 2
   * @return a message indicating whether or not a match occured
   */
  public String checkForMatch(int row1, int col1, int row2, int col2)
  {
    String msg = "";

    Tile t1 = gameboard[row1][col1];
    Tile t2 = gameboard[row2][col2];

    if(t1.equals(t2)){
      t1.foundMatch();
      t2.foundMatch();
      msg = "Matched";
    }
    else{
      t1.hide();
      t2.hide();
    }
    
     return msg;
  }

  /** 
   * Checks the provided values fall within the range of the gameboard's dimension
   * and that the tile has not been previously matched
   * 
   * @param rpw the row value of Tile
   * @param col the column value of Tile
   * @return true if row and col fall on the board and the row,col tile is unmatched, false otherwise
   */
  public boolean validateSelection(int row, int col)
  {
    if(row>=gameboard.length){if(col>=gameboard[row].length){return false;}return false;}
    if(gameboard[row][col].matched())return false;
    /* your code here */

    return true;
  }

}
