package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.models.Product;
import ru.kpfu.kutyavina.service.DBConnection;
import ru.kpfu.kutyavina.service.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> getAllProduct() throws SQLException {
        return null;
    }

    @Override
    public List<Product> getProductByType(String type) {
        String query = "SELECT * FROM product WHERE type = ?";
        List<Product> products = null;
        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, type);
            ResultSet resultSet = ps.executeQuery();
            products = ProductService.getProductsSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        return null;
    }
}
