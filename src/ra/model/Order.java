package ra.model;

import ra.config.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private int userId;
    private double total;
    private Date buyDate = new Date();
    private String receiver;
    private String phoneNumber;
    private String address;
    private byte status = 0;
    private List<CartItem> orderDetail = new ArrayList<>();

    public Order() {
    }

    public Order(int id, int userId, double total, Date buyDate, String receiver, String phoneNumber, String address, byte status) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.buyDate = buyDate;
        this.receiver = receiver;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public List<CartItem> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<CartItem> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID : "+id +"|Name : "+receiver +"|Total : "+total +"| Date : "+ buyDate+ "|Status : " + Message.getStatusByCode(status);
    }
}
