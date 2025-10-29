package com.example.BangkokBankTest.service;

import com.example.BangkokBankTest.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final Map<Long, User> userData = new HashMap<>();

    public UserService() {
        userData.put(1L, new User(1L, "Leanne Graham", "Bret", "Sincere@april.biz",
                "1-770-736-8031 x56442", "hildegard.org"));
        userData.put(2L, new User(2L, "Ervin Howell", "Antonette", "Shanna@melissa.tv",
                "010-692-6593 x09125", "anastasia.net"));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userData.values());
    }

    public User getUserById(Long userId) {
        return userData.get(userId);
    }

    public User createUser(User user) {
        user.setId((long) (userData.size() + 1));
        userData.put(user.getId(), user);
        return user;
    }

    public void updateUser(Long userId, User updatedUser) {
        userData.replace(userId, updatedUser);
    }

    public void deleteUser(Long id) {
        userData.remove(id);
    }

}
