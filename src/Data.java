import java.util.ArrayList;
/*
store respective data here

 */
public class Data {

    public static ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Course> courses = new ArrayList<>();

    public void setStudentList(ArrayList<Student> students) {
        Data.students = students;
    }

    public void setCourseList(ArrayList<Course> courses) {
        Data.courses = courses;
    }

    public static ArrayList<Student> getStudentList() {
        return Data.students;
    }

    public static ArrayList<Course> getCourseList() {
        return Data.courses;
    }

    public static void setStudents(ArrayList<Student> students) {
        Data.students = students;
    }

    public static void setCourses(ArrayList<Course> courses) {
        Data.courses = courses;
    }
}








