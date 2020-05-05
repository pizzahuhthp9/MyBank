/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.sub;

import bank.CounterService;
import bank.BankAccount;
import bank.MainBank;
import base.Customer;
import base.Person;
import dataaccess.CustomerDaoImp;
import dataaccess.model.DBDao;
import java.util.Scanner;
import menu.Menu;
import menu.Menu;

/**
 *
 * @author karn
 */
public class BankMenuTemplate extends Menu {

    private static MainBank main = loadData();
    private static CounterService cs;
    
    public static void main(String[] args) {
        String menu = "============Menu============\n"
                + "1. Deposit\n"
                + "2. Withdraw\n"
                + "3. Transfer\n"
                + "4. Create Account\n"
                + "5. Delete Account\n"
                + "6. Register Member\n"
                + "7. Check Account\n"
                + "0. Exit\n"
                + "----Select: ";
        Scanner sc = new Scanner(System.in);
        String subId;
        int check;
        do {            
            System.out.print("please insert sub bank id (insert \"0\" to cancel) :");
            subId = sc.next();
            check = main.searchSubBankById("sub" + subId);
            if (subId.equals("0")) {
                return;
            } else if(subId.length() != 3){
                System.out.println("ID must be 3 digits number");
                pressEnter();
            } else if(check == -1){
                System.out.println("Sub bank does not exist");
                pressEnter();
            }
            
        } while (check == -1 || subId.length() != 3);
        
        cs = main.getSubBanks().get(check).getCounterService();
        cs.getSubBank().setMainBank(main);
        
        int input;
        do {
            System.out.print(menu);
            input = sc.nextInt();
            switch (input) {
                case 1:
                    deposit();
                    break;
                case 2: 
                    withdraw();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    createAccount();
                    break;
                case 5:
                    deleteAccount();
                    break;
                case 6:
                    register();
                    break;
                case 7:
                    check();
                    break;
                case 0:
                    System.out.println("finished");
                    break;
                default:
                    System.out.println(input + " is not menu");
                    pressEnter();
                    break;
            }
        } while (input != 0);

    }
    
    public static void deposit(){
        Scanner sc = new Scanner(System.in);
        String id;
        int check;
        do {            
            System.out.print("insert your Account ID (insert \"0\" to cancel): ");
            id = sc.next();
            check = main.searchAccountById("acc" + id);
            if (id.equals("0")) {
                return;
            } else if(check == -1 || id.length() != 3){
                System.out.println("Account Not Found");
                pressEnter();
            }
        } while (check == -1 || id.length() != 3);
        int money;
        do {            
            System.out.print("insert amount of MONEY : ");
            money = sc.nextInt();
            if (money < 0) {
                System.out.println("please insert unsigned value");
                pressEnter();
            }
        } while (money < 0);
        
        cs.deposit(money, "acc" + id);
    }
    
    public static void withdraw(){
        Scanner sc = new Scanner(System.in);
        String id;
        int check;
        do {            
            System.out.print("insert your Account ID (insert \"0\" to cancel): ");
            id = sc.next();
            check = main.searchAccountById("acc" + id);
            if (id.equals("0")) {
                return;
            } else if(check == -1 || id.length() != 3){
                System.out.println("Account Not Found");
                pressEnter();
            }
        } while (check == -1 || id.length() != 3);
        int money;
        do {            
            System.out.print("insert amount of MONEY : ");
            money = sc.nextInt();
            if (money < 0) {
                System.out.println("please insert unsigned value");
                pressEnter();
            }
        } while (money < 0);
        
        cs.withdraw(money, "acc" + id);
    }
    
    public static void transfer(){
        Scanner sc = new Scanner(System.in);
        String id1;
        int checkId1;
        do {            
            System.out.print("insert your Account ID (insert \"0\" to cancel): ");
            id1 = sc.next();
            checkId1 = main.searchAccountById("acc" + id1);
            if (id1.equals("0")) {
                return;
            } else if(checkId1 == -1 || id1.length() != 3){
                System.out.println("Account Not Found");
                pressEnter();
            }
        } while (checkId1 == -1 || id1.length() != 3);
        
        String id2;
        int check;
        do {            
            System.out.print("insert your Account ID (insert \"0\" to cancel): ");
            id2 = sc.next();
            check = main.searchAccountById("acc" + id2);
            if (id2.equals("0")) {
                return;
            } else if(check == -1 || id2.length() != 3){
                System.out.println("Account Not Found");
                pressEnter();
            }
        } while (check == -1 || id2.length() != 3);
        
        int money;
        do {            
            System.out.print("insert amount of MONEY : ");
            money = sc.nextInt();
            if (money < 0) {
                System.out.println("please insert unsigned value");
                pressEnter();
            }
        } while (money < 0);
        
        if (cs.transfer(money, "acc" + id1, "acc" + id2)) {
            System.out.println("transfer complete");
        } else{
            System.out.println("not enough money");
        }
        pressEnter();
    }

    public static void createAccount(){
        Scanner sc = new Scanner(System.in);
        String cusId;
        int cusCheck;
        do {            
            System.out.print("insert your Customer ID (insert \"0\" to cancel): ");
            cusId = sc.next();
            cusCheck = main.searchCustomerById("cus" + cusId);
            if (cusId.equals("0")) {
                return;
            } else if(cusCheck == -1){
                System.out.println("ID Not Found");
                pressEnter();
            }
        } while (cusId.length() != 3 || cusCheck == -1);
        
        System.out.println(main.getCustomers().get(cusCheck));
        
        String accId;
        int check = 0;
        do {            
            System.out.print("insert your Account ID (insert \"0\" to cancel): ");
            accId = sc.next();
            check = main.searchAccountById("acc" + accId);
            if (accId.equals("0")) {
                return;
            } else if(check >= 0){
                System.out.println("ID is already exist");
                pressEnter();
            }
        } while (accId.length() != 3 || check >= 0);
        cs.newAccount("acc" + accId, main.getCustomers().get(cusCheck));
    }
    
    public static void deleteAccount(){
        Scanner sc = new Scanner(System.in);
        int cusCheck;
        String cusId;
        
        do {            
            System.out.print("insert your Customer ID (insert \"0\" to cancel): ");
            cusId = sc.next();
            cusCheck = main.searchCustomerById("cus" + cusId);
            if (cusId.equals("0")) {
                return;
            } else if(cusCheck == -1){
                System.out.println("ID Not Found");
                pressEnter();
            }
        } while (cusId.length() != 3 || cusCheck == -1);
        
        String accId;
        int check = 0;
        do {            
            System.out.print("insert your Account ID (insert \"0\" to cancel): ");
            accId = sc.next();
            check = main.searchAccountById("acc" + accId);
            if (accId.equals("0")) {
                return;
            } else if(check == -1){
                System.out.println("Account is not exist");
                pressEnter();
            }
        } while (accId.length() != 3 || check == -1);
        
        cs.deleteAccount(main.getBankAccounts().get(check));
    }
    
    public static void register() {
        Scanner sc = new Scanner(System.in);

        String id;
        int check;
        do {
            System.out.print("insert 3 digit number for id (insert \"0\" to cancel): ");
            id = sc.next();
            check = main.searchCustomerById("cus" + id);
            if (id.equals("0")) {
                return;
            } else if (check >= 0) {
                System.out.println("id is already exist");
                pressEnter();
            } else if (id.length() != 3) {
                System.out.println("id was " + id.length() + " number");
                pressEnter();
            }
        } while (id.length() != 3 || check >= 0);

        System.out.print("insert name (insert \"0\" to cancel):");
        String name = sc.next();
        if (name.equals("0")) {
            return;
        }

        System.out.print("insert surname (insert \"0\" to cancel):");
        String surname = sc.next();
        if (surname.equals("0")) {
            return;
        }

        String tele;
        do {
            System.out.print("insert telephone number (insert \"0\" to cancel):");
            tele = sc.next();
            if (tele.equals("0")) {
                pressEnter();
            } else if (tele.length() != 10) {
                System.out.println("telephone number must be 10 digits number:");
                pressEnter();
            }
        } while (tele.length() != 10);

        System.out.print("insert email (insert \"0\" to cancel):");
        String email = sc.next();
        if (surname.equals("0")) {
            return;
        }

        System.out.print("insert address (insert \"0\" to cancel):");
        String address = sc.next();
        if (surname.equals("0")) {
            return;
        }
        cs.newCustomer("cus" + id , name, surname, tele, email, address);
    }
    
    public static void check(){
        Scanner sc = new Scanner(System.in);
        String accId;
        int check = 0;
        do {            
            System.out.print("insert your Account ID (insert \"0\" to cancel): ");
            accId = sc.next();
            check = main.searchAccountById("acc" + accId);
            if (accId.equals("0")) {
                return;
            } else if(check == -1){
                System.out.println("Account is not exist");
                pressEnter();
            }
        } while (accId.length() != 3 || check == -1);
        
        System.out.println(main.getBankAccounts().get(check));
        pressEnter();
    }
}

