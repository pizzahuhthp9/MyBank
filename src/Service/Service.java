/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author 62130500127
 */
public interface Service {
    public void withdraw(int money, String id);
    public void transfer(int money, String id1, String id2);
}
