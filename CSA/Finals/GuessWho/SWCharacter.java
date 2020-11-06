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
    private int kesselSpeed;
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
    //private Object[] attributes = { name, jedi, droid, humanLike, wookie, darkSide, lightSide, bountyHunter, smuggler, kesselSpeed, empire, rebel, resistance, firstOrder, separatist, galacticRepublic, ewok, fluffy, slimey, lightsaberColor, tall, shortH, pilot, annoying, cute, bandersFav, gotButtWhooped, insideATauntaun, stillLiving, lostALimb, spaceBallsCharacter};
    private ArrayList attributes = new ArrayList<>(){{
        add(name);
        add(jedi);
        add(droid);
        add(humanLike);
        add(wookie);
        add(darkSide);
        add(lightSide);
        add(bountyHunter);
        add(smuggler);
        add(kesselSpeed);
        add(empire);
        add(rebel);
        add(resistance);
        add(firstOrder);
        add(separatist);
        add(galacticRepublic);
        add(ewok);
        add(fluffy);
        add(slimey);
        add(lightsaberColor);
        add(tall);
        add(shortH);
        add(pilot);
        add(annoying);
        add(cute);
        add(bandersFav);
        add(gotButtWhooped);
        add(insideATauntaun);
        add(stillLiving);
        add(lostALimb);
        add(spaceBallsCharacter);
    }};



    public SWCharacter(String charInfo){
        String optionsString[] = charInfo.split(",");
        System.out.println(optionsString);
        for(int i = 0; i < attributes.size(); i++){
            try{
                if(optionsString[i] == "0") attributes.set(i,false);
                else attributes.set(i,true);
            }
            catch(Exception e){
                try{
                    attributes.set(i,Integer.valueOf(optionsString[i]));
                }
                catch(Exception e2){
                    try{
                        attributes.set(i,optionsString[i]);
                    }
                    catch(Exception e3){
                        System.out.println(e3);
                    }
                }
            }
        }
    }

    public String getName() { return name; }
    public int getKesselSpeed() { return kesselSpeed; }
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
}