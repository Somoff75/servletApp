package com.example.demo.session;

import java.util.HashMap;
import java.util.Map;

public class UserMap {
    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "password");
        users.put("user1", "password1");
        users.put("user2", "password2");
        users.put("user3", "password3");

    }
    public static Map<String, String> getUsers() {

        return users;
    }
}
