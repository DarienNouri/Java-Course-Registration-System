import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Course implements Serializable {

    private String name;
    private String id;
    private int maxStudents;
    private int currentStudents;
    private ArrayList<String> studentNames;
    private String instructor;
    private int sectionNumber;
    private String location;

    public Course(String name, String id, int maxStudents, int currentStudents, ArrayList<String> studentNames, String instructor,
                  int sectionNumber, String location){
        this.name = name;
        this.id = id;
        this.maxStudents = maxStudents;
        this.currentStudents = currentStudents;
        this.studentNames = studentNames;
        this.instructor = instructor;
        this.sectionNumber = sectionNumber;
        this.location = location;
    }
    public Course(String name, String id, int maxStudents, int currentStudents, String instructor,
                  int sectionNumber, String location){
        this.name = name;
        this.id = id;
        this.maxStudents = maxStudents;
        this.currentStudents = currentStudents;
        this.studentNames = new ArrayList<String>();
        this.instructor = instructor;
        this.sectionNumber = sectionNumber;
        this.location = location;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public void setCurrentStudents(int currentStudents) {
        this.currentStudents = currentStudents;
    }

    public ArrayList<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(ArrayList<String> studentNames) {
        this.studentNames = studentNames;
    }

    public void addStudentName(String studentName){
        studentNames.add(studentName);
    }
    public void removeStudentName(String studentName){
        studentNames.remove(studentName);
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void registerStudent(String studentName){
        if(currentStudents < maxStudents){
            studentNames.add(studentName);
            currentStudents+=1;
            System.out.println(studentName + "has successfully been registered for" + name);
        } else{
            System.out.println("The " + name + " is full and cannot accept more students");
        }
    }

    @Override
    public String toString(){
        return ("Course name: " + name + " Course id: " + id + " Max Students: " + maxStudents + " Current Students: "
                + currentStudents + " Instructor: " + instructor + " Section Number: " + sectionNumber + " Location: "
                + location);
    }

}
