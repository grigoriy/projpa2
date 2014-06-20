package com.grigoriyalexeev.projpa2;

import javax.persistence.*;


@Entity
public class Employee {

    @Id
    private int id;
    private String name;
    private long salary;
    @ManyToOne
    @JoinColumn
    private Company company;

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object other) {
        
        if (other == null) {
            return false;
        }
        if (! (other instanceof Employee)) {
            return false;
        }
        Employee otherEmployee = (Employee) other;
        if (otherEmployee.getId() != id) {
            return false;
        }
        if (otherEmployee.getName() != name) {
            return false;
        }
        if (otherEmployee.getSalary() != salary) {
            return false;
        }
        if (otherEmployee.getCompany() != company) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

        return 31 * (int)(id + name.hashCode() + salary + company.hashCode());
    }

    @Override
    public String toString() {

        return "Employee: id=" + id +
            ", name=" + name +
            ", salary=" + salary +
            ", company=" + company.getName();
    }
}
