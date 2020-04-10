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
public class MainBank {
private int vault;
    private int totalMoney;
    private SubBank[] subBanks;
    
    public MainBank(int vault,int totalMoney){
        
    }
    
    public void deposit(){
        
    }
    
    public void withdraw(){
        
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
    
    public void transfer(){
        
    }
    
    public void tranferValt(int money){
        
    }
    public void newSubBank(int money){
        
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
