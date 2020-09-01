public class mediaPlayer {
    public static void main(String[] args){
        Song s1 = new Song();
        Song s2 = new Song("Back in Black","ACDC");
        Song s3 = new Song("Midnight Train","Jason Aldean",4);
        Song s4 = new Song("Love Like Crazy","Lee Brice",5,1.29);

        System.out.println(s1.title);
        System.out.println(s2.artist);
        System.out.println(s3.rating);
        System.out.println(s4.price);

        Movie m1 = new Movie();
        Movie m2 = new Movie("Back to the Future","Robert Zemeckis");
        Movie m3 = new Movie("Men in Black: International","F. Gary Gray",1);
        Movie m4 = new Movie("Foodfight!","Lawrence Kasanoff",1,.99);
    }
}