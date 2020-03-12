/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.model;

import java.io.Serializable;

/**
 *
 * @author mspace_developer
 */
public class crimeCategory implements Serializable{
    private int id=0;
    private String crime_category="";
    private String crime_notes="";
    private String crime_punishment="";
    private String crime_color="";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrime_category() {
        return crime_category;
    }

    public void setCrime_category(String crime_category) {
        this.crime_category = crime_category;
    }

    public String getCrime_notes() {
        return crime_notes;
    }

    public void setCrime_notes(String crime_notes) {
        this.crime_notes = crime_notes;
    }

    public String getCrime_punishment() {
        return crime_punishment;
    }

    public void setCrime_punishment(String crime_punishment) {
        this.crime_punishment = crime_punishment;
    }

    public String getCrime_color() {
        return crime_color;
    }

    public void setCrime_color(String crime_color) {
        this.crime_color = crime_color;
    }

   

    
}
