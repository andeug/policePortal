/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mspace_developer
 */
public class jobTittle implements Serializable {

    private int id = 0;
    private String job_tittle = "";
    private String job_email = "";
    private String job_notes = "";
    private String dateCreated = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob_tittle() {
        return job_tittle;
    }

    public void setJob_tittle(String job_tittle) {
        this.job_tittle = job_tittle;
    }

    public String getJob_email() {
        return job_email;
    }

    public void setJob_email(String job_email) {
        this.job_email = job_email;
    }

    public String getJob_notes() {
        return job_notes;
    }

    public void setJob_notes(String job_notes) {
        this.job_notes = job_notes;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

}
