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
public class crimeController {

    public String fetchCrimeNameById(int id) {
        String ret = "";
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT crime_type FROM tCRIMETYPE where id=? ");
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getString("crime_type");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public List<SelectItem> getCrimes() {
        List<SelectItem> ret = new ArrayList<>();
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id,crime_type FROM tCRIMETYPE order by id asc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int value = rs.getInt("id");
                    String label = rs.getString("crime_type");
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

    public int fetchCrimeIdByName(String crimeName) {
        int ret = 0;
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("SELECT id FROM tCRIMETYPE where crime_type=? ");
                pst.setString(1, crimeName);
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
}
