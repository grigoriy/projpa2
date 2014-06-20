package com.grigoriyalexeev.projpa2;

import javax.persistence.*;
import java.util.*;


@Entity
public class Company {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id;
    private String name;
    @OneToMany(mappedBy="company", fetch=FetchType.EAGER)
    private Collection<Employee> employees;


    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }


    public void addEmployee(Employee employee) {

        if (employees == null) {
            employees = new ArrayList<Employee>();
        }
        employees.add(employee);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }
        if (! (other instanceof Company)) {
            return false;
        }
        Company otherCompany = (Company) other;
        if (otherCompany.getId() != id) {
            return false;
        }
        if (otherCompany.getName() != name) {
            return false;
        }
        if (! otherCompany.getEmployees().containsAll(employees) &&
                employees.containsAll(otherCompany.getEmployees())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31 * (int)(id + name.hashCode());
    }

    @Override
    public String toString() {
        return "Company name: " + name + "\n" + "Employees: " + employees + "\n";
    }
}
