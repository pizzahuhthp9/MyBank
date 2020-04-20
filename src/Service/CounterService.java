/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import bank.BankAccount;
import bank.SubBank;
import base.Customer;
import base.Employee;

/**
 *
 * @author 62130500127
 */
public class CounterService implements Service{
    private Employee employee;
    private SubBank subBank;

    public CounterService(Employee employee) {
        this.employee = employee;
    }
    
    
    public void deposite(int money, String id){
        subBank.deposit(money, id);
    }
    
    public boolean deleteAccount(String id){
        subBank.deleteBankAccount(id);
    }
    
    public BankAccount newAccount(String id, Customer customer){
        return subBank.createBankAccount(id, 0, customer);
    }

    public void setSubBank(SubBank subBank) {
        this.subBank = subBank;
    }
    
    @Override
    public String toString() {
        return "CounterService{" + "employee=" + employee + ", subBank=" + subBank + '}';
    }

    @Override
    public void withdraw(int money, String id) {
        subBank.withdraw(money, id);
    }

    @Override
    public void transfer(int money, String id1, String id2) {
        subBank.transfer(money, id1, id2);
    }
     
}
