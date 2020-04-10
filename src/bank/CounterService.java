/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import base.Employee;

/**
 *
 * @author 62130500127
 */
public class CounterService implements Service{
    private Employee employee;
    private SubBank subBank;

    public CounterService(Employee employee, SubBank subBank) {
        this.employee = employee;
        this.subBank = subBank;
    }
    
    
    public boolean deposite(int money){
        return true;
    }
    
    public boolean deleteAccount(String Id){
        return false;
        
    }
    
    public boolean newAccount(String Id){
        return false;
        
    }

    @Override
    public String toString() {
        return "CounterService{" + "employee=" + employee + ", subBank=" + subBank + '}';
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
