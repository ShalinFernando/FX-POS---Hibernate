package lk.ijse.dep.app.dto;

import lk.ijse.dep.app.entity.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO extends SuperDTO{

    private String id;
    private LocalDate date;
    private CustomerDTO customer;
    private List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(String id, LocalDate date, CustomerDTO customer, List<OrderDetailDTO> orderDetailDTOS) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.orderDetailDTOS = orderDetailDTOS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<OrderDetailDTO> getOrderDetailDTOS() {
        return orderDetailDTOS;
    }

    public void setOrderDetailDTOS(List<OrderDetailDTO> orderDetailDTOS) {
        this.orderDetailDTOS = orderDetailDTOS;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", customer=" + customer +
                ", orderDetailDTOS=" + orderDetailDTOS +
                '}';
    }
}
