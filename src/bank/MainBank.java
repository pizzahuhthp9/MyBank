/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import base.Customer;
import base.Employee;
import dataaccess.BankAccountDaoImp;
import dataaccess.CustomerDaoImp;
import dataaccess.EmployeeDaoImp;
import dataaccess.MainBankDaoImp;
import dataaccess.SubBankDaoImp;
import dataaccess.model.DBDao;
import dataaccess.model.MainBankDBDao;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//        subBanks = subDao.getAll();
        for (int i = 0; i < subBanks.size(); i++) {
            if (subBanks.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int searchAccountById(String id) { 
//        bankAccounts = accDao.getAll();
        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getAccountId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEmployeeById(String id) { 
//        employees = empDao.getAll();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public int searchCustomerById(String id){
//        customers = cusDao.getAll();
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
//                accDao.update(bankAccounts.get(index1));
//                accDao.update(bankAccounts.get(index2));
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
//            accDao.update(bankAccounts.get(index));
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
//                accDao.update(bankAccounts.get(index));
                totalMoney -= money;
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
//            subDao.update(sub1);
//            subDao.update(sub2);
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
//        subDao.update(sub);
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
//        subDao.update(sub);
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
        File file = new File("src//menu//sub//" + sub.getId() + "Menu.java");
        file.delete();
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
    
    private void createMenuFile(SubBank sub){
        try {
            FileWriter wtr = new FileWriter("src//menu//sub//" + sub.getId() + "Menu.java");
            wtr.write(getString1(sub));
            wtr.write(getString2());
            wtr.close();
        } catch (IOException ex) {
            Logger.getLogger(MainBank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "MainBank{" + "vault=" + vault + ", totalMoney=" + totalMoney + ", subBanksCount=" + subBanks.size() + '}';
    }
    
    private String getString1(SubBank sub){
        return "/*\n" +
" * To change this license header, choose License Headers in Project Properties.\n" +
" * To change this template file, choose Tools | Templates\n" +
" * and open the template in the editor.\n" +
" */\n" +
"package menu.sub;\n" +
"\n" +
"import bank.CounterService;\n" +
"import bank.BankAccount;\n" +
"import bank.MainBank;\n" +
"import base.Customer;\n" +
"import base.Person;\n" +
"import dataaccess.CustomerDaoImp;\n" +
"import dataaccess.model.DBDao;\n" +
"import java.util.Scanner;\n" +
"import menu.Menu;\n" +
"\n" +
"/**\n" +
" *\n" +
" * @author karn\n" +
" */\n" +
"public class "+ sub.getId() +"Menu extends Menu {\n" +
"\n" +
"    private static MainBank main = loadData();\n" +
"    private static CounterService cs;\n" +
"    \n" +
"    public static void main(String[] args) {\n" +
"        String menu = \"============Menu============\\n\"\n" +
"                + \"1. Deposit\\n\"\n" +
"                + \"2. Withdraw\\n\"\n" +
"                + \"3. Transfer\\n\"\n" +
"                + \"4. Create Account\\n\"\n" +
"                + \"5. Delete Account\\n\"\n" +
"                + \"6. Register\\n\"\n" +
"                + \"7. Check Account\\n\"\n" +
"                + \"0. Exit\\n\"\n" +
"                + \"============================\\n\"\n" +
"                + \"----Select: \";\n" +
"        Scanner sc = new Scanner(System.in);\n" +
"        cs = main.getSubBanks().get(main.searchSubBankById(\""+sub.getId()+"\")).getCounterService();\n" +
"        cs.getSubBank().setMainBank(main);" +
"        \n" +
"        int input;\n" +
"        do {\n" +
"            System.out.print(menu);\n" +
"            input = sc.nextInt();\n" +
"            switch (input) {\n" +
"                case 1:\n" +
"                    deposit();\n" +
"                    break;\n" +
"                case 2: \n" +
"                    withdraw();\n" +
"                    break;\n" +
"                case 3:\n" +
"                    transfer();\n" +
"                    break;\n" +
"                case 4:\n" +
"                    createAccount();\n" +
"                    break;\n" +
"                case 5:\n" +
"                    deleteAccount();\n" +
"                    break;\n" +
"                case 6:\n" +
"                    register();\n" +
"                    break;\n" +
"                case 7:\n" +
"                    check();\n" +
"                    break;\n" +
"                case 0:\n" +
"                    System.out.println(\"finished\");\n" +
"                    break;\n" +
"                default:\n" +
"                    System.out.println(input + \" is not menu\");\n" +
"                    pressEnter();\n" +
"                    break;\n" +
"            }\n" +
"        } while (input != 0);\n" +
"\n" +
"    }\n" +
"    \n" +
"    public static void deposit(){\n" +
"        Scanner sc = new Scanner(System.in);\n" +
"        String id;\n" +
"        int check;\n" +
"        do {            \n" +
"            System.out.print(\"insert your Account ID (insert \\\"0\\\" to cancel): \");\n" +
"            id = sc.next();\n" +
"            check = main.searchAccountById(\"acc\" + id);\n" +
"            if (id.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if(check == -1 || id.length() != 3){\n" +
"                System.out.println(\"Account Not Found\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (check == -1 || id.length() != 3);\n" +
"        int money;\n" +
"        do {            \n" +
"            System.out.print(\"insert amount of MONEY : \");\n" +
"            money = sc.nextInt();\n" +
"            if (money < 0) {\n" +
"                System.out.println(\"please insert unsigned value\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (money < 0);\n" +
"        \n" +
"        cs.deposit(money, \"acc\" + id);\n" +
"    }\n" +
"    \n" +
"    public static void withdraw(){\n" +
"        Scanner sc = new Scanner(System.in);\n" +
"        String id;\n" +
"        int check;\n" +
"        do {            \n" +
"            System.out.print(\"insert your Account ID (insert \\\"0\\\" to cancel): \");\n" +
"            id = sc.next();\n" +
"            check = main.searchAccountById(\"acc\" + id);\n" +
"            if (id.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if(check == -1 || id.length() != 3){\n" +
"                System.out.println(\"Account Not Found\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (check == -1 || id.length() != 3);\n" +
"        int money;\n" +
"        do {            \n" +
"            System.out.print(\"insert amount of MONEY : \");\n" +
"            money = sc.nextInt();\n" +
"            if (money < 0) {\n" +
"                System.out.println(\"please insert unsigned value\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (money < 0);\n" +
"        \n" +
"        cs.withdraw(money, \"acc\" + id);\n" +
"    }\n" +
"    \n" +
"    public static void transfer(){\n" +
"        Scanner sc = new Scanner(System.in);\n" +
"        String id1;\n" +
"        int checkId1;\n" +
"        do {            \n" +
"            System.out.print(\"insert your Account ID (insert \\\"0\\\" to cancel): \");\n" +
"            id1 = sc.next();\n" +
"            checkId1 = main.searchAccountById(\"acc\" + id1);\n" +
"            if (id1.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if(checkId1 == -1 || id1.length() != 3){\n" +
"                System.out.println(\"Account Not Found\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (checkId1 == -1 || id1.length() != 3);\n" +
"        \n" +
"        String id2;\n" +
"        int check;\n" +
"        do {            \n" +
"            System.out.print(\"insert destination Account ID (insert \\\"0\\\" to cancel): \");\n" +
"            id2 = sc.next();\n" +
"            check = main.searchAccountById(\"acc\" + id2);\n" +
"            if (id2.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if(check == -1 || id2.length() != 3){\n" +
"                System.out.println(\"Account Not Found\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (check == -1 || id2.length() != 3);\n" +
"        \n" +
"        int money;\n" +
"        do {            \n" +
"            System.out.print(\"insert amount of MONEY : \");\n" +
"            money = sc.nextInt();\n" +
"            if (money < 0) {\n" +
"                System.out.println(\"please insert unsigned value\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (money < 0);\n" +
"        \n" +
"        if (cs.transfer(money, \"acc\" + id1, \"acc\" + id2)) {\n" +
"            System.out.println(\"transfer complete\");\n" +
"        } else{\n" +
"            System.out.println(\"not enough money\");\n" +
"        }\n" +
"        pressEnter();\n" +
"    }\n" +
"\n" +
"    public static void createAccount(){\n" +
"        Scanner sc = new Scanner(System.in);\n" +
"        String cusId;\n" +
"        int cusCheck;\n" +
"        do {            \n" +
"            System.out.print(\"insert your Customer ID (insert \\\"0\\\" to cancel): \");\n" +
"            cusId = sc.next();\n" +
"            cusCheck = main.searchCustomerById(\"cus\" + cusId);\n" +
"            if (cusId.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if(cusCheck == -1){\n" +
"                System.out.println(\"ID Not Found\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (cusId.length() != 3 || cusCheck == -1);\n" +
"        \n" +
"        System.out.println(main.getCustomers().get(cusCheck));\n" +
"        \n" +
"        String accId;\n" +
"        int check = 0;\n" +
"        do {            \n" +
"            System.out.print(\"insert your Account ID (insert \\\"0\\\" to cancel): \");\n" +
"            accId = sc.next();\n" +
"            check = main.searchAccountById(\"acc\" + accId);\n" +
"            if (accId.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if(check >= 0){\n" +
"                System.out.println(\"ID is already exist\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (accId.length() != 3 || check >= 0);\n" +
"        cs.newAccount(\"acc\" + accId, main.getCustomers().get(cusCheck));\n" +
"    }\n" +
"    \n";
    }
    
    private String getString2(){
        return "    public static void deleteAccount(){\n" +
"        Scanner sc = new Scanner(System.in);\n" +
"        int cusCheck;\n" +
"        String cusId;\n" +
"        \n" +
"        do {            \n" +
"            System.out.print(\"insert your Customer ID (insert \\\"0\\\" to cancel): \");\n" +
"            cusId = sc.next();\n" +
"            cusCheck = main.searchCustomerById(\"cus\" + cusId);\n" +
"            if (cusId.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if(cusCheck == -1){\n" +
"                System.out.println(\"ID Not Found\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (cusId.length() != 3 || cusCheck == -1);\n" +
"        \n" +
"        String accId;\n" +
"        int check = 0;\n" +
"        do {            \n" +
"            System.out.print(\"insert your Account ID (insert \\\"0\\\" to cancel): \");\n" +
"            accId = sc.next();\n" +
"            check = main.searchAccountById(\"acc\" + accId);\n" +
"            if (accId.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if(check == -1){\n" +
"                System.out.println(\"Account is not exist\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (accId.length() != 3 || check == -1);\n" +
"        \n" +
"        cs.deleteAccount(main.getBankAccounts().get(check));\n" +
"    }\n" +
"    \n" +
"    public static void register() {\n" +
"        Scanner sc = new Scanner(System.in);\n" +
"\n" +
"        String id;\n" +
"        int check;\n" +
"        do {\n" +
"            System.out.print(\"insert 3 digit number for id (insert \\\"0\\\" to cancel): \");\n" +
"            id = sc.next();\n" +
"            check = main.searchCustomerById(\"cus\" + id);\n" +
"            if (id.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if (check >= 0) {\n" +
"                System.out.println(\"id is already exist\");\n" +
"                pressEnter();\n" +
"            } else if (id.length() != 3) {\n" +
"                System.out.println(\"id was \" + id.length() + \" number\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (id.length() != 3 || check >= 0);\n" +
"\n" +
"        System.out.print(\"insert name (insert \\\"0\\\" to cancel):\");\n" +
"        String name = sc.next();\n" +
"        if (name.equals(\"0\")) {\n" +
"            return;\n" +
"        }\n" +
"\n" +
"        System.out.print(\"insert surname (insert \\\"0\\\" to cancel):\");\n" +
"        String surname = sc.next();\n" +
"        if (surname.equals(\"0\")) {\n" +
"            return;\n" +
"        }\n" +
"\n" +
"        String tele;\n" +
"        do {\n" +
"            System.out.print(\"insert telephone number (insert \\\"0\\\" to cancel):\");\n" +
"            tele = sc.next();\n" +
"            if (tele.equals(\"0\")) {\n" +
"                pressEnter();\n" +
"            } else if (tele.length() != 10) {\n" +
"                System.out.println(\"telephone number must be 10 digits number:\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (tele.length() != 10);\n" +
"\n" +
"        System.out.print(\"insert email (insert \\\"0\\\" to cancel):\");\n" +
"        String email = sc.next();\n" +
"        if (surname.equals(\"0\")) {\n" +
"            return;\n" +
"        }\n" +
"\n" +
"        System.out.print(\"insert address (insert \\\"0\\\" to cancel):\");\n" +
"        String address = sc.next();\n" +
"        if (surname.equals(\"0\")) {\n" +
"            return;\n" +
"        }\n" +
"        cs.newCustomer(\"cus\" + id , name, surname, tele, email, address);\n" +
"    }\n" +
"    \n" +
"    public static void check(){\n" +
"        Scanner sc = new Scanner(System.in);\n" +
"        String accId;\n" +
"        int check = 0;\n" +
"        do {            \n" +
"            System.out.print(\"insert your Account ID (insert \\\"0\\\" to cancel): \");\n" +
"            accId = sc.next();\n" +
"            check = main.searchAccountById(\"acc\" + accId);\n" +
"            if (accId.equals(\"0\")) {\n" +
"                return;\n" +
"            } else if(check == -1){\n" +
"                System.out.println(\"Account is not exist\");\n" +
"                pressEnter();\n" +
"            }\n" +
"        } while (accId.length() != 3 || check == -1);\n" +
"        \n" +
"        System.out.println(main.getBankAccounts().get(check));\n" +
"        pressEnter();\n" +
"    }\n" +
"}\n" +
"\n";
    }
    
}
