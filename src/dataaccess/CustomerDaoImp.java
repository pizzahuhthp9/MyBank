/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import base.Customer;
import base.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dataaccess.model.DBDao;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 62130500127
 */
public class CustomerDaoImp implements DBDao<Customer>{

    @Override
    public void insert(Customer obj) {
        String sql = "INSERT INTO customers VALUES(?,?,?,?,?,?)";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getCustomerId());
            pstm.setString(2, obj.getFirstName());
            pstm.setString(3, obj.getLastName());
            pstm.setString(4, obj.getTelephone());
            pstm.setString(5, obj.getEmail());
            pstm.setString(6, obj.getAddress());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Customer obj) {
                String sql = "DELETE FROM customers WHERE id = ?";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getCustomerId());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Customer obj) {
        String sql = "UPDATE customers SET id = ?, fname = ?, lname = ?, tele = ?, email = ?, adds = ?";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getCustomerId());
            pstm.setString(2, obj.getFirstName());
            pstm.setString(3, obj.getLastName());
            pstm.setString(4, obj.getTelephone());
            pstm.setString(5, obj.getEmail());
            pstm.setString(6, obj.getAddress());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Customer findById(String id) {
        Customer cus = null;
        try(Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT * FROM customers WHERE id = "+ id);
            if (rs.next()) {
                cus = new Customer(rs.getString(1), new Person(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cus;
    }

    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> list = new ArrayList<Customer>();
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT * FROM customers");
            while(rs.next()){
                list.add(new Customer(rs.getString(1), new Person(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
