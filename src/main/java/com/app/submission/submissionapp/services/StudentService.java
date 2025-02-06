package com.app.submission.submissionapp.services;

import com.app.submission.submissionapp.mdels.Student;
import com.app.submission.submissionapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class StudentService {
    // Hibernate SessionFactory
    protected static SessionFactory sf = HibernateUtil.getSessionFactory();
    protected static Session session;

    // Singleton pattern
    private static StudentService studentService;

    public static synchronized StudentService getInstance() {
        if (studentService == null) {
            studentService = new StudentService();
        }
        return studentService;
    }
    public void addStudent(Student student) {
        session = sf.openSession();
        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();
    }
    public List<Student> getAllStudents() {
        session = sf.openSession();
        List<Student> students = session.createQuery("from Student").list();
        session.close();
        return students;
    }
}
