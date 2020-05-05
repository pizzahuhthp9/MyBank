/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.owner;

import bank.MainBank;
import dataaccess.DBConnection;
import dataaccess.MainBankDaoImp;
import dataaccess.model.MainBankDBDao;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author 62130500127
 */
public class SetUp {

    public static void main(String[] args) throws SQLException {
        System.out.print("this setup will delete all the data do you confirm? Y/N : ");
        Scanner sc = new Scanner(System.in);
        String drop1 = "DROP TABLE customers";
        String drop2 = "DROP TABLE employees";
        String drop3 = "DROP TABLE main_bank";
        String drop4 = "DROP TABLE sub_bank";
        String drop5 = "DROP TABLE accounts";
        String create1 = "CREATE TABLE customers(\n"
                + "    id CHAR(6) PRIMARY KEY,\n"
                + "    fname VARCHAR(20) NOT NULL,\n"
                + "    lanme VARCHAR(20) NOT NULL,\n"
                + "    tele CHAR(10),\n"
                + "    email VARCHAR(50) NOT NULL,\n"
                + "    adds VARCHAR(100) NOT NULL\n"
                + ")";
        String create2 = "CREATE TABLE employees(\n"
                + "    id CHAR(6) PRIMARY KEY,\n"
                + "    fname VARCHAR(20) NOT NULL,\n"
                + "    lname VARCHAR(20) NOT NULL,\n"
                + "    tele CHAR(10) ,\n"
                + "    email VARCHAR(50) NOT NULL,\n"
                + "    adds VARCHAR(100) NOT NULL,\n"
                + "    avlble BOOLEAN NOT NULL\n"
                + ")";
        String create3 = "CREATE TABLE main_bank(\n"
                + "    vault INT,\n"
                + "    ttl_money INT\n"
                + ")";
        String create4 = "CREATE TABLE sub_bank(\n"
                + "    id CHAR(6) PRIMARY KEY,\n"
                + "    vault INT,\n"
                + "    emp_id CHAR(6)\n"
                + ")";
        String create5 = "CREATE TABLE accounts(\n"
                + "    id CHAR(6) PRIMARY KEY,\n"
                + "    cus_id CHAR(6) NOT NULL,\n"
                + "    money INT\n"
                + ")";
        String insert = "INSERT INTO main_bank VALUES(0,0)";
        String input = sc.next();
        if (input.equalsIgnoreCase("y")) {
            System.out.print("insert a amount of bank money : ");
            int money = sc.nextInt();
            if (money >= 10000) {
                MainBank m = new MainBank(money, money);
                MainBankDBDao mainDao = new MainBankDaoImp();
                try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
                    try {
                        stm.execute(drop1);
                        stm.execute(drop2);
                        stm.execute(drop3);
                        stm.execute(drop4);
                        stm.execute(drop5);
                    } catch (SQLSyntaxErrorException ex) {
                    }
                    stm.execute(create1);
                    stm.execute(create2);
                    stm.execute(create3);
                    stm.execute(create4);
                    stm.execute(create5);
                    stm.execute(insert);
                    mainDao.update(m);

                    File dir = new File("src//menu//sub");
                    File[] files = dir.listFiles();
                    for (File file : files) {
                        if (file.getName().equals("BankMenuTemplate.java")) {
                            continue;
                        }
                        file.delete();
                    }

                }
            } else if (money >= 0) {
                System.out.println("low amount of money");
            } else {
                System.out.println("amount can not be minus");
            }
        }
        System.out.println("Setup finished");
    }

}
