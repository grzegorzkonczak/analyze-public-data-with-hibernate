package com.checkrise.countrymgr.controller;

import com.checkrise.countrymgr.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CountryHibernateDAO implements CountryDAO {

    // Hold a reusable reference to a SessionFactory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // Create StandardServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }


    // Returns list of all countries
    @Override
    public List<Country> fetchAllCountries() {
        // Open session
        Session session = sessionFactory.openSession();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Country> criteriaQuery = builder.createQuery(Country.class);

        // Specify criteria root
        criteriaQuery.from(Country.class);

        // Execute query
        List<Country> countries = session.createQuery(criteriaQuery).getResultList();

        // Close the session
        session.close();

        return countries;
    }

    @Override
    public void update(Country country) {
        // Open session
        Session session = sessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // Use session to update contact
        session.update(country);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    @Override
    public Country findByCode(String code) {
        // Open session
        Session session = sessionFactory.openSession();

        // Retrieve the persistent object (or null if not found)
        Country country = session.get(Country.class, code);

        // Close the session
        session.close();

        // Return the contact object
        return country;
    }
}
