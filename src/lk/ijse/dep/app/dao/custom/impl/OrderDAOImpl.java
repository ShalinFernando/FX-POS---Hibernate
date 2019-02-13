package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.CrudDAOImpl;
import lk.ijse.dep.app.dao.custom.OrderDAO;
import lk.ijse.dep.app.entity.Order;

public class OrderDAOImpl extends CrudDAOImpl<Order,String> implements OrderDAO {
    @Override
    public int count() throws Exception {
        return 0;
    }
}
