/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.login;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import police.database.Database;
import police.navigator.facePainter;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@SessionScoped
public class loginBean implements Serializable {

    public String uname = "";
    public String passwd = "";
    public String confirmPasswd = "";
    public String loggedin = "";
    public String logout = "";
    public String fname = "";
    public String sname = "";
    public int id = 0;
    public String taskadmin = "";
    public String errMsg = "";
    public String displayStyle = "";
    public String displayNorm = "";
    public String excelExportName = "";
    @ManagedProperty(value = "#{facePainter}")
    public facePainter facePainter;

    public facePainter getFacePainter() {
        return facePainter;
    }

    public void setFacePainter(facePainter facePainter) {
        this.facePainter = facePainter;
    }

    public void logOut() {
        System.out.println("Logging out..." + new java.util.Date());
        this.uname = "";
        this.passwd = "";
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("uname");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("passwd");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("fname");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sname");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("uid");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf?faces-redirect=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // return "Logged out successfully.";
    }

    public void homeDirectory() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("service.jsf?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setLoggedin(String loggedin) {
        this.loggedin = loggedin;
    }

    public String getConfirmPasswd() {
        return confirmPasswd;
    }

    public void setConfirmPasswd(String confirmPasswd) {
        this.confirmPasswd = confirmPasswd;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getDisplayStyle() {
        String t = "";
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("taskAdminValue")) {
            t = klpd.get("taskAdminValue").toString();
        }
        if (t.equalsIgnoreCase("Y")) {
            displayStyle = "display:block";
        } else {
            displayStyle = "display:none";
        }

        return displayStyle;
    }

    public void setDisplayStyle(String displayStyle) {
        this.displayStyle = displayStyle;
    }

    public String getExcelExportName() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat dnf = new SimpleDateFormat("MMddYYYYHhmm");
        String names = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uname").toString();
        excelExportName = names.concat(dnf.format(dt));
        return excelExportName;
    }

    public void setExcelExportName(String excelExportName) {
        this.excelExportName = excelExportName;
    }

    public String getDisplayNorm() {
        String t = "";
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("taskAdminValue")) {
            t = klpd.get("taskAdminValue").toString();
        }
        if (t.equalsIgnoreCase("Y")) {
            displayNorm = "display:none";
        } else {
            displayNorm = "display:block";
        }
        return displayNorm;
    }

    public void setDisplayNorm(String displayNorm) {
        this.displayNorm = displayNorm;
    }

    public void checkLoginType() {

        loginUser(uname, passwd);

    }

    public void loginUser(String username, String password) {

        System.out.println("Uname: " + username);
        System.out.println("Passwd: " + password);
        System.out.println("Getting login values as at " + new java.util.Date());
        if (username == null || username.length() == 0 || password == null || password.length() == 0) {
            errMsg = "";
        }
        Database obj = new Database();
        Connection lconn = obj.getConnectiondbPOLICE();
        PreparedStatement pst;
        ResultSet rs;
        if (lconn != null) {
            String sql = "SELECT * FROM tPOLICEUSERS where username=\"" + username + "\" and password=\"" + password + "\"";
            System.out.println(sql);

            try {
                password = this.sha256(username + password);
                pst = lconn.prepareStatement("SELECT * FROM tPOLICEUSERS where username=? and password=?");
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    try {
                        fname = rs.getString("firstname");
                        sname = rs.getString("surname");
                        this.id = rs.getInt("id");
                        System.out.println("task user is: " + id);
                        this.taskadmin = rs.getString("taskadmin");
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uname", username);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("passwd", password);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fname", fname);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sname", sname);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", String.valueOf(id));
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taskAdminValue", taskadmin);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("department_no", rs.getInt("dept_no"));

                        String una = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uname");
                        String pas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("passwd");

                        System.out.println("Session uname: " + una + " passed: " + pas);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("police.jsf?faces-redirect=true");
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Successful", "Welcome: " + fname + " " + sname));
                    } catch (IOException ex) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to login. Please try again."));
                        errMsg = "Failed to login. Please try again.";
                        Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Username or Password"));
                    errMsg = "Invalid Username or Password.";
                }
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Failed to login. Please try again."));
                errMsg = "Failed to login. Please try again.";
                Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        obj.close(lconn);
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
}
