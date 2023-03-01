
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;



interface StudentInterface {
    


    void viewAllCoursesNotFull();

    void register();

    void drop();

    void viewCurrentStudentCourses();


}




public class Student extends User implements StudentInterface, Serializable {

    public String firstName;
    public String lastName;
    public ArrayList<String> enrolledCourses = new ArrayList<>();


    public Student(){

    };

    public Student(String firstName, String lastName, String username, String password, ArrayList<String> courses){
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrolledCourses = courses;

    }

    public Student(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = null;
        this.password = null;
        this.enrolledCourses = new ArrayList<String>();
    }

    //Implemented Methods
    public void viewAllCoursesNotFull(){
        printCourseHeaders();
        for(Course course: Data.courses){
            if(course.getCurrentStudents() < course.getMaxStudents()){
                printfCourse(course);
            }
        }
    }

    public void register() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Course ID: ");
        String courseID = in.nextLine();
        for (Course course : Data.courses) {
            if (course.getId().equals(courseID)) {
                if (course.getCurrentStudents() < course.getMaxStudents()) {
                    course.setCurrentStudents(course.getCurrentStudents() + 1);
                    enrolledCourses.add(course.getId());
                    course.addStudentName(firstName + " " + lastName);
                    System.out.println("You have been registered for " + course.getName());
                    return;
                } else {
                    System.out.println("Course is full");
                    return;
                }
            }
        }
        System.out.println("Course not found");
    }






    public void drop() {
        if(enrolledCourses.size() == 0){
            System.out.println("You cannot drop a course because you are not enrolled in any courses");
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Course ID: ");
        String courseID = in.nextLine();

        for (Course course : Data.courses) {
            if (course.getId().equals(courseID)) {
                if (course.getCurrentStudents() > 0) {
                    course.setCurrentStudents(course.getCurrentStudents() - 1);
                    enrolledCourses.remove(course.getId());
                    course.removeStudentName(firstName + " " + lastName);
                    System.out.println("You have been withdrawn from " + course.getName());
                } else {
                    System.out.println("Course is empty");
                }
            }
        }
    }



    public void viewCurrentStudentCourses() {
        if (enrolledCourses.size() == 0) {
            System.out.println("Student not currently enrolled in any courses");
            printCourseHeaders();
            for (Course course : Data.courses) {
                if (enrolledCourses.contains(course.getId())) {
                    printfCourse(course);
                }
            }
        } else {System.out.println("Student not currently enrolled in any courses");}
    }





    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public  ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public ArrayList<Course> getEnrolledCoursesList() {

        ArrayList<Course> enrolledCourseList = new ArrayList<>();

        for (Course course : Data.courses) {
            if (enrolledCourses.contains(course.getId())) {
                enrolledCourseList.add(course);
            }
        }
        return enrolledCourseList;
    }



    public void setEnrolledCourses(ArrayList<String> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }












}
