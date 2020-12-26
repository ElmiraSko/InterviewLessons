package ru.erasko.task1;

public class Main {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        new Ping(pingPong);
        new Pong(pingPong);
    }
}
