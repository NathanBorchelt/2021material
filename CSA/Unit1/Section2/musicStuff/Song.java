public class Song {
    String title;
    String artist;
    int rating;
    double price;

    public Song(){
        title = "Title";
        artist = "Artist";
    }

    public Song(String t, String a){
        title = t;
        artist = a;
    }

    public Song(String t, String a, int r){
        title = t;
        artist = a;
        rating = r;
    }
    public Song(String t, String a, int r, double p){
        title = t;
        artist = a;
        rating = r;
        price = p;
    }
}