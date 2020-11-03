import java.util.Scanner;

import java.io.File;

import java.io.FileWriter;

import java.io.BufferedWriter;

public class BufferWriter {

     //no need for a main function since it is a class.
    private String DATA_FILE;
    private static Scanner in;
    private static BufferedWriter out;

    public BufferWriter(String fileName){
        this.DATA_FILE=fileName;
    }
//since static, you can call it using BufferWriter.readString();
    public String readString(){
        if(in == null){
            try{
                in = new Scanner(new File(this.DATA_FILE));
            }
            catch(Exception e){
                System.err.println("Cannot open file for input!");
                e.printStackTrace();
            }
        }
        try{
            if(in.hasNext()){
                String s = in.nextLine();
                return s;
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            System.err.println("Cannot read the file....");
            e.printStackTrace();
        }
        return null;
    }
    public void writeString(String s){
        if(out == null){
            try{
                out = new BufferedWriter(new FileWriter(this.DATA_FILE));
            }
            catch(Exception e){
                System.err.println("Cannot open file for output!");
                e.printStackTrace();
            }
        }
        try{
            out.write(s);
            out.newLine();  //adds in a new line of data
            //out.write("|");  //this is like the byte space from FileWriting
        }
        catch (Exception e) {
            System.err.println("Cannot write file!");
            e.printStackTrace();
        }
    }
    public void saveAndClose(){
        //closes after the Scanner is done reading the file
        if (in != null) {
            try {
                //close() closes and saves the file you are writing to
                in.close();
                in = null;
            }
            catch (Exception e) {
                System.err.println("Cannot close input file!");
                e.printStackTrace();
            }
        }
        //closes after the BufferedWriter is done writing to the file
        if (out != null) {
            try {
                //close() closes and saves the file you are writing to
                out.close();
                out = null;
            }
            catch (Exception e) {
                System.err.println("Cannot close output file!");
                e.printStackTrace();
            }
        }    
    }
}

