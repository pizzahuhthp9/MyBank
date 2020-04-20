package test;

import Service.*;
import bank.BankAccount;
import bank.MainBank;
import bank.SubBank;
import base.Customer;
import base.Employee;
import base.Person;

public class BankTest {
    public static void main(String[] args) {
        MainBank m1 = new MainBank(1000000);
        Person p1 = new Person("Noppawit", "Chowarun", "0942890990", "npwchr.work@gmail.com", "39/325");
        Person p2 = new Person("John", "Warlock", "0817529581", "John@mail.com", "15/931");
        Person p3 = new Person("Kelvin", "klei", "0720180028", "kelvin@mail.com", "58/924");
        Person p4 = new Person("Sarah", "Matar", "0841930755", "sarah@mail.com", "91/128");
        Person p5 = new Person("Steve", "Craft", "0921588490", "steve@mail.com", "92/653");

        Customer c1 = new Customer("c01", p2);
        Customer c2 = new Customer("c02", p1);
        Customer c3 = new Customer("c03", p5);
        
        Employee e1 = new Employee("e01", p1);
        Employee e2 = new Employee("e02", p3);
        Employee e3 = new Employee("e03", p4);
        
        CounterService cs1 = new CounterService(e1);
        
        SubBank sb1 = new SubBank("53/968", cs1, m1);
        
        m1.addSubBank(sb1);
        
        BankAccount ba1 = cs1.newAccount("ac01", c1);
        cs1.deposite(500, "ac01");
        
        
    }
}
