package com.utkarsh.model;

import java.io.Serializable;

public class Enrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int enrollmentId;
	private int studentId;
	private int courseId;

	public Enrollment() {
	}

	public Enrollment(int enrollmentId, int studentId, int courseId) {
		this.enrollmentId = enrollmentId;
		this.studentId = studentId;
		this.courseId = courseId;
	}

	// Getters & Setters
	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return enrollmentId + " | Student ID: " + studentId + " | Course ID: " + courseId;
	}
}