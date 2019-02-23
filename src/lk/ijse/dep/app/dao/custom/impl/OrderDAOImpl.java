package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.CrudDAOImpl;
import lk.ijse.dep.app.dao.custom.OrderDAO;
import lk.ijse.dep.app.entity.Order;

import java.sql.ResultSet;

public class OrderDAOImpl extends CrudDAOImpl<Order,String> implements OrderDAO {
    @Override
    public Long count() throws Exception {
        Long count= (Long) session.createQuery("SELECT COUNT (o) FROM lk.ijse.dep.app.entity.Order o").uniqueResult();
        return count;
    }
}
