import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Serialization implements Serializable {


    public static void serializeStudentList() {
        ArrayList<Student> studentList = Data.students;
        try {
            String fileName = "StudentList.ser";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(studentList);
            oos.close();
            fos.close();
            System.out.println("StudentsList file stored: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while serializing students list: " + e);
        }
    }


    public static void deserializeStudentList() {
        ArrayList<Student> studentDeserializedList = new ArrayList<Student>();
        try {
            String fileName = "StudentList.ser";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            studentDeserializedList = (ArrayList<Student>)ois.readObject();
            ois.close();
            fis.close();
            Data.students = studentDeserializedList;
            System.out.println("ArrayList deserialized from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred during deserialization: " + e);
        }
    }


    public static void serializeCourseList(){
            ArrayList<Course> courseList = Data.courses;
            try {
                FileOutputStream fos = new FileOutputStream("CourseList.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(courseList);
                oos.close();
                fos.close();
                System.out.println("Serialization Successful");
            }
            catch (IOException ioe) {ioe.printStackTrace();}

    }


    public static void deserializeCourseList() {
        ArrayList<Course> deserializedCourse = new ArrayList<Course>();
        try{
            FileInputStream fis = new FileInputStream("CourseList.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            deserializedCourse = (ArrayList<Course>)ois.readObject();
            ois.close();
            fis.close();
            Data.courses = deserializedCourse;
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
            return;
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return;
        }
    }
}








