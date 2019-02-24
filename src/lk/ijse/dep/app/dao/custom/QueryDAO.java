package lk.ijse.dep.app.dao.custom;

import lk.ijse.dep.app.dao.SuperDAO;
import lk.ijse.dep.app.entity.CustomEntity;

import java.util.List;
import java.util.Optional;

public interface QueryDAO extends SuperDAO {

    List<CustomEntity> findOrderDetailsWithItemDescriptions(String orderId) throws Exception;

    List<CustomEntity> findAllOrdersWithCustomerNameAndTotal() throws Exception;

}
