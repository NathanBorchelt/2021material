public class MediaLibRunner{
  public static void main(String[] args)
  {
    System.out.println("Welcome to your Media Library");
    MediaLib myLib = new MediaLib();

    //Step 1-6
    Book myBook = new Book("The Loord of the Rings","Tolkien");
    System.out.println("Book Created " + myBook;
    System.out.println("Library:\n"+myLib);
    myLib.addBook(myBook);
    System.out.println("Library:\n"+myLib)
    
    myBook.adjustRating(15);
    System.out.println(myBook)
  }
}