/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import police.database.Database;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class policeController {

    public String fetchOfficerNameById(int userId) {
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT firstname,surname,username FROM tPOLICEUSERS where id=? ");
                pst.setInt(1, userId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("firstname") + " " + rs.getString("surname") + "(" + rs.getString("username") + ")";
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public List<SelectItem> getPoliceOfficers() {
        List<SelectItem> ret = new ArrayList<>();
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id,firstname,surname,username FROM tPOLICEUSERS order by id asc ");
                rs = pst.executeQuery();
                if (rs.next()) {
                    String label = rs.getString("firstname") + " " + rs.getString("surname") + "(" + rs.getString("username") + ")";
                    int value = rs.getInt("id");
                    SelectItem si = new SelectItem();
                    si.setLabel(label);
                    si.setValue(value);
                    ret.add(si);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public List<SelectItem> getCategories() {
        List<SelectItem> ret = new ArrayList<>();
        return ret;
    }

    public int fetchOfficerIdByUsername(String officerName) {
        int ret = 0;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                String rt = "";
                int loc = officerName.indexOf("(");
                int loc2 = officerName.indexOf(")");
                if (loc2 > 0 && loc > 0) {
                    officerName = officerName.substring((loc + 1), loc2);
//            System.out.println("new username fixed: " + username);
                }
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id FROM tPOLICEUSERS where username=? ");
                pst.setString(1, officerName);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getInt("id");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public int fetchOfficerIdByEmail(String officerEmail) {
        int ret = 0;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id FROM tPOLICEUSERS where email_address=? ");
                pst.setString(1, officerEmail);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getInt("id");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public String fetchOfficerNameByEmail(String officerEmail) {
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id FROM tPOLICEUSERS where email_address=? ");
                pst.setString(1, officerEmail);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("firstname") + " " + rs.getString("surname") + "(" + rs.getString("username") + ")";
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public String fetchOfficerNameByUsername(String officerUsername) {
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                int loc = officerUsername.indexOf("(");
                int loc2 = officerUsername.indexOf(")");
                if (loc2 > 0 && loc > 0) {
                    officerUsername = officerUsername.substring((loc + 1), loc2);
//            System.out.println("new username fixed: " + username);
                }
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id FROM tPOLICEUSERS where username=? ");
                pst.setString(1, officerUsername);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("firstname") + " " + rs.getString("surname") + "(" + rs.getString("username") + ")";
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public String fetchOfficerEmailByUsername(String officerUsername) {
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                int loc = officerUsername.indexOf("(");
                int loc2 = officerUsername.indexOf(")");
                if (loc2 > 0 && loc > 0) {
                    officerUsername = officerUsername.substring((loc + 1), loc2);
//            System.out.println("new username fixed: " + username);
                }
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT email_address FROM tPOLICEUSERS where username=? ");
                pst.setString(1, officerUsername);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("email_address");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public String fetchOfficerEmailById(int userId) {
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT email_address FROM tPOLICEUSERS where id=? ");
                pst.setInt(1, userId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("email_address");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

}
