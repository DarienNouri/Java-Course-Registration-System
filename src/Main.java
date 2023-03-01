

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static Scanner in = new Scanner(System.in);
    //public static ArrayList<Course> courseList = new ArrayList<Course>();
    //public static ArrayList<Student> studentList = new ArrayList<Student>();


    public static void main(String[] args) throws IOException {
        parseCSV();
        try{
            Serialization.deserializeStudentList();
            Serialization.deserializeCourseList();
        } catch (Exception e) {
            System.out.println("Error");

        }


        System.out.println(Data.students.toString());

//        Data.students.add(new Student("Darien","Nouri", null, null, null));
//        Data.courses.add(new Course("Data Structures", "CSCI-UA 102", 3, 3, "Fall 2019", 3, "Darien Nouri"));
//        Data.courses.add(new Course("Intro to Computer Science2", "CSCI-UA 103", 5, 2, "Fall 2019", 3, "Darien Nouri"));
//        Data.courses.add(new Course("Intro to Computer Science3", "CSCI-UA 104", 15, 10, "Fall 2019", 3, "Darien Nouri"));
//        Data.courses.get(0).addStudentName("Darien Nouri");
//        ArrayList<String> courseName = new ArrayList<>();
//        courseName.add("CSCI-UA 102");
//        Data.students.get(0).setEnrolledCourses(courseName);


        //Login
        System.out.println("Welcome to Darien's fire ass University");
        System.out.println("Are you a student or an admin? ");
        System.out.println("1. Student");
        System.out.println("2. Admin");
        System.out.println("3. Exit");

        int choice = in.nextInt();
        //Student Login
        if(choice == 1) {
            Student student = studentLogin();
            if (student == null) {
                System.out.println("Invalid Login");
                System.exit(0);
            }
            studentMenu(student);


        }

        //Admin Login
        else if(choice == 2) {

            Admin admin = adminLogin();
            if (admin == null) {
                System.out.println("Invalid Login");
                System.exit(0);
            }
            adminMenu(admin);
        }


    }



    public static void parseCSV() throws IOException {
        ArrayList<Course> localCourseList = new ArrayList<>();
        String line = "";
        String delimiter = ",";

        try {
            int lineCount = 0;
            BufferedReader br = new BufferedReader(new FileReader("/Users/darien/Library/Mobile Documents/com~apple~CloudDocs/02NYU/Data Structures/Code/HW1/MyUniversityCourses.csv"));

            while ((line = br.readLine()) != null) {
                lineCount+=1;
                if(lineCount>1) {
                    String[] parsedLine = line.split(delimiter);
                    Course c = new Course(parsedLine[0],parsedLine[1],Integer.parseInt(parsedLine[2]),Integer.parseInt(parsedLine[3]),
                            parsedLine[5],Integer.parseInt(parsedLine[6]),parsedLine[7]);

                    localCourseList.add(c);
                }

            }
        } catch(IOException ex){
            ex.printStackTrace();
        }

        Data.setCourses(localCourseList);

    }

    public static Student studentLogin(){

        System.out.println("Enter your First Name: ");
        String firstName = in.next();
        System.out.println("Enter your Last Name: ");
        String lastName = in.next();


        for(Student itterStudent: Data.students){

            if(itterStudent.getFirstName().equals(firstName) && itterStudent.getLastName().equals(lastName)){
                if (itterStudent.getUsername() == null && itterStudent.getPassword() == null){
                    System.out.println("You have not set a username or password yet");
                    System.out.println("Set Username: ");
                    String username = in.next();
                    System.out.println("Set Password: ");
                    String password = in.next();
                    itterStudent.setUsername(username);
                    itterStudent.setPassword(password);

                }
                else {
                    System.out.println("Enter your Username: ");
                    String username = in.next();
                    System.out.println("Enter your Password: ");
                    String password = in.next();
                    do {
                        System.out.println("Invalid username or password. Try again");
                        System.out.println("Enter your Username: ");
                        username = in.next();
                        System.out.println("Enter your Password: ");
                        password = in.next();

                    } while (!itterStudent.getUsername().equals(username) && !itterStudent.getPassword().equals(password));

                }
                Student student = itterStudent;
                return student;
            }
        }
        return null;
    }

    public static Admin adminLogin(){
        System.out.println("Enter your Username: ");
        String username = in.next();
        System.out.println("Enter your Password: ");
        String password = in.next();
        if(username.equals("Admin") && password.equals("Admin001")){
            Admin admin = new Admin(username, password);
            return admin;
        }
        else System.out.println("Invalid username or password");
        return null;
    }


    public static void studentMenu(Student student) throws IOException {
        int choice;
        do{
            System.out.println("\nSelect from the following options: ");
            System.out.println("1. View Courses");
            System.out.println("2. View Courses that are Not Full");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. View Registered Courses");
            System.out.println("6. Exit");

            try{
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Choice");
                continue;
            }


            switch (choice) {
                case 1:
                    student.viewCourses();
                    break;
                case 2:
                    student.viewAllCoursesNotFull();
                    break;
                case 3:
                    student.register();
                    break;
                case 4:
                    student.drop();
                    break;
                case 5:
                    student.viewCurrentStudentCourses();
                    break;
                case 6:
                    Serialization.serializeStudentList();
                    Serialization.serializeCourseList();
                    System.out.println("Goodbye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        } while (true);
    }








    public static void adminMenu(Admin admin) throws IOException {


        do{
            System.out.println("\n--Main Menu--");
            System.out.println("Select from the following options: ");
            System.out.println("1. Course Management");
            System.out.println("2. Reports");
            System.out.println("3. Exit");
            int taskChoice = in.nextInt();
            switch(taskChoice) {
                case 1:
                    System.out.println("\nSelect from the following options: ");
                    System.out.println("1. Add a Course");
                    System.out.println("2. Delete a Course");
                    System.out.println("3. Edit a Course");
                    System.out.println("4. Display Course Info");
                    System.out.println("5. Register a Student");
                    System.out.println("6. Return to Main Menu");
                    int mgtChoice = in.nextInt();
                    if (mgtChoice == 1) {
                        admin.createCourse();
                        break;
                    } else if (mgtChoice == 2) {
                        admin.deleteCourse();
                        break;
                    } else if (mgtChoice == 3) {
                        admin.editCourse();
                        break;
                    } else if (mgtChoice == 4) {
                        admin.displayCourseInfo();
                        break;
                    } else if (mgtChoice == 5) {
                        admin.registerStudent();
                        break;
                    } else if (mgtChoice == 6) {
                        System.out.println("Returning to Main Menu");
                        break;

                    } else {
                        System.out.println("Invalid Choice");
                    }
                    break;
                case 2:
                    int reportsChoice;
                    do{
                        System.out.println("\nSelect from the following options: ");
                        System.out.println("1. View all Courses");
                        System.out.println("2. View all Courses that are Full");
                        System.out.println("3. Write list of Full Courses to File");
                        System.out.println("4. View all Students in a Course");
                        System.out.println("5. View all Courses a Student is Registered in");
                        System.out.println("6. Sort Courses by Number of Students Registered");
                        System.out.println("7. Return to Main Menu");
                        reportsChoice = in.nextInt();
                        switch(reportsChoice){
                            case 1:
                                admin.viewCourses();
                                break;
                            case 2:
                                admin.viewAllFullCourses();
                                break;
                            case 3:
                                admin.writeFullCoursesToFile();
                                break;
                            case 4:
                                admin.viewAllStudentsInCourse();
                                break;
                            case 5:
                                admin.viewCoursesByStudent();
                                break;
                            case 6:
                                admin.sortByNumRegistered();
                                break;
                            case 7:
                                System.out.println("Returning to Main Menu...");

                                break;
                            default:
                                System.out.println("Invalid Choice");
                                break;
                        }
                    }while(reportsChoice != 7);
                    break;



                    case 3:
                        Serialization.serializeStudentList();
                        Serialization.serializeCourseList();
                    System.out.println("Goodbye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        } while (true);

    }


}