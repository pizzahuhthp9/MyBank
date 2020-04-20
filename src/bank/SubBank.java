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
    private Machine[] machines;
    private int machineCount;
    private CounterService counterService;

    public SubBank(String address, CounterService counter, MainBank mainBank) {
        this.address = address;
        this.counterService = counter;
        counter.setSubBank(this);
        machines = new Machine[10];
        this.mainBank = mainBank;
    }

    private void expandMachineSzie() {
        Machine[] temp = new Machine[machines.length + 10];
        System.arraycopy(machines, 0, temp, 0, machines.length);
        machines = temp;
    }

    private int searchMachine(String id) {
        for (int i = 0; i < machines.length - 1; i++) {
            if (machines[i].getMachineId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isMachineFull() {
        if (machines.length == machineCount) {
            return true;
        }
        return false;
    }

    public boolean addMoneyToATM(String id, int money) {
        int index = searchMachine(id);
        if (index >= 0 && money >= this.vault) {
            machines[index].receiveMoney(money);
            vault -= money;
            return true;
        }
        return false;
    }

    public void createATM(String id, int money, String location) {
        int index = searchMachine(id);
        if (index == -1) {
            if (isMachineFull()) {
                expandMachineSzie();
            }
            if (money > this.vault) {
                machines[machineCount++] = new ATM(id, this, money, location);
            }

        }
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
        mainBank.deposit(money, id, this);
    }
    
    public void withdraw(int money, String id){
        if (money > vault) {
            System.out.println("Bank have not enough money");
            return;
        }
        mainBank.withdraw(money, id, this);
    }
    
    public void deposit(int money, String id, Machine machine){
        if(mainBank.deposit(money, id, this)){
            vault -= money;
            machine.receiveMoney(money);
        }
    }
    
    public void withdraw(int money, String id, Machine machine){
        if (mainBank.withdraw(money, id, this)) {
            vault += money;
            machine.decreaseMoney(money);
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

    public int getMachineCount() {
        return machineCount;
    }

    public Machine[] getMachines() {
        return machines;
    }

    @Override
    public String toString() {
        return "SubBank{" + "vault=" + vault + ", address=" + address + ", ATMs=" + machines + ", counterService=" + counterService + '}';
    }

}
