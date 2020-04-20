/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import bank.BankAccount;

/**
 *
 * @author 62130500127
 */
public class CreditCard {
    BankAccount account;
    String cardId;

    public CreditCard(BankAccount account, String cardId) {
        this.account = account;
        this.cardId = cardId;
    }

    public BankAccount getAccount() {
        return account;
    }

    public String getCardId() {
        return cardId;
    }
    
    
}
