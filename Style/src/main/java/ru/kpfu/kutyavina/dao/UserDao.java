package ru.kpfu.kutyavina.dao;


import ru.kpfu.kutyavina.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void updateName(User user, String name);
    void  delete (User user);
    void  add (User user);
    boolean findByEmail (String email);
    User find (String email, String password);
    int findId(User user);
}
