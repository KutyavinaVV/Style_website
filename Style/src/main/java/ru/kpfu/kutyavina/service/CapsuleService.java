package ru.kpfu.kutyavina.service;

import ru.kpfu.kutyavina.dao.CapsuleProductDao;
import ru.kpfu.kutyavina.dao.CapsuleProductDaoImpl;
import ru.kpfu.kutyavina.models.Capsule;
import ru.kpfu.kutyavina.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CapsuleService {

    public static List<Capsule> getCapsuleList (ResultSet resultSet) throws SQLException {
        List<Capsule> capsules  = new ArrayList<>();
        while (resultSet.next()){
            Capsule capsule = new Capsule();
            capsule.setId(resultSet.getString("id"));
            capsule.setName(resultSet.getString("name"));
            capsule.setUserId(resultSet.getString("user_id"));
            CapsuleProductDao cpd = new CapsuleProductDaoImpl();
            capsule.setProducts(cpd.findProduct(Integer.parseInt(resultSet.getString("id"))));
            capsules.add(capsule);
        }
        return capsules;
    }
}
