package com.utkarsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utkarsh.model.Course;
import com.utkarsh.util.DatabaseUtil;

public class CourseDAO {
	public void insert(Course c) {
		String sql = "INSERT INTO Course(course_name, duration) VALUES (?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, c.getCourseName());
			ps.setInt(2, c.getDuration());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Course> getAll() {
		List<Course> list = new ArrayList<>();
		String sql = "SELECT * FROM Course";
		try (Connection conn = DatabaseUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new Course(rs.getInt("id"), rs.getString("course_name"), rs.getInt("duration")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void update(Course c) {
		String sql = "UPDATE Course SET course_name=?, duration=? WHERE id=?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, c.getCourseName());
			ps.setInt(2, c.getDuration());
			ps.setInt(3, c.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM Course WHERE id=?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
