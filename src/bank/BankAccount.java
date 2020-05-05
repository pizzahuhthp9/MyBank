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
import base.Customer;
import dataaccess.BankAccountDaoImp;
import dataaccess.model.DBDao;
import java.util.Objects;

/**
 *
 * @author 62130500127
 */
public class BankAccount {
    private Customer owner;
    private int money;
    private String accountId;
    private DBDao accDao = new BankAccountDaoImp();

    public BankAccount(String id,Customer owner, int money) {
        this.accountId = id;
        this.owner = owner;
        this.money = money;
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
        accDao.update(this);
    }
    
    public void decreaseMoney(int money){
        if (this.money >= money) {
            this.money -= money;
            accDao.update(this);
        }
    }
    
    @Override
    public String toString() {
        return String.format("AccountId : %s\nname : %s %s\nCurrent Money : %d\n", accountId, owner.getFirstName(), owner.getLastName(), money);
    }
    
}

