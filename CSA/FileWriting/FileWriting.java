//this versio uses FileOutputStream as a way of writing
import java.io.*;
public class FileWriting{
    public static void main(String[] args) throws IOException{
        //write to file
        //basically print a bunch of random numbers into a file
        try(FileOutputStream output= new FileOutputStream("temp.txt")){
            //if we do create a file from above, we cando the following
            byte space = 32;
            for(int i = 0; i< 100; i++){
                //create rand num
                int randNum = (int)(Math.random()*10);
                //write num to file
                output.write(randNum);
                //add space
                output.write(space);
            }
        }
        //read to file
        try(FileInputStream input = new FileInputStream("temp.txt")){
            //we know that it is ints, so value is set to int
            int value;
            while((value=input.read())!=-1){
                if(value!=32){
                    System.out.println(value);
                }
            }

        }
        //read from file
    }
}