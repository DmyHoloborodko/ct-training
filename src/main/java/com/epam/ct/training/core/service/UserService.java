package com.epam.ct.training.core.service;

import com.epam.ct.training.core.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>(Arrays.asList(new User("0"), new User("1")));

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String id) {
        return users.get(parseInt(id));
    }

    public void addUser(User user) {
        System.out.println(user);
        users.add(user);
        System.out.println(users);
    }
}
