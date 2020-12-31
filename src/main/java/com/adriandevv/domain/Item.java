package com.adriandevv.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Representa un item (Clase abstracta, no instanciable)
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@MappedSuperclass
public abstract class Item {
    @Id
    private int id;
    private String name;
    private String description;

    public Item() {
    }

    /**
     * Construye un item
     * @param id El id del item
     * @param name El nombre del item
     * @param description La descripcion del item
     */
    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
