package ru.erasko.task3;

public abstract class Figure {

    private String name;

    public abstract void draw();

    public Figure(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

