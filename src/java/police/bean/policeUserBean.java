/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.bean;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import police.controller.policeController;
import police.database.Database;
import police.login.checkLogin;
import police.model.policeUsers;
import police.navigator.contextMSG;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class policeUserBean {

    /**
     * Creates a new instance of policeUserBean
     */
    public policeUserBean() {
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
    private String passport_number = "0";
    private String reports_to = "";
    private String dept_no = "";
    private String policeCode = "";
    private String taskAdmin = "";
    private String job_no = "";
    private int job_no_id = 0;
    private int reports_to_id = 0;
    private int dept_no_id = 0;
    private List<policeUsers> policeUsers = new ArrayList<>();
    private List<policeUsers> filterdPoliceUsers;
    private List<policeUsers> SelectedPoliceUsers;
    private policeUsers onePoliceUsers;

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

    public List<policeUsers> getPoliceUsers() {
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("policeUsersList")) {
            policeUsers = (List<policeUsers>) klpd.get("policeUsersList");
        }
        return policeUsers;
    }

    public void setPoliceUsers(List<policeUsers> policeUsers) {
        this.policeUsers = policeUsers;
    }

    public List<policeUsers> getFilterdPoliceUsers() {
        return filterdPoliceUsers;
    }

    public void setFilterdPoliceUsers(List<policeUsers> filterdPoliceUsers) {
        this.filterdPoliceUsers = filterdPoliceUsers;
    }

    public List<policeUsers> getSelectedPoliceUsers() {
        return SelectedPoliceUsers;
    }

    public void setSelectedPoliceUsers(List<policeUsers> SelectedPoliceUsers) {
        this.SelectedPoliceUsers = SelectedPoliceUsers;
    }

    public policeUsers getOnePoliceUsers() {
        return onePoliceUsers;
    }

    public void setOnePoliceUsers(policeUsers onePoliceUsers) {
        this.onePoliceUsers = onePoliceUsers;
    }

    @PostConstruct
    public void init() {
        fetchPoliceUsers();
    }

    public void fetchPoliceUsers() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        policeUsers = new ArrayList<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("policeUsersList");
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tPOLICEUSERS order by id desc ");
                rs = pst.executeQuery();
                while (rs.next()) {
                    policeUsers si = new policeUsers();
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
                    si.setDept_no(new departmentBean().fetchDepartmentNameById(rs.getInt("dept_no")));
                    si.setReports_to(new policeController().fetchOfficerNameById(rs.getInt("reports_to")));
                    si.setTaskAdmin(rs.getString("taskAdmin"));
                    si.setPoliceCode(rs.getString("police_code"));
                    si.setReports_to_id(rs.getInt("reports_to"));
                    si.setDept_no_id(rs.getInt("dept_no"));
                    si.setJob_no_id(rs.getInt("job_no"));
                    si.setJob_no(new jobsBean().fetchJobTittleById(rs.getInt("job_no")));
                    policeUsers.add(si);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("policeUsersList", policeUsers);
                }
//                new contextMSG().info("loaded " + policeUsers.size());
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadOnePoliceUser(policeUsers user) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        if (user != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("onePoliceUser", user);
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
            this.taskAdmin = user.getTaskAdmin();
            this.reports_to = user.getReports_to();
            this.dept_no = user.getDept_no();
            this.policeCode = user.getPoliceCode();
            this.job_no = user.getJob_no();
            this.job_no_id = user.getJob_no_id();
            this.dept_no_id = user.getDept_no_id();
            this.reports_to_id = user.getReports_to_id();
        } else {
            System.out.println("@Load one Police User failed");
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
        taskAdmin = "";
        reports_to = "";
        dept_no = "";
        job_no = "";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("onePoliceUser");
    }

    public void updateUser() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                if (klpd.containsKey("onePoliceUser")) {
                    policeUsers lus = (policeUsers) klpd.get("onePoliceUser");
                    PreparedStatement pst;
                    pst = lconn.prepareStatement("update tPOLICEUSERS set username=?,password=?,email_address=?,"
                            + "contact_number=?,firstname=?,surname=?,middlename=?,id_number=?,passport_number=?"
                            + ",dept_no=?,reports_to=?,taskAdmin=?,job_no=? where id=? ");
                    pst.setString(1, username);
                    pst.setString(2, this.sha256(username + lus.getPassword()));
                    pst.setString(3, email_address);
                    pst.setString(4, contact_number);
                    pst.setString(5, firstname);
                    pst.setString(6, surname);
                    pst.setString(7, middlename);
                    pst.setString(8, id_number);
                    pst.setString(9, passport_number);
                    pst.setInt(10, dept_no_id);
                    pst.setInt(11, reports_to_id);
                    pst.setString(12, taskAdmin);
                    pst.setInt(13, job_no_id);
                    pst.setInt(14, lus.getId());
                    int ret = pst.executeUpdate();
                    if (ret > 0) {
                        new contextMSG().info("Successful, Updated User: " + username);
                        this.fetchPoliceUsers();
                        this.resetFields();
                        PrimeFaces.current().executeScript("PF('edtpnl').hide()");
                    } else {
                        new contextMSG().error("Failed to Update User: " + username);
                    }
                    pst.close();
                    lconn.close();
                }
            }
        } catch (SQLException ex) {
            new contextMSG().error("Failed to Update User: " + username);
            Logger.getLogger(policeUserBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String sha256(String text) {
        String ret = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xFF & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            String sha = hexString.toString();
            ret = sha;

            System.out.println("Encrypted " + text + " to Sha-256: " + sha);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void insertUser() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("insert into tPOLICEUSERS (username,password,email_address,"
                        + "contact_number,police_code,dept_no,reports_to,taskAdmin,firstname,surname,"
                        + "middlename,id_number,passport_number,job_no) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, username);
                pst.setString(2, this.sha256(username + password));
                pst.setString(3, email_address);
                pst.setString(4, contact_number);
                pst.setString(5, policeCode);
                pst.setString(6, dept_no);
                pst.setString(7, reports_to);
                pst.setString(8, taskAdmin);
                pst.setString(9, firstname);
                pst.setString(10, surname);
                pst.setString(11, middlename);
                pst.setString(12, id_number);
                pst.setString(13, passport_number);
                pst.setString(14, job_no);

                int ret = pst.executeUpdate();
                if (ret > 0) {
                    new contextMSG().info("Successful, Saved User: " + username);
                    this.fetchPoliceUsers();
                    this.resetFields();
                    PrimeFaces.current().executeScript("PF('addpnl').hide()");
                } else {
                    new contextMSG().error("Failed to Save User: " + username);
                }
                pst.close();
                lconn.close();

            }
        } catch (SQLException ex) {
            new contextMSG().error("Failed to Save User: " + username);
            Logger.getLogger(policeUserBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SelectItem> getStaffs() {
        List<SelectItem> ret = new ArrayList<>();
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id,username,firstname,surname FROM tPOLICEUSERS order by id asc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int value = rs.getInt("id");
                    String label = rs.getString("firstname") + " " + rs.getString("surname") + "(" + rs.getString("username") + ")";
                    SelectItem si = new SelectItem();
                    si.setLabel(label);
                    si.setValue(value);
                    ret.add(si);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public List<SelectItem> getAdminValue() {
        List<SelectItem> ret = new ArrayList<>();
        SelectItem si = new SelectItem();
        si.setLabel("Admin");
        si.setValue("Y");
        ret.add(si);
        SelectItem d = new SelectItem();
        d.setLabel("Standard User");
        d.setValue("N");
        ret.add(d);
        SelectItem d1 = new SelectItem();
        d1.setLabel("Disabled");
        d1.setValue("D");
        ret.add(d1);

        return ret;
    }
}
