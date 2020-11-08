package ru.kpfu.kutyavina.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class CheckerUp {

    public static boolean checkEmail(String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        return pattern.matcher(email).matches();
    }

    public static boolean checkPassword (String password, String password_repeat) {
        return password.equals(password_repeat);
    }

    public static boolean checkName (String name) {
        return !(name.isEmpty());
    }

    public static boolean checkData (String date) {
        Date dateNow = new Date();
        Date d = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dateInString = date;
        try {
            d = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateNow.compareTo(d) < 0;
    }

    public static boolean checkNumber(String num) {
        Pattern pattern = Pattern.compile("^([+]?[0-9\\s-\\(\\)]{10,25})*$");
        return pattern.matcher(num).matches();
    }
}
