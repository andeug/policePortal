/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.model;

/**
 *
 * @author mspace_developer
 */
public class evidenceEntries {

    private int id = 0;
    private String evidence_notes = "";
    private String officer = "";
    private String dateCreated = "";
    private String dateUpdate = "";
    private String case_number = "";
    private String evidence_number = "";
    private String evidence_attachent = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvidence_notes() {
        return evidence_notes;
    }

    public void setEvidence_notes(String evidence_notes) {
        this.evidence_notes = evidence_notes;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getCase_number() {
        return case_number;
    }

    public void setCase_number(String case_number) {
        this.case_number = case_number;
    }

    public String getEvidence_number() {
        return evidence_number;
    }

    public void setEvidence_number(String evidence_number) {
        this.evidence_number = evidence_number;
    }

    public String getEvidence_attachent() {
        return evidence_attachent;
    }

    public void setEvidence_attachent(String evidence_attachent) {
        this.evidence_attachent = evidence_attachent;
    }
    
}
