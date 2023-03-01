import java.util.ArrayList;

public abstract class User {

    protected String username;
    protected String password;

    public User(){}

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }
    public static void printCourseHeaders(){
        String format = "%-45s %-20s %-14s %-35s %-14s %-15s%n";
        System.out.printf(format, "Course Name", "Course ID", "Max Students", "Instructor", "Section #", "Location");
    }

    public static void printfCourse(Course course){
        String format = "%-45s %-20s %-14s %-35s %-14s %-15s%n";
        System.out.printf(format,
                course.getName(), course.getId(), course.getMaxStudents(),
                 course.getInstructor(), course.getSectionNumber(),
                course.getLocation());
    }


    //Methods Common to both Admin and Student
    public void viewCourses() {
        printCourseHeaders();
        for (Course course : Data.courses) {
            printfCourse(course);
        }
    }

    public void displayCourseInfo() {
        System.out.println("Course ID: ");

        String courseID = null;
        try {
            printCourseHeaders();
            for(Course course : Data.courses){
                if(course.getId().equals(courseID)){
                    printfCourse(course);
                }
            }
        }catch (Exception e){
            System.out.println("Course not found.");
        }

    }






    public void viewCurrentStudentCourses(String firstName, String lastName) {
        if(Data.students.size() == 0){
            System.out.println("No students have been registered yet.");
        }
        else{
            for(Student student: Data.students){
                if(student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)){
                    System.out.println(student.getEnrolledCourses());
                }
            }
        }

    }


    //Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


