/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Service.CounterService;
import bank.MainBank;
import bank.SubBank;
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
public class SubBankDaoImp implements DBDao<SubBank>{

    @Override
    public void insert(SubBank obj) {
        String sql = "INSERT INTO sub_bank VALUES('?',?,'?');";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getId());
            pstm.setInt(2, obj.getVault());
            pstm.setString(3, obj.getCounterService().getEmployee().getEmployeeId());
            pstm.execute();        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void delete(SubBank obj) {
        
    }

    @Override
    public void update(SubBank obj) {
        String sql = "UPDATE sub_bank SET vault = ?, emp_id = '?' WHERE id = '?'";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, obj.getVault());
            pstm.setString(2, obj.getCounterService().getEmployee().getEmployeeId());
            pstm.setString(3, obj.getId());
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public SubBank findById(String id) {
        SubBank sb = null;
        try(Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()){
            ResultSet rsSub = stm.executeQuery(String.format("SELECT * FROM sub_bank WHERE id = '%s'", id));
            boolean temp = rsSub.next();
            ResultSet rsEmp = stm.executeQuery(String.format("SELECT * FROM employees WHERE id = '%s'", rsSub.getString("emp_id")));
            ResultSet rsMain = stm.executeQuery("SELECT * FROM main_bank");
            if (temp && rsEmp.next() && rsMain.next()) {
                sb = new SubBank(rsSub.getString("id"), 
                        new CounterService(
                                new Employee(rsEmp.getString("emp_id"), 
                                new Person(rsEmp.getString("fname"), rsEmp.getString("lname"), rsEmp.getString("tele"), rsEmp.getString("email"), rsEmp.getString("adds")), rsEmp.getBoolean("free"))), 
                        new MainBank(rsMain.getInt("vault"), rsMain.getInt("ttl_money")));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sb;
    }

    @Override
    public ArrayList<SubBank> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
