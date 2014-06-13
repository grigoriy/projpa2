package com.grigoriyalexeev.projpa2;

import javax.persistence.*;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pUnit");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        System.out.println("Started transaction.");
        Employee employee = new Employee();
        employee.setName("TestName");
        employee.setSalary(100);
        try {
            manager.persist(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        transaction.commit();

        System.out.println("Finished transaction.");
        factory.close();
    }
}
