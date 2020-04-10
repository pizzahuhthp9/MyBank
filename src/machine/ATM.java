/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine;

import bank.Service;
import bank.SubBank;

/**
 *
 * @author 62130500127
 */
public class ATM extends Machine implements Service {
    public ATM
        (String machineId,SubBank subbank,int money,String location,MachineStatus status) {
       
    }

    @Override
    public boolean withdraw(int money) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean transfer(int money, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
