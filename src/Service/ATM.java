/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Service.Service;
import bank.MainBank;
import bank.SubBank;
import base.CreditCard;
import machine.Machine;
import machine.MachineStatus;

/**
 *
 * @author 62130500127
 */
public class ATM extends Machine{
    CreditCard card;
    
    public ATM(String machineId,MainBank main,int money) {
       super(machineId, money, main);
    }

    public String getMachineId(){
        return super.getMachineId();
    }
        
    public int getMoney(){
        return super.getMoney();
    }
    
    public void receiveCard(CreditCard card){
        if (this.card != null) {
            System.out.println("Card is already insert");
            return;
        }
        this.card = card;
        
//        while(card != null){
//            displayMenu();
//        }
    }
    
    public void returnCard(){
        this.card = null;
    }
        
    public void withdraw(int money) {
        if (money > super.getMoney()) {
            System.out.println("ATM have not enough money");
            return;
        }
        super.getMainBank().withdraw(money, card.getAccount().getAccountId());
    }

    public void transfer(int money, String id) {
        super.getMainBank().transferBankAccount(money, card.getAccount().getAccountId(), id);
    }

    @Override
    protected void displayMenu() {
        System.out.printf("================\n1.)withdraw\n2.transfer\n================");
    }
}
