package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.models.Capsule;
import ru.kpfu.kutyavina.models.Product;

import java.util.List;

public interface CapsuleDao {
    List<Capsule> getByUserId(int idUser);
    void create (String name, int idUser);
    void updateName (String name, int id);
    void delete (int id);
}
