package ru.erasko.task1;

public class Pong implements Runnable {

    PingPong pingPong;

    public Pong(PingPong pingPong) {
        this.pingPong = pingPong;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {
            pingPong.writePong();
        }
    }
}
