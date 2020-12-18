package cn.wolfcode.mongodb.service.impl;

import cn.wolfcode.mongodb.domain.User;
import cn.wolfcode.mongodb.repository.UserRepository;
import cn.wolfcode.mongodb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        user.setId(null);
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User get(String id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
