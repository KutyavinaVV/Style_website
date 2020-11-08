package ru.kpfu.kutyavina.models;

import ru.kpfu.kutyavina.service.AppointmentList;

public class Appointment {

    private String id;
    private String user_id;
    private String date;
    private String time;
    private String name;

    public String getAllName() {
        AppointmentList al = new AppointmentList();
        return al.get(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
