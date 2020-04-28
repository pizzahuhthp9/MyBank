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
public class CounterService{
    private Employee employee;
    private SubBank subBank;

    public CounterService(Employee employee) {
        employee.setFree(false);
        this.employee = employee;  
    }

    public Employee getEmployee() {
        return employee;
    }

    public SubBank getSubBank() {
        return subBank;
    }
    
    public void setSubBank(SubBank subBank) {
        this.subBank = subBank;
    }
    
    public void deposite(int money, String id){
        subBank.deposit(money, id);
    }
    
    public void withdraw(int money, String id) {
        subBank.withdraw(money, id);
    }

    public void transfer(int money, String id1, String id2) {
        subBank.transfer(money, id1, id2);
    }
    
    public void deleteAccount(BankAccount acc){
        subBank.deleteBankAccount(acc);
    }
    
    public BankAccount newAccount(String id, Customer customer){
        return subBank.createBankAccount(id, 0, customer);
    }
    
    
    @Override
    public String toString() {
        return "CounterService{" + "employee=" + employee + ", subBank=" + subBank + '}';
    }

    
     
}
