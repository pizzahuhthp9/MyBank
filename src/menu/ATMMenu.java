/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import Service.ATM;
import java.util.Scanner;

/**
 *
 * @author karn
 */
public class ATMMenu {

    private static String menu = "Select Menu\n"
            + "1. Deposit Money\n"
            + "2. Withdraw Money\n"
            + "0. Exit\n"
            + "----Select: ";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("insert card id : ");
        String cardId = input.next();
        
        System.out.print(menu);

//        switch () {
//            case 1:
//                break;
//            case 2:
//                
//                break;
//            default:
//           
//        }
    }
}
