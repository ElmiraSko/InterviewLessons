package ru.erasko;

import ru.erasko.model.Student;
import ru.erasko.service.StudentService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        final short max = 100;
        final short min = 56;

        StudentService studentService = new StudentService();

        for (int i = 1; i < 1001; i++) {
            short mark = (short) (Math.random()*((max-min)+1)+min);
           studentService.save(new Student("student-" + i, mark));
        }

        List<Student> studentList1 = studentService.findAll();
        System.out.println("Количество студентов: " + studentList1.size());

        Student student = studentService.findById(5);
        System.out.println(student);
        student.setName("Ivan");
        student.setMark((short)100);
        studentService.update(student);
        System.out.println(studentService.findById(5));

        studentService.delete(student);

        List<Student> studentList2 = studentService.findAll();
        System.out.println("Количество студентов: " + studentList2.size());
    }
}