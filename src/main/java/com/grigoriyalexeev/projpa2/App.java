package com.grigoriyalexeev.projpa2;

import javax.persistence.*;
import java.util.List;


public class App {

    public static void main( String[] args ) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pUnit");
        EntityManager manager = factory.createEntityManager();
        EmployeeService service = new EmployeeService(manager);

        int ID = 158;
        String NAME = "John Doe";
        long SALARY = 45000;
        long RAISE = 1000;

        // create and persist an employee
        manager.getTransaction().begin();
        Employee employee = service.createEmployee(ID, NAME, SALARY);
        manager.getTransaction().commit();
        System.out.println("Persisted " + employee);

        // find specific employee
        manager.getTransaction().begin();
        employee = service.findEmployee(ID);
        manager.getTransaction().commit();
        System.out.println("Found " + employee);

        // find all employees
        manager.getTransaction().begin();
        List<Employee> employees = service.findAllEmployees();
        manager.getTransaction().commit();
        for (Employee emp : employees) {
            System.out.println("Found employee: " + emp);
        }

        // update the employee
        manager.getTransaction().begin();
        employee = service.raiseEmployeeSalary(ID, RAISE);
        manager.getTransaction().commit();
        System.out.println("Updated " + employee);

        // remove an employee
        manager.getTransaction().begin();
        service.removeEmployee(ID);
        manager.getTransaction().commit();
        System.out.println("Removed employee " + ID);

        manager.close();
        factory.close();
    }
}
