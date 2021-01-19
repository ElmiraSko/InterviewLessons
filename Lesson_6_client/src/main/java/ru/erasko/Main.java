package ru.erasko;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Client client = new Client("127.0.0.1", 8088);

        client.getAllUsers();
        client.getUserById(2L);
        client.saveNewUser(new User("Tom", 54));
        client.getAllUsers();

        Map<String, Object> params = new HashMap<>(5);
        params.put("name", "Tom");
        client.getUserByName(params);

        client.close();
    }
}
