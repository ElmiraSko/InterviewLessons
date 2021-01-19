package ru.erasko;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Client {
    private static final String HOST_PORT = "localhost:8088";

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
        Request request = new Request.Builder()
                .setMethod("GET")
                .setSimplePath("/web-app/users")
                .setHost(HOST_PORT)
                .build();
        output.println(request);
        output.flush();

        System.out.println("=== getAllUsers ===");
        serverAnswer(input);
    }

    public void getUserById(long id) {
        Request request = new Request.Builder()
                .setMethod("GET")
                .setPathWithVariable("/web-app/users/get", id)
                .setHost(HOST_PORT)
                .build();
        output.println(request);
        output.flush();

        System.out.println("=== getUserById ===");
        serverAnswer(input);
    }

    public void getUserByName(Map<String, Object> params) {
        Request request = new Request.Builder()
                .setMethod("GET")
                .setPathWithRequestParam("/web-app/users/get", params)
                .setHost(HOST_PORT)
                .build();
        output.println(request);
        output.flush();

        System.out.println("=== getUserByName ===");
        serverAnswer(input);
    }

    public void saveNewUser(User newUser) {
        Request request = new Request.Builder()
                .setMethod("POST")
                .setSimplePath("/web-app/users/add")
                .setHost(HOST_PORT)
                .setContentType("application/json")
                .setRequestBody(newUser)
                .build();
        output.println(request);
        output.flush();

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
                if (answer.startsWith("HTTP"))
                    System.out.println("Статус ответа: " + answer);
                if (answer.startsWith("{") || answer.startsWith("["))
                    System.out.println(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}