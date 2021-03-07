package ru.geekbrains.krylov.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryClass {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure("conf/hibernate.cfg.xml").buildSessionFactory();
            } catch (Throwable e) {
                sessionFactory = new Configuration().configure("conf/hibernate.mock.cfg.xml").buildSessionFactory();
            }
        }
        return sessionFactory;
    }
}
