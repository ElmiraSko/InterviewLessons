package ru.erasko;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    private static final String HOST_PORT = "Host: localhost:8088";

    private Socket socket = null;
    private BufferedReader input = null;
    private PrintWriter output = null;

    public Client(String host, int port) {

        try {
            socket = new Socket(host, port);
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            output = new PrintWriter(socket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected!");
    }

    public void getAllUsers() {
        //отправляем запрос
        output.println("GET /web-app/users HTTP/1.1");
        output.println(HOST_PORT);
        output.println();
        output.flush();

        // ответ сервера
        System.out.println("=== getAllUsers ===");
        serverAnswer(input);
    }

    public void getUserById(long id) {
        //отправляем запрос
        output.println("GET /web-app/users/get/" + id + " HTTP/1.1");
        output.println(HOST_PORT);
        output.println();
        output.flush();

        // ответ сервера
        System.out.println("=== getUserById ===");
        serverAnswer(input);
    }

    public void getUserByName(String name) {
        //отправляем запрос
        output.println("GET /web-app/users/get?name=" + name + " HTTP/1.1");
        output.println(HOST_PORT);
        output.println();
        output.flush();

        // ответ сервера
        System.out.println("=== getUserByName ===");
        serverAnswer(input);
    }

    public void saveNewUser(String newName, int newAge) {

        StringBuilder newUser = new StringBuilder("{\r\n");
        newUser.append("  \"name\": \"");
        newUser.append(newName);
        newUser.append("\",\r\n");
        newUser.append("  \"age\": ");
        newUser.append(newAge);
        newUser.append("\r\n}");

        System.out.println(newUser);
        //отправляем запрос
        output.println("POST /web-app/users/add HTTP/1.1");
        output.println(HOST_PORT);
        output.println("Content-Type: application/json");
        output.println("Content-Length: 39");
        output.println();
        output.println(newUser.toString());
        output.flush();
        // ответ сервера
        System.out.println("=== saveNewUser ===");
        serverAnswer(input);
    }

    public void close() {
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serverAnswer(BufferedReader input) {
        try {
            while (!input.ready());
            while (input.ready()) {
                String answer = input.readLine();
                System.out.println(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}