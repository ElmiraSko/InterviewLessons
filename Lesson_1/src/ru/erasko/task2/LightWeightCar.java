package ru.erasko.task2;

public class LightWeightCar extends Car {

    public LightWeightCar(String name, String color, Engine engine) {
        super(name, color, engine);
    }

    public void open() {
        System.out.println("Car is open");
    }
    @Override
    public void start() {
        System.out.println("Car starting");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

    @Override
    public void stop() {
        System.out.println("Car is stop");
    }
}
