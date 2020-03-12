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
public class policeUsers implements Serializable {

    private int id = 0;
    private String username = "";
    private String password = "";
    private String email_address = "";
    private String contact_number = "";
    private String logged_in_time = "";
    private String start_date = "";
    private String end_date = "";
    private String firstname = "";
    private String surname = "";
    private String middlename = "";
    private String id_number = "";
    private String passport_number = "";
    private String reports_to = "";
    private String dept_no = "";
    private String policeCode = "";
    private String taskAdmin = "";
    private String job_no = "";
    private int job_no_id = 0;
    private int reports_to_id = 0;
    private int dept_no_id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getLogged_in_time() {
        return logged_in_time;
    }

    public void setLogged_in_time(String logged_in_time) {
        this.logged_in_time = logged_in_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getReports_to() {
        return reports_to;
    }

    public void setReports_to(String reports_to) {
        this.reports_to = reports_to;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getPoliceCode() {
        return policeCode;
    }

    public void setPoliceCode(String policeCode) {
        this.policeCode = policeCode;
    }

    public String getTaskAdmin() {
        return taskAdmin;
    }

    public void setTaskAdmin(String taskAdmin) {
        this.taskAdmin = taskAdmin;
    }

    public String getJob_no() {
        return job_no;
    }

    public void setJob_no(String job_no) {
        this.job_no = job_no;
    }

    public int getJob_no_id() {
        return job_no_id;
    }

    public void setJob_no_id(int job_no_id) {
        this.job_no_id = job_no_id;
    }

    public int getReports_to_id() {
        return reports_to_id;
    }

    public void setReports_to_id(int reports_to_id) {
        this.reports_to_id = reports_to_id;
    }

    public int getDept_no_id() {
        return dept_no_id;
    }

    public void setDept_no_id(int dept_no_id) {
        this.dept_no_id = dept_no_id;
    }

}
