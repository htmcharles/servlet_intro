package com.app.submission.submissionapp.util;


import com.app.submission.submissionapp.models.Student;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            //POSTGRESQL

            //settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            //Postgresql
            //  settings.put(Environment.URL, "jdbc:postgresql://localhost:3306/myapp");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/rcamis_db");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "");
            //PostgreSQL
            //settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            settings.put(Environment.SHOW_SQL, true);
            settings.put(Environment.HBM2DDL_AUTO, "create-drop");
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(Student.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}