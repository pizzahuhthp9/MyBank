/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.model;

import bank.MainBank;

/**
 *
 * @author 62130500127
 */
public interface MainBankDBDao {
    void update(MainBank obj);
    MainBank getBank();
}
