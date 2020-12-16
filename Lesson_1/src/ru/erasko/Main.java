package ru.erasko;

import ru.erasko.task1.Person;
import ru.erasko.task2.*;
import ru.erasko.task3.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println(" === Задание 1 === ");

        Person person = new Person.Builder()
                .setFirstName("Anna")
                .setLastName("Sergeeva")
                .setAge(23)
                .build();

        System.out.println(person);

        System.out.println(" === Задание 2 === ");

        Car car1 = new Lorry("Car1", "White", new EngineOne());
        Car car2 = new LightWeightCar("Car2", "Yellow", new EngineTwo());

        List<Moveable> moveableCars = new ArrayList<>();
        moveableCars.add(car1);
        moveableCars.add(car2);

        for (Moveable car: moveableCars) {
            car.move();
        }

        System.out.println(" === Задание 3 === ");

        List<Figure> figures = new ArrayList<>();
        List<HavingArea> havingAreas = new ArrayList<>();

        Figure circle = new Circle("Круг", 5);

        Figure square = new Square("Квадрат", 4);

        Figure triangle = new Triangle("Треугольник", 3, 4, 5);

        figures.add(circle);
        figures.add(square);
        figures.add(triangle);

        for (Figure figure : figures) {
            System.out.println(figure.getName());
            figure.draw();

            if (figure instanceof HavingArea) {
                havingAreas.add((HavingArea) figure);
            }

        }

        for (HavingArea havingArea: havingAreas) {
            System.out.println(havingArea.findArea());
        }
    }

}

