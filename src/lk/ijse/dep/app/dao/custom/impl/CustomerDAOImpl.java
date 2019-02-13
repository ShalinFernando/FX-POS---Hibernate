package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.CrudDAOImpl;
import lk.ijse.dep.app.dao.custom.CustomerDAO;
import lk.ijse.dep.app.entity.Customer;
import lk.ijse.dep.app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl extends CrudDAOImpl<Customer, String> implements CustomerDAO {

}
