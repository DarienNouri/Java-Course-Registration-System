import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


interface AdminInterface {

    //Management
    static void createCourse() {};
    void deleteCourse();
    void editCourse();
    void displayCourseInfo();
    void registerStudent();

    //Reports
    void viewAllFullCourses();
    void writeFullCoursesToFile();
    void viewAllStudentsInCourse();
    void viewCoursesByStudent();
    void sortByNumRegistered();

}


public class Admin extends User implements AdminInterface {

    static Scanner in = new Scanner(System.in);

//    private String firstName;
//    private String lastName;
//    private String username;
//    private String password;
      //public Scanner in = new Scanner(System.in);


    public Admin(String username, String password){
        super(username, password);

    }

    //Management
    public void createCourse(){

        System.out.println("Course Name: ");
        String courseName = in.nextLine();

        System.out.println("Course ID: ");
        String courseID = in.nextLine();

        System.out.println("Maximum # of Students: ");
        int maxStudents = Integer.parseInt(in.nextLine());

        System.out.println("Instructor: ");
        String instructor = in.nextLine();

        System.out.println("Section #: ");
        int sectionNumber = Integer.parseInt(in.nextLine());

        System.out.println("Location: ");
        String location = in.nextLine();

        int currentStudents = 0;

        ArrayList<String> studentNames = new ArrayList<String>();

        Course course = new Course(courseName, courseID, maxStudents, currentStudents, studentNames, instructor, sectionNumber, location);

        Data.courses.add(course);


        //Courses.add(course);
    }

    public void deleteCourse(){

            System.out.println("Course ID: ");
            String courseID = in.nextLine();
            try {
                for (int i = 0; i < Data.courses.size(); i++) {
                    if (Data.courses.get(i).getId().equals(courseID)) {
                        Data.courses.remove(i);
                        System.out.println("Course Deleted.");
                        return;
                    }
                }
            }catch (Exception e){
                System.out.println("Course not found.");
            }



    }

    public void editCourse() {
        //Edit a course (this will allow the admin to edit any information on the course except for course ID and name)
        System.out.println("Course ID: ");
        String courseID = in.nextLine();
        for (Course course : Data.courses) {
            if (course.getId().equals(courseID)) {
                //switch menu
                do {

                    System.out.println("1. Edit Maximum # of Students");
                    System.out.println("2. Edit Instructor");
                    System.out.println("3. Edit Section #");
                    System.out.println("4. Edit Location");
                    System.out.println("5. Exit");
                    int choice = Integer.parseInt(in.nextLine());
                    switch(choice){
                        case 1:
                            System.out.println("Enter New Maximum # of Students: ");
                            int newMaxStudents = Integer.parseInt(in.nextLine());
                            course.setMaxStudents(newMaxStudents);
                            break;
                        case 2:
                            System.out.println("Enter New Instructor: ");
                            String newInstructor = in.nextLine();
                            course.setInstructor(newInstructor);
                            break;
                        case 3:
                            System.out.println("Enter New Section #: ");
                            int newSectionNumber = Integer.parseInt(in.nextLine());
                            course.setSectionNumber(newSectionNumber);
                            break;
                        case 4:
                            System.out.println("Enter New Location: ");
                            String newLocation = in.nextLine();
                            course.setLocation(newLocation);
                            break;
                        case 5:
                            return;
                    }


                } while (true);
            }
        }
            System.out.println("Course not found.");
    }




    public void displayCourseInfo() {
        System.out.println("Course ID: ");
        String courseID = in.nextLine();
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

    public void registerStudent(){
        //Register a student
        System.out.println("First Name: ");
        String firstName = in.nextLine();

        System.out.println("Last Name: ");
        String lastName = in.nextLine();
        ArrayList<String> emptyEnrolledCourses = new ArrayList<String>();
        Student student = new Student(firstName, lastName, null, null, emptyEnrolledCourses);

        Data.students.add(student);

        for(Student s : Data.students){
            System.out.println(s.getFirstName()+ " " + s.getLastName());
        }


    }

    //Reports
    public void viewCourses() {
        //View all courses displayed with even column spacing
        //Course Name, Course ID, Maximum # of Students, Current # of Students, Instructor, Section #, Location
        String format = "%-45s %-15s %-15s %-20s %-28s %-20s %-15s%n";
        System.out.printf(format, "Course Name", "Course ID", "Max Students", "Current Students", "Instructor", "Section #", "Location");
        for (int i = 0; i < Data.courses.size(); i++) {
            System.out.printf(format, Data.courses.get(i).getName(), Data.courses.get(i).getId(), Data.courses.get(i).getMaxStudents(),
                    Data.courses.get(i).getCurrentStudents(), Data.courses.get(i).getInstructor(), Data.courses.get(i).getSectionNumber(),
                    Data.courses.get(i).getLocation());
        }
    }

    public void viewAllFullCourses(){
        printCourseHeaders();
        for(Course course: Data.courses){
            if(course.getCurrentStudents() == course.getMaxStudents()){
                printfCourse(course);
            }
        }
    }

    public void writeFullCoursesToFile(){
//Write all courses that are full to a file
        try {
            FileWriter fileWriter = new FileWriter("FullCourses.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String format = "%-45s %-15s %-10s %-10s %-10s %-20s %-15s%n";
            printWriter.printf(format, "Course Name", "Course ID", "Max Students", "Current Students", "Instructor", "Section #", "Location");
            for (int i = 0; i < Data.courses.size(); i++) {
                if (Data.courses.get(i).getCurrentStudents() == Data.courses.get(i).getMaxStudents()) {
                    printWriter.printf(format, Data.courses.get(i).getName(), Data.courses.get(i).getId(), Data.courses.get(i).getMaxStudents(),
                            Data.courses.get(i).getCurrentStudents(), Data.courses.get(i).getInstructor(), Data.courses.get(i).getSectionNumber(),
                            Data.courses.get(i).getLocation());
                }
            }
            printWriter.close();
            System.out.println("File written successfully.");
        }catch (Exception e){
            System.out.println("Error writing to file.");
        }


    }



    public void viewAllStudentsInCourse(){
        System.out.println("Enter Course ID: ");
        String courseID = in.nextLine();
        for(Course course: Data.courses){
            if(course.getId().contains(courseID)){
                if(course.getCurrentStudents() > 0) {
                    System.out.println("Students in " + course.getName() + ": ");
                    for (String student : course.getStudentNames()) {
                        System.out.println(student);
                    }
                    return;
                }else{
                    System.out.println("No students are registered in this course.");
                    return;
                }
            }
        }
        System.out.println("Course not found.");
    }

    public void viewCoursesByStudent(){
        System.out.println("Enter Student First Name: ");
        String firstName = in.nextLine();
        System.out.println("Enter Student Last Name: ");
        String lastName = in.nextLine();
        for(Student student: Data.students){
            if(student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)){
                student.viewCurrentStudentCourses();
            } else{
                System.out.println("Student not found.");
            }
        }
    }

    public void sortByNumRegistered(){
        //Sort courses by the number of students registered
        Data.courses.sort(new Comparator<Course>() {

            public int compare(Course o1, Course o2) {
                return o1.getCurrentStudents() - o2.getCurrentStudents();
            }
        });
    }








}
