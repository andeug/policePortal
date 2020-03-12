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
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;
import police.database.Database;
import police.login.checkLogin;
import police.model.department;
import police.navigator.contextMSG;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class departmentBean {

    /**
     * Creates a new instance of departmentBean
     */
    public departmentBean() {
    }
    private int id = 0;
    private String department = "";
    private String department_email = "";
    private String notes = "";
    private String dateCreated = "";
    private List<department> deptList = new ArrayList<>();
    private List<department> filterdDeptList;
    private List<department> SelectedDeptList;
    private department oneDepartment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment_email() {
        return department_email;
    }

    public void setDepartment_email(String department_email) {
        this.department_email = department_email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<department> getDeptList() {
        Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (klpd.containsKey("deptListList")) {
            deptList = (List<department>) klpd.get("deptListList");
        }
        return deptList;
    }

    public void setDeptList(List<department> deptList) {
        this.deptList = deptList;
    }

    public List<department> getFilterdDeptList() {
        return filterdDeptList;
    }

    public void setFilterdDeptList(List<department> filterdDeptList) {
        this.filterdDeptList = filterdDeptList;
    }

    public List<department> getSelectedDeptList() {
        return SelectedDeptList;
    }

    public void setSelectedDeptList(List<department> SelectedDeptList) {
        this.SelectedDeptList = SelectedDeptList;
    }

    public department getOneDepartment() {
        return oneDepartment;
    }

    public void setOneDepartment(department oneDepartment) {
        this.oneDepartment = oneDepartment;
    }

    @PostConstruct
    public void init() {
        fetchDepartments();
    }

    public void fetchDepartments() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        deptList = new ArrayList<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("deptListList");
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tDEPARTMENT  order by id desc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    department si = new department();
                    si.setDepartment(rs.getString("department"));
                    si.setDepartment_email(rs.getString("department_email"));
                    si.setNotes(rs.getString("notes"));
                    si.setDateCreated(rs.getString("dateCreated"));
                    si.setId(rs.getInt("id"));
                    deptList.add(si);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deptListList", deptList);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(departmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        new contextMSG().info("loaded " + deptList.size());
    }

    public void loadOneDepartment(department dept) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        if (dept != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("oneDept", dept);
            this.id = dept.getId();
            this.department = dept.getDepartment();
            this.department_email = dept.getDepartment_email();
            this.notes = dept.getNotes();
        } else {
            System.out.println("@Load one department failed");
        }
    }

    public void resetFields() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        id = 0;
        department = "";
        department_email = "";
        notes = "";

        FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().remove("oneDept");
    }

    public void updateDept() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                Map klpd = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                if (klpd.containsKey("oneDept")) {
                    department lus = (department) klpd.get("oneDept");
                    pst = lconn.prepareStatement("update tDEPARTMENT set department=?,department_email=?,notes=?"
                            + "where id=? ");
                    pst.setString(1, department);
                    pst.setString(2, department_email);
                    pst.setString(3, notes);
                    pst.setInt(4, lus.getId());
                    int ret = pst.executeUpdate();
                    if (ret > 0) {
                        new contextMSG().info("Successful, Updated department: " + department);
                        this.fetchDepartments();
                        this.resetFields();
                        PrimeFaces.current().executeScript("PF('edtpnl').hide()");
                    } else {
                        new contextMSG().error("Failed to Update department: " + department);
                    }
                    pst.close();
                    lconn.close();

                }
            }
        } catch (SQLException ex) {
            new contextMSG().error("Failed to Update department: " + department);
            Logger.getLogger(departmentBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertDepartment() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("insert into tDEPARTMENT (department,department_email,notes"
                        + ") values (?,?,?)");
                pst.setString(1, department);
                pst.setString(2, department_email);
                pst.setString(3, notes);
                int ret = pst.executeUpdate();
                if (ret > 0) {
                    new contextMSG().info("Successful, Saved department: " + department);
                    this.fetchDepartments();
                    this.resetFields();
                    PrimeFaces.current().executeScript("PF('addpnl').hide()");
                } else {
                    new contextMSG().error("Failed to Save department: " + department);
                }
                pst.close();
                lconn.close();

            }
        } catch (SQLException ex) {
            new contextMSG().error("Failed to Save department: " + department);
            Logger.getLogger(departmentBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SelectItem> getDepts() {
        List<SelectItem> ret = new ArrayList<>();
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id,department FROM tDEPARTMENT order by id asc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int value = rs.getInt("id");
                    String label = rs.getString("department");
                    SelectItem si = new SelectItem();
                    si.setLabel(label);
                    si.setValue(value);
                    ret.add(si);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(departmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public String fetchDepartmentNameById(int id) {
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT department FROM tDEPARTMENT where id=? ");
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("department");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(departmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
