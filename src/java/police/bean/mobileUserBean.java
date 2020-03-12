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
import police.database.Database;
import police.login.checkLogin;
import police.model.cleanedEvidence;
import police.model.mobileUsers;
import police.navigator.contextMSG;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class mobileUserBean {

    /**
     * Creates a new instance of mobileUserBean
     */
    public mobileUserBean() {
    }
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
    private List<mobileUsers> mobileUsers = new ArrayList<>();
    private List<mobileUsers> filterdMobileUsers;
    private List<mobileUsers> SelectedMobileUsers;
    private mobileUsers oneMobileUsers;

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

    public List<mobileUsers> getMobileUsers() {
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("mobileUsersList")) {
            mobileUsers = (List<mobileUsers>) klpd.get("mobileUsersList");
        }
        return mobileUsers;
    }

    public void setMobileUsers(List<mobileUsers> mobileUsers) {
        this.mobileUsers = mobileUsers;
    }

    public List<mobileUsers> getFilterdMobileUsers() {
        return filterdMobileUsers;
    }

    public void setFilterdMobileUsers(List<mobileUsers> filterdMobileUsers) {
        this.filterdMobileUsers = filterdMobileUsers;
    }

    public List<mobileUsers> getSelectedMobileUsers() {
        return SelectedMobileUsers;
    }

    public void setSelectedMobileUsers(List<mobileUsers> SelectedMobileUsers) {
        this.SelectedMobileUsers = SelectedMobileUsers;
    }

    public mobileUsers getOneMobileUsers() {
        return oneMobileUsers;
    }

    public void setOneMobileUsers(mobileUsers oneMobileUsers) {
        this.oneMobileUsers = oneMobileUsers;
    }

    @PostConstruct
    public void init() {
        fetchMobileUsers();
    }

    public void fetchMobileUsers() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        mobileUsers = new ArrayList<>();
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("mobileUsersList");
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tMOBILEUSERS  order by id desc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    mobileUsers si = new mobileUsers();
                    si.setContact_number(rs.getString("contact_number"));
                    si.setEmail_address(rs.getString("email_address"));
                    si.setEnd_date(rs.getString("end_date"));
                    si.setFirstname(rs.getString("firstname"));
                    si.setId(rs.getInt("id"));
                    si.setId_number(rs.getString("id_number"));
                    si.setLogged_in_time(rs.getString("logged_in_time"));
                    si.setMiddlename(rs.getString("middlename"));
                    si.setPassport_number(rs.getString("passport_number"));
                    si.setPassword(rs.getString("password"));
                    si.setStart_date(rs.getString("start_date"));
                    si.setSurname(rs.getString("surname"));
                    si.setUsername(rs.getString("username"));
                    mobileUsers.add(si);
                     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mobileUsersList",mobileUsers);
                }
//                new contextMSG().info("loaded " + mobileUsers.size());
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(mobileUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadOneMobileUser(mobileUsers user) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        if (user != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("oneMobileUser", user);
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.email_address = user.getEmail_address();
            this.contact_number = user.getContact_number();
            this.logged_in_time = user.getLogged_in_time();
            this.start_date = user.getStart_date();
            this.end_date = user.getEnd_date();
            this.firstname = user.getFirstname();
            this.surname = user.getSurname();
            this.middlename = user.getMiddlename();
            this.id_number = user.getId_number();
            this.passport_number = user.getPassport_number();
        } else {
            System.out.println("@Load one mobile User failed");
        }
    }

    public void resetFields() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        id = 0;
        username = "";
        password = "";
        email_address = "";
        contact_number = "";
        logged_in_time = "";
        start_date = "";
        end_date = "";
        firstname = "";
        surname = "";
        middlename = "";
        id_number = "";
        passport_number = "";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("oneMobileUser");
    }

    public void updateUser() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("update tMOBILEUSERS set username=?,password=?,email_address=?,"
                        + "contact_number=?,firstname=?,surname=?,middlename=?,id_number=?,passport_number=? where id=? ");
                pst.setString(1, username);
                pst.setString(2, password);
                pst.setString(3, email_address);
                pst.setString(4, contact_number);
                pst.setString(5, firstname);
                pst.setString(6, surname);
                pst.setString(7, middlename);
                pst.setString(8, id_number);
                pst.setString(9, passport_number);
                pst.setInt(10, id);
                int ret = pst.executeUpdate();
                if (ret > 0) {
                    new contextMSG().info("Successful, Updated User: " + username);
                    this.resetFields();
                } else {
                    new contextMSG().error("Failed to Update User: " + username);
                }
                pst.close();
                lconn.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(mobileUserBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
