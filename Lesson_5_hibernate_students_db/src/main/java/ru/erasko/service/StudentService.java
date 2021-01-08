package ru.erasko.service;

import ru.erasko.dao.StudentDao;
import ru.erasko.dao.StudentDaoImpl;
import ru.erasko.model.Student;

import java.util.List;

public class StudentService {

    private final StudentDao usersDao = new StudentDaoImpl();

    public StudentService() {
    }

    public Student findById(long id) {
        return usersDao.findById(id);
    }

    public void save(Student student) {
       usersDao.save(student);
    }

    public void update(Student student) {
        usersDao.update(student);
    }

    public void delete(Student student) {
       usersDao.delete(student);
    }

    public List<Student> findAll() {
       return usersDao.findAll();
    }
}
