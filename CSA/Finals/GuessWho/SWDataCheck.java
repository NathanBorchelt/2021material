import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SWDataCheck {
    public static void main(String[] args) {
        ArrayList<SWCharacter> avalibleChars = new ArrayList<SWCharacter>();
        try{
            //https://www.journaldev.com/709/java-read-file-line-by-line
            BufferedReader allFile = new BufferedReader(new FileReader("SWData.txt"));
            String fileLine = allFile.readLine();
            while(fileLine != null){
                //System.out.println(fileLine);
                SWCharacter swchar = new SWCharacter(fileLine);
                avalibleChars.add(swchar);
                fileLine = allFile.readLine();
            }
            allFile.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        for(SWCharacter swc : avalibleChars){
            System.out.println(swc.getName());
            System.out.println(swc.isJedi() + "\t" +
            swc.isDroid() + "\t" +
            swc.isHumanLike() + "\t" +
            swc.isWookie() + "\t" +
            swc.isDarkSide() + "\t" +
            swc.isLightSide() + "\t" +
            swc.isBountyHunter() + "\t" +
            swc.isSmuggler() + "\t" +
            swc.getKesselSpeed() + "\t" +
            swc.isEmpire() + "\t" +
            swc.isRebel() + "\t" +
            swc.isResistance() + "\t" +
            swc.isFirstOrder() + "\t" +
            swc.isSeparatist() + "\t" +
            swc.isGalacticRepublic() + "\t" +
            swc.isEwok() + "\t" +
            swc.isFluffy() + "\t" +
            swc.isSlimey() + "\t" +
            swc.getLightsaberColor() + "\t" +
            swc.isTall() + "\t" +
            swc.isShortH() + "\t" +
            swc.isPilot() + "\t" +
            swc.isAnnoying() + "\t" +
            swc.isCute() + "\t" +
            swc.isBandersFav() + "\t" +
            swc.isGotButtWhooped() + "\t" +
            swc.isInsideATauntaun() + "\t" +
            swc.isStillLiving() + "\t" +
            swc.isLostALimb() + "\t" +
            swc.isSpaceBallsCharacter());
        }
    }
}
