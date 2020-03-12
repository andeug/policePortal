package test;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import police.controller.policeController;
import police.database.Database;
import police.navigator.contextMSG;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mspace_developer
 */
public class test {

    // static final private String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static final private String ALPHABET = "123456789";

    final private Random rng = new SecureRandom();

    char randomChar() {
        return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
    }

    String randomUUID(int length, int spacing, char spacerChar) {
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while (length > 0) {
            if (spacer == spacing) {
                sb.append(spacerChar);
                spacer = 0;
            }
            length--;
            spacer++;
            sb.append(randomChar());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        int id = 0;
        String case_number = "CASE:" + id + ":" + new test().randomUUID(5, 1, '-').replace("-", "");
        // new test().InsertTestRecord();
        new test().UpdateTestRecord();
        System.out.println("uuid = " + case_number);
    }

    private String checkNumberGender(int myNumber) {
        String ret = "";
        boolean even = false;
        boolean prime = true;

        if (myNumber % 2 == 0) {
            even = true;
            prime = false;
        } else {
            for (int i = 3; i * i <= myNumber; i += 2) {
                if (myNumber % i == 0) {
                    prime = false;
                }
            }
        }

        if (even) {
            // System.out.println("The given number is Even.");
            ret = "M";
        } else {
            if (prime) {
                // System.out.println("The given number is Prime.");
                ret = "M";
            } else {
                ///System.out.println("The given number is Odd.");
                ret = "F";
            }
        }
        return ret;
    }

    private int checkNumberEvenOddPrime(int myNumber) {
        int ret = 0;
        boolean even = false;
        boolean prime = true;

        if (myNumber % 2 == 0) {
            even = true;
            prime = false;
        } else {
            for (int i = 3; i * i <= myNumber; i += 2) {
                if (myNumber % i == 0) {
                    prime = false;
                }
            }
        }

        if (even) {
            // System.out.println("The given number is Even.");
            ret = 2;
        } else {
            if (prime) {
                // System.out.println("The given number is Prime.");
                ret = 5;
            } else {
                ///System.out.println("The given number is Odd.");
                ret = 1;
            }
        }
        return ret;
    }

    public void UpdateTestRecord() {
        for (int i = 0; i < 150; i++) {
            String case_number = new test().randomUUID(5, 1, '-').replace("-", "");
            System.out.println("Case number==== " + case_number);
            try {
                Database obj = new Database();

                try (Connection lconn = obj.getConnectiondbPOLICE()) {
                    PreparedStatement pst;
                    ResultSet rs;
                    pst = lconn.prepareStatement("update  `tUPLOADEDEVIDENCE` "
                            + "set crime_longitude=? where id=?");
                    pst.setString(1, "36.8" + case_number);
                    pst.setInt(2, i);

                    int ret = pst.executeUpdate();
                    if (ret > 0) {
                        System.out.println(this.checkNumberGender(i) + "  Successful, Saved User: " + i);
                    } else {
                        System.out.println("Failed to Save User: " + i);
                    }
                    pst.close();
                    lconn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(test.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void InsertTestRecord() {
        String case_number = new test().randomUUID(5, 1, '-').replace("-", "");
        for (int i = 0; i < 150; i++) {
            try {
                Database obj = new Database();

                try (Connection lconn = obj.getConnectiondbPOLICE()) {
                    PreparedStatement pst;
                    ResultSet rs;
                    pst = lconn.prepareStatement("INSERT INTO `tUPLOADEDEVIDENCE`"
                            + "(crime_type,suspect_name,suspect_height,suspect_gender,suspect_description,suspect_evidence,crime_description,"
                            + "crime_latitude,crime_longitude,crime_name_location,reportedBy)"
                            + " VALUES ( ?,?,?,?, ?,? ,?, ?, ?, ?, ?)");
                    pst.setInt(1, this.checkNumberEvenOddPrime(i));
                    pst.setString(2, "Suspect Test" + i);
                    pst.setString(3, "5.3");
                    pst.setString(4, "M");
                    pst.setString(5, "black hair, brown teeth, walks with a limb");
                    pst.setString(6, "");
                    pst.setString(7, "Suspect took a Knife from the kitchen able  and stabbed the husband with it");
                    pst.setString(8, "-1.28333");
                    pst.setString(9, "36." + case_number);
                    pst.setString(10, "Nairobi Street");
                    pst.setString(11, "1");

                    int ret = pst.executeUpdate();
                    if (ret > 0) {
                        System.out.println("Successful, Saved User: " + i);
                    } else {
                        System.out.println("Failed to Save User: " + i);
                    }
                    pst.close();
                    lconn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(test.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
