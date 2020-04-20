/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

/**
 *
 * @author 62130500127
 */
public class Employee extends Person{
    private String employeeId;

    public Employee(String employeeId, Person person) {
        super(person.getFirstName(), person.getLastName(), person.getTelephone(), person.getEmail(), person.getAddress());
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + '}';
    }
    
    
}
