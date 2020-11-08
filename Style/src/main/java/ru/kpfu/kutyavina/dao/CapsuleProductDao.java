package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.exeption.NoThisIdException;
import ru.kpfu.kutyavina.exeption.ProductAlreadyExistsException;
import ru.kpfu.kutyavina.models.Product;
import java.util.List;

public interface CapsuleProductDao {
    void add(int productID, int capsuleID) throws NoThisIdException;
    List<Product> findProduct(int capsuleID);
    void delete(int productID, int capsuleID);
    boolean check (int productID, int capsuleID) throws ProductAlreadyExistsException;
    void deleteByCapsuleId (int id);
}
