package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageOrdersBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.ItemDAO;
import lk.ijse.dep.app.dao.custom.OrderDAO;
import lk.ijse.dep.app.dao.custom.OrderDetailDAO;
import lk.ijse.dep.app.dto.ItemDTO;
import lk.ijse.dep.app.dto.OrderDTO;
import lk.ijse.dep.app.dto.OrderDTO2;
import lk.ijse.dep.app.dto.OrderDetailDTO;
import lk.ijse.dep.app.entity.Customer;
import lk.ijse.dep.app.entity.Item;
import lk.ijse.dep.app.entity.Order;
import lk.ijse.dep.app.entity.OrderDetail;
import lk.ijse.dep.app.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;

public class ManageOrdersBOImpl implements ManageOrdersBO {
    private OrderDAO orderDAO;
    private ItemDAO itemDAO;
    private OrderDetailDAO orderDetailDAO;

    public ManageOrdersBOImpl() {

        orderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
        itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        orderDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    }

    @Override
    public List<OrderDTO2> getOrdersWithCustomerNamesAndTotals() throws Exception {
        return null;
    }

    @Override
    public List<OrderDTO> getOrders() throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try (Session session = mySession) {
            orderDAO.setSession(session);

            session.beginTransaction();
            List<OrderDTO> orderDTOS = orderDAO.findAll().map(Converter::<OrderDTO>getDTOList).get();
            session.getTransaction().commit();
            return orderDTOS;
        } catch (Exception ex) {
            mySession.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public String generateOrderId() throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try (Session session = mySession) {
            orderDAO.setSession(session);

            session.beginTransaction();
            String count = orderDAO.count() + 1 + "";
            session.getTransaction().commit();
            System.out.println(count);
            return count;
        } catch (Exception ex) {
            mySession.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void createOrder(OrderDTO dto) throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        boolean result = false;
        try (Session session = mySession) {
            orderDAO.setSession(session);
            session.beginTransaction();
            orderDAO.save(new Order(dto.getId(), Date.valueOf(dto.getDate()), Converter.getEntity(dto.getCustomer())));
            result = true;
            if (result) {
                orderDetailDAO.setSession(session);

                for (OrderDetailDTO detailDTO : dto.getOrderDetailDTOS()) {
                    orderDetailDAO.save(new OrderDetail(dto.getId(),
                            detailDTO.getCode(), detailDTO.getQty(), detailDTO.getUnitPrice()));


                    itemDAO.setSession(session);
                    Item item = itemDAO.find(detailDTO.getCode()).get();
                    int qty = item.getQtyOnHand() - detailDTO.getQty();
                    item.setQtyOnHand(qty);
                    itemDAO.update(item);

                }
            }

            session.getTransaction().commit();
        } catch (Exception ex) {
            mySession.getTransaction().rollback();
            throw ex;
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
