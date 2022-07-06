package cn.edu.hit.service;

import cn.edu.hit.dao.UserDao;
import cn.edu.hit.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {
    /*void test();*/

    int changeName(String name);
    void addUser(User user);
    User login(User user);
}
