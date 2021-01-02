package ru.erasko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.erasko.model.Student;
import ru.erasko.util.HibernateUtil;

import java.util.List;

public class StudentDaoImpl implements StudentDao{

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Student findById(long id) {
        Session session = sessionFactory.openSession();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }

    public void save(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(student);
        tx1.commit();
        session.close();
    }

    public void update(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(student);
        tx1.commit();
        session.close();
    }

    public void delete(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(student);
        tx1.commit();
        session.close();
    }

    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        List<Student> students = (List<Student>) session.createQuery("From Student").list();
        session.close();
        return students;
    }
}
