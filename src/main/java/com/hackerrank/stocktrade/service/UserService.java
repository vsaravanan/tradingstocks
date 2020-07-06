package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.exception.NoSuchResourceFoundException;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Transactional
    public User getOrCreateUser(User user) {
        User tmpUser = getUserOrNull(user.getId());

        if (tmpUser == null) {
            tmpUser = new User(user.getId(), user.getName());
            userRepository.save(tmpUser);
        }
        return tmpUser;

    }


    private User getUserOrNull(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserById(Long id) {

        User user = getUserOrNull(id);

        if (user == null) {
            throw new NoSuchResourceFoundException("No user with given id found.");
        }

        return user;
    }

    public Iterable<User> findAll() {

        return userRepository.findAll();
    }

    @Transactional
    public void deleteAll() {
        userRepository.deleteAll();
    }


}
