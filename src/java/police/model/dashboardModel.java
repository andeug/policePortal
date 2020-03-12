/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class dashboardModel {

    private int id = 0;
    private String crime_type = "";
    private int crime_rate = 0;
    private int crime_total = 0;
    private String crime_color = "";

    /**
     * Creates a new instance of dashboardModel
     */
    public dashboardModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrime_type() {
        return crime_type;
    }

    public void setCrime_type(String crime_type) {
        this.crime_type = crime_type;
    }

    public int getCrime_rate() {
        return crime_rate;
    }

    public void setCrime_rate(int crime_rate) {
        this.crime_rate = crime_rate;
    }

    public int getCrime_total() {
        return crime_total;
    }

    public void setCrime_total(int crime_total) {
        this.crime_total = crime_total;
    }

    public String getCrime_color() {
        return crime_color;
    }

    public void setCrime_color(String crime_color) {
        this.crime_color = crime_color;
    }

    
}
