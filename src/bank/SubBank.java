/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import machine.Machine;

/**
 *
 * @author 62130500127
 */
public class SubBank {
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
       machines = new Machine[1];
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
       if (index >= 0) {
           machines[index].receiveMoney(money);     //กานจะเขียน
           return true;
       }
       return false;
   }
   
   public void expandBankAccountsSize(){
       BankAccount[] temp = new BankAccount[bankAccounts.length + 10];
       System.arraycopy(bankAccounts, 0, temp, 0, bankAccounts.length);
       bankAccounts = temp;
   }
   
   public void createATM(String id){
       int index = searchMachine(id);
       if (index == -1){
           if (isMachineFull()) {
               expandBankAccountsSize();
           }
           
       }
   }
   
   public void transferbankAccountsMoney(int Money,String id,String address){
       
   }
   public void increaseMoney(int money,String id){
       
   }
   public void decreaseMoney(int money,String id){
       
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
