package ua.kiev.prog.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Mobiles")
public class Mobile {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private String name;
    private String path;
    private double price;
    private String color;
    private String description;
    private int discount;
    @OneToMany(mappedBy = "mobile", cascade = CascadeType.ALL)
    private List<MobilePhone> phones = new ArrayList<>();
    private int countSold;
    private int countForSale;
    @Column(name = "general_count")
    private int generalCount;
    @Enumerated(EnumType.STRING)
    private MobileStatus status;

    public Mobile() {
    }

    public Mobile(Brand brand, String name, double price, String path, String color, String description, int discount) { // +path
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.path = path;
        this.color = color;
        this.description = description;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<MobilePhone> getPhones() {
        return phones;
    }

    public void setPhones(List<MobilePhone> phones) {
        this.phones = phones;
    }

    public int getCountForSale() {
        return countForSale;
    }

    public void setCountForSale(int countForSale) {
        this.countForSale = countForSale;
    }

    public int getCountSold() {
        return countSold;
    }

    public void setCountSold(int countSold) {
        this.countSold = countSold;
    }

    public int getGeneralCount() {
        return generalCount;
    }

    public void setGeneralCount(int generalCount) {
        this.generalCount = generalCount;
    }

    public MobileStatus getStatus() {
        return status;
    }

    public void setStatus(MobileStatus status) {
        this.status = status;
    }

    public void add() {
        synchronized (this) {
            countForSale += 1;
            generalCount += 1;
        }
    }

    public void delete() {
        synchronized (this) {
            countForSale -= 1;
            countSold += 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mobile mobile = (Mobile) o;
        return id == mobile.id &&
                Double.compare(mobile.price, price) == 0 &&
                Objects.equals(name, mobile.name) &&
                Objects.equals(color, mobile.color) &&
                Objects.equals(description, mobile.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, color, description);
    }
}
