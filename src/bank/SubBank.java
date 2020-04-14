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
   private Machine[] ATMs;
   private BankAccount[] bankAccounts;
   private CounterService counterService;
   
   public SubBank
        (int vault,String address,CounterService counterService){
       
   }
   public boolean addMoneyToATM(String Id,int money){
       return true;
   }
   
   public void createATM(){
       
   }
   
   public void transferbankAccountsMoney
        (int Money,String id,String address){
       
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
        return "SubBank{" + "vault=" + vault + ", address=" + address + ", ATMs=" + ATMs + ", bankAccounts=" + bankAccounts + ", counterService=" + counterService + '}';
    }
   
}
