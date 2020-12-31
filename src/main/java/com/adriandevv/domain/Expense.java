package com.adriandevv.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Representa una entidad Gasto
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Entity
@Table(name = "expenses")
public class Expense extends Item {
    private double price;
    private Date boughtOn;
    private int quantity;
    private String user;

    public Expense() {
    }

    /**
     * Construye un objeto de tipo gasto
     * @param id El id del gasto
     * @param name El nombre del gasto
     * @param description La descripción del gasto
     */
    public Expense(int id, String name, String description) {
        super(id, name, description);
    }


    /**
     * Construye un objeto de tipo gasto
     * @param id El id del gasto
     * @param name El nombre del gasto
     * @param description La descripción del gasto
     * @param price El precio del gasto (en dolares)
     * @param boughtOn La fecha de compra del gasto (dd/mm/yy)
     * @param quantity La cantidad del gasto (cuantas unidades se han comprado)
     * @param user El usuario que lo ha comprado
     */
    public Expense(int id, String name, String description, double price, Date boughtOn, int quantity, String user) {
        this(id, name, description);
        this.price = price;
        this.boughtOn = boughtOn;
        this.quantity = quantity;
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getBoughtOn() {
        return boughtOn;
    }

    public void setBoughtOn(Date boughtOn) {
        this.boughtOn = boughtOn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "price=" + price +
                ", quantity=" + quantity +
                ", boughtOn=" + boughtOn +
                '}';
    }
}
