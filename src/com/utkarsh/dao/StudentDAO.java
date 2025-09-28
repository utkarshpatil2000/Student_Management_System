package com.utkarsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utkarsh.model.Student;
import com.utkarsh.util.DatabaseUtil;

public class StudentDAO {
	public void insert(Student s) {
		String sql = "INSERT INTO Student(name, email, phone) VALUES (?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getPhone());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Student> getAll() {
		List<Student> list = new ArrayList<>();
		String sql = "SELECT * FROM Student";
		try (Connection conn = DatabaseUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void update(Student s) {
		String sql = "UPDATE Student SET name=?, email=?, phone=? WHERE id=?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getPhone());
			ps.setInt(4, s.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM Student WHERE id=?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
