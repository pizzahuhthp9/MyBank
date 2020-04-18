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
import java.util.Objects;

/**
 *
 * @author 62130500127
 */
public class BankAccount {
    private Customer owner;
    private int money;
    private String accountId;

    public BankAccount(String id,Customer owner) {
        this.accountId = id;
        this.owner = owner;
    }

    public Customer getOwner() {
        return owner;
    }

    public int getMoney() {
        return money;
    }

    public String getAccountId() {
        return accountId;
    }
    
    public void receiveMoney(int money){
        this.money += money;
    }
    
    public void decreaseMoney(int money){
        this.money -= money;
    }
    
    @Override
    public String toString() {
        return "BankAccount{" + "owner=" + owner + ", Money=" + money + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BankAccount other = (BankAccount) obj;
        if (this.money != other.money) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return true;
    }
    
    
    
}

