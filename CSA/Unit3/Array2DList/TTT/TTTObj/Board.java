public class Board{
    private Box boxes [][] = {{new Box(),new Box(),new Box()},
                              {new Box(),new Box(),new Box()},
                              {new Box(),new Box(),new Box()}};
    //create getter
    //since retuning Box[][] must delcare in method
    public Box[][] getBoxes(){
        return this.boxes;
    }
    public String toString(){
        String outString = "";
        for(Box[] box: boxes){
            for(Box symbol : box){
                outString+=("["+symbol.getBoxStat()+"]");
            }
        outString+="\n";
        }
        return outString;
    }
}
