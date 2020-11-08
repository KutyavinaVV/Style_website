package ru.kpfu.kutyavina.dao;

import ru.kpfu.kutyavina.models.Appointment;

import java.util.List;

public interface AppointmentDao {
    List<String> getBookedTime(String data);
    List<Appointment> getByUserId(int user_id);
    void delete (String date, String time);
    void create (String date, String time, String user_id, String name);
    void deleteByUserId(int id);
}
