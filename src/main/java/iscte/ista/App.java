package iscte.ista;
import com.google.gson.Gson;
/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Gson gson = new Gson();
        Student john_Student = new Student("John", 123456);
        String json = gson.toJson(john_Student);

        Student paul_Student;
        String paul = "{'name':'Paul' ,'number':987654}";
        paul_Student = gson.fromJson(paul, Student.class);

    }
}
