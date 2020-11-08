package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.models.Capsule;
import ru.kpfu.kutyavina.service.CapsuleService;
import ru.kpfu.kutyavina.service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CapsuleDaoImpl implements CapsuleDao {

    @Override
    public List<Capsule> getByUserId(int idUser) {
        String query = "SELECT * FROM capsule WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement state = conn.prepareStatement(query);
            state.setInt(1, idUser);
            ResultSet set = state.executeQuery();
            List<Capsule> capsules = null;
            capsules = CapsuleService.getCapsuleList(set);
            return capsules;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void create(String name, int idUser) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("INSERT INTO capsule (name, user_id) VALUES ((?), (?)) returning id;");
            state.setString(1, name);
            state.setInt(2, idUser);
            ResultSet resultSet = state.executeQuery();
            resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateName(String name, int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("UPDATE capsule SET name = (?) WHERE id = (?)");
            state.setString(1, name);
            state.setInt(2, id);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            CapsuleProductDao cpd = new CapsuleProductDaoImpl();
            cpd.deleteByCapsuleId(id);
            PreparedStatement state = connection.prepareStatement("DELETE FROM capsule WHERE id = (?)");
            state.setInt(1, id);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
