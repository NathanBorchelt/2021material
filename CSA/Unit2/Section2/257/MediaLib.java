public class MediaLib
{
  private Book book;

  public void addBook(Book b)
  {
    book = b;
  }

  public String toString() 
  {
    String info = "";
    if (book != null){
        info +="Book: " + book + "\n"
    }    
    return info;
  }
}