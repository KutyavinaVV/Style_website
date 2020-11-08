package ru.kpfu.kutyavina.dao;

public interface FavoriteDao {
    void add (int user_id, String name);
    void delete (int user_id, String name);
    boolean read(int user_id, String name);
}
