import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class MainActivity{
    public static void main(String[] args) {
        Random rand = new Random();
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Econ", "Jeff Bezos"));
        courses.add(new Course("Physics", "Steven Hawkins"));
        courses.add(new Course("Cooking","Gordon Ramsey"));
        courses.add(new Course("Gym","Dwyane \"The Rock\" Johnson"));

        List<String> students = Arrays.asList("Forche","Caleb","Pat","Hunter","Reese","Nathan");
        /*
        currently we have two different students
            noew let's reduce this to one list where the index will be the couses and the values will be the students

            {
                student:course{
                    name,
                    instructor
                },
                student:course{
                    name,
                    instructor
                },student:course{name,instructor}
            }
            */
        Map<String,Course> assignments = new HashMap<>();
        for (String s : students){
            int index = rand.nextInt(courses.size());
            assignments.put(s,courses.get(index));
        }
        for(Map.Entry<String,Course> entry:assignments.entrySet()){
            /*
            System.out.println(entry.getKey() + " is attending " + entry.getValue().getName() +" is taught by " + entry.getValue().getInstructor());
            System.out.println(entry+"\n");
            */
            if(entry.getValue().getName() == "Physics"){
                System.out.println(entry.getKey() + " is attending " + entry.getValue().getName() +" is taught by " + entry.getValue().getInstructor());
            }

            if(entry.getKey() == "Pat"){
                System.out.println(entry.getKey() + " is attending " + entry.getValue().getName() +" is taught by " + entry.getValue().getInstructor());
            }
        }
    }
}