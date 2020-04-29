/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import bank.CounterService;
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
public class SubBankDaoImp implements DBDao<SubBank> {

    @Override
    public void insert(SubBank obj) {
        String sql = "INSERT INTO sub_bank VALUES(?,?,?)";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getId());
            pstm.setInt(2, obj.getVault());
            pstm.setString(3, obj.getCounterService().getEmployee().getEmployeeId());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SubBankDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(SubBank obj) {
        String sql = "DELETE FROM sub_bank WHERE id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getId());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SubBankDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(SubBank obj) {
        String sql = "UPDATE sub_bank SET vault = ?, emp_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, obj.getVault());
            pstm.setString(2, obj.getCounterService().getEmployee().getEmployeeId());
            pstm.setString(3, obj.getId());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SubBankDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public SubBank findById(String id) {
        SubBank sb = null;
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM main_bank, sub_bank LEFT JOIN employees ON emp_id = employees.id WHERE sub_bank.id = '" + id + "'");
            if (rs.next()) {
                sb = new SubBank(id,
                        new CounterService(
                                new Employee(rs.getString(6), new Person(rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)), rs.getBoolean(12))),
                        new MainBank(rs.getInt(1), rs.getInt(2)),
                        rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubBankDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb;
    }

    @Override
    public ArrayList<SubBank> getAll() {
        ArrayList<SubBank> list = new ArrayList<SubBank>();
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM main_bank, sub_bank LEFT JOIN employees ON emp_id = employees.id");
            while (rs.next()) {
                list.add(new SubBank(rs.getString(3),
                        new CounterService(
                                new Employee(rs.getString(6), new Person(rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)), rs.getBoolean(12))),
                        new MainBank(rs.getInt(1), rs.getInt(2)),
                        rs.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubBankDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
