/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import base.Customer;
import bank.BankAccount;
import bank.SubBank;
/**
 *
 * @author 62130500127
 */
public class MainBank {
private int vault;
    private int totalMoney;
    private SubBank[] subBanks;
    private BankAccount[] bankAccount;
    
    public MainBank(int vault,int totalMoney){
        vault = this.vault;
        totalMoney = this.totalMoney;
        subBanks = new SubBank[1];
        bankAccount = new BankAccount[1];
    }
    
    public void deposit(int amount){
       totalMoney += amount; 
    }
    
    public void withdraw(int amount){
        
    if (amount > totalMoney){    
      System.out.println("Insufficient Funds!!!");
    } else {
      totalMoney -= amount;
    }
     
    }

    public int getVault() {
        return vault;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public SubBank[] getSubBanks() {
        return subBanks;
    }

    public void setVault(int vault) {
        
        this.vault = vault;
    }
    
    public int searchMachine(String id){
        for (int i = 0; i < bankAccount.length; i++) {
          if(bankAccount[i].getAccountId().equals(id))
              return i;
        }
    return -1;
    }
    
    public void transfer(int money,String id1,String id2){//โอนเงินระหว่างbankaccount
       int x =  searchMachine(id1);
       if(x != -1){
           bankAccount[x].decreaseMoney(money);// ลดเงินในบัญชีที่ 1 ไปใส่ บัญชี/
           int y =  searchMachine(id2);
           if(y != -1){
               bankAccount[y].receiveMoney(money);
           }
       }
    }
    public int searchSubBank(String ad){
        for (int i = 0; i < subBanks.length; i++) {
          if(subBanks[i].getAddress().equals(ad))
              return i;
        }
    return -1;
    }
    
    public void tranferVeult(int money,String ad1,String ad2){//subBank veult
        int x =  searchSubBank(ad1);
       if(x != -1){
           subBanks[x].decreaseAccountMoney(money, ad1);// ลดเงินในบัญชีที่ 1 ไปใส่ บัญชี/
           int y =  searchMachine(ad2);
           if(y != -1){
           subBanks[y].increaseAccountMoney(money, ad2);
           }
       }
    }
    public void newSubBank(SubBank[] money){// new object ยัดเข้าไปใยarray
        for (int i = 0; i < subBanks.length; i++) {
            if(subBanks[i].isMachineFull()) {
                subBanks[i].expandBankAccountsSize();
            }
            
            
        }
    }
    public void deleteSubBank(){
        
    }
    
    @Override
    public String toString() {
        return "MainBank{" + "vault=" + vault + ", totalMoney=" + totalMoney + ", subBanks=" + subBanks + '}';
    }   
    public static void main(String[] args) {
        // TODO code application logic here
    }
      
}
