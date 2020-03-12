/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.bean.Case;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class newCase {

    /**
     * Creates a new instance of newCase
     */
    public newCase() {
    }
    private String case_number;
    private int crime_type_id = 0;
    private int assignedOfficer_id = 0;
    private String officer_statement = "";
    private String user_home = "";
    private String user_statement = "";
    private String user_name = "";
    private String user_telno = "";
    private String user_address = "";
    private String user_emailAddress = "";
    private String user_id_no = "";
    private String user_nextKin_name = "";
    private String user_nextKin_telno = "";
    private String user_nextKin_idno = "";
    private String user_passport_no = "";

    public String getCase_number() {
        return case_number;
    }

    public void setCase_number(String case_number) {
        this.case_number = case_number;
    }

    public int getCrime_type_id() {
        return crime_type_id;
    }

    public void setCrime_type_id(int crime_type_id) {
        this.crime_type_id = crime_type_id;
    }

    public int getAssignedOfficer_id() {
        return assignedOfficer_id;
    }

    public void setAssignedOfficer_id(int assignedOfficer_id) {
        this.assignedOfficer_id = assignedOfficer_id;
    }

    public String getOfficer_statement() {
        return officer_statement;
    }

    public void setOfficer_statement(String officer_statement) {
        this.officer_statement = officer_statement;
    }

    public String getUser_home() {
        return user_home;
    }

    public void setUser_home(String user_home) {
        this.user_home = user_home;
    }

    public String getUser_statement() {
        return user_statement;
    }

    public void setUser_statement(String user_statement) {
        this.user_statement = user_statement;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_telno() {
        return user_telno;
    }

    public void setUser_telno(String user_telno) {
        this.user_telno = user_telno;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_emailAddress() {
        return user_emailAddress;
    }

    public void setUser_emailAddress(String user_emailAddress) {
        this.user_emailAddress = user_emailAddress;
    }

    public String getUser_id_no() {
        return user_id_no;
    }

    public void setUser_id_no(String user_id_no) {
        this.user_id_no = user_id_no;
    }

    public String getUser_nextKin_name() {
        return user_nextKin_name;
    }

    public void setUser_nextKin_name(String user_nextKin_name) {
        this.user_nextKin_name = user_nextKin_name;
    }

    public String getUser_nextKin_telno() {
        return user_nextKin_telno;
    }

    public void setUser_nextKin_telno(String user_nextKin_telno) {
        this.user_nextKin_telno = user_nextKin_telno;
    }

    public String getUser_nextKin_idno() {
        return user_nextKin_idno;
    }

    public void setUser_nextKin_idno(String user_nextKin_idno) {
        this.user_nextKin_idno = user_nextKin_idno;
    }

    public String getUser_passport_no() {
        return user_passport_no;
    }

    public void setUser_passport_no(String user_passport_no) {
        this.user_passport_no = user_passport_no;
    }

    public void resetFields(){
     officer_statement = "";
    user_home = "";
    user_statement = "";
    user_name = "";
    user_telno = "";
    user_address = "";
    user_emailAddress = "";
    user_id_no = "";
    user_nextKin_name = "";
    user_nextKin_telno = "";
    user_nextKin_idno = "";
    user_passport_no = "";
    }
}
