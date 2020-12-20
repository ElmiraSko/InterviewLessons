package ru.erasko.task3;

public class Triangle extends Figure implements HavingArea {

    private double a, b, c;

    public Triangle(String name, double a, double b, double c) {
        super(name);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double findArea() {
        System.out.println("Площадь треугольника");
        double p = (a + b + c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    @Override
    public void draw() {
        System.out.println("Рисуем " + this.getName());
    }
}
