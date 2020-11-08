package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.exeption.NoThisIdException;
import ru.kpfu.kutyavina.exeption.ProductAlreadyExistsException;
import ru.kpfu.kutyavina.models.Product;
import ru.kpfu.kutyavina.service.DBConnection;
import ru.kpfu.kutyavina.service.ProductService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CapsuleProductDaoImpl implements CapsuleProductDao {
    @Override
    public void add(int productID, int capsuleID) throws NoThisIdException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("INSERT INTO capsule_product (id_product, id_capsule) VALUES ((?), (?));");
            state.setInt(1, productID);
            state.setInt(2, capsuleID);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoThisIdException("Такой капсулы не существует");
        }
    }

    @Override
    public List<Product> findProduct(int capsuleID) {
        List<Product> products = null;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("SELECT * FROM capsule_product INNER JOIN product p on capsule_product.id_product = p.id where capsule_product.id_capsule = (?);");
            state.setInt(1, capsuleID);
            ResultSet resultSet = state.executeQuery();
            products = ProductService.getProductsSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void delete(int productID, int capsuleID) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("DELETE FROM capsule_product WHERE id_product = (?) AND id_capsule = (?)");
            state.setInt(1, productID);
            state.setInt(2, capsuleID);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean check(int productID, int capsuleID) throws ProductAlreadyExistsException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("SELECT from capsule_product where id_product = (?) and id_capsule = (?)");
            state.setInt(1, productID);
            state.setInt(2,capsuleID);
            ResultSet set = state.executeQuery();
            if(set.next()) {
                throw new ProductAlreadyExistsException("Exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void deleteByCapsuleId (int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement st = connection.prepareStatement("DELETE from capsule_product where id_capsule = (?)");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
