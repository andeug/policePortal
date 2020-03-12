/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;
import police.database.Database;
import police.login.checkLogin;
import police.model.jobTittle;
import police.navigator.contextMSG;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class jobsBean {

    /**
     * Creates a new instance of jobsBean
     */
    public jobsBean() {
    }
    private int id = 0;
    private String job_tittle = "";
    private String job_email = "";
    private String job_notes = "";
    private String dateCreated = "";
    private List<jobTittle> jobTittles = new ArrayList<>();
    private List<jobTittle> filterdJobTittles;
    private List<jobTittle> SelectedJobTittles;
    private jobTittle oneJobTittles;

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

    public List<jobTittle> getJobTittles() {
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("jobTittlesList")) {
            jobTittles = (List<jobTittle>) klpd.get("jobTittlesList");
        }
        return jobTittles;
    }

    public void setJobTittles(List<jobTittle> jobTittles) {
        this.jobTittles = jobTittles;
    }

    public List<jobTittle> getFilterdJobTittles() {
        return filterdJobTittles;
    }

    public void setFilterdJobTittles(List<jobTittle> filterdJobTittles) {
        this.filterdJobTittles = filterdJobTittles;
    }

    public List<jobTittle> getSelectedJobTittles() {
        return SelectedJobTittles;
    }

    public void setSelectedJobTittles(List<jobTittle> SelectedJobTittles) {
        this.SelectedJobTittles = SelectedJobTittles;
    }

    public jobTittle getOneJobTittles() {
        return oneJobTittles;
    }

    public void setOneJobTittles(jobTittle oneJobTittles) {
        this.oneJobTittles = oneJobTittles;
    }

    @PostConstruct
    public void init() {
        fetchJobTittles();
    }

    public void fetchJobTittles() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        jobTittles = new ArrayList<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("jobTittlesList");
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tJOBTITTLE  order by id desc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    jobTittle si = new jobTittle();
                    si.setJob_tittle(rs.getString("job_tittle"));
                    si.setJob_email(rs.getString("job_email"));
                    si.setJob_notes(rs.getString("job_notes"));
                    si.setDateCreated(rs.getString("date_Created"));
                    si.setId(rs.getInt("id"));
                    jobTittles.add(si);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jobTittlesList", jobTittles);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(jobsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        new contextMSG().info("loaded " + jobTittles.size());
    }

    public void loadOneJobTittle(jobTittle job) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        if (job != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("oneJobTittle", job);
            this.id = job.getId();
            this.job_tittle = job.getJob_tittle();
            this.job_email = job.getJob_email();
            this.job_notes = job.getJob_notes();
        } else {
            System.out.println("@Load one jobTittle failed");
        }
    }

    public void resetFields() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        id = 0;
        job_tittle = "";
        job_email = "";
        job_notes = "";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("oneJobTittle");
    }

    public void updateJobTittle() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                if (klpd.containsKey("oneJobTittle")) {
                    jobTittle lus = (jobTittle) klpd.get("oneJobTittle");

                    pst = lconn.prepareStatement("update tJOBTITTLE set job_tittle=?,job_email=?,job_notes=?"
                            + "where id=? ");
                    pst.setString(1, job_tittle);
                    pst.setString(2, job_email);
                    pst.setString(3, job_notes);
                    pst.setInt(4, lus.getId());
                    System.out.println(pst);
                    int ret = pst.executeUpdate();
                    if (ret > 0) {
                        new contextMSG().info("Successful, Updated job_tittle: " + job_tittle);
                        this.fetchJobTittles();
                        this.resetFields();
                        PrimeFaces.current().executeScript("PF('edtpnl').hide()");
                    } else {
                        new contextMSG().error("Failed to Update job_tittle: " + job_tittle);
                    }
                    pst.close();
                    lconn.close();

                }
            }
        } catch (SQLException ex) {
            new contextMSG().error("Failed to Update job_tittle: " + job_tittle);
            Logger.getLogger(jobsBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertJobTittle() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                pst = lconn.prepareStatement("insert into tJOBTITTLE (job_tittle,job_email,job_notes"
                        + ") values (?,?,?)");
                pst.setString(1, job_tittle);
                pst.setString(2, job_email);
                pst.setString(3, job_notes);

                int ret = pst.executeUpdate();
                if (ret > 0) {
                    new contextMSG().info("Successful, Saved job_tittle: " + job_tittle);
                    this.fetchJobTittles();
                    this.resetFields();
                    PrimeFaces.current().executeScript("PF('addpnl').hide()");
                } else {
                    new contextMSG().error("Failed to Save job_tittle: " + job_tittle);
                }
                pst.close();
                lconn.close();

            }
        } catch (SQLException ex) {
              new contextMSG().error("Failed to Save job_tittle: " + job_tittle);
            Logger.getLogger(jobsBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SelectItem> getJobs() {
        List<SelectItem> ret = new ArrayList<>();
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id,job_tittle FROM tJOBTITTLE order by id asc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int value = rs.getInt("id");
                    String label = rs.getString("job_tittle");
                    SelectItem si = new SelectItem();
                    si.setLabel(label);
                    si.setValue(value);
                    ret.add(si);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(jobsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
     public String fetchJobTittleById(int id) {
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT job_tittle FROM tJOBTITTLE where id=? ");
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("job_tittle");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(departmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
