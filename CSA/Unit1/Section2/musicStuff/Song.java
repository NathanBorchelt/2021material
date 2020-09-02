public class Song {
    String title;
    String artist;
    int rating;
    double price;
    int minuets;
    int seconds;
    double duration;

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

    public void setTitle(String t){title=t;}
    public void setArtist(String a){artist=a;}
    public void setRating(int r){rating = r;}
    public void setPrice(double p){price = p;}
    public void setDuration(int m, int s){minuets=m;seconds=s;}
    public void setDuration(double d){duration=d;}


    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public int getRating(){return rating;}
    public double getPrice(){return price;}
    public String getDurationMS(){return minuets + ":" + seconds;}
    public double getDuration(){return duration;}
}