/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package police.navigator;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author mspace
 */
@ManagedBean
@SessionScoped
public class facePainter implements Serializable {

    /**
     * Creates a new instance of facePainter
     */
    public facePainter() {
    }

    public String mainContent = "content/dashBoard.xhtml";

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }
    //************************************************************************CONTENT

    public void dashBoardPage() {
        setMainContent("content/dashBoard.xhtml");
    }

    public void evidence() {
        setMainContent("content/emergence.xhtml");
    }
    //************************************************************************HOTMAP

    public void hotmap() {
        setMainContent("hotmap/hotmap.xhtml");
    }

    public void hotmapMarker() {
        setMainContent("hotmap/mapMarker.xhtml");
    }
    //******************************************************************EVIDENCE

    public void uploadedEvidence() {
        setMainContent("evidence/uploadedEvidence.xhtml");
    }

    public void cleanedEvidence() {
        setMainContent("evidence/cleanEvidence.xhtml");
    }
    //***********************************************************************CASES

    public void cases() {
        setMainContent("cases/case.xhtml");
    }

    public void openCases() {
        setMainContent("cases/openCase.xhtml");
    }

    public void closedCases() {
        setMainContent("cases/closedCase.xhtml");
    }

    public void pendingCases() {
        setMainContent("cases/pendingCase.xhtml");
    }

    //**************************************************************** PATROL
    public void traffic() {
        setMainContent("patrol/traffic.xhtml");
    }

    public void patrol() {
        setMainContent("patrol/patrol.xhtml");
    }

    public void patrolLogs() {
        setMainContent("patrol/patrollogs.xhtml");
    }

    public void patrolDuties() {
        setMainContent("patrol/patrolDuties.xhtml");
    }
    //*************************************************************** REPORT

    public void newReport() {
        setMainContent("reports/newReport.xhtml");
    }

    public void openReport() {
        setMainContent("reports/openReport.xhtml");
    }

    public void closedReport() {
        setMainContent("reports/closedReport.xhtml");
    }

    //*************************************************************** SETTINGS
    public void departments() {
        setMainContent("settings/departments.xhtml");
    }

    public void domain() {
        setMainContent("settings/domains.xhtml");
    }

    public void job() {
        setMainContent("settings/jobs.xhtml");
    }

    public void crimeTypes() {
        setMainContent("settings/crimetypes.xhtml");
    }

    public void crimeCategory() {
        setMainContent("settings/crimecategories.xhtml");
    }

    public void changePassword() {
        setMainContent("settings/changePassword.xhtml");
    }

    public void offManager() {
        setMainContent("settings/offmanager.xhtml");
    }

    //***************************************************************** POLICE
    public void policeUsers() {
        setMainContent("police/policeUsers.xhtml");
    }

    public void mobileUsers() {
        setMainContent("police/mobileUsers.xhtml");
    }

    //******************************************************************* CI
    public void ci() {
        setMainContent("informant/ci.xhtml");
    }

    public void ciList() {
        setMainContent("informant/cilist.xhtml");
    }

}
