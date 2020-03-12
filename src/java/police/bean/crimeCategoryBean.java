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
import police.model.crimeCategory;
import police.navigator.contextMSG;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class crimeCategoryBean {

    /**
     * Creates a new instance of crimeCategoryBean
     */
    public crimeCategoryBean() {
    }
    private int id = 0;
    private String crime_category = "";
    private String crime_notes = "";
    private String crime_punishment = "";
    private String crime_color = "";
    private List<crimeCategory> crimeCategories = new ArrayList<>();
    private List<crimeCategory> filterdcrimeCategories;
    private List<crimeCategory> SelectedcrimeCategories;
    private crimeCategory oneCrimeCategory;

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

    public List<crimeCategory> getCrimeCategories() {
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("crimeCategories")) {
            crimeCategories = (List<crimeCategory>) klpd.get("crimeCategories");
        }
        return crimeCategories;
    }

    public void setCrimeCategories(List<crimeCategory> crimeCategories) {
        this.crimeCategories = crimeCategories;
    }

    public List<crimeCategory> getFilterdcrimeCategories() {
        return filterdcrimeCategories;
    }

    public void setFilterdcrimeCategories(List<crimeCategory> filterdcrimeCategories) {
        this.filterdcrimeCategories = filterdcrimeCategories;
    }

    public List<crimeCategory> getSelectedcrimeCategories() {
        return SelectedcrimeCategories;
    }

    public void setSelectedcrimeCategories(List<crimeCategory> SelectedcrimeCategories) {
        this.SelectedcrimeCategories = SelectedcrimeCategories;
    }

    public crimeCategory getOneCrimeCategory() {
        return oneCrimeCategory;
    }

    public void setOneCrimeCategory(crimeCategory oneCrimeCategory) {
        this.oneCrimeCategory = oneCrimeCategory;
    }

    @PostConstruct
    public void init() {
        fetchCrimeCategory();
    }

    public void fetchCrimeCategory() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        crimeCategories = new ArrayList<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("crimeCategories");
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tCRIMECATEGORIES  order by id desc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    crimeCategory si = new crimeCategory();
                    si.setCrime_category(rs.getString("crime_category"));
                    si.setCrime_notes(rs.getString("crime_notes"));
                    si.setCrime_color(rs.getString("crime_color"));
                    si.setId(rs.getInt("id"));
                    crimeCategories.add(si);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("crimeCategories", crimeCategories);

                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(crimeCategoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadOneCrimeCategory(crimeCategory category) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        if (category != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("oneCrimeCategory", category);
            this.id = category.getId();
            this.crime_category = category.getCrime_category();
            this.crime_color = category.getCrime_color();
            this.crime_notes = category.getCrime_notes();
        } else {
            System.out.println("@Load one crime_category failed");
        }
    }

    public void resetFields() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        id = 0;
        crime_category = "";
        crime_notes = "";
        crime_color = "";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("oneCrimeCategory");
    }

    public void updateCrimeCategory() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                if (klpd.containsKey("oneCrimeCategory")) {
                    crimeCategory lus = (crimeCategory) klpd.get("oneCrimeCategory");
                    pst = lconn.prepareStatement("update tCRIMECATEGORIES set crime_category=?,crime_notes=?,crime_color=?"
                            + "where id=? ");
                    pst.setString(1, crime_category);
                    pst.setString(2, crime_notes);
                    pst.setString(3, "#"+crime_color);
                    pst.setInt(4, lus.getId());
                    int ret = pst.executeUpdate();
                    if (ret > 0) {
                        new contextMSG().info("Successful, Updated crime_category: " + crime_category);
                        this.fetchCrimeCategory();
                        this.resetFields();
                        PrimeFaces.current().executeScript("PF('edtpnl').hide()");
                    } else {
                        new contextMSG().error("Failed to Update crime_category: " + crime_category);
                    }
                    pst.close();
                    lconn.close();

                }
            }
        } catch (SQLException ex) {
            new contextMSG().error("Failed to Update crime_category: " + crime_category);
            Logger.getLogger(crimeCategoryBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertCategory() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                pst = lconn.prepareStatement("insert into tCRIMECATEGORIES (crime_category,crime_notes,crime_color"
                        + ") values (?,?,?)");
                pst.setString(1, crime_category);
                pst.setString(2, crime_notes);
                pst.setString(3, "#"+crime_color);

                int ret = pst.executeUpdate();
                if (ret > 0) {
                    new contextMSG().info("Successful, Saved crime_category: " + crime_category);
                    this.fetchCrimeCategory();
                    this.resetFields();
                    PrimeFaces.current().executeScript("PF('addpnl').hide()");
                } else {
                    new contextMSG().error("Failed to Save crime_category: " + crime_category);
                }
                pst.close();
                lconn.close();

            }
        } catch (SQLException ex) {
            new contextMSG().error("Failed to Save crime_category: " + crime_category);
            Logger.getLogger(crimeCategoryBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SelectItem> getCrimeCategory() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        List<SelectItem> ret = new ArrayList<>();
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tCRIMECATEGORIES  order by id desc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    SelectItem si = new SelectItem();
                    si.setLabel(rs.getString("crime_category"));
                    si.setValue(rs.getInt("id"));
                    ret.add(si);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(crimeCategoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public String fetchCrimeCategoryColor(int id) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select crime_color from tCRIMECATEGORIES  where id=?");
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("crime_color");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(crimeCategoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
     public String fetchCrimeCategoryName(int id) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select crime_category from tCRIMECATEGORIES  where id=?");
                 pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("crime_category");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(crimeCategoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
