package cn.wolfcode.mongodb.service;

import cn.wolfcode.mongodb.domain.User;

import java.util.List;

public interface IUserService {


    void save(User user);

    void update(User user);

    void delete(String id);

    User get(String id);

    List<User> listAll();

}
