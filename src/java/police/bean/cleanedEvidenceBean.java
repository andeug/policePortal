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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import police.controller.crimeController;
import police.controller.mobileUserController;
import police.controller.policeController;
import police.database.Database;
import police.login.checkLogin;
import police.model.cleanedEvidence;
import police.navigator.contextMSG;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@ViewScoped
public class cleanedEvidenceBean {

    /**
     * Creates a new instance of cleanedEvidence
     */
    public cleanedEvidenceBean() {
    }
    private int id = 0;
    private String crime_type = "";
    private int crime_type_id = 0;
    private String suspect_name = "";
    private String suspect_gender = "";
    private String suspect_height = "";
    private String suspect_description = "";
    private String suspect_evidence = "";
    private String crime_description = "";
    private String crime_latitude = "";
    private String crime_longitude = "";
    private String crime_name_location = "";
    private String reportedBy = "";
    private String dateReceived = "";
    private String dateCleaned = "";
    private String cleanedBy = "";
    private String Notes = "";
    private List<cleanedEvidence> cleanedEvidence = new ArrayList<>();
    private List<cleanedEvidence> filteredCleanedEvidence;
    private List<cleanedEvidence> SelectedCleanedEvidence;
    private cleanedEvidence oneCleanedEvidence;
    private java.text.SimpleDateFormat dformat1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
    private Date sdate = new Date();
    private Date edate = new Date();

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

    public String getSuspect_name() {
        return suspect_name;
    }

    public void setSuspect_name(String suspect_name) {
        this.suspect_name = suspect_name;
    }

    public String getSuspect_gender() {
        return suspect_gender;
    }

    public void setSuspect_gender(String suspect_gender) {
        this.suspect_gender = suspect_gender;
    }

    public String getSuspect_height() {
        return suspect_height;
    }

    public void setSuspect_height(String suspect_height) {
        this.suspect_height = suspect_height;
    }

    public String getSuspect_description() {
        return suspect_description;
    }

    public void setSuspect_description(String suspect_description) {
        this.suspect_description = suspect_description;
    }

    public String getSuspect_evidence() {
        return suspect_evidence;
    }

    public void setSuspect_evidence(String suspect_evidence) {
        this.suspect_evidence = suspect_evidence;
    }

    public String getCrime_description() {
        return crime_description;
    }

    public void setCrime_description(String crime_description) {
        this.crime_description = crime_description;
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

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getDateCleaned() {
        return dateCleaned;
    }

    public void setDateCleaned(String dateCleaned) {
        this.dateCleaned = dateCleaned;
    }

    public String getCleanedBy() {
        return cleanedBy;
    }

    public void setCleanedBy(String cleanedBy) {
        this.cleanedBy = cleanedBy;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public int getCrime_type_id() {
        return crime_type_id;
    }

    public void setCrime_type_id(int crime_type_id) {
        this.crime_type_id = crime_type_id;
    }

    public List<cleanedEvidence> getCleanedEvidence() {
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("cleanedEvidenceList")) {
            cleanedEvidence = (List<cleanedEvidence>) klpd.get("cleanedEvidenceList");
        }
        return cleanedEvidence;
    }

    public void setCleanedEvidence(List<cleanedEvidence> cleanedEvidence) {
        this.cleanedEvidence = cleanedEvidence;
    }

    public List<cleanedEvidence> getFilteredCleanedEvidence() {
        return filteredCleanedEvidence;
    }

    public void setFilteredCleanedEvidence(List<cleanedEvidence> filteredCleanedEvidence) {
        this.filteredCleanedEvidence = filteredCleanedEvidence;
    }

    public List<cleanedEvidence> getSelectedCleanedEvidence() {
        return SelectedCleanedEvidence;
    }

    public void setSelectedCleanedEvidence(List<cleanedEvidence> SelectedCleanedEvidence) {
        this.SelectedCleanedEvidence = SelectedCleanedEvidence;
    }

    public cleanedEvidence getOneCleanedEvidence() {
        return oneCleanedEvidence;
    }

    public void setOneCleanedEvidence(cleanedEvidence oneCleanedEvidence) {
        this.oneCleanedEvidence = oneCleanedEvidence;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    @PostConstruct
    public void init() {
        fetchCleanedEvidence();
    }

    public void fetchCleanedEvidence() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        cleanedEvidence = new ArrayList<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("cleanedEvidenceList");

        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tCLEANEDEVIDENCE where (dateReceived between '" + formatDate(sdate, 1) + "' and '" + formatDate(edate, 2) + "') order by id desc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    cleanedEvidence si = new cleanedEvidence();
                    si.setCrime_type(new crimeController().fetchCrimeNameById(rs.getInt("crime_type")));
                    si.setId(rs.getInt("id"));
                    si.setCrime_description(rs.getString("crime_description"));
                    si.setCrime_latitude(rs.getString("crime_latitude"));
                    si.setCrime_longitude(rs.getString("crime_longitude"));
                    si.setCrime_name_location(rs.getString("crime_name_location"));
                    si.setDateReceived(rs.getString("dateReceived"));
                    si.setReportedBy(new mobileUserController().fetchMobileUserByID(rs.getInt("reportedBy")));
                    si.setSuspect_description(rs.getString("suspect_description"));
                    si.setSuspect_evidence(rs.getString("suspect_evidence"));
                    si.setSuspect_gender(rs.getString("suspect_gender"));
                    si.setSuspect_height(rs.getString("suspect_height"));
                    si.setSuspect_name(rs.getString("suspect_name"));
                    si.setCleanedBy(new policeController().fetchOfficerNameById(rs.getInt("cleanedBy")));
                    si.setDateCleaned(rs.getString("dateCleaned"));
                    si.setNotes(rs.getString("Notes"));
                    si.setCrime_type_id(rs.getInt("crime_type"));
                    cleanedEvidence.add(si);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cleanedEvidenceList", cleanedEvidence);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(cleanedEvidenceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadOneCleanedEvidence(cleanedEvidence evidence) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        if (evidence != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("oneCleanedEvidence", evidence);
            this.id = evidence.getId();
            this.crime_type = evidence.getCrime_type();
            this.suspect_name = evidence.getSuspect_name();
            this.suspect_height = evidence.getSuspect_height();
            this.suspect_description = evidence.getSuspect_description();
            this.suspect_evidence = evidence.getSuspect_evidence();
            this.crime_description = evidence.getCrime_description();
            this.crime_latitude = evidence.getCrime_latitude();
            this.crime_longitude = evidence.getCrime_longitude();
            this.crime_name_location = evidence.getCrime_name_location();
            this.reportedBy = evidence.getReportedBy();
            this.dateReceived = evidence.getDateReceived();
            this.suspect_gender = evidence.getSuspect_gender();
            this.cleanedBy = evidence.getCleanedBy();
            this.dateCleaned = evidence.getDateCleaned();
            this.Notes = evidence.getNotes();
            this.crime_type_id = evidence.getCrime_type_id();
        } else {
            System.out.println("@Load oneCleanedEvidence failed");
        }
    }

    public void resetFields() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        id = 0;
        crime_type = "";
        suspect_name = "";
        suspect_height = "";
        suspect_gender = "";
        suspect_description = "";
        suspect_evidence = "";
        crime_description = "";
        crime_latitude = "";
        crime_longitude = "";
        crime_name_location = "";
        reportedBy = "";
        dateReceived = "";
        cleanedBy = "";
        dateCleaned = "";
        Notes = "";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("oneCleanedEvidence");
    }

    public void updateCleanedEvidence() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        String cleanedBy = "";
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("uid")) {
            cleanedBy = klpd.get("uid").toString();
        }
        if (klpd.containsKey("oneCleanedEvidence")) {
            cleanedEvidence lus = (cleanedEvidence) klpd.get("oneCleanedEvidence");
            try {
                Database obj = new Database();
                try (Connection lconn = obj.getConnectiondbPOLICE()) {
                    PreparedStatement pst;
                    pst = lconn.prepareStatement("update tCLEANEDEVIDENCE  set crime_type=?,suspect_name=?,suspect_height=?,suspect_gender=?,"
                            + "suspect_description=?,suspect_evidence=?,crime_description=?,crime_latitude=?,crime_longitude=?,crime_name_location=?,"
                            + "reportedBy=?,dateReceived=?,cleanedBy=?,Notes=? where id=?");
                    pst.setInt(1, crime_type_id);
                    pst.setString(2, suspect_name);
                    pst.setString(3, suspect_height);
                    pst.setString(4, suspect_gender);
                    pst.setString(5, suspect_description);
                    pst.setString(6, suspect_evidence);
                    pst.setString(7, crime_description);
                    pst.setString(8, crime_latitude);
                    pst.setString(9, crime_longitude);
                    pst.setString(10, crime_name_location);
                    pst.setString(11, reportedBy);
                    pst.setString(12, dateReceived);
                    pst.setString(13, cleanedBy);
                    pst.setString(14, Notes);
                    pst.setInt(15, lus.getId());
                    int ret = pst.executeUpdate();

                    if (ret > 0) {
                        new contextMSG().info("Successful, Updated evidence of crime_type: " + crime_type);
                        this.fetchCleanedEvidence();
                        this.resetFields();
                        PrimeFaces.current().executeScript("PF('viewpnl').hide()");
                    } else {
                        new contextMSG().error("Failed to Update evidence of crime_type: " + crime_type);
                    }
                    pst.close();
                    lconn.close();

                }
            } catch (SQLException ex) {
                new contextMSG().error("Failed to Update evidence of crime_type: " + crime_type);
                Logger.getLogger(cleanedEvidenceBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String formatDate(Date dt, int type)//1=startdate 2=enddate
    {
        String date = "";
        if (dt == null) {
            dt = new Date();
            System.out.println("Null date received.");
        }
        if (type == 1) {
            try {
                date = dformat1.format(dt) + " 00:00:00";
            } catch (Exception e) {
                Logger.getLogger(cleanedEvidenceBean.class.getName()).log(Level.SEVERE, null, e);
                date = dformat1.format(new Date() + " 00:00:00");
            }
        } else {
            try {
                date = dformat1.format(dt) + " 23:59:59";
            } catch (Exception e) {
                Logger.getLogger(cleanedEvidenceBean.class.getName()).log(Level.SEVERE, null, e);
                date = dformat1.format(new Date() + " 23:59:59");
            }
        }
        return date;
    }
}
