public class PlayerBoard {
    private SWCharacter[] playerBoard  = new SWCharacter[25];

    public void fillBoard(SWCharacter[] swChars){
        this.playerBoard = swChars;
    }

    public SWCharacter returnChar(byte index){
        try{
            return playerBoard[index];
        }
        catch(Exception e){
            return null;
        }
    }
    public void changeValidity(byte index){
        playerBoard[index].validity(!playerBoard[index].isValidChar());
    }

}
