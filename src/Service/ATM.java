/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Service.Service;
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
    
    public ATM(String machineId,SubBank subbank,int money,String location) {
       super(machineId, subbank, money, location);
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
        
        while(card != null){
            displayMenu();
        }
    }
    
    private void returnCard(){
        this.card = null;
    }
        
    private void withdraw(int money) {
        if (money > super.getMoney()) {
            System.out.println("ATM have not enough money");
            return;
        }
        super.getSubBank().withdraw(money, card.getAccount().getAccountId());
    }

    private void transfer(int money, String id2) {
        super.getSubBank().transfer(money, card.getAccount().getAccountId(), id2);
    }

    @Override
    protected void displayMenu() {
        System.out.printf("================\n1.)withdraw\n2.transfer\n================");
    }
}
