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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import police.controller.crimeController;
import police.controller.mobileUserController;
import police.controller.policeController;
import police.database.Database;
import police.login.checkLogin;
import police.model.dashboardModel;
import police.model.evidenceModel;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
public class dashBoard {

    /**
     * Creates a new instance of dashBoard
     */
    public dashBoard() {
    }
    private BarChartModel genderBasedBar;
    private BarChartModel crimeTypeBasedBar;
    private BarChartModel caseBasedBar;
    private BarChartModel policeBasedBar;
    private BarChartModel policeCleanRateBar;
    private BarChartModel policeEmergenceResponseBar;

    public void updateOutputValues() {
    }

    @PostConstruct
    public void init() {
        createGenderBasedCrimeRate();
        createCrimeTypeBasedCrimeRate();
        createCaseBasedCrimeRate();
        createPoliceBasedCrimeRate();
        createPoliceCleanRate();
        createPoliceEMergencerspRate();
    }

    public BarChartModel getGenderBasedBar() {
        return genderBasedBar;
    }

    public BarChartModel getCrimeTypeBasedBar() {
        return crimeTypeBasedBar;
    }

    public BarChartModel getCaseBasedBar() {
        return caseBasedBar;
    }

    public BarChartModel getPoliceBasedBar() {
        return policeBasedBar;
    }

    public BarChartModel getPoliceCleanRateBar() {
        return policeCleanRateBar;
    }

    public BarChartModel getPoliceEmergenceResponseBar() {
        return policeEmergenceResponseBar;
    }

    private BarChartModel initGenderBaseBarModel() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Male");

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Female");
        try {
            String sql = "SELECT count(*) as net,crime_type,suspect_gender FROM tUPLOADEDEVIDENCE group by crime_type,suspect_gender";
            //  System.out.println(sql);       

            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String crimeType = new crimeController().fetchCrimeNameById(rs.getInt("crime_type"));
                    int total = rs.getInt("net");
                    String suspect_gender = rs.getString("suspect_gender");
                    if (suspect_gender.equalsIgnoreCase("M") || suspect_gender.equalsIgnoreCase("male")) {
                        boys.set(crimeType, total);
                    } else {
                        girls.set(crimeType, total);
                    }

                }
                model.addSeries(boys);
                model.addSeries(girls);
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    private BarChartModel initCrimeTypeBarModel() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        BarChartModel model = new BarChartModel();
        ChartSeries crime = new ChartSeries();
        crime.setLabel("Crime Type");

        try {
            String sql = "SELECT count(*) as net,crime_type FROM tUPLOADEDEVIDENCE group by crime_type";
            //System.out.println(sql);
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {

                    String crimeType = new crimeController().fetchCrimeNameById(rs.getInt("crime_type"));
                    int total = rs.getInt("net");
                    crime.set(crimeType, total);

                }
                model.addSeries(crime);
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    private BarChartModel initCaseBarModel() {

        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        BarChartModel model = new BarChartModel();
        ChartSeries case_stat = new ChartSeries();
        case_stat.setLabel("Case Status ");
        try {
            String sql = "SELECT count(*) as net,case_status FROM tCASE group by case_status";
            //System.out.println(sql);
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int total = rs.getInt("net");
                    int case_status = rs.getInt("case_status");
                    if (case_status == 0) {
                        case_stat.set("Open", total);
                    } else if (case_status == 1) {
                        case_stat.set("Closed", total);
                    }
                } else {
                    case_stat.set("Open", 0);
                    case_stat.set("Closed", 0);
                }
                model.addSeries(case_stat);
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    private BarChartModel initPoliceBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries open = new ChartSeries();
        open.setLabel("Open ");
        open.set("2004", 120);
        open.set("2005", 100);
        open.set("2006", 44);
        open.set("2007", 150);
        open.set("2008", 25);

        ChartSeries closed = new ChartSeries();
        closed.setLabel("Closed ");
        closed.set("2004", 52);
        closed.set("2005", 60);
        closed.set("2006", 110);
        closed.set("2007", 135);
        closed.set("2008", 120);

        model.addSeries(open);
        model.addSeries(closed);

        return model;
    }

    private BarChartModel initPoliceCleanBarModel() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        BarChartModel model = new BarChartModel();
        ChartSeries crime = new ChartSeries();
        crime.setLabel("Crime Type");

        try {
            String sql = "SELECT count(*) as net,cleanedBy FROM tCLEANEDEVIDENCE group by cleanedBy";
            //System.out.println(sql);
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {

                    String officer = new policeController().fetchOfficerNameById(rs.getInt("cleanedBy"));
                    int total = rs.getInt("net");
                    crime.set(officer, total);

                }
                model.addSeries(crime);
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    private BarChartModel initPoliceEmergencyBarModel() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        BarChartModel model = new BarChartModel();
        ChartSeries crime = new ChartSeries();
        crime.setLabel("Emergency");

        try {
            String sql = "SELECT count(*) as net,reporting_officer FROM tEMERGENCE group by reporting_officer";
            //System.out.println(sql);
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {

                    String officer = new policeController().fetchOfficerNameById(rs.getInt("reporting_officer"));
                    int total = rs.getInt("net");
                    crime.set(officer, total);

                }
                model.addSeries(crime);
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    private void createGenderBasedCrimeRate() {

        genderBasedBar = initGenderBaseBarModel();
        genderBasedBar.setTitle("Crime Rate Per Gender");
        genderBasedBar.setAnimate(true);
        genderBasedBar.setShowDatatip(true);
        genderBasedBar.setMouseoverHighlight(true);
        genderBasedBar.setLegendPosition("ne");
        Axis xAxis = genderBasedBar.getAxis(AxisType.X);
        xAxis.setLabel("Crime Type");

        Axis yAxis = genderBasedBar.getAxis(AxisType.Y);
        yAxis.setLabel("Crime Frequency");
    }

    private void createCrimeTypeBasedCrimeRate() {

        crimeTypeBasedBar = initCrimeTypeBarModel();
        crimeTypeBasedBar.setTitle("Crime Rate Per Crime Type");
        crimeTypeBasedBar.setAnimate(true);
        crimeTypeBasedBar.setShowDatatip(true);
        crimeTypeBasedBar.setMouseoverHighlight(true);
        crimeTypeBasedBar.setLegendPosition("ne");
        Axis xAxis = crimeTypeBasedBar.getAxis(AxisType.X);
        xAxis.setLabel("Crime Type");

        Axis yAxis = crimeTypeBasedBar.getAxis(AxisType.Y);
        yAxis.setLabel("Crime Frequency");
    }

    private void createCaseBasedCrimeRate() {

        caseBasedBar = initCaseBarModel();
        caseBasedBar.setTitle("Case Status");
        caseBasedBar.setAnimate(true);
        caseBasedBar.setShowDatatip(true);
        caseBasedBar.setMouseoverHighlight(true);
        caseBasedBar.setLegendPosition("ne");
        Axis xAxis = caseBasedBar.getAxis(AxisType.X);
        xAxis.setLabel("Status");

        Axis yAxis = caseBasedBar.getAxis(AxisType.Y);
        yAxis.setLabel("Case Frequency");
    }

    private void createPoliceBasedCrimeRate() {

        policeBasedBar = initPoliceBarModel();
        policeBasedBar.setTitle("Case Resolution Rate Per Officer");
        policeBasedBar.setAnimate(true);
        policeBasedBar.setShowDatatip(true);
        policeBasedBar.setMouseoverHighlight(true);
        policeBasedBar.setLegendPosition("ne");
        Axis xAxis = policeBasedBar.getAxis(AxisType.X);
        xAxis.setLabel("Status");

        Axis yAxis = policeBasedBar.getAxis(AxisType.Y);
        yAxis.setLabel("Case Frequency");
    }

    private void createPoliceCleanRate() {

        policeCleanRateBar = initPoliceCleanBarModel();
        policeCleanRateBar.setTitle("Clean Frequency Per Officer");
        policeCleanRateBar.setAnimate(true);
        policeCleanRateBar.setShowDatatip(true);
        policeCleanRateBar.setMouseoverHighlight(true);
        policeCleanRateBar.setLegendPosition("ne");
        Axis xAxis = policeCleanRateBar.getAxis(AxisType.X);
        xAxis.setLabel("Officer");

        Axis yAxis = policeCleanRateBar.getAxis(AxisType.Y);
        yAxis.setLabel("Clean Frequency");
    }

    private void createPoliceEMergencerspRate() {

        policeEmergenceResponseBar = initPoliceEmergencyBarModel();
        policeEmergenceResponseBar.setTitle("Emergence Response Frequency Per Officer");
        policeEmergenceResponseBar.setAnimate(true);
        policeEmergenceResponseBar.setShowDatatip(true);
        policeEmergenceResponseBar.setMouseoverHighlight(true);
        policeEmergenceResponseBar.setLegendPosition("ne");
        Axis xAxis = policeEmergenceResponseBar.getAxis(AxisType.X);
        xAxis.setLabel("Officer");

        Axis yAxis = policeEmergenceResponseBar.getAxis(AxisType.Y);
        yAxis.setLabel("Response Frequency");
    }

    public List<evidenceModel> getEvidence() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        List<evidenceModel> ret = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tUPLOADEDEVIDENCE order by id desc limit 5";
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    evidenceModel si = new evidenceModel();
                    si.setCrime_description(rs.getString("crime_description"));
                    si.setReportedBy(new mobileUserController().fetchMobileUserByID(rs.getInt("reportedBy")));
                    si.setDateReceived(rs.getString("dateReceived"));
                    si.setSuspect_name(rs.getString("suspect_name"));
                    si.setSuspect_description(rs.getString("suspect_description"));
                    si.setCrime_type(new crimeController().fetchCrimeNameById(rs.getInt("crime_type")));
                    ret.add(si);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public List<dashboardModel> getCrimeRate() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        List<dashboardModel> ret = new ArrayList<>();
        try {
            String sql = "SELECT count(*) as net,crime_type FROM tUPLOADEDEVIDENCE group by crime_type order by net desc limit 6 ";
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    dashboardModel si = new dashboardModel();
                    si.setCrime_total(rs.getInt("net"));
                    si.setCrime_color("#4890cc");
                    si.setCrime_type(new crimeController().fetchCrimeNameById(rs.getInt("crime_type")));
                    ret.add(si);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    public int getUnrespondedEmergence() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        int ret = 0;
        try {
            String sql = "SELECT count(*) as net FROM tEMERGENCE where status=0";
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getInt("net");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public int getRespondedEmergence() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        int ret = 0;
        try {
            String sql = "SELECT count(*) as net FROM tEMERGENCE where status=1";
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    ret = rs.getInt("net");
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
