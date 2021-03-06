package ua.kiev.prog.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Baskets")
public class Basket {

    @Id
    @GeneratedValue
    @Column(name = "basket_id")
    private long id;
    private String name;
    private int totalQuantity;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser us;
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<MobilePhone> phones = new ArrayList<>();
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private BasketStatus status;

    public Basket(String name, CustomUser us) {
        this.name = name;
        this.us = us;
        this.status = BasketStatus.NOT_PAID;
    }

    public Basket() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<MobilePhone> getPhones() {
        return phones;
    }

    public void setPhones(List<MobilePhone> phones) {
        this.phones = phones;
    }

    public CustomUser getUs() {
        return us;
    }

    public void setUs(CustomUser us) {
        this.us = us;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public BasketStatus getStatus() {
        return status;
    }

    public void setStatus(BasketStatus status) {
        this.status = status;
    }


    public double countTotalPrice() {
        double totalPrice = 0.0;
        for (MobilePhone phone : phones) {
            totalPrice += phone.getMobile().getPrice();
        }
        return totalPrice;
    }

    public int countTotalQuantity() {
        return phones.size();
    }
}
