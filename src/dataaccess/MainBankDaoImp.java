/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import dataaccess.model.MainBankDBDao;
import bank.MainBank;
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
public class MainBankDaoImp implements MainBankDBDao{

    @Override
    public void update(MainBank obj) {
        String sql = "UPDATE main_bank SET vault = ?, ttl_money = ?";
        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, obj.getVault());
            pstm.setInt(2, obj.getTotalMoney());
            pstm.executeUpdate();
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
    }

    @Override
    public MainBank getBank() {
        MainBank mb = null;
        String sql = "SELECT * FROM main_bank";
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement();){
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                mb = new MainBank(rs.getInt("vault"), rs.getInt("ttl_money"));
            }
        } catch (SQLException ex) {
            System.out.println("ex");
        }
        return mb;
    }

}
