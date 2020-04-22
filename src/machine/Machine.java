/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine;

import bank.MainBank;
import bank.SubBank;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author 62130500127
 */
public abstract class Machine {
    private String machineId;
    private int money;
    private MachineStatus status;
    private MainBank mainBank;

    public Machine(String machineId, int money, MainBank main) {
        this.machineId = machineId;
        this.money = money;
        this.mainBank = main;
        if (money > 1000) {
            status = MachineStatus.AVAILABLE;
        } else{
            status = MachineStatus.MAINTENANCE;
        }
    }
    
    public String getMachineId(){
        return machineId;
    }

    public MainBank getMainBank() {
        return mainBank;
    }

    public void receiveMoney(int money){
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
    
    protected abstract void displayMenu();

    @Override
    public String toString() {
        return "Machine{" + "machineId=" + machineId + ", money=" + money + ", status=" + status + '}';
    }
    
}
