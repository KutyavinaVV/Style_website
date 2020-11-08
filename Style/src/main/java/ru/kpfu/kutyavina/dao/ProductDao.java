package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.models.Product;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> getAllProduct() throws SQLException;
    List<Product> getProductByType (String type);
    Product getProductById (int id) throws SQLException;
}
