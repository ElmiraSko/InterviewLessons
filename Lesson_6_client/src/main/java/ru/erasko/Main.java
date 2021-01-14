package ru.erasko;

public class Main {

    public static void main(String[] args) {

        Client client = new Client("127.0.0.1", 8088);
        client.getAllUsers();
        client.getUserById(2);
        client.saveNewUser("newUser", 54);
        client.getUserByName("newUser");
        client.getAllUsers();
        client.close();
    }
}
