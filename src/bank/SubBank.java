/*private int vault;
    private String address;
    private Machine[] machines;
    private int machineCount;
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import Service.CounterService;
import base.Customer;
import base.Employee;
import Service.ATM;
import machine.Machine;
import machine.MachineStatus;

/**
 *
 * @author 62130500127
 */
public class SubBank {

    private MainBank mainBank;
    private int vault;
    private String address;
    private CounterService counterService;

    public SubBank(String address, CounterService counter, MainBank mainBank) {
        this.address = address;
        this.counterService = counter;
        counter.setSubBank(this);
        this.mainBank = mainBank;
    }

    public BankAccount createBankAccount(String id, int money, Customer customer) {
        int index = mainBank.searchAccount(id);
        if (index == -1 && customer.getBankAccount() == null) {
            BankAccount ac = new BankAccount(id, customer);
            mainBank.addAccount(ac);
            customer.setBankAccount(ac);
            return ac;
        } else{
            System.out.println("the account is already exist");
            return null;
        }
    }
    
    public boolean deleteBankAccount(String id){
        int index = mainBank.searchAccount(id);
        if (index >= 0) {
            mainBank.deleteAccount(id);
            return true;
        }
        return false;
    }

    public void transfer(int money, String id1, String id2) {
        mainBank.transferBankAccount(money, id1, id2);
    }
    
    public void deposit(int money, String id){
        if (mainBank.deposit(money, id)) {
            vault += money;
        }
    }
    
    public void withdraw(int money, String id){
        if (money > vault) {
            System.out.println("Bank have not enough money");
            return;
        }
        if (mainBank.withdraw(money, id)) {
            vault -= money;
        }
    }

    public void increaseVault(int money) {
        this.vault += money;
    }

    public void decreaseVault(int money) {
        if (this.vault >= money) {
            this.vault -= money;
        }
    }

    public int getVault() {
        return vault;
    }

    public String getAddress() {
        return address;
    }

    public CounterService getCounterService() {
        return counterService;
    }

    @Override
    public String toString() {
        return "SubBank{" + "vault=" + vault + ", address=" + address + ", counterService=" + counterService + '}';
    }

}
