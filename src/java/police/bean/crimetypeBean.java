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
import org.primefaces.PrimeFaces;
import police.database.Database;
import police.login.checkLogin;
import police.model.crimeType;
import police.navigator.contextMSG;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class crimetypeBean {

    /**
     * Creates a new instance of crimetypeBean
     */
    public crimetypeBean() {
    }
    private int id = 0;
    private String crime_type = "";
    private String notes = "Add notes";
    private String category = "0";
    private String crime_category = "";
    private int crime_category_id = 0;
    private List<crimeType> crimeTypes = new ArrayList<>();
    private List<crimeType> filterdCrimeTypes;
    private List<crimeType> SelectedCrimeTypes;
    private crimeType oneCrimeType;

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

    public List<crimeType> getCrimeTypes() {
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("crimeTypesList")) {
            crimeTypes = (List<crimeType>) klpd.get("crimeTypesList");
        }
        return crimeTypes;
    }

    public void setCrimeTypes(List<crimeType> crimeTypes) {
        this.crimeTypes = crimeTypes;
    }

    public List<crimeType> getFilterdCrimeTypes() {
        return filterdCrimeTypes;
    }

    public void setFilterdCrimeTypes(List<crimeType> filterdCrimeTypes) {
        this.filterdCrimeTypes = filterdCrimeTypes;
    }

    public List<crimeType> getSelectedCrimeTypes() {
        return SelectedCrimeTypes;
    }

    public void setSelectedCrimeTypes(List<crimeType> SelectedCrimeTypes) {
        this.SelectedCrimeTypes = SelectedCrimeTypes;
    }

    public crimeType getOneCrimeType() {
        return oneCrimeType;
    }

    public void setOneCrimeType(crimeType oneCrimeType) {
        this.oneCrimeType = oneCrimeType;
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

    @PostConstruct
    public void init() {
        fetchCrimeTypes();
    }

    public void fetchCrimeTypes() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        crimeTypes = new ArrayList<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("crimeTypesList");
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tCRIMETYPE  order by id desc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    crimeType si = new crimeType();
                    si.setCrime_type(rs.getString("crime_type"));
                    si.setNotes(rs.getString("notes"));
                    si.setCategory(new crimeCategoryBean().fetchCrimeCategoryName(rs.getInt("category")));
                    si.setCrime_category_id(rs.getInt("category"));
                    si.setId(rs.getInt("id"));
                    crimeTypes.add(si);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("crimeTypesList", crimeTypes);

                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(crimetypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        new contextMSG().info("loaded " + crimeTypes.size());
    }

    public void loadOneCrimeType(crimeType crime) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        if (crime != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("oneCrimeType", crime);
            this.id = crime.getId();
            this.crime_type = crime.getCrime_type();
            this.category = crime.getCategory();
            this.notes = crime.getNotes();
            this.crime_category_id = crime.getCrime_category_id();
        } else {
            System.out.println("@Load one crime_type failed");
        }
    }

    public void resetFields() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        id = 0;
        crime_type = "";
        notes = "";
        category = "";
        crime_category = "";
        crime_category_id = 0;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("oneCrimeType");
    }

    public void updateCrimeType() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                if (klpd.containsKey("oneCrimeType")) {
                    crimeType lus = (crimeType) klpd.get("oneCrimeType");
                    pst = lconn.prepareStatement("update tCRIMETYPE set crime_type=?,notes=?,category=?"
                            + " where id=? ");
                    pst.setString(1, crime_type);
                    pst.setString(2, notes);
                    pst.setInt(3, crime_category_id);
                    pst.setInt(4, lus.getId());
                    // System.out.println(pst);
                    int ret = pst.executeUpdate();
                    if (ret > 0) {
                        new contextMSG().info("Successful, Updated crime_type: " + crime_type);
                        this.fetchCrimeTypes();
                        this.resetFields();
                        PrimeFaces.current().executeScript("PF('edtpnl').hide()");
                    } else {
                        new contextMSG().error("Failed to Update crime_type: " + crime_type);
                    }
                    pst.close();
                    lconn.close();
                }
            }
        } catch (SQLException ex) {
            new contextMSG().error("Failed to Update crime_type: " + crime_type);
            Logger.getLogger(crimetypeBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertCrimeType() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                pst = lconn.prepareStatement("insert into tCRIMETYPE (crime_type,notes,category"
                        + ") values (?,?,?)");
                pst.setString(1, crime_type);
                pst.setString(2, notes);
                pst.setInt(3, crime_category_id);

                int ret = pst.executeUpdate();
                if (ret > 0) {
                    new contextMSG().info("Successful, Saved crime_type: " + crime_type);
                    this.fetchCrimeTypes();
                    this.resetFields();
                    PrimeFaces.current().executeScript("PF('addpnl').hide()");
                } else {
                    new contextMSG().error("Failed to Save crime_type: " + crime_type);
                }
                pst.close();
                lconn.close();

            }
        } catch (SQLException ex) {
            new contextMSG().error("Failed to Save crime_type: " + crime_type);
            Logger.getLogger(crimetypeBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int fetchCrimeCategoryId(int crimeTypeID) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        int ret = 0;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select category from tCRIMETYPE  where id=?");
                pst.setInt(1, crimeTypeID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getInt("category");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(crimetypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
