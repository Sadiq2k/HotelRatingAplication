package com.user.Service.Service;

import com.user.Service.Entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User saveUser(User user);

    User getUser(String userId);

}
