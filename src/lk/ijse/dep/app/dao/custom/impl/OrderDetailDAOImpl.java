package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.CrudDAOImpl;
import lk.ijse.dep.app.dao.custom.OrderDetailDAO;
import lk.ijse.dep.app.entity.OrderDetail;
import lk.ijse.dep.app.entity.OrderDetailPK;

import java.util.List;
import java.util.Optional;

public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail, OrderDetailPK> implements OrderDetailDAO {
    @Override
    public Optional<List<OrderDetail>> find(String orderId) throws Exception {
        return Optional.empty();
    }
}
