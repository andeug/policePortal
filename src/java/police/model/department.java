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
public class department implements Serializable {

    private int id = 0;
    private String department = "";
    private String department_email = "";
    private String notes = "";
    private String dateCreated = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment_email() {
        return department_email;
    }

    public void setDepartment_email(String department_email) {
        this.department_email = department_email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

}
