/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import dataaccess.EmployeeDaoImp;
import dataaccess.model.DBDao;

/**
 *
 * @author 62130500127
 */
public class Employee extends Person{
    private String employeeId;
    private boolean free;
    
    DBDao empDao = new EmployeeDaoImp();

    public Employee(String employeeId, Person person, boolean status) {
        super(person.getFirstName(), person.getLastName(), person.getTelephone(), person.getEmail(), person.getAddress());
        this.employeeId = employeeId;
        this.free = status;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setFree(boolean free) { // update emp
        this.free = free;
        empDao.update(this);
    }

    public boolean isFree() {
        return free;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ID : " + employeeId + ", Name :" + super.getFirstName() + "\tStatus : ");
        if (free) {
            s.append("free");
        } else{
            s.append("working");
        }
        return s.toString();
    }

    
    
    
}
