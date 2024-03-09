package org.example.app.repository.impl;

import org.example.app.database.DBConn;
import org.example.app.entity.Employee;
import org.example.app.repository.AppRepository;
import org.example.app.utils.Rounder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeRepository implements AppRepository<Employee> {

    private static final Logger LOGGER =
            Logger.getLogger(EmployeeRepository.class.getName());

    public void create(Employee employee) {
        String sql = "INSERT INFORMATION ABOUT employees (nameofemployee, position, phone) VALUES (?, ?, ?)";
        try (Connection conn = DBConn.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getNameofemployee());
            ps.setString(2, employee.getPosition());
            ps.setDouble(3, Rounder.round(employee.getPhone(), 2));
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public List<Employee> read() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DBConn.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Employee(
                                rs.getLong("id"),
                                rs.getString("nameofemployee"),
                                rs.getString("position"),
                                Rounder.round(rs.getDouble("phone"), 2)
                        )
                );
            }
            return list;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Employee readById(Long id) {
        Employee employee = null;
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = DBConn.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee(id, rs.getString("nameofemployee"),
                        rs.getString("position"),
                        Rounder.round(rs.getDouble("phone"), 2)
                );
            }
            ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return employee;
    }

    public void update(Employee employee) {
        String sql = "UPDATE employees SET nameofemployee = ?, position = ?, phone = ? WHERE id = ?";
        try (Connection conn = DBConn.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getNameofemployee());
            ps.setString(2, employee.getPosition());
            ps.setDouble(3, Rounder.round(employee.getPhone(), 2));
            ps.setLong(4, employee.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM employees where id = ?";
        try (Connection conn = DBConn.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
