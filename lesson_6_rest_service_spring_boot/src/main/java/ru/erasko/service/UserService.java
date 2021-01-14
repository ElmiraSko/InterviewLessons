package ru.erasko.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.erasko.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static List<User> users;
    {
        users = new ArrayList<>();
        users.add(new User(1L, "User1", 25));
        users.add(new User(2L, "User2", 38));
        users.add(new User(3L, "User3", 42));
    }

    public List<User> findAllUsers() {
        logger.info("UserService method: findAllUsers()");
        return users;
    }

    public User findUserById(Long id) {
        logger.info("UserService method: findUserById()");
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByName(String name) {
        logger.info("UserService method: findUserByName()");
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByAge(int age) {
        logger.info("UserService method: findUserByAge()");
        for (User user : users) {
            if (user.getAge() == age) {
                return user;
            }
        }
        return null;
    }

    public boolean addNewUser(User newUser) {
        logger.info("UserService method: addNewUser()");
        Long idNewUser = (long) (users.size() + 1);
        return users.add(new User(idNewUser, newUser.getName(), newUser.getAge()));
    }
}
