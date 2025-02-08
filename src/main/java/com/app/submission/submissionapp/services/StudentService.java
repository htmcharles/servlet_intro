package com.app.submission.submissionapp.services;

import com.app.submission.submissionapp.models.Student;
import com.app.submission.submissionapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentService {
    // Hibernate SessionFactory
    protected static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected static Session hibernateSession;

    // Singleton pattern
    private static StudentService instance;

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void saveStudent(Student student) {
        hibernateSession = sessionFactory.openSession();
        hibernateSession.beginTransaction();
        hibernateSession.persist(student);
        hibernateSession.getTransaction().commit();
    }

    public List<Student> fetchAllStudents() {
        hibernateSession = sessionFactory.openSession();
        List<Student> studentList = hibernateSession.createQuery("from Student").list();
        hibernateSession.close();
        return studentList;
    }
}
