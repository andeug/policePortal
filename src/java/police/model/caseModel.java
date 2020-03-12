/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mspace_developer
 */
public class caseModel {

    private int id = 0;
    private String case_number = "";
    private List<suspectModel> suspectList = new ArrayList<>();
    private String reporting_officer = "";
    private String assigned_officer = "";
    private List<witnessModel> witnessList = new ArrayList<>();
    private List<victimModel> victimList = new ArrayList<>();
    private String reporting_officer_statement = "";
    private int case_status = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCase_number() {
        return case_number;
    }

    public void setCase_number(String case_number) {
        this.case_number = case_number;
    }

    public List<suspectModel> getSuspectList() {
        return suspectList;
    }

    public void setSuspectList(List<suspectModel> suspectList) {
        this.suspectList = suspectList;
    }

    public String getReporting_officer() {
        return reporting_officer;
    }

    public void setReporting_officer(String reporting_officer) {
        this.reporting_officer = reporting_officer;
    }

    public String getAssigned_officer() {
        return assigned_officer;
    }

    public void setAssigned_officer(String assigned_officer) {
        this.assigned_officer = assigned_officer;
    }

    public List<witnessModel> getWitnessList() {
        return witnessList;
    }

    public void setWitnessList(List<witnessModel> witnessList) {
        this.witnessList = witnessList;
    }

    public List<victimModel> getVictimList() {
        return victimList;
    }

    public void setVictimList(List<victimModel> victimList) {
        this.victimList = victimList;
    }

    public String getReporting_officer_statement() {
        return reporting_officer_statement;
    }

    public void setReporting_officer_statement(String reporting_officer_statement) {
        this.reporting_officer_statement = reporting_officer_statement;
    }

    public int getCase_status() {
        return case_status;
    }

    public void setCase_status(int case_status) {
        this.case_status = case_status;
    }
    
}
