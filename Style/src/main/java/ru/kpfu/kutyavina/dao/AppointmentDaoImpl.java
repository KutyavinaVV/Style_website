package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.models.Appointment;
import ru.kpfu.kutyavina.service.AppointmentService;
import ru.kpfu.kutyavina.service.CheckerUp;
import ru.kpfu.kutyavina.service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {


    @Override
    public List<String> getBookedTime(String data) {
        String query = "SELECT * FROM appointment WHERE date = (?)";
        List<String> date = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, data);
            ResultSet set = state.executeQuery();
            date = AppointmentService.getTimeList(set);
            return date;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Appointment> getByUserId(int user_id) {
        String query = "SELECT * FROM appointment WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement state = conn.prepareStatement(query);
            state.setInt(1, user_id);
            ResultSet set = state.executeQuery();
            List<Appointment> appointments = null;
            appointments = AppointmentService.getAppointments(set);
            return appointments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String date, String time) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("DELETE FROM appointment WHERE \"date\" = (?) and \"time\" = (?)");
            state.setString(1, date);
            state.setString(2, time);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(String date, String time, String user_id, String name) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement state = connection.prepareStatement("INSERT INTO appointment ( user_id, \"date\", \"time\", name) VALUES ((?), (?), (?), (?));");
            state.setInt(1, Integer.parseInt(user_id));
            state.setString(2, date);
            state.setString(3, time);
            state.setString(4, name);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByUserId(int id) {
        List<Appointment> appointments = getByUserId(id);
        for (Appointment a: appointments) {
            if (!CheckerUp.checkData(a.getDate())) {
                try (Connection connection = DBConnection.getConnection()) {
                    PreparedStatement state = connection.prepareStatement("DELETE FROM appointment WHERE \"date\" = (?)");
                    state.setString(1, a.getDate());
                    state.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
