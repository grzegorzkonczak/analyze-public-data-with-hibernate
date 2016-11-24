package com.checkrise.countrymgr;


import com.checkrise.countrymgr.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Application {
    // Hold a reusable reference to a SessionFactory
    private static final SessionFactory sessionFactory = buldSessionFactory();

    private static SessionFactory buldSessionFactory() {
        // Create StandardServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        // Display a list of countries
        fetchAllCountries().forEach(System.out::println);
    }

    private static List<Country> fetchAllCountries() {
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
}
