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
public class cleanedEvidence implements Serializable {

    private int id = 0;
    private String crime_type = "";
     private int crime_type_id = 0;
    private String suspect_name = "";
    private String suspect_height = "";
    private String suspect_description = "";
    private String suspect_evidence = "";
    private String crime_description = "";
    private String crime_latitude = "";
    private String crime_longitude = "";
    private String crime_name_location = "";
    private String reportedBy = "";
    private String dateReceived = "";
    private String suspect_gender = "";
    private String dateCleaned = "";
    private String cleanedBy = "";
    private String Notes = "";

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

    public String getSuspect_gender() {
        return suspect_gender;
    }

    public void setSuspect_gender(String suspect_gender) {
        this.suspect_gender = suspect_gender;
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

}
