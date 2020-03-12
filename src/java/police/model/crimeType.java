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
public class crimeType implements Serializable{
    private int id=0;
    private String crime_type="";
    private String notes="";
    private String category="";
     private String crime_category = "";
    private int crime_category_id = 0;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCrime_category() {
        return crime_category;
    }

    public void setCrime_category(String crime_category) {
        this.crime_category = crime_category;
    }

    public int getCrime_category_id() {
        return crime_category_id;
    }

    public void setCrime_category_id(int crime_category_id) {
        this.crime_category_id = crime_category_id;
    }
    
}
