/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caritas.model.Entity;

/**
 *
 * @author demetrio
 */
public class Peca {
    
    private int id;
    private String name;
    private int amount;

    public Peca() {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", amount=" + amount + '}';
    }
}
