/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import base.Employee;
import base.Person;
import dataaccess.model.DBDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 62130500127
 */
public class EmployeeDaoImp implements DBDao<Employee>{

    @Override
    public void insert(Employee obj) {
        String sql = "INSERT INTO employees VALUES(?,?,?,?,?,?,?)";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getEmployeeId());
            pstm.setString(2, obj.getFirstName());
            pstm.setString(3, obj.getLastName());
            pstm.setString(4, obj.getTelephone());
            pstm.setString(5, obj.getEmail());
            pstm.setString(6, obj.getAddress());
            pstm.setBoolean(7, obj.isFree());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Employee obj) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getEmployeeId());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update(Employee obj) {
        String sql = "UPDATE employees SET id = ?, fname = ?, lname = ?, tele = ?, email = ?, adds = ?, avlble = ? WHERE id = ?";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getEmployeeId());
            pstm.setString(2, obj.getFirstName());
            pstm.setString(3, obj.getLastName());
            pstm.setString(4, obj.getTelephone());
            pstm.setString(5, obj.getEmail());
            pstm.setString(6, obj.getAddress());
            pstm.setBoolean(7, obj.isFree());
            pstm.setString(8, obj.getEmployeeId());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Employee findById(String id) {
        Employee emp = null;
        try(Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT * FROM employees WHERE id = " + id);
            if (rs.next()) {
                emp = new Employee(rs.getString(1), new Person(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)), rs.getBoolean(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emp;
    }

    @Override
    public ArrayList<Employee> getAll() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT * FROM employees");
            while(rs.next()){
                list.add(new Employee(rs.getString(1), new Person(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)), rs.getBoolean(7)));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public ArrayList<Employee> getFreeEmp() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT * FROM employees WHERE avlble = true");
            while(rs.next()){
                list.add(new Employee(rs.getString(1), new Person(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)), rs.getBoolean(7)));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
