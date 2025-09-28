package com.utkarsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utkarsh.model.Enrollment;
import com.utkarsh.util.DatabaseUtil;

public class EnrollmentDAO {
	public void insert(Enrollment e) {
		String sql = "INSERT INTO Enrollment(student_id, course_id) VALUES (?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, e.getStudentId());
			ps.setInt(2, e.getCourseId());
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<Enrollment> getAll() {
		List<Enrollment> list = new ArrayList<>();
		String sql = "SELECT * FROM Enrollment";
		try (Connection conn = DatabaseUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new Enrollment(rs.getInt("enrollment_id"), rs.getInt("student_id"), rs.getInt("course_id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void update(Enrollment e) {
		String sql = "UPDATE Enrollment SET student_id=?, course_id=? WHERE enrollment_id=?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, e.getStudentId());
			ps.setInt(2, e.getCourseId());
			ps.setInt(3, e.getEnrollmentId());
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM Enrollment WHERE enrollment_id=?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
