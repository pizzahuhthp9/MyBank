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
public class Customer {
    private String customerId;
    private BankAccount[] BankAccount;
    
    public Customer(String customerId){
        
    }
    public String getCustomerId(){
        return null;
        
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", BankAccount=" + BankAccount + '}';
    } 
}
