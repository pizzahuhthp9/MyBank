/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import bank.BankAccount;
import base.Customer;
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
public class BankAccountDaoImp implements DBDao<BankAccount> {

    @Override
    public void insert(BankAccount obj) {
        String sql = "INSERT INTO accounts VALUES(?,?,?)";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getAccountId());
            pstm.setString(2, obj.getOwner().getCustomerId());    
            pstm.setInt(3, obj.getMoney());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BankAccountDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(BankAccount obj) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getAccountId());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BankAccountDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(BankAccount obj) {
        String sql = "UPDATE accounts SET id = ?, cus_id = ?, money = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getAccountId());
            pstm.setString(2, obj.getOwner().getCustomerId());
            pstm.setInt(3, obj.getMoney());
            pstm.setString(4, obj.getAccountId());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BankAccountDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public BankAccount findById(String id) {
        BankAccount acc = null;
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement();) {
            ResultSet rs = stm.executeQuery("SELECT * FROM accounts JOIN customers ON cus_id = customers.id WHERE accounts.id = " + id);
            if (rs.next()) {
                acc = new BankAccount(id, new Customer(rs.getString(4), new Person(rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9))), rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankAccountDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    @Override
    public ArrayList<BankAccount> getAll() {
        ArrayList<BankAccount> list = new ArrayList<BankAccount>();
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM accounts JOIN customers ON cus_id = customers.id");
            while(rs.next()){
                list.add(new BankAccount(rs.getString(1), new Customer(rs.getString(4), new Person(rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9))), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankAccountDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
