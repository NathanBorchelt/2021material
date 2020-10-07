/*
 * Activity 3.7.5
 */
import java.util.ArrayList;

public class LibraryRunner{
    static Library library = new Library();
    static ArrayList<Book> childrensBooks = library.getChildrensBooks();
    public static void main(String[] arg){
        String authorToFind = "Jean Webster";
        for (Book book: childrensBooks){
            System.out.println(book.getTitle());
        }
        System.out.println();
        for(Book b: childrensBooks){
            if (b.getAuthor().equals(authorToFind)){
                System.out.println(b.getTitle());
            }
        }
        //get rating of sky island
        System.out.println();
        String bookToFind = "Sky Island";
        double ratingSI = 0;
        for(Book b: childrensBooks){
            if (b.getTitle().equals(bookToFind)){
                ratingSI = b.getRating();
            }
        }
        System.out.println(ratingSI);
        System.out.println();

        int ratingSICountGoodEnough = 0;
        for (Book b: childrensBooks){
            if(b.getRating()>=ratingSI){
                System.out.println(b.getTitle());
                System.out.println(b.getAuthor());
                ratingSICountGoodEnough++;
            }
        }
        System.out.format("There were %d books with a rating of atleast %2.2f.", ratingSICountGoodEnough, ratingSI);
    
        min();
        max();
        mode();
        System.out.println(childrensBooks.size());
        average();

    }
    public static void min(){
        double min=Integer.MAX_VALUE;
        String title = "";
        for (Book b : childrensBooks){
            if (b.getRating()<min){
                min = b.getRating();
                title = b.getTitle();
            }
        }
        System.out.format("\nThe lowest rating is %1.2f by %s.",min, title);
    }
    public static void max(){
        double max=Integer.MIN_VALUE;
        String title = "";
        for (Book b : childrensBooks){
            if (b.getRating()>max){
                max = b.getRating();
                title = b.getTitle();
            }
        }
        System.out.format("\nThe highest rating is %1.2f by %s.",max, title);
    }
    public static void average(){
        double avg = 0;
        for (Book b : childrensBooks){
            avg += b.getRating();
        }
        System.out.format("\nThe average is %2.3f",avg/childrensBooks.size());
    }
    public static void mode() {
        double mode = childrensBooks.get(0).getRating();;
        int maxCount = 0;
        for (int i = 0; i < childrensBooks.size(); i++) {
            double value = childrensBooks.get(i).getRating();;
            int count = 0;
            for (int j = 0; j < childrensBooks.size(); j++) {
                if (childrensBooks.get(j).getRating() == value) count++;
                if (count > maxCount) {
                    mode = value;
                    maxCount = count;
                    }
                }
        }
        if (maxCount > 1) {
            System.out.println("\nThe mode is "+mode);
        }
        else{
            System.out.println("\nThere is no mode.");
        }
    }
}