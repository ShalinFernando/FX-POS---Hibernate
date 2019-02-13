package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageCustomersBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.CustomerDAO;
import lk.ijse.dep.app.dto.CustomerDTO;
import lk.ijse.dep.app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ManageCustomersBOImpl implements ManageCustomersBO {

    private CustomerDAO customerDAO;

    public ManageCustomersBOImpl() {
        customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    }

    public List<CustomerDTO> getCustomers() throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try(Session session = mySession){
            customerDAO.setSession(session);
            session.beginTransaction();
            List<CustomerDTO> customerDTOS = customerDAO.findAll().map(Converter::<CustomerDTO>getDTOList).get();
            session.getTransaction().commit();
            return customerDTOS;
        }catch(Exception ex){
            mySession.getTransaction().rollback();
            throw ex;
        }

    }

    public void createCustomer(CustomerDTO dto) throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try(Session session = mySession){
            customerDAO.setSession(session);
            session.beginTransaction();
            customerDAO.save(Converter.getEntity(dto));
            session.getTransaction().commit();
        }catch(Exception ex){
            mySession.getTransaction().rollback();
            throw ex;
        }
    }

    public void updateCustomer(CustomerDTO dto) throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try(Session session = mySession){
            customerDAO.setSession(session);
            session.beginTransaction();
            customerDAO.update(Converter.getEntity(dto));
            session.getTransaction().commit();
        }catch(Exception ex){
            mySession.getTransaction().rollback();
            throw ex;
        }
    }

    public void deleteCustomer(String customerID) throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try(Session session = mySession){
            customerDAO.setSession(session);
            session.beginTransaction();
            customerDAO.delete(customerID);
            session.getTransaction().commit();
        }catch(Exception ex){
            mySession.getTransaction().rollback();
            throw ex;
        }
    }

    public CustomerDTO findCustomer(String id) throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try(Session session = mySession){
            customerDAO.setSession(session);
            session.beginTransaction();
            CustomerDTO customerDTO = customerDAO.find(id).map(Converter::<CustomerDTO>getDTO).orElse(null);
            session.getTransaction().commit();
            return customerDTO;
        }catch(Exception ex){
            mySession.getTransaction().rollback();
            throw ex;
        }
    }

}
