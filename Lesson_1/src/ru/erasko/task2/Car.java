package ru.erasko.task2;

public abstract class Car implements Moveable, Stopable{

    private String name;
    private String color;
    private Engine engine;

    public Car(String name, String color, Engine engine) {
        this.name = name;
        this.color = color;
        this.engine = engine;
    }

    abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

