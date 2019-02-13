package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageOrdersBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.OrderDAO;
import lk.ijse.dep.app.dto.ItemDTO;
import lk.ijse.dep.app.dto.OrderDTO;
import lk.ijse.dep.app.dto.OrderDTO2;
import lk.ijse.dep.app.entity.Order;
import lk.ijse.dep.app.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;

public class ManageOrdersBOImpl implements ManageOrdersBO {
    private OrderDAO orderDAO;

    public ManageOrdersBOImpl() {
        orderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    }

    @Override
    public List<OrderDTO2> getOrdersWithCustomerNamesAndTotals() throws Exception {
        return null;
    }

    @Override
    public List<OrderDTO> getOrders() throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try {
            Session session = mySession;
            orderDAO.setSession(session);
            session.beginTransaction();
            List<OrderDTO> orderDTOS = orderDAO.findAll().map(Converter::<OrderDTO>getDTOList).get();
            session.getTransaction().commit();
            return orderDTOS;

        } catch (Exception e) {
            mySession.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public String generateOrderId() throws Exception {
        return getOrders().size() + 1 + "";
    }

    @Override
    public void createOrder(OrderDTO dto) throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try {
            Session session = mySession;
            orderDAO.setSession(session);
            session.beginTransaction();
            orderDAO.save(new Order(dto.getId(), Date.valueOf(dto.getDate()),dto.getCustomerId()));
            session.getTransaction().commit();
        } catch (Exception e) {
            mySession.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public OrderDTO findOrder(String orderId) throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try {
            Session session = mySession;
            orderDAO.setSession(session);
            session.beginTransaction();
            OrderDTO orderDTO = orderDAO.find(orderId).map(Converter::<OrderDTO>getDTO).orElse(null);
            session.getTransaction().commit();
            return orderDTO;
        } catch (Exception e) {
            mySession.getTransaction().rollback();
            throw e;
        }
    }

}
