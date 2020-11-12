import java.util.ArrayList;
public class SWCharacter{
    private String name;
    private boolean jedi;
    private boolean droid;
    private boolean humanLike;
    private boolean wookie;
    private boolean darkSide;
    private boolean lightSide;
    private boolean bountyHunter;
    private boolean smuggler;
    private byte kesselSpeed;
    private boolean empire;
    private boolean rebel;
    private boolean resistance;
    private boolean firstOrder;
    private boolean separatist;
    private boolean galacticRepublic;
    private boolean ewok;
    private boolean fluffy;
    private boolean slimey;
    private String lightsaberColor;
    private boolean tall;
    private boolean shortH;
    private boolean pilot;
    private boolean annoying;
    private boolean cute;
    private boolean bandersFav;
    private boolean gotButtWhooped;
    private boolean insideATauntaun;
    private boolean stillLiving;
    private boolean lostALimb;
    private boolean spaceBallsCharacter;
    private boolean validChar;

    public SWCharacter(String charInfo){
        String optionsString[] = charInfo.split(",");
        this.name = optionsString[0];
        this.jedi = (Byte.valueOf(optionsString[1])==1);
        this.droid = (Byte.valueOf(optionsString[2])==1);
        this.humanLike = (Byte.valueOf(optionsString[3])==1);
        this.wookie = (Byte.valueOf(optionsString[4])==1);
        this.darkSide = (Byte.valueOf(optionsString[5])==1);
        this.lightSide = (Byte.valueOf(optionsString[6])==1);
        this.bountyHunter = (Byte.valueOf(optionsString[7])==1);
        this.smuggler = (Byte.valueOf(optionsString[8])==1);
        this.kesselSpeed = Byte.valueOf(optionsString[9]);
        this.empire = (Byte.valueOf(optionsString[10])==1);
        this.rebel = (Byte.valueOf(optionsString[11])==1);
        this.resistance = (Byte.valueOf(optionsString[12])==1);
        this.firstOrder = (Byte.valueOf(optionsString[13])==1);
        this.separatist = (Byte.valueOf(optionsString[14])==1);
        this.galacticRepublic = (Byte.valueOf(optionsString[15])==1);
        this.ewok = (Byte.valueOf(optionsString[16])==1);
        this.fluffy = (Byte.valueOf(optionsString[17])==1);
        this.slimey = (Byte.valueOf(optionsString[18])==1);
        this.lightsaberColor = optionsString[19];
        this.tall = (Byte.valueOf(optionsString[20])==1);
        this.shortH = (Byte.valueOf(optionsString[21])==1);
        this.pilot = (Byte.valueOf(optionsString[22])==1);
        this.annoying = (Byte.valueOf(optionsString[23])==1);
        this.cute = (Byte.valueOf(optionsString[24])==1);
        this.bandersFav = (Byte.valueOf(optionsString[25])==1);
        this.gotButtWhooped = (Byte.valueOf(optionsString[26])==1);
        this.insideATauntaun = (Byte.valueOf(optionsString[27])==1);
        this.stillLiving = (Byte.valueOf(optionsString[28])==1);
        this.lostALimb = (Byte.valueOf(optionsString[29])==1);
        this.spaceBallsCharacter = (Byte.valueOf(optionsString[30])==1);
        this.validChar = true;
    }

    public String toString(){return name;}
    public void validity(boolean validity){this.validChar = validity;}

    public String getName() { return name; }
    public byte getKesselSpeed() { return kesselSpeed; }
    public String getLightsaberColor() { return lightsaberColor; }
    public boolean isAnnoying() { return annoying; }
    public boolean isBandersFav() { return bandersFav; }
    public boolean isBountyHunter() { return bountyHunter; }
    public boolean isCute() { return cute; }
    public boolean isDarkSide() { return darkSide; }
    public boolean isDroid() { return droid; }
    public boolean isEmpire() { return empire; }
    public boolean isEwok() { return ewok; }
    public boolean isFirstOrder() { return firstOrder; }
    public boolean isFluffy() { return fluffy; }
    public boolean isGalacticRepublic() { return galacticRepublic; }
    public boolean isGotButtWhooped() { return gotButtWhooped; }
    public boolean isHumanLike() { return humanLike; }
    public boolean isInsideATauntaun() { return insideATauntaun; }
    public boolean isJedi() { return jedi; }
    public boolean isLightSide() { return lightSide; }
    public boolean isLostALimb() { return lostALimb; }
    public boolean isPilot() { return pilot; }
    public boolean isRebel() { return rebel; }
    public boolean isResistance() { return resistance; }
    public boolean isSeparatist() { return separatist; }
    public boolean isShortH() { return shortH; }
    public boolean isSlimey() { return slimey; }
    public boolean isSmuggler() { return smuggler; }
    public boolean isSpaceBallsCharacter() { return spaceBallsCharacter; }
    public boolean isStillLiving() { return stillLiving; }
    public boolean isTall() { return tall; }
    public boolean isWookie() { return wookie; }
    public boolean isValidChar() {return validChar;}
}