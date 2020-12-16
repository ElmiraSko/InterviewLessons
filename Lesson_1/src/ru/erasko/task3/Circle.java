package ru.erasko.task3;

public class Circle extends Figure implements HavingArea {
    private double r;
    private double Pi = 3.14;

    public Circle(String name, double r) {
        super(name);
        this.r = r;
    }

    @Override
    public double findArea() {
        System.out.println("Площадь круга");
        return Pi * r * r;
    }

    @Override
    public void draw() {
        System.out.println("Рисуем " + this.getName());
    }
}
