public class Song{
    private String title;
    private String artist;
    private int rating;

    public Song(String t, String a){
        title = title;
        artist = a;
        rating = 0;
    }
    //accessor
    public String getTitle(){
        return title;
    }
    public String getArtist(){
        return artist;
    }
    public int getRating(){
        return rating;
    }
    public String toString(){
        String info = "\"" + title + "\", written by " + artist;
        if (rating != 0) 
        { 
        info += ", rating is " + rating;
        }
        return info;
    }

    //mutator
    public void setTitle(String t){
        title = t;
    }

    public void setArtist(String a){
        artist = a;
    }

    public void adjustRating(int r){
        if ((rating +r) >= 0) && (rating + r)){
            rating += r
        }
    }
}