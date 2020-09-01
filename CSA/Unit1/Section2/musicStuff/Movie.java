public class Movie {
        String title;
        String director;
        int rating;
        double price;
    
        public Movie(){
            title = "Title";
            director = "director";
        }
    
        public Movie(String t, String d){
            title = t;
            director = d;
        }
    
        public Movie(String t, String d, int r){
            title = t;
            director = d;
            rating = r;
        }
        public Movie(String t, String d, int r, double p){
            title = t;
            director = d;
            rating = r;
            price = p;
        }
    } 
    }
}