package com.grigoriyalexeev.projpa2;

import javax.persistence.*;
import java.util.*;


public class CompanyService {

    protected EntityManager manager;

    public CompanyService(EntityManager manager) {
        this.manager = manager;
    }

    public Company createCompany(String name) {

        Company company = new Company(name);
        manager.persist(company);

        return company;
    }

    public void removeCompany(long id) {

        Company company = findCompany(id);
        if (company != null) {
            manager.remove(company);
        }
    }

    public Company findCompany(long id) {
        return manager.find(Company.class, id);
    }

    public List<Company> findAllCompanies() {

        TypedQuery<Company> query = manager.createQuery(
                "SELECT e FROM Company e", Company.class);

        return query.getResultList();
    }

    public void addEmployee(long companyId, Employee employee) {

        Company company = manager.find(Company.class, companyId);
        company.addEmployee(employee);
    }
}
