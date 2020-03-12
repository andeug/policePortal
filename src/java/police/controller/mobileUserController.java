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
import java.util.logging.Level;
import java.util.logging.Logger;
import police.database.Database;

/**
 *
 * @author mspace_developer
 */
public class mobileUserController {
       

      public String fetchMobileUserByID(int id) {
        String ret = "";
        try {
             Database obj = new Database();
            Connection lconn = obj.getConnectiondbPOLICE();
            PreparedStatement pst;
            ResultSet rs;
            pst = lconn.prepareStatement("SELECT firstname,surname,username FROM tMOBILEUSERS where id=? ");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                ret = rs.getString("firstname") + " " + rs.getString("surname") + "(" + rs.getString("username") + ")";
            }
             pst.close();
            lconn.close();
        } catch (SQLException ex) {
            Logger.getLogger(policeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    } 
}
