package ua.kiev.prog.dao;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser user;
    private String deliveryMethod;
    private String deliveryAddress;
    private String ipAddress;
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;
    @JoinColumn(name = "declaration_number")
    private String declarationNumber;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(CustomUser user, Basket basket, String deliveryMethod, String deliveryAddress, String ipAddress, OrderStatus status) {
        this.user = user;
        this.basket = basket;
        this.deliveryMethod = deliveryMethod;
        this.deliveryAddress = deliveryAddress;
        this.ipAddress = ipAddress;
        this.status = status;
        this.declarationNumber = "-";
    }

    public Order() {
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDeclarationNumber() {
        return declarationNumber;
    }

    public void setDeclarationNumber(String declarationNumber) {
        this.declarationNumber = declarationNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

