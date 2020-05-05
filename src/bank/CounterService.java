/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import bank.BankAccount;
import bank.SubBank;
import base.Customer;
import base.Employee;
import base.Person;

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
    
    public void deposit(int money, String id){
        subBank.deposit(money, id);
    }
    
    public void withdraw(int money, String id) {
        subBank.withdraw(money, id);
    }

    public boolean transfer(int money, String id1, String id2) {
        return subBank.transfer(money, id1, id2);
    }
    
    public void deleteAccount(BankAccount acc){
        subBank.deleteBankAccount(acc);
    }
    
    public boolean newAccount(String id, Customer customer){
        return subBank.createBankAccount(id, 0, customer);
    }
    
    public boolean newCustomer(String id, String name, String lname, String tele, String mail, String adds){
//        return subBank.createCustomer(new Customer(id, person));
        return subBank.createCustomer(new Customer(id, new Person(name, lname, tele, mail, adds)));
    }
    
    
    @Override
    public String toString() {
        return "CounterService{" + "employee=" + employee + ", subBank=" + subBank + '}';
    }
}
