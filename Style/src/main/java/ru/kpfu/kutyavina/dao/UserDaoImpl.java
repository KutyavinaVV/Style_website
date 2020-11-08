package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.models.User;
import ru.kpfu.kutyavina.service.DBConnection;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private final String ADD = "INSERT INTO \"user\" (user_name, email, password_hash) VALUES ((?), (?), (?));";
    private final String SELECT = "SELECT user_name, id from \"user\" WHERE email = (?) AND password_hash = (?)";
    private final String SELECT_EMAIL = "SELECT from \"user\" WHERE email = (?)";
    private final String UPDATE = "UPDATE \"user\" SET user_name = (?) WHERE user_name = (?) AND email = (?);";
    private final String DELETE = "DELETE FROM \"user\" WHERE email = (?) AND user_name = (?);";

    @Override
    public void updateName(User user, String name) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement(UPDATE);
            state.setString(1, name);
            state.setString(2, user.getName());
            state.setString(3, user.getEmail());
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement(DELETE);
            state.setString(1, user.getEmail());
            state.setString(2, user.getName());
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement(ADD);
            state.setString(1, user.getName());
            state.setString(2, user.getEmail());
            state.setInt(3, user.getPassword());
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean findByEmail(String email) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement(SELECT_EMAIL);
            state.setString(1, email);
            ResultSet set = state.executeQuery();
            return set.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User find(String email, String password) {
        try (Connection connection = DBConnection.getConnection()) {
             PreparedStatement state = connection.prepareStatement(SELECT);
            state.setString(1, email);
            state.setInt(2, password.hashCode());
            ResultSet set = state.executeQuery();
            if(set.next()) {
                System.out.println(set.getString(1));
                return new User(set.getString("user_name"), email,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findId(User user) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement(SELECT);
            state.setString(1, user.getEmail());
            state.setInt(2, user.getPassword());
            ResultSet set = state.executeQuery();
            if(set.next()) {
                return Integer.parseInt(set.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
