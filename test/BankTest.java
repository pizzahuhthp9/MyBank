

import Service.*;
import bank.*;
import base.*;
import dataaccess.*;
import dataaccess.model.*;
import java.util.ArrayList;

public class BankTest {
    public static void main(String[] args) {
        
        ArrayList<SubBank> list = new ArrayList<SubBank>();
        
        MainBank m1 = new MainBank(1000000,1000000);
        
        Person p1 = new Person("Noppawit", "Chowarun", "0942890990", "npwchr.work@gmail.com", "39/325");
        Person p2 = new Person("John", "Warlock", "0817529581", "John@mail.com", "15/931");
        Person p3 = new Person("Kelvin", "klei", "0720180028", "kelvin@mail.com", "58/924");
        Person p4 = new Person("Sarah", "Matar", "0841930755", "sarah@mail.com", "91/128");
        Person p5 = new Person("Steve", "Craft", "0921588490", "steve@mail.com", "92/653");

        Customer c1 = new Customer("cus001", p2);
        Customer c2 = new Customer("cus002", p1);
        Customer c3 = new Customer("cus003", p5);
        
        Employee e1 = new Employee("emp001", p1, true);
        Employee e2 = new Employee("emp002", p3, true);
        Employee e3 = new Employee("emp003", p4, true);

//        CounterService cs1 = new CounterService(e1);
//        CounterService cs2 = new CounterService(e2);
//        CounterService cs3 = new CounterService(e3);
//        
//        SubBank sb1 = new SubBank("sub001", cs1, m1);
//        SubBank sb2 = new SubBank("sub002", cs2, m1);
//        SubBank sb3 = new SubBank("sub003", cs3, m1);
//        
//        BankAccount ba1 = cs1.newAccount("acc001", c1);
//        CreditCard cc1 = new CreditCard(ba1, "0000000000000001");
//        
//        BankAccount ba2 = cs1.newAccount("acc002", c2);
//        CreditCard cc2 = new CreditCard(ba2, "0000000000000002");
//        
//        BankAccount ba3 = cs1.newAccount("acc003", c3);
//        CreditCard cc3 = new CreditCard(ba3, "0000000000000003");
        
//        list.add(sb1);
//        list.add(sb2);
//        list.add(sb3);
        
        DBDao<SubBank> subDao = new SubBankDaoImp();        
        EmployeeDaoImp empDao = new EmployeeDaoImp();
        DBDao<Customer> cusDao = new CustomerDaoImp();
        DBDao<BankAccount> accDao = new BankAccountDaoImp();
        
        empDao.insert(e1);
        empDao.insert(e2);
        empDao.insert(e3);
        
        empDao.update(e1);
        empDao.update(e2);
        empDao.update(e3);
        System.out.println(empDao.getFreeEmp());
    }
}
