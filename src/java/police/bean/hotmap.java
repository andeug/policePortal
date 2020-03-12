/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import police.controller.crimeController;
import police.controller.mobileUserController;
import police.database.Database;
import police.login.checkLogin;
import police.model.crimeCategory;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
@RequestScoped
public class hotmap implements Serializable {

    /**
     * Creates a new instance of hotmap
     */
    public hotmap() {
    }
    private MapModel simpleModel;
    private Marker marker;
    private MapModel circleModel;

    public Marker getMarker() {
        return marker;
    }

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
        createHotmap();
        circularHotmap();
    }

    public void createHotmap() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        try {
            String sql = "SELECT * FROM tUPLOADEDEVIDENCE";
            System.out.println(sql);
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String longitude = rs.getString("crime_longitude");
                    String latitude = rs.getString("crime_latitude");
                    Double Lat = Double.parseDouble(latitude);
                    Double Long = Double.parseDouble(longitude);
                    String crimeType = new crimeController().fetchCrimeNameById(rs.getInt("crime_type"));
                    String reportedBy = new mobileUserController().fetchMobileUserByID(rs.getInt("reportedBy"));
                    String suspect_name = rs.getString("suspect_name");
                    LatLng coord1 = new LatLng(Lat, Long);
                    simpleModel.addOverlay(new Marker(coord1, crimeType + ": reportedBy: " + reportedBy + " Suspect: " + suspect_name));
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void onStateChange(StateChangeEvent event) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        LatLngBounds bounds = event.getBounds();
        int zoomLevel = event.getZoomLevel();

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(zoomLevel)));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", bounds.getNorthEast().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", bounds.getSouthWest().toString()));
    }

    public void onPointSelect(PointSelectEvent event) {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        LatLng latlng = event.getLatLng();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
    }

    public void circularHotmap() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        circleModel = new DefaultMapModel();
        try {
            String sql = "SELECT * FROM tUPLOADEDEVIDENCE";
            System.out.println(sql);
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String longitude = rs.getString("crime_longitude");
                    String latitude = rs.getString("crime_latitude");
                    Double Lat = Double.parseDouble(latitude);
                    Double Long = Double.parseDouble(longitude);
                    LatLng coord1 = new LatLng(Lat, Long);
                    Circle circle1 = new Circle(coord1, 300);
                    String crimeCategorycolor = new crimeCategoryBean().fetchCrimeCategoryColor(new crimetypeBean().fetchCrimeCategoryId(rs.getInt("crime_type")));
                    circle1.setStrokeColor(crimeCategorycolor);
                    circle1.setFillColor(crimeCategorycolor);
//                    System.out.println("Crime Type: "+rs.getInt("crime_type")+" || Color code: "+crimeCategorycolor);
                    circle1.setFillOpacity(0.5);
                    circleModel.addOverlay(circle1);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MapModel getCircleModel() {
        return circleModel;
    }

    public void onCircleSelect(OverlaySelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Circle Selected", null));
    }

    public List<crimeCategory> getLegendCrimeCategory() {
        checkLogin cl = new checkLogin();
        cl.isLoggedIn();
        cl = null;
        List<crimeCategory> ret = new ArrayList<>();
        try {
            Database obj = new Database();
            try (Connection lconn = obj.getConnectiondbPOLICE()) {
                PreparedStatement pst;
                ResultSet rs;
                pst = lconn.prepareStatement("select * from tCRIMECATEGORIES  order by id desc");
                rs = pst.executeQuery();
                while (rs.next()) {
                    crimeCategory si = new crimeCategory();
                    si.setCrime_category(rs.getString("crime_category"));
                    si.setCrime_notes(rs.getString("crime_notes"));
                    si.setCrime_color(rs.getString("crime_color"));
                    si.setId(rs.getInt("id"));
                    ret.add(si);
                }
                pst.close();
                lconn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(crimeCategoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
