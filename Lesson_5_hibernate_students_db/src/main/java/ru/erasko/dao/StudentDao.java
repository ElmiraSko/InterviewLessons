package ru.erasko.dao;

import ru.erasko.model.Student;

import java.util.List;

public interface StudentDao {

    Student findById(long id);

    void save(Student student);

    void update(Student student);

    void delete(Student student);

    List<Student> findAll();
}
