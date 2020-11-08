package ru.kpfu.kutyavina.service;

import ru.kpfu.kutyavina.models.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    public static List<String> getTimeList(ResultSet resultSet) throws SQLException {
        List<String> timeList  = AppointmentList.getTime();
        List<String> answerList = new ArrayList<>();
        while (resultSet.next()){

            String time = resultSet.getString("time");
            String name = resultSet.getString("name");

            switch (name) {
                case "cons30":
                case  "cons60":
                    answerList.add(time);
                    break;
                case "analysis":
                    answerList.add(time);
                    answerList.add(timeList.get(timeList.indexOf(time) + 2));
                    answerList.add(timeList.get(timeList.indexOf(time) + 1));
                    break;
                case "newG":
                    answerList = timeList;
                    break;

            }
        }
        return answerList;
    }

    public static List<Appointment> getAppointments (ResultSet resultSet) throws SQLException {
        List<Appointment> appointments  = new ArrayList<>();
        while (resultSet.next()){
            Appointment appointment = new Appointment();
            appointment.setId(resultSet.getString("id"));
            appointment.setName(resultSet.getString("name"));
            appointment.setDate(resultSet.getString("date"));
            appointment.setUser_id(resultSet.getString("user_id"));
            appointment.setTime(resultSet.getString("time"));

            appointments.add(appointment);
        }
        return appointments;
    }
}
