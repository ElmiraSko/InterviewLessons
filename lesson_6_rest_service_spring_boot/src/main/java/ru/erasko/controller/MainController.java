package ru.erasko.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.erasko.model.User;
import ru.erasko.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        logger.info("Метод контроллера: findAll()");
        return  userService.findAllUsers();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        logger.info("Метод контроллера: getUserById()");
        User user = userService.findUserById(userId);
        return user!=null ?
                new ResponseEntity<>(user, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<User> findUserByName(@RequestParam("name") String name) {
        logger.info("Метод контроллера: findUserByName()");
        User user = userService.findUserByName(name);
        return user!=null ?
                ResponseEntity.ok().body(user) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> saveNewUser(@RequestBody User user) {
        logger.info("Метод контроллера: saveNewUser()");
        if(userService.addNewUser(user))
        return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
