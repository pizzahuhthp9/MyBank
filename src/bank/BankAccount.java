/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author 62130500127
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import base.Customer;

/**
 *
 * @author 62130500127
 */
public class BankAccount {
    private Customer owner;
    private int Money;

    public BankAccount(Customer owner) {
        this.owner = owner;
    }

    public Customer getOwner() {
        return owner;
    }

    public int getMoney() {
        return Money;
    }

    @Override
    public String toString() {
        return "BankAccount{" + "owner=" + owner + ", Money=" + Money + '}';
    }
    
    public int compareTo(BankAccount b){
        return 0;
        
    }
    
}

