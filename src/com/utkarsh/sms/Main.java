package com.utkarsh.sms;

import java.util.Scanner;

import com.utkarsh.dao.CourseDAO;
import com.utkarsh.dao.EnrollmentDAO;
import com.utkarsh.dao.StudentDAO;
import com.utkarsh.model.Course;
import com.utkarsh.model.Enrollment;
import com.utkarsh.model.Student;

public class Main {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			StudentDAO studentDAO = new StudentDAO();
			CourseDAO courseDAO = new CourseDAO();
			EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

			while (true) {
				System.out.println("\n--- SMS Menu ---");
				System.out.println("1. Add Student");
				System.out.println("2. List Students");
				System.out.println("3. Update Student");
				System.out.println("4. Delete Student");
				System.out.println("5. Add Course");
				System.out.println("6. List Courses");
				System.out.println("7. Enroll Student");
				System.out.println("8. List Enrollments");
				System.out.println("0. Exit");
				System.out.print("Choose: ");
				int ch = sc.nextInt();
				sc.nextLine();

				switch (ch) {
				case 1: {
					System.out.print("Name: ");
					String name = sc.nextLine();
					System.out.print("Email: ");
					String email = sc.nextLine();
					System.out.print("Phone: ");
					String phone = sc.nextLine();
					studentDAO.insert(new Student(0, name, email, phone));
				}

				case 2:
					studentDAO.getAll().forEach(System.out::println);

				case 3: {
					System.out.print("Enter ID: ");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.print("New Name: ");
					String name = sc.nextLine();
					System.out.print("New Email: ");
					String email = sc.nextLine();
					System.out.print("New Phone: ");
					String phone = sc.nextLine();
					studentDAO.update(new Student(id, name, email, phone));
				}

				case 4: {
					System.out.print("Enter ID: ");
					int id = sc.nextInt();
					studentDAO.delete(id);
				}

				case 5: {
					System.out.print("Course Name: ");
					String cname = sc.nextLine();
					System.out.print("Duration: ");
					int dur = sc.nextInt();
					courseDAO.insert(new Course(0, cname, dur));
				}

				case 6:
					courseDAO.getAll().forEach(System.out::println);

				case 7: {
					System.out.print("Student ID: ");
					int sid = sc.nextInt();
					System.out.print("Course ID: ");
					int cid = sc.nextInt();
					enrollmentDAO.insert(new Enrollment(0, sid, cid));
				}

				case 8:
					enrollmentDAO.getAll().forEach(System.out::println);

				case 0: {
					System.out.println("Bye!");
					return;
				}

				default:
					System.out.println("Invalid choice.");
				}
			}
		}
	}
}