/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import base.Customer;
import bank.SubBank;
import base.Employee;
import dataaccess.BankAccountDaoImp;
import dataaccess.CustomerDaoImp;
import dataaccess.EmployeeDaoImp;
import dataaccess.MainBankDaoImp;
import dataaccess.SubBankDaoImp;
import dataaccess.model.DBDao;
import dataaccess.model.MainBankDBDao;
import java.util.ArrayList;

/**
 *
 * @author 62130500127
 */
public class MainBank {

    private int vault;
    private int totalMoney;
    private ArrayList<SubBank> subBanks;
    private ArrayList<BankAccount> bankAccounts;
    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;

    MainBankDBDao main = new MainBankDaoImp();
    DBDao subDao = new SubBankDaoImp();
    DBDao empDao = new EmployeeDaoImp();
    DBDao accDao = new BankAccountDaoImp();
    DBDao cusDao = new CustomerDaoImp();

    public MainBank(int money, int totalMoney) {
        this.vault = money;
        this.totalMoney = totalMoney;
        subBanks = new ArrayList<SubBank>();
        bankAccounts = new ArrayList<BankAccount>();
        employees = new ArrayList<Employee>();
        customers = new ArrayList<Customer>();
    }

    public int getVault() {
        return vault;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public ArrayList<SubBank> getSubBanks() {
        return subBanks;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Employee> getFreeEmployees() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).isFree()) {
                list.add(employees.get(i));
            }
        }
        return list;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public int searchSubBankById(String id) { 
        for (int i = 0; i < subBanks.size(); i++) {
            if (subBanks.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int searchAccountById(String id) { 
        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getAccountId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEmployeeById(String id) { 
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public int searchCustomerById(String id){
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public boolean transferBankAccount(int money, String id1, String id2) {
        int index1 = searchAccountById(id1);
        if (index1 >= 0) {
            int index2 = searchAccountById(id2);
            if (index2 >= 0 && bankAccounts.get(index1).getMoney() >= money) {
                bankAccounts.get(index1).decreaseMoney(money);
                bankAccounts.get(index2).receiveMoney(money);
                accDao.update(bankAccounts.get(index1));
                accDao.update(bankAccounts.get(index2));
                return true;
            }
        }
        return false;
    }

    public boolean deposit(int money, String id, SubBank sub) {
        int index = searchAccountById(id);
        if (index >= 0) {
            bankAccounts.get(index).receiveMoney(money);
            sub.increaseVault(money);
            totalMoney += money;
            accDao.update(bankAccounts.get(index));
            main.update(this);
            return true;
        }
        return false;
    }

    public boolean withdraw(int money, String id, SubBank sub) {
        int index = searchAccountById(id);
        if (index >= 0) {
            if (bankAccounts.get(index).getMoney() >= money) {
                bankAccounts.get(index).decreaseMoney(money);
                sub.decreaseVault(money);
                accDao.update(bankAccounts.get(index));
                main.update(this);
                return true;
            } else {
                System.out.println("Not enough Money");
            }
        }
        return false;
    }

    public void transferVault(int money, SubBank sub1, SubBank sub2) {
        if (sub1.getVault() > money) {
            sub1.decreaseVault(money);
            sub2.increaseVault(money);
            subDao.update(sub1);
            subDao.update(sub2);
        } else {
            System.out.println("not enough money");
            System.out.println(sub1);
        }

    }

    public void giveSubBankMoney(int money, SubBank sub) {
        if (money > vault) {
            System.out.println("not enough money");
            System.out.println(this);
            return;
        }
        sub.increaseVault(money);
        vault -= money;
        main.update(this);
        subDao.update(sub);
    }

    public void receiveMoneyFromSubBank(int money, SubBank sub) {
        if (sub.getVault() < money) {
            System.out.println("not enough money");
            System.out.println(sub);
            return;
        }
        sub.decreaseVault(money);
        vault += money;
        main.update(this);
        subDao.update(sub);
    }

    public void newAccount(BankAccount account) {
        addAccount(account);
        accDao.insert(account);
    }
    
    public void addAccount(BankAccount account) {
        int index = searchAccountById(account.getAccountId());
        if (index >= 0) {
            System.out.println("Account is already exist");
            return;
        }
        bankAccounts.add(account);
    }

    public void deleteAccount(BankAccount acc) {
            bankAccounts.remove(acc);
            accDao.delete(acc);
    }

    public void newSubBank(SubBank sub) {
        addSubBank(sub);
        subDao.insert(sub);
        createMenuFile(sub);
    }

    public void addSubBank(SubBank sub) {
        int index = searchSubBankById(sub.getId());
        if (index >= 0) {
            System.out.println("SubBank is already exist");
            return;
        }
        subBanks.add(sub);
    }

    public void deleteSubBank(SubBank sub) {
        this.vault += sub.getVault();
        sub.getCounterService().getEmployee().setFree(true);
        subBanks.remove(sub);
        main.update(this);
        subDao.delete(sub);
    }

    public void newEmployee(Employee emp) {
        addEmployee(emp);
        empDao.insert(emp);
    }

    public void addEmployee(Employee emp) {
        int index = searchEmployeeById(emp.getEmployeeId());
        if (index >= 0) {
            System.out.println("Employee is already exist");
            return;
        }
        employees.add(emp);
    }

    public void deleteEmployee(Employee emp) {
        employees.remove(emp);
        empDao.delete(emp);
    }

    public void newCustomer(Customer cus) {
        addCustomer(cus);
        cusDao.insert(cus);
    }
    
    public void addCustomer(Customer cus) {
        int index = searchCustomerById(cus.getCustomerId());
        if (index >= 0) {
            System.out.println("Customer is already exist");
            return;
        }
        customers.add(cus);
    }
        
    public void deleteCustomer(Customer cus){
        customers.remove(cus);
        cusDao.delete(cus);
    }

    public void listSubBank(int startWith) {
        if (subBanks.size() == 0) {
            System.out.println("No SubBank");
            return;
        }
        int count = 0;
        for (int i = startWith - 1; i < subBanks.size() + startWith - 1; i++) {
            System.out.println(i + 1 + ".)" + subBanks.get(count));
            count++;
        }
    }

    public void listAccount() {
        if (bankAccounts.size() == 0) {
            System.out.println("No Account");
            return;
        }
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println(i + 1 + ".)" + bankAccounts.get(i));
        }
    }

    public void listEmployee() {
        if (employees.size() == 0) {
            System.out.println("No Employee");
            return;
        }
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(i + 1 + ".)" + employees.get(i));
        }
    }

    public void listFreeEmployee() {
        int count = 1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).isFree()) {
                System.out.println(count + ".)" + employees.get(i));
                count++;
            }
        }
        if (count == 1) {
            System.out.println("No free Employee");
        }
    }
    
    public void listCustomer(){
        if (customers.size() == 0) {
            System.out.println("No Customer");
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(i + 1 + ".)" + customers.get(i));
        }
    }
    
    public void createMenuFile(SubBank sub){
        
    }

    @Override
    public String toString() {
        return "MainBank{" + "vault=" + vault + ", totalMoney=" + totalMoney + ", subBanksCount=" + subBanks.size() + '}';
    }

}
