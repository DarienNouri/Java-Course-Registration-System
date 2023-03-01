# Java-Course-Registration-System
Course Registration System For NYU
# Student Class
The Student class is a subclass of the User class and implements the StudentInterface. It represents a student in the course registration system and has fields for the student's first and last name, as well as an ArrayList of the courses they are currently enrolled in.

## Fields
firstName - the student's first name 

lastName - the student's last name

enrolledCourses - an ArrayList of the courses the student is currently enrolled in

## Constructors
Student() - a default constructor with no parameters

Student(String firstName, String lastName, String username, String password, ArrayList<String> courses) - a constructor that takes in a student's first name, last name, username, password, and a list of enrolled courses

Student(String firstName, String lastName) - a constructor that takes in a student's first name and last name and sets the username, password, and enrolled courses to null or empty ArrayLists

## Methods
viewAllCoursesNotFull() - a method that prints out the details of all courses that are not full

register() - a method that allows the student to register for a course by entering the course ID

drop() - a method that allows the student to drop a course by entering the course ID

viewCurrentStudentCourses() - a method that prints out the details of the courses the student is currently enrolled in


# Data Class
The Data class is responsible for storing data related to students and courses. It contains two ArrayLists of type Student and Course respectively, which can be accessed and modified through getter and setter methods.

The setStudentList and setCourseList methods are used to set the ArrayLists of students and courses, respectively, whereas the getStudentList and getCourseList methods are used to retrieve the ArrayLists.

The class also has static methods setStudents and setCourses, which can be used to directly set the ArrayLists of students and courses.

Overall, the Data class plays an important role in storing and managing data in the course registration system.

# Course Class

The Course class represents a course in the course registration system. It contains several fields such as name, id, maxStudents, currentStudents, studentNames, instructor, sectionNumber, and location.

The name field stores the name of the course, whereas the id field stores the unique identifier of the course. The maxStudents and currentStudents fields store the maximum number of students that can be enrolled in the course and the current number of enrolled students, respectively. The studentNames field is an ArrayList of strings that stores the names of the students enrolled in the course.

The instructor, sectionNumber, and location fields store the name of the instructor, the section number of the course, and the location where the course is held, respectively.

The Course class has several getter and setter methods for these fields. The addStudentName and removeStudentName methods are used to add and remove students from the studentNames field. The registerStudent method is used to register a student for the course if there is space available.

The toString method provides a string representation of the Course object.

The Course class also implements the Serializable interface, allowing instances of the class to be serialized and deserialized.




