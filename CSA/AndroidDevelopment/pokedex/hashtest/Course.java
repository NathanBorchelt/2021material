public class Course{
    public String name;
    public String instructor;
    public Course(String name, String instructor){
        this.name=name;
        this.instructor=instructor;
    }
    public Course(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getInstructor(){
        return instructor;
    }
    public void setInstructor(String instructor){
        this.instructor=instructor;
    }
}