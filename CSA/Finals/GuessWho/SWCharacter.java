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
    private byte lightsaberColor;
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

    public SWCharacter(String charInfo){
        String optionsString[] = charInfo.split(",");
        this.name = optionsString[0];
        this.jedi = optionsString[1].equals("1");
        this.droid = optionsString[2].equals("1");
        this.humanLike = optionsString[3].equals("1");
        this.wookie = optionsString[4].equals("1");
        this.darkSide = optionsString[5].equals("1");
        this.lightSide = optionsString[6].equals("1");
        this.bountyHunter = optionsString[7].equals("1");
        this.smuggler = optionsString[8].equals("1");
        this.kesselSpeed = Byte.valueOf(optionsString[9]);     
        this.empire = optionsString[10].equals("1");
        this.rebel = optionsString[11].equals("1");
        this.resistance = optionsString[12].equals("1");
        this.firstOrder = optionsString[13].equals("1");
        this.separatist = optionsString[14].equals("1");
        this.galacticRepublic = optionsString[15].equals("1");
        this.ewok = optionsString[16].equals("1");
        this.fluffy = optionsString[17].equals("1");
        this.slimey = optionsString[18].equals("1");
        this.lightsaberColor = Byte.valueOf(optionsString[19]);
        this.tall = optionsString[20].equals("1");
        this.shortH = optionsString[21].equals("1");
        this.pilot = optionsString[22].equals("1");
        this.annoying = optionsString[23].equals("1");
        this.cute = optionsString[24].equals("1");
        this.bandersFav = optionsString[25].equals("1");
        this.gotButtWhooped = optionsString[26].equals("1");
        this.insideATauntaun = optionsString[27].equals("1");
        this.stillLiving = optionsString[28].equals("1");
        this.lostALimb = optionsString[29].equals("1");
        this.spaceBallsCharacter = optionsString[30].equals("1");

    }

    public String toString(){return name;}

    public String getName() { return name; }
    public byte getKesselSpeed() { return kesselSpeed; }
    public byte getLightsaberColor() { return lightsaberColor; }
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

    interface QuestionsToAsk{
        boolean questionB();
        byte questionI();
    }

    private QuestionsToAsk[] questionsToAsks = new QuestionsToAsk[]{
        new QuestionsToAsk(){public boolean questionB() { return isJedi(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these}},
        new QuestionsToAsk(){public boolean questionB() { return isDroid(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isHumanLike(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isWookie(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isDarkSide(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isLightSide(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isBountyHunter(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isSmuggler(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public byte questionI() { return getKesselSpeed(); }
            @Override public boolean questionB() {return false;}},
        new QuestionsToAsk(){public boolean questionB() { return isEmpire(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isRebel(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isResistance(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isFirstOrder(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isSeparatist(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isGalacticRepublic(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isEwok(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isSlimey(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public byte questionI(){return getLightsaberColor();}
            @Override public boolean questionB() {return false;}},
        new QuestionsToAsk(){public boolean questionB() { return isTall(); }
             @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isShortH(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isPilot(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isAnnoying(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isCute(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isBandersFav(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isGotButtWhooped(); }
            @Override public byte questionI() {return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isInsideATauntaun(); }
            @Override public byte questionI() { return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isStillLiving(); }
            @Override public byte questionI() { return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isLostALimb(); }
            @Override public byte questionI() { return 0;}}, //I Do not know why I need these, but the JVM is very unhappy if i do not have these},
        new QuestionsToAsk(){public boolean questionB() { return isSpaceBallsCharacter(); }
            @Override public byte questionI() {return 0;}}};

        public boolean questionB(int index){return questionsToAsks[index].questionB();}
        public byte questionI(int index){return questionsToAsks[index].questionI();}
    }