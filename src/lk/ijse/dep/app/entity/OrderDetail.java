package lk.ijse.dep.app.entity;

import javax.persistence.*;

@Entity
public class OrderDetail extends SuperEntity {

    @EmbeddedId
    private OrderDetailPK orderDetailPK;
    private int qty;
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "code", insertable = false, updatable = false)
    private Item item;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailPK orderDetailPK, int qty, double unitPrice) {
        this.orderDetailPK = orderDetailPK;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDetail(String orderId, String itemCode, int qty, double unitPrice) {
        this.orderDetailPK = new OrderDetailPK(orderId, itemCode);
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public Order getOrder() {
        return order;
    }

    public Item getItem() {
        return item;
    }

    public OrderDetailPK getOrderDetailPK() {
        return orderDetailPK;
    }

    public void setOrderDetailPK(OrderDetailPK orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
