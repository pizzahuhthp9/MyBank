/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import base.Customer;
import machine.ATM;
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
   private BankAccount[] bankAccounts;
   private int accountCount;
   private CounterService counterService;
   
   public SubBank(int vault,String address,CounterService counterService){
       this.vault = vault;
       this.address = address;
       this.counterService = counterService;
       machines = new Machine[10];
       bankAccounts = new BankAccount[10];
   }
   
   public int searchMachine(String id){
       for (int i = 0; i < machines.length-1; i++) {
           if (machines[i].getMachineId().equals(id)) {
               return i;
           }
       }
       return -1;
   }
   
   public int searchAccount(String id){
       for (int i = 0; i < bankAccounts.length-1; i++) {
           if (bankAccounts[i].getAccountId().equals(id)) {
               return i;
           }
       }
       return -1;
   }
   
   public boolean isBankAccountsFull(){
       if (bankAccounts.length == accountCount) {
           return true;
       }
       return false;
   }
   
   public boolean isMachineFull(){
       if (machines.length == machineCount) {
           return true;
       }
       return false;
   }
   
   public boolean addMoneyToATM(String id,int money){
       int index = searchMachine(id);
       if (index >= 0 && money >= this.vault) {
           machines[index].receiveMoney(money);
           vault -= money;
           return true;
       }
       return false;
   }
   
   public void expandBankAccountsSize(){
       BankAccount[] temp = new BankAccount[bankAccounts.length + 10];
       System.arraycopy(bankAccounts, 0, temp, 0, bankAccounts.length);
       bankAccounts = temp;
   }
   
   public void expandMachineSzie(){
       Machine[] temp = new Machine[bankAccounts.length + 10];
       System.arraycopy(machines, 0, temp, 0, machines.length);
       machines = temp;
   }
   
   public void createATM(String id, int money, String location){
       int index = searchMachine(id);
       if (index == -1){
           if (isMachineFull()) {
               expandMachineSzie();
           }
           if (money > this.vault) {
               machines[machineCount++] = new ATM(id, this, money, location, MachineStatus.AVAILABLE);
           }
           
       }
   }
   
   public void createBankAccount(String id, int money, Customer customer){
       int index = searchAccount(id);
       if (index == -1) {
           if (isBankAccountsFull()) {
               expandBankAccountsSize();
           }
           bankAccounts[accountCount++] = new BankAccount(id, customer);
           bankAccounts[accountCount].receiveMoney(money);
           vault+=money;
       }
   }
   
   public void transferbankAccountsMoney(int Money,String id,String address){
       
   }
   
   public void increaseAccountMoney(int money,String id){
       int index = searchAccount(id);
       if (index >= 0) {
           bankAccounts[index].receiveMoney(money);
       }
   }
   
   public void decreaseAccountMoney(int money,String id){
       int index = searchAccount(id);
       if (index >= 0) {
           bankAccounts[index].decreaseMoney(money);
       }
   }
   
   public void increaseVault(int money){
       this.vault += money;
   }
   
   public void decreaseVault(int money){
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
        return "SubBank{" + "vault=" + vault + ", address=" + address + ", ATMs=" + machines + ", bankAccounts=" + bankAccounts + ", counterService=" + counterService + '}';
    }
   
}
