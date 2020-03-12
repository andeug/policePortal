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
public class emergency implements Serializable {

    private int id = 0;
    private String crime_latitude = "";
    private String crime_longitude = "";
    private String crime_name_location = "";
    private String emergency = "";
    private int status = 0;
    private String responding_officer_name="";
    private int responding_officer=0;
    private String response_alert_by="";

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

    public String getResponding_officer_name() {
        return responding_officer_name;
    }

    public void setResponding_officer_name(String responding_officer_name) {
        this.responding_officer_name = responding_officer_name;
    }

    public int getResponding_officer() {
        return responding_officer;
    }

    public void setResponding_officer(int responding_officer) {
        this.responding_officer = responding_officer;
    }

    public String getResponse_alert_by() {
        return response_alert_by;
    }

    public void setResponse_alert_by(String response_alert_by) {
        this.response_alert_by = response_alert_by;
    }

}
