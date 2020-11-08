package ru.kpfu.kutyavina.models;

import ru.kpfu.kutyavina.dao.UserDao;
import ru.kpfu.kutyavina.dao.UserDaoImpl;

public class User {
    private String name;
    private String email;
    private int password;
    private String id;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password.hashCode();
        findId();
    }

    private void findId() {
        UserDao ud = new UserDaoImpl();
        this.id = Integer.toString(ud.findId(this));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public int getPassword() {
        return password;
    }
}
