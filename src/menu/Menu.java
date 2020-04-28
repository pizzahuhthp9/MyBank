/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import bank.BankAccount;
import bank.MainBank;
import bank.SubBank;
import base.Customer;
import base.Employee;
import dataaccess.BankAccountDaoImp;
import dataaccess.CustomerDaoImp;
import dataaccess.EmployeeDaoImp;
import dataaccess.MainBankDaoImp;
import dataaccess.SubBankDaoImp;
import dataaccess.model.DBDao;
import dataaccess.model.MainBankDBDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 62130500127
 */
public abstract class Menu {
    public static void pressEnter() {
        System.out.print("press ENTER to continue...");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(OwnerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MainBank loadData() {
        MainBankDBDao mainDB = new MainBankDaoImp();
        DBDao subDB = new SubBankDaoImp();
        DBDao empDB = new EmployeeDaoImp();
        DBDao accDB = new BankAccountDaoImp();
        DBDao cusDB = new CustomerDaoImp();

        ArrayList<SubBank> subList = subDB.getAll();
        ArrayList<BankAccount> accList = accDB.getAll();
        ArrayList<Employee> empList = empDB.getAll();
        ArrayList<Customer> cusList = cusDB.getAll();
        MainBank main = mainDB.getBank();

        for (int i = 0; i < subList.size(); i++) {
            main.addSubBank(subList.get(i));
        }
        for (int i = 0; i < empList.size(); i++) {
            main.addEmployee(empList.get(i));
        }
        for (int i = 0; i < accList.size(); i++) {
            main.addAccount(accList.get(i));
        }
        for (int i = 0; i < cusList.size(); i++) {
            main.addCustomer(cusList.get(i));
        }
        return main;
    }
}
