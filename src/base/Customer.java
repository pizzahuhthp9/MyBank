/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import bank.BankAccount;

/**
 *
 * @author 62130500127
 */
public class Customer extends Person{
    private String customerId;
    private BankAccount BankAccount;
    
    public Customer(String customerId, Person person){
        super(person.getFirstName(), person.getLastName(), person.getTelephone(), person.getEmail(), person.getAddress());
        this.customerId = customerId;
    }
    
    public String getCustomerId(){
        return customerId;
    }

    public BankAccount getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(BankAccount BankAccount) {
        this.BankAccount = BankAccount;
    }
    
    

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", BankAccount=" + BankAccount + '}';
    } 
}
