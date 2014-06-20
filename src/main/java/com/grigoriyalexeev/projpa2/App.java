package com.grigoriyalexeev.projpa2;

import javax.persistence.*;
import java.util.List;


public class App {

    public static void main( String[] args ) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pUnit");
        EntityManager manager = factory.createEntityManager();
        EmployeeService empService = new EmployeeService(manager);
        CompanyService compService = new CompanyService(manager);

        String COMPANYNAME = "My Company";
        int ID = 158;
        String NAME = "John Doe";
        long SALARY = 45000;
        long RAISE = 1000;

        // create and persist a company
        manager.getTransaction().begin();
        Company company = compService.createCompany(COMPANYNAME);
        manager.getTransaction().commit();
        System.out.println("Persisted " + company);

        // create and persist an employee
        manager.getTransaction().begin();
        Employee employee = empService.createEmployee(ID, NAME, SALARY, company);
        manager.getTransaction().commit();
        System.out.println("Persisted " + employee);

        // add employee to company
        manager.getTransaction().begin();
        compService.addEmployee(company.getId(), employee);
        manager.getTransaction().commit();
        System.out.println("Added employee to company: " + company);

        // find specific employee
        manager.getTransaction().begin();
        employee = empService.findEmployee(ID);
        manager.getTransaction().commit();
        System.out.println("Found " + employee);

        // find all employees
        manager.getTransaction().begin();
        List<Employee> employees = empService.findAllEmployees();
        manager.getTransaction().commit();
        for (Employee emp : employees) {
            System.out.println("Found employee: " + emp);
        }

        // update the employee
        manager.getTransaction().begin();
        employee = empService.raiseEmployeeSalary(ID, RAISE);
        manager.getTransaction().commit();
        System.out.println("Updated " + employee);

        // remove an employee
        manager.getTransaction().begin();
        empService.removeEmployee(ID);
        manager.getTransaction().commit();
        System.out.println("Removed employee " + ID);

        manager.close();
        factory.close();
    }
}
