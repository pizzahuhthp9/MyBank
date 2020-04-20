/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import bank.SubBank;
import machine.Machine;
import machine.MachineStatus;

/**
 *
 * @author 62130500127
 */
public class DepositMachine extends Machine{
    public DepositMachine(String machineId,SubBank subbank,int money,String location) {
            super(machineId, subbank, money, location);
    }
                
    public void deposit(int money,String id){
        
    }

    @Override
    protected void displayMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
