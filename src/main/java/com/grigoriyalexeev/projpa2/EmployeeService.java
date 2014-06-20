package com.grigoriyalexeev.projpa2;

import javax.persistence.*;
import java.util.List;


public class EmployeeService {
    
    protected EntityManager manager;

    public EmployeeService(EntityManager manager) {
        this.manager = manager;
    }

    public Employee createEmployee(int id, String name, long salary, Company company) {

        Employee employee = new Employee(id);
        employee.setName(name);
        employee.setSalary(salary);
        employee.setCompany(company);
        manager.persist(employee);

        return employee;
    }

    public void removeEmployee(int id) {

        Employee employee = findEmployee(id);
        if (employee != null) {
            manager.remove(employee);
        }
    }

    public Employee raiseEmployeeSalary(int id, long raise) {

        Employee employee = findEmployee(id);
        if (employee != null) {
            employee.setSalary(employee.getSalary() + raise);
        }

        return employee;
    }

    public Employee findEmployee(int id) {
        return manager.find(Employee.class, id);
    }

    public List<Employee> findAllEmployees() {

        TypedQuery<Employee> query = manager.createQuery(
                "SELECT e FROM Employee e", Employee.class);

        return query.getResultList();
    }
}
