public class mediaPlayer {
    public static void main(String[] args){
        Song s1 = new Song();
        Song s2 = new Song("Back in Black","ACDC");
        Song s3 = new Song("Midnight Train","Jason Aldean",4);
        Song s4 = new Song("Love Like Crazy","Lee Brice",5,1.29);
        /*
        Movie m1 = new Movie();
        Movie m2 = new Movie("Back to the Future","Robert Zemeckis");
        Movie m3 = new Movie("Men in Black: International","F. Gary Gray",1);
        Movie m4 = new Movie("Foodfight!","Lawrence Kasanoff",1,.99);
        */

        System.out.println(s1.title);
        s1.setTitle("Hotel California");
        System.out.println(s1.getTitle());
        s1.setDuration(6, 30);
        System.out.println(s1.getDuration());
        s2.setDuration(2, 35);
        s3.setDuration(4, 15);
        s4.setDuration(3, 48);
        s1.setDuration(6.5);
        s2.setDuration(2+(35/60));
        s3.setDuration(4.25);
        s4.setDuration(3+(48/60));
       // avgTime();
    }
    /*
    public static void avgTime(){
        double totalTime = s1.getDuration() + s2.getDuration() + s3.getDuration() + s4.getDuration();
        double average = totalTime/4;
    Does not work because cannot see s1, s1 is a local obj in the main meathod, to fix, passin a list of objs
        
    }*/

}