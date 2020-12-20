package ru.erasko.task3;

public class Square extends Figure implements HavingArea {

    double a;

    public Square(String name, double a) {
        super(name);
        this.a = a;
    }

    @Override
    public double findArea() {
        System.out.println("Площадь квадрата");
        return a * a;
    }

    @Override
    public void draw() {
        System.out.println("Рисуем " + this.getName());
    }
}
