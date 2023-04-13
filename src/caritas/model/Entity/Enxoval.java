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
public class Enxoval {
    
    private int id;
    private int total;
    private int year;

    public Enxoval() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Trosseau{" + "id=" + id + ", total=" + total + ", year=" + year + '}';
    }
    
}
