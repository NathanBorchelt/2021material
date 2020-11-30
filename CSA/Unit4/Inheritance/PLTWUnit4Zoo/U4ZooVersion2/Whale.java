public class Whale extends Animal implements Swimming
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Duck
     */
    public Whale()
    {
       super("Willie", "being large an dungainly, the whale is always selfconscious");
    }

    public String makeNoise(){return "beluga beckons";}
    public String eat(){return "The whale just opens its mouth and swims";}
    public String swim(){return "swims slowly an sedately";}
}
