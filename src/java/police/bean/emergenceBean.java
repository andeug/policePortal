/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.bean;

import java.io.Serializable;
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
import police.controller.policeController;
import police.database.Database;
import police.login.checkLogin;
import police.model.emergency;
import police.model.evidenceModel;
import police.navigator.contextMSG;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class emergenceBean implements Serializable {

    private int id = 0;
    private String crime_latitude = "";
    private String crime_longitude = "";
    private String crime_name_location = "";
    private String emergency = "";
    private int status = 0;
    private int responding_officer = 0;
    private String response_alert_by = "";
    private List<emergency> emergencyList = new ArrayList<>();
    private List<emergency> emergencyOneList = new ArrayList<>();
    private List<emergency> emergencyListFilterd;
    private List<emergency> emergencyListSelected;
    private emergency oneEmergence;

    /**
     * Creates a new instance of emergenceBean
     */
    public emergenceBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrime_latitude() {
        return crime_latitude;
    }

    public void setCrime_latitude(String crime_latitude) {
        this.crime_latitude = crime_latitude;
    }

    public String getCrime_longitude() {
        return crime_longitude;
    }

    public void setCrime_longitude(String crime_longitude) {
        this.crime_longitude = crime_longitude;
    }

    public String getCrime_name_location() {
        return crime_name_location;
    }

    public void setCrime_name_location(String crime_name_location) {
        this.crime_name_location = crime_name_location;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponse_alert_by() {
        return response_alert_by;
    }

    public void setResponse_alert_by(String response_alert_by) {
        this.response_alert_by = response_alert_by;
    }

    public List<emergency> getEmergencyList() {
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("emergencyList")) {
            emergencyList = (List<emergency>) klpd.get("emergencyList");
        }
        return emergencyList;
    }

    public void setEmergencyList(List<emergency> emergencyList) {
        this.emergencyList = emergencyList;
    }

    public List<emergency> getEmergencyListFilterd() {
        return emergencyListFilterd;
    }

    public void setEmergencyListFilterd(List<emergency> emergencyListFilterd) {
        this.emergencyListFilterd = emergencyListFilterd;
    }

    public List<emergency> getEmergencyListSelected() {
        return emergencyListSelected;
    }

    public void setEmergencyListSelected(List<emergency> emergencyListSelected) {
        this.emergencyListSelected = emergencyListSelected;
    }

    public emergency getOneEmergence() {
        return oneEmergence;
    }

    public void setOneEmergence(emergency oneEmergence) {
        this.oneEmergence = oneEmergence;
    }

    public List<emergency> getEmergencyOneList() {
        fetchOneEmergence();
        return emergencyOneList;
    }

    public void setEmergencyOneList(List<emergency> emergencyOneList) {
        this.emergencyOneList = emergencyOneList;
    }

    public int getResponding_officer() {
        return responding_officer;
    }

    public void setResponding_officer(int responding_officer) {
        this.responding_officer = responding_officer;
    }

    @PostConstruct
    public void init() {
        fetchEmergence();
    }

    public void fetchEmergence() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        emergencyList = new ArrayList<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("emergencyList");
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tEMERGENCE order by id desc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    emergency si = new emergency();
                    si.setCrime_latitude(rs.getString("crime_latitude"));
                    si.setCrime_longitude(rs.getString("crime_longitude"));
                    si.setCrime_name_location(rs.getString("crime_name_location"));
                    si.setEmergency(rs.getString("emergency"));
                    si.setStatus(rs.getInt("status"));
                    si.setId(rs.getInt("id"));
                    si.setResponding_officer(rs.getInt("reporting_officer"));
                    si.setResponding_officer_name(new policeController().fetchOfficerNameById(rs.getInt("reporting_officer")));
                    si.setResponse_alert_by(new policeController().fetchOfficerNameById(rs.getInt("response_by")));
                    emergencyList.add(si);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("emergencyList", emergencyList);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(evidenceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fetchOneEmergence() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        emergencyOneList = new ArrayList<>();
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tEMERGENCE where status=0 limit 5");
                rs = pst.executeQuery();
                while (rs.next()) {
                    emergency si = new emergency();
                    si.setCrime_latitude(rs.getString("crime_latitude"));
                    si.setCrime_longitude(rs.getString("crime_longitude"));
                    si.setCrime_name_location(rs.getString("crime_name_location"));
                    si.setEmergency(rs.getString("emergency"));
                    si.setStatus(rs.getInt("status"));
                    si.setId(rs.getInt("id"));
                    si.setResponding_officer(rs.getInt("reporting_officer"));
                    si.setResponding_officer_name(new policeController().fetchOfficerNameById(rs.getInt("reporting_officer")));
                    emergencyOneList.add(si);
                }
                pst.close();
                lconn.close();
                if (emergencyOneList.size() > 0) {
                    //System.out.println("emergence");
                    PrimeFaces.current().executeScript("PF('viewpnlmain').show()");
                }else{
                PrimeFaces.current().executeScript("PF('viewpnlmain').hide()");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(evidenceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void oneEvidence(emergency em) {
//        System.out.println("@Load one Emergency Checking loggged in");
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
//        System.out.println("@Load one Emergency After loginee id..." + em);
        if (em != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("oneEmergency", em);
            this.id = em.getId();
            this.crime_latitude = em.getCrime_latitude();
            this.crime_longitude = em.getCrime_longitude();
            this.crime_name_location = em.getCrime_name_location();
            this.emergency = em.getEmergency();
            this.status = em.getStatus();
            this.responding_officer = em.getResponding_officer();

            System.out.println("@Load one Emergency Success");
        } else {
            System.out.println("@Load one Emergency failed");
        }
    }

    public void resetFields() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        id = 0;
        crime_latitude = "";
        crime_longitude = "";
        crime_name_location = "";
        this.status = 0;
        this.emergency = "";
        responding_officer = 0;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("oneEmergency");
    }

    public void updateEmergence() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        String response_by = "";
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("uid")) {
            response_by = klpd.get("uid").toString();
        }
        if (klpd.containsKey("oneEmergency")) {
            emergency lus = (emergency) klpd.get("oneEmergency");
            try {
                Database obj = new Database();
                try (Connection lconn = obj.getConnectiondbPOLICE()) {
                    PreparedStatement pst;
                    pst = lconn.prepareStatement("update tEMERGENCE set status=?,response_by=?,reporting_officer=? where id=?");
                    pst.setInt(1, 1);
                    pst.setString(2, response_by);
                    pst.setInt(3, responding_officer);
                    pst.setInt(4, lus.getId());
                    int ret = pst.executeUpdate();
                    if (ret > 0) {
                        new contextMSG().info("Successful, Response to Emergence: ");
                        this.resetFields();
                        PrimeFaces.current().executeScript("PF('viewemrpnl').hide()");
                        PrimeFaces.current().executeScript("PF('viewpnlmain').hide()");
                    } else {
                        new contextMSG().error("Failed to Save Response details: ");
                    }
                    pst.close();
                    lconn.close();

                }
            } catch (SQLException ex) {
                new contextMSG().error("Failed to Save Response details: ");
                Logger.getLogger(evidenceBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateListEmergence() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        String response_by = "";
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("uid")) {
            response_by = klpd.get("uid").toString();
        }
        if (klpd.containsKey("oneEmergency")) {
            emergency lus = (emergency) klpd.get("oneEmergency");
            try {
                Database obj = new Database();
                try (Connection lconn = obj.getConnectiondbPOLICE()) {
                    PreparedStatement pst;
                    pst = lconn.prepareStatement("update tEMERGENCE set status=?,response_by=?,reporting_officer=? where id=?");
                    pst.setInt(1, 1);
                    pst.setString(2, response_by);
                    pst.setInt(3, responding_officer);
                    pst.setInt(4, lus.getId());
                    int ret = pst.executeUpdate();
                    if (ret > 0) {
                        new contextMSG().info("Successful, Response to Emergence: ");
                        this.resetFields();
                        PrimeFaces.current().executeScript("PF('viewemrpnl').hide()");
                    } else {
                        new contextMSG().error("Failed to Save Response details: ");
                    }
                    pst.close();
                    lconn.close();

                }
            } catch (SQLException ex) {
                new contextMSG().error("Failed to Save Response details: ");
                Logger.getLogger(evidenceBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
