package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.exeption.NoThisIdException;
import ru.kpfu.kutyavina.exeption.ProductAlreadyExistsException;
import ru.kpfu.kutyavina.service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoriteDaoImpl implements FavoriteDao {
    @Override
    public void add(int user_id, String name) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("INSERT INTO favorite (user_id, article) VALUES ((?), (?));");
            state.setInt(1, user_id);
            state.setString(2, name);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int user_id, String name) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("DELETE FROM favorite WHERE user_id = (?) AND article = (?)");
            state.setInt(1, user_id);
            state.setString(2, name);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean read(int user_id, String name) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("SELECT from favorite where user_id = (?) and article = (?)");
            state.setInt(1, user_id);
            state.setString(2,name);
            ResultSet set = state.executeQuery();
            if(set.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
