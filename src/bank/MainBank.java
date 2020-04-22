/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import base.Customer;
import bank.SubBank;
import base.Employee;

/**
 *
 * @author 62130500127
 */
public class MainBank {

    private int vault;
    private int totalMoney;
    private SubBank[] subBanks;
    private BankAccount[] bankAccount;
    private int subBankCount;
    private int accountCount;

    public MainBank(int money) {
        this.vault = money;
        this.totalMoney = money;
        subBanks = new SubBank[1];
        bankAccount = new BankAccount[1];
    }

    public int getVault() {
        return vault;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public int searchSubBank(String id) {
        for (int i = 0; i < subBanks.length-1; i++) {
            if (subBanks[i].getAddress().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int searchAccount(String id) {
        for (int i = 0; i < bankAccount.length-1; i++) {
            if (bankAccount[i].getAccountId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void transferBankAccount(int money, String id1, String id2) {
        int x = searchAccount(id1);
        if (x >= 0) {
            bankAccount[x].decreaseMoney(money);
            int y = searchAccount(id2);
            if (y >= 0) {
                bankAccount[y].receiveMoney(money);
            }
        }
    }

    public boolean deposit(int money, String id) {
        int index = searchAccount(id);
        if (index >= 0) {
            bankAccount[index].receiveMoney(money);
            totalMoney += money;
            return true;
        }
        return false;
    }

    public boolean withdraw(int money, String id) {
        int index = searchAccount(id);
        if (index >= 0) {
            if (bankAccount[index].getMoney() >= money) {
                bankAccount[index].decreaseMoney(money);
                totalMoney -= money;
                return true;
            } else {
                System.out.println("Not enough Money");
            }
        }
        return false;
    }

    public void transferVault(int money, String ad1, String ad2) {
        int x = searchSubBank(ad1);
        if (x != -1) {
            subBanks[x].decreaseVault(money);
            int y = searchSubBank(ad2);
            if (y != -1) {
                subBanks[y].increaseVault(money);
            }
        }
    }
    
    public void giveSubBankMoney(int money, String ad){
        if (money > vault) {
            return;
        }
        int index = searchSubBank(ad);
        subBanks[index].increaseVault(money);
        vault -= money;
    }

    protected void addAccount(BankAccount account) {
        BankAccount[] temp = new BankAccount[bankAccount.length + 1];
        System.arraycopy(bankAccount, 0, temp, 0, bankAccount.length);
        bankAccount = temp;
        bankAccount[accountCount++] = account;
    }

    protected void deleteAccount(String id) {
        int index = searchAccount(id);
        bankAccount[index] = bankAccount[accountCount--];
        bankAccount[accountCount + 1] = null;
    }

    public void addSubBank(SubBank sub) {
        int index = searchSubBank(sub.getAddress());
        if (index >= 0) {
            System.out.println("SubBank is already exist subBank");
            return;
        }
        subBanks[subBankCount++] = sub;
        SubBank[] temp = new SubBank[subBanks.length + 1];
        System.arraycopy(subBanks, 0, temp, 0, subBanks.length);
        subBanks = temp;
    }

    public void deleteSubBank(String address) {
        int index = searchSubBank(address);
        if (index >= 0) {
            this.vault += subBanks[index].getVault();
            subBanks[index] = null;
            subBankCount--;
            subBanks[index] = subBanks[subBankCount];
            subBanks[subBankCount] = null;
            System.out.println("Deleted");
        } else {
            System.out.println("Sub bank address not exist");
        }
    }

    @Override
    public String toString() {
        return "MainBank{" + "vault=" + vault + ", totalMoney=" + totalMoney + ", subBanks=" + subBanks + '}';
    }

}
