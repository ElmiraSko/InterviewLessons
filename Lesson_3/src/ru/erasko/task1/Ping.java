package ru.erasko.task1;

public class Ping implements Runnable {
    PingPong pingPong;

    public Ping(PingPong pingPong) {
        this.pingPong = pingPong;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {
            pingPong.writePing();
        }
    }
}
