/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package police.login;

/**
 *
 * @author Andrew
 */
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import police.database.Database;

@ManagedBean
@RequestScoped
public class logon {

    public String uname = "";
    public String passwd = "";
    public String confirmPasswd = "";
    public String loggedin = "";
    public String logout = "";
    public String fname = "";
    public String sname = "";
    public int id = 0;
    public String taskadmin = "";
    public String displayStyle = "";
    public String displayNorm = "";
    Database obj = new Database();

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

    public String getLogon() {
        String ret = "";
        String lpasswd = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("passwd");
        String luname = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("uname");
        System.out.println("Uname: " + luname);
        System.out.println("Passwd: " + lpasswd);
        if (luname == null || luname.length() == 0 || lpasswd == null || lpasswd.length() == 0) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Connection lconn = obj.getConnectiondbPOLICE();
        Statement st;
        ResultSet rs;
        if (lconn != null) {
            String sql = "SELECT id,username,password,firstname,surname,taskadmin FROM dbSMS.tUSER where username=\"" + luname + "\" and password=\"" + lpasswd + "\"";
            System.out.println(sql);
            try {
                st = lconn.createStatement();
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    try {
                        fname = rs.getString("firstname");
                        sname = rs.getString("surname");
                        this.id = rs.getInt("id");
                        this.taskadmin = rs.getString("taskadmin");
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uname", luname);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("passwd", lpasswd);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fname", fname);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sname", sname);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", String.valueOf(id));
                        String una = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uname");
                        String pas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("passwd");
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taskAdminValue", taskadmin);

                        System.out.println("Session uname: " + una + " passed: " + pas);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("service.jsf");
                    } catch (IOException ex) {
                        try {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
                        } catch (IOException ex1) {
                            ex1.printStackTrace();
                        }
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (SQLException ex) {

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
                } catch (IOException ex3) {
                    ex3.printStackTrace();
                }
                ex.printStackTrace();
            }
        }
        obj.close(lconn);
        return ret;
    }

}
