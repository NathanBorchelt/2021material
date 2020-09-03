public class StringNotes {
    public static void main(String[] args){
        String name = "Morty";//modern way, literal assignment - is an object
        String name2 = new String("Rick"); // old way, class constuctor
        int number = 0; //number is a n object of the integer CLASS
        System.out.println(name.concat(name2));  //Rick version on the +
        System.out.println(name + name2);        //modern version
        //System.out.printf();    printed concatination 

        name = name + name2;
        System.out.println(name)
        name += name2;
        System.out.println(name);
        // name -= name2 cannot do that

        //impolicit type conversion
        int age = 17;
        System.out.println("your age is;  " + age);

        System.out.println(name.substring(0,5));
        System.out.println(name.substring(0,name.indexOf("t")));
        System.out.println(name.substring(name.indexOf("R")));
        

    }
}