package com.soap.server;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "com.soap.server.UserService")
public class UserServiceImpl implements UserService {

    private static List<User> userList = new ArrayList<>();

    @Override
    public String createUser(int id, String username, String password) {
        for(User u : userList) {
            if(u.getId() == id) return "Error: User ID already exists.";
        }
        userList.add(new User(id, username, password));
        return "Success: User created.";
    }

    @Override
    public String retrieveUser(int id) {
        for (User u : userList) {
            if (u.getId() == id) {
                return u.toString();
            }
        }
        return "Error: User not found.";
    }

    @Override
    public String updateUser(int id, String username, String password) {
        for (User u : userList) {
            if (u.getId() == id) {
                u.setUsername(username);
                u.setPassword(password);
                return "Success: User updated.";
            }
        }
        return "Error: User not found.";
    }

    @Override
    public String deleteUser(int id) {
        for (User u : userList) {
            if (u.getId() == id) {
                userList.remove(u);
                return "Success: User deleted.";
            }
        }
        return "Error: User not found.";
    }
}