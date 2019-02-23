package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.CrudDAOImpl;
import lk.ijse.dep.app.dao.custom.OrderDetailDAO;
import lk.ijse.dep.app.entity.OrderDetail;
import lk.ijse.dep.app.entity.OrderDetailPK;

import java.util.List;

public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail, OrderDetailPK> implements OrderDetailDAO {
    @Override
    public List<OrderDetail> find(String orderId) throws Exception {
        List<OrderDetail> list = session.createQuery("SELECT od FROM lk.ijse.dep.app.entity.OrderDetail od where od.order.id=?1", OrderDetail.class)
                .setParameter(1, orderId)
                .list();

        for (OrderDetail orderDetail : list) {
            System.out.println(orderDetail.getOrder() + "imal" + orderDetail.getQty());
        }
        return list;
    }
}

