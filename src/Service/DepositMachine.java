/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import bank.MainBank;
import bank.SubBank;
import machine.Machine;
import machine.MachineStatus;

/**
 *
 * @author 62130500127
 */
public class DepositMachine extends Machine{
    public DepositMachine(String machineId,MainBank mainBank,int money) {
            super(machineId, money, mainBank);
    }
                
    public void deposit(int money,String id){
        super.getMainBank().deposit(money, id);
    }

    @Override
    protected void displayMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
