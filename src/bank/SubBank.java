/*private int vault;
    private String address;
    private Machine[] machines;
    private int machineCount;
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import base.Customer;
import base.Employee;
import dataaccess.BankAccountDaoImp;
import dataaccess.SubBankDaoImp;
import dataaccess.model.DBDao;

/**
 *
 * @author 62130500127
 */
public class SubBank {

    private MainBank mainBank;
    private int vault;
    private String id;
    private CounterService counterService;
    private DBDao subDao = new SubBankDaoImp();

    public SubBank(String id, CounterService counter, MainBank mainBank, int vault) {
        this.id = id;
        this.counterService = counter;
        counter.setSubBank(this);
        this.mainBank = mainBank;
        this.vault = vault;
    }

    public boolean createBankAccount(String id, int money, Customer customer) {
        int index = mainBank.searchAccountById(id);
        if (index == -1) {
            BankAccount ac = new BankAccount(id, customer, 0);
            mainBank.newAccount(ac);
            return true;
        } else {
            System.out.println("the account is already exist");
            return false;
        }
    }

    public boolean deleteBankAccount(BankAccount acc) {
        int index = mainBank.searchAccountById(acc.getAccountId());
        if (index >= 0) {
            mainBank.deleteAccount(acc);
            return true;
        }
        return false;
    }

    public boolean createCustomer(Customer cus) {
        int index = mainBank.searchCustomerById(cus.getCustomerId());
        if (index == -1) {
            mainBank.newCustomer(cus);
            return true;
        }
        return false;
    }

    public boolean transfer(int money, String id1, String id2) {
        return mainBank.transferBankAccount(money, id1, id2);
    }

    public void deposit(int money, String id) {
        if (mainBank.deposit(money, id, this)) {
            vault += money;
            subDao.update(this);
        }
    }

    public void withdraw(int money, String id) {
        if (money > vault) {
            System.out.println("Bank have not enough money");
            return;
        }
        if (mainBank.withdraw(money, id, this)) {
            vault -= money;
            subDao.update(this);
        }
    }

    public void increaseVault(int money) {
        this.vault += money;
        subDao.update(this);
    }

    public void decreaseVault(int money) {
        if (this.vault >= money) {
            this.vault -= money;
            subDao.update(this);
        }
    }

    public int getVault() {
        return vault;
    }

    public String getId() {
        return id;
    }

    public CounterService getCounterService() {
        return counterService;
    }

    public void setMainBank(MainBank mainBank) {
        this.mainBank = mainBank;
    }

    @Override
    public String toString() {
        return "SubBank{" + "id=" + id + ", vault=" + vault + ", counterServiceEmployee=" + counterService.getEmployee().getFirstName();

    }

}
