Student Management System Documentation

The Student Management System (SMS) is a simple Java application for managing students, courses, and enrollments. It uses JDBC to interact with a MySQL database, offers a console-driven menu, and even has a basic Swing GUI for easier interaction.

Database Structure

The system has three main tables to keep track of students, courses, and which students are enrolled in which courses.

SQL Schema
CREATE TABLE Student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20)
);

CREATE TABLE Course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    duration INT NOT NULL
);

CREATE TABLE Enrollment (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (course_id) REFERENCES Course(id)
);

Entity Relationship
erDiagram
    STUDENT {
        int id PK
        varchar name
        varchar email
        varchar phone
    }
    COURSE {
        int id PK
        varchar course_name
        int duration
    }
    ENROLLMENT {
        int enrollment_id PK
        int student_id FK
        int course_id FK
    }
    STUDENT ||--o{ ENROLLMENT : enrolls
    COURSE  ||--o{ ENROLLMENT : includes

Project Structure

The code is organized neatly into packages:

com.utkarsh.model → Core entities: Student, Course, Enrollment

com.utkarsh.dao → Database access classes

com.utkarsh.util → Database connection helper

com.utkarsh.sms → Console app (Main, TestCRUD)

com.utkarsh.ui → Swing GUI interface (GUI)

Model Classes

The model package contains the Java beans for all entities, making it easy to transfer data between the database and the UI.

Student.java

Represents a student with basic information.

public class Student implements Serializable {
    private int id;
    private String name;
    private String email;
    private String phone;

    public Student(int id, String name, String email, String phone) { ... }

    @Override
    public String toString() {
        return id + " | " + name + " | " + email + " | " + phone;
    }
}


Fields: id, name, email, phone

Purpose: Holds student info for both DAO and UI

Course.java

Represents a course with a name and duration.

public class Course implements Serializable {
    private int id;
    private String courseName;
    private int duration;

    public Course(int id, String courseName, int duration) { ... }

    @Override
    public String toString() {
        return id + " | " + courseName + " | " + duration;
    }
}


Fields: id, courseName, duration

Purpose: Keeps course info structured and easy to display

Enrollment.java

Links students with courses to track enrollments.

public class Enrollment implements Serializable {
    private int enrollmentId;
    private int studentId;
    private int courseId;

    public Enrollment(int enrollmentId, int studentId, int courseId) { ... }

    @Override
    public String toString() {
        return enrollmentId + " | Student ID: " + studentId + " | Course ID: " + courseId;
    }
}


Fields: enrollmentId, studentId, courseId

Purpose: Manages the many-to-many relationship between students and courses

Data Access Objects (DAOs)

Each DAO handles database operations for its entity. All database communication goes through DatabaseUtil.

StudentDAO.java

Handles CRUD for students:

insert(Student s) → Add a new student

getAll() → Get all students

update(Student s) → Update student info

delete(int id) → Remove student by ID

public void insert(Student s) { ... }
public List<Student> getAll() { ... }
public void update(Student s) { ... }
public void delete(int id) { ... }

CourseDAO.java

Manages courses:

insert(Course c) → Add a course

getAll() → List all courses

update(Course c) → Modify course details

delete(int id) → Delete a course

EnrollmentDAO.java

Handles enrollments:

insert(Enrollment e) → Create a new enrollment

getAll() → List all enrollments

update(Enrollment e) → Update enrollment

delete(int id) → Remove enrollment

Utilities
DatabaseUtil.java

A single class to provide a JDBC connection to the MySQL database.

public static Connection getConnection() {
    String url = "jdbc:mysql://localhost:3306/sms";
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(url, "root", "root");
}


Initializes JDBC driver

Returns a connection object for DAOs

Command-Line Interface

The console menu makes it easy to manage students, courses, and enrollments.

Main.java

Uses Scanner to take user input

Menu options include:

Add/List/Update/Delete Student

Add/List Course

Enroll/List Enrollment

Exit

The program loops until the user chooses to exit.

TestCRUD.java

A placeholder class for testing CRUD operations. Can be used to add unit tests or quick experiments.

Graphical User Interface (GUI)

A simple Swing GUI provides an alternative to the console.

Input fields: JTextField for name, email, phone

Buttons: Add Student, Show Students

Display: JTextArea shows all students

Validation: Ensures name/email aren’t empty, checks email format

GUI Actions
private void addStudent(ActionEvent e) { ... }
private void showStudents() { ... }


Launch the GUI with:

SwingUtilities.invokeLater(() -> new GUI());


✅ Summary:

The Student Management System allows you to add, view, update, and delete students and courses, manage enrollments, and interact via either the console or a Swing GUI. JDBC ensures all changes are safely stored in the MySQL database.