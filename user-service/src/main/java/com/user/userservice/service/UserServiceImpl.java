package com.user.userservice.service;

import com.user.userservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    //fake user list
    List<User> userList = List.of(
            new User(11L,"Md Al Shariar", "123456"),
            new User(12L,"Salman Sohel", "789045"),
            new User(13L,"Ali Arafat Jakaria", "234567"),
            new User(14L,"Mohammad Mostafizur Rahman", "4567890987")
    );

    @Override
    public User getUser(Long id) {
        return this.userList.stream().filter(user -> user.getUserId().equals(id)).findAny().orElse(null);
    }
}
