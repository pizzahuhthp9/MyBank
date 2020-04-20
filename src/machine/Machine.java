/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine;

import bank.SubBank;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author 62130500127
 */
public abstract class Machine {
    private String machineId;
    private SubBank subBank;
    private int money;
    private String location;
    private MachineStatus status;

    public Machine(String machineId, SubBank subBank, int money, String location) {
        this.machineId = machineId;
        this.subBank = subBank;
        this.money = money;
        this.location = location;
        if (money > 1000) {
            status = MachineStatus.AVAILABLE;
        } else{
            status = MachineStatus.MAINTENANCE;
        }
    }
    
    public String getMachineId(){
        return machineId;
    }

    public void receiveMoney(int money){
        status = MachineStatus.AVAILABLE;
        this.money+=money;
    } 
    
    public void decreaseMoney(int money){
        this.money -= money;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Machine other = (Machine) obj;
        if (!Objects.equals(this.machineId, other.machineId)) {
            return false;
        }
        return true;
    }
    
    public int getMoney(){
        return money;
    }

    public SubBank getSubBank() {
        return subBank;
    }
    
    public void returnMoneyToSubBank(){
        subBank.increaseVault(money);
        money = 0;
        status = MachineStatus.CLOSE;
    }
    
    protected abstract void displayMenu();

    @Override
    public String toString() {
        return "Machine{" + "machineId=" + machineId + ", subBank=" + subBank + ", money=" + money + ", status=" + status + '}';
    }
    
}
