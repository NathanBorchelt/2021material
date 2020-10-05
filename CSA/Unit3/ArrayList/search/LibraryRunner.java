/*
 * Activity 3.7.5
 */
import java.util.ArrayList;

public class LibraryRunner{
    public static void main(String[] arg){
        Library library = new Library();
        ArrayList<Book> childrensBooks = library.getChildrensBooks();
        String authorToFind = "Jean Webster";
        for (Book book: childrensBooks){
            System.out.println(book.getTitle())
        }
    }
}