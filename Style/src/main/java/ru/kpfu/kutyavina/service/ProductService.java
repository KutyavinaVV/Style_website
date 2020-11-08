package ru.kpfu.kutyavina.service;

import ru.kpfu.kutyavina.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public static List<Product> getProductsSet(ResultSet resultSet) throws SQLException {
        List<Product> products  = new ArrayList<>();
        while (resultSet.next()){
            Product product = new Product();
            product.setId(resultSet.getString("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setLink(resultSet.getString("link"));
            product.setComposition(resultSet.getString("composition"));
            product.setPath(resultSet.getString("path"));
            product.setType(resultSet.getString("type"));
            products.add(product);
        }
        return products;
    }
}
