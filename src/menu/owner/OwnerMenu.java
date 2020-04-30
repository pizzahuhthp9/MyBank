/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.owner;

import bank.CounterService;
import bank.BankAccount;
import bank.MainBank;
import bank.SubBank;
import base.Employee;
import base.Person;
import dataaccess.BankAccountDaoImp;
import dataaccess.EmployeeDaoImp;
import dataaccess.MainBankDaoImp;
import dataaccess.SubBankDaoImp;
import dataaccess.model.DBDao;
import dataaccess.model.MainBankDBDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.Menu;
import menu.Menu;

/**
 *
 * @author karn
 */
public class OwnerMenu extends Menu{
    
    static MainBank main;
    static String menu = "============Menu============\n"
                + "1.  Create SubBank\n"
                + "2.  Delete SubBank\n"
                + "3.  Add Employee\n"
                + "4.  Remove Employee\n"
                + "5.  transfer money\n"
                + "6.  List Employee\n"
                + "7.  List Sub Bank\n"
                + "8.  List Customer\n"
                + "9.  List Account\n"
                + "10. Check Money\n"
                + "0. Exit\n"
                + "----Select: ";
    
    public static void main(String[] args) {
        main = loadData();
        Scanner sc = new Scanner(System.in);
        int input;
        do {
            System.out.print(menu);
            input = sc.nextInt();
            switch (input) {
                case 1:
                    createSubBank();
                    break;
                case 2:
                    deleteSubBank();
                    break;
                case 3:
                    newEmployee();
                    break;
                case 4:
                    removeEmployee();
                    break;
                case 5:
                    transferMoney();
                    break;
                case 6:
                    listEmp();
                    break;
                case 7:
                    listSub();
                    break;
                case 8:
                    listCus();
                    break;
                case 9 :
                    listAccount();
                    break;
                case 10:
                    check();
                    break;
                case 0:
                    System.out.println("finished");
                    break;
                default:
                    System.out.println(input + " is not in menu");
                    pressEnter();
                    break;
            }
        } while (input != 0);
    }

    public static void createSubBank() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> empList = main.getFreeEmployees();
        if (!empList.isEmpty()) {
            int empIndex;
            do {
                System.out.println("============Employee(s) List============");
                main.listFreeEmployee();
                System.out.print("Select Employee (insert \"0\" to cancel): ");
                empIndex = sc.nextInt();
                if (empIndex > empList.size()) {
                    System.out.println(empIndex + " is not in a list");
                    pressEnter();
                } else if (empIndex < 0) {
                    System.out.println("insert unsigned value");
                    pressEnter();
                }
            } while (empIndex > empList.size() || empIndex < 0);

            if (empIndex == 0) {
                return;
            }
            String id;
            int check;
            do {
                System.out.print("insert 3 digit number for id (insert \"0\" to cancel): ");
                id = sc.next();
                check = main.searchSubBankById("sub" + id);
                if (id.equals("0")) {
                    return;
                } else if (check >= 0) {
                    System.out.println("id is already exist");
                    pressEnter();
                } else if (id.length() != 3) {
                    System.out.println("id was " + id.length() + " number");
                    pressEnter();
                }
            } while (id.length() != 3 || id.equals("0") || check >= 0);

            main.newSubBank(new SubBank("sub" + id, new CounterService(empList.get(empIndex - 1)), main, 0));
            System.out.println("add finished");
        } else {
            System.out.println("there's no employees available");
            pressEnter();
        }

    }

    public static void deleteSubBank() {
        Scanner sc = new Scanner(System.in);
        if (main.getSubBanks().size() > 0) {
            System.out.println("============Sub Bank(s) List============");
            main.listSubBank(1);
            int index;
            do {
                System.out.print("select Sub Bank to delete (insert \"0\" to cancel): ");
                index = sc.nextInt();
                if (index == 0) {
                    pressEnter();
                    return;
                } else if (index > main.getSubBanks().size()) {
                    System.out.println(index + " not in a list");
                } else if (index < 0) {
                    System.out.println("insert unsigned value");
                }
            } while (index > main.getSubBanks().size() || index < 0);
            main.deleteSubBank(main.getSubBanks().get(index - 1));
            System.out.println("Deleted");
            pressEnter();
        } else {
            System.out.println("no Sub Bank");
            pressEnter();
        }
    }

    public static void newEmployee() {
        Scanner sc = new Scanner(System.in);
        String id;
        int check;
        do {
            System.out.print("insert 3 digits number for id (insert \"0\" to cancel):");
            id = sc.next();
            check = main.searchEmployeeById("emp" + id);
            if (id.equals("0")) {
                return;
            } else if (check >= 0) {
                System.out.println("id is already exist");
                pressEnter();
            } else if (id.length() != 3) {
                System.out.println("id was " + id.length() + " number");
                pressEnter();
            }
        } while (id.length() != 3 || check >= 0 || id.equals("0"));
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

        main.newEmployee(new Employee("emp" + id, new Person(name, surname, tele, email, address), true));
    }

    public static void removeEmployee() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> list = main.getFreeEmployees();
        if (list.size() > 0) {
            System.out.println("============Employee(s) List============");
            main.listFreeEmployee();
            int index;
            do {
                System.out.print("select Employee to delete (insert \"0\" to cancel): ");
                index = sc.nextInt();
                if (index == 0) {
                    pressEnter();
                    return;
                } else if (index > main.getFreeEmployees().size()) {
                    System.out.println(index + " is not in a list");
                } else if (index < 0) {
                    System.out.println("insert unsigned value");
                }
            } while (index > main.getFreeEmployees().size() || index < 0);

            main.deleteEmployee(list.get(index - 1));
            System.out.println("Deleted");
            pressEnter();
        } else {
            System.out.println("no employee");
            pressEnter();
        }
    }

    public static void transferMoney() {
        Scanner sc = new Scanner(System.in);
        if (main.getSubBanks().size() > 0) {
            System.out.println("============bank list============");
            System.out.println("1.)main Bank vault = " + main.getVault());
            main.listSubBank(2);
            int from;
            do {
                System.out.print("transfer from (insert \"0\" to cancel):");
                from = sc.nextInt();
                if (from == 0) {
                    pressEnter();
                    return;
                } else if (from > main.getSubBanks().size() + 1) {
                    System.out.println(from + " not in a list");
                    pressEnter();
                } else if (from < 0) {
                    System.out.println("insert unsigned value");
                    pressEnter();
                }
            } while (from > main.getSubBanks().size() + 1 || from < 0);

            int to;
            do {
                System.out.print("transfer to   (insert \"0\" to cancel):");
                to = sc.nextInt();
                if (to == 0) {
                    pressEnter();
                    return;
                } else if (to > main.getSubBanks().size() + 1) {
                    System.out.println(to + " not in a list");
                    pressEnter();
                } else if (to < 0) {
                    System.out.println("insert unsigned value");
                    pressEnter();
                }
            } while (to > main.getSubBanks().size() + 1 || to < 0);

            int money;
            do {
                System.out.print("insert amount of money (insert \"0\" to cancel):");
                money = sc.nextInt();
                if (money < 0) {
                    System.out.println("insert unsigned values");
                    pressEnter();
                }
            } while (money < 0);

            if (from != to) {
                if (from == 1) {
                    main.giveSubBankMoney(money, main.getSubBanks().get(to - 2));
                } else if (to == 1) {
                    main.receiveMoneyFromSubBank(money, main.getSubBanks().get(from - 2));
                } else {
                    main.transferVault(money, main.getSubBanks().get(from - 2), main.getSubBanks().get(to - 2));
                }
            }
            System.out.println("transfer finished");
            pressEnter();
        } else{
            System.out.println("no Sub Bank");
            pressEnter();
        }
    }

    public static void listSub() {
        main.listSubBank(1);
        pressEnter();
    }

    public static void listEmp() {
        main.listEmployee();
        pressEnter();
    }
    
    public static void listCus(){
        main.listCustomer();
        pressEnter();
    }
    
    public static void listAccount(){
        main.listAccount();
        pressEnter();
    }

    public static void check() {
        System.out.println("Total Money : " + main.getTotalMoney() + " AND Vault = " + main.getVault());
        pressEnter();
    }
}
