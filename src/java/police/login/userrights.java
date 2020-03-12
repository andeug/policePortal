/*
 * To change this ee; choose Tools | Templates
 * and open the template in the editor.
 */
package police.login;

/**
 *
 * @author Davis
 */
import java.io.Serializable;
import java.sql.*;
import javax.faces.context.FacesContext;
import police.database.Database;

public class userrights implements Serializable {

    private int id;
    private String newdept;
    private String dept;
    private String newmsgcat;
    private String msgcats;
    private String pendingSmsTasks;
    private String pendingSmsReplyTasks;
    private String pendingEmailTasks;
    private String tasksinprogress;
    private String tasksEinprogress;
    private String discardedtasks;
    private String discardedEtasks;
    private String reassignedtasks;
    private String reassignedEtasks;
    private String completedtasks;
    private String completedEtasks;
    private String myopentasks;
    private String myclosedtasks;
    private String sendsmstoclients;
    private String sendemailtoclients;
    private String sendsmstoagents;
    private String sentsmsdept;
    private String smsturnaround;
    private String tasksgroupedbystatus;
    private String tasksEgroupedbystatus;
    private String viewusers;
    private String newuser;
    private String viewsmsout;
    private String viewsentemails;
    private String receivedsms;
    private String receivedsmstotals;
    private String receivedsmscategory;
    private String sentemailsummary;
    private String receivedEmails;
    private String smsUser;
    private String sentSmsUser;
    private String smsDept;
    private String billing;
    private String newPublicTemp;
    private String allPublicTemp;
    private int saveClicked = 0;
 Database obj = new Database();

    public int getSaveClicked() {
        return saveClicked;
    }

    public void setSaveClicked(int saveClicked) {
        this.saveClicked = saveClicked;
    }

    public String getNewPublicTemp() {
        return newPublicTemp;
    }

    public void setNewPublicTemp(String newPublicTemp) {
        this.newPublicTemp = newPublicTemp;
    }

    public String getAllPublicTemp() {
        return allPublicTemp;
    }

    public void setAllPublicTemp(String allPublicTemp) {
        this.allPublicTemp = allPublicTemp;
    }

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }

    public String getSentSmsUser() {
        return sentSmsUser;
    }

    public void setSentSmsUser(String sentSmsUser) {
        this.sentSmsUser = sentSmsUser;
    }
    public String receivedsmsSummary;

    public String getReceivedsmsSummary() {
        return receivedsmsSummary;
    }

    public void setReceivedsmsSummary(String receivedsmsSummary) {
        this.receivedsmsSummary = receivedsmsSummary;
    }

    public String getSmsDept() {
        return smsDept;
    }

    public void setSmsDept(String smsDept) {
        this.smsDept = smsDept;
    }

    public String getReceivedsmscategory() {
        return receivedsmscategory;
    }

    public void setReceivedsmscategory(String receivedsmscategory) {
        this.receivedsmscategory = receivedsmscategory;
    }

    public String getSmsUser() {
        return smsUser;
    }

    public void setSmsUser(String smsUser) {
        this.smsUser = smsUser;
    }

    public String getPendingSmsReplyTasks() {
        return pendingSmsReplyTasks;
    }

    public void setPendingSmsReplyTasks(String pendingSmsReplyTasks) {
        this.pendingSmsReplyTasks = pendingSmsReplyTasks;
    }

    public String getCompletedEtasks() {
        return completedEtasks;
    }

    public void setCompletedEtasks(String completedEtasks) {
        this.completedEtasks = completedEtasks;
    }

    public String getReassignedEtasks() {
        return reassignedEtasks;
    }

    public void setReassignedEtasks(String reassignedEtasks) {
        this.reassignedEtasks = reassignedEtasks;
    }

    public String getDiscardedEtasks() {
        return discardedEtasks;
    }

    public void setDiscardedEtasks(String discardedEtasks) {
        this.discardedEtasks = discardedEtasks;
    }

    public String getTasksEinprogress() {
        return tasksEinprogress;
    }

    public void setTasksEinprogress(String tasksEinprogress) {
        this.tasksEinprogress = tasksEinprogress;
    }

    public String getTasksEgroupedbystatus() {
        return tasksEgroupedbystatus;
    }

    public void setTasksEgroupedbystatus(String tasksEgroupedbystatus) {
        this.tasksEgroupedbystatus = tasksEgroupedbystatus;
    }

    public String getSendemailtoclients() {
        return sendemailtoclients;
    }

    public void setSendemailtoclients(String sendemailtoclients) {
        this.sendemailtoclients = sendemailtoclients;
    }

    public String getReceivedEmails() {
        return receivedEmails;
    }

    public void setReceivedEmails(String receivedEmails) {
        this.receivedEmails = receivedEmails;
    }

    public String getSentemailsummary() {
        return sentemailsummary;
    }

    public void setSentemailsummary(String sentemailsummary) {
        this.sentemailsummary = sentemailsummary;
    }

    public String getReceivedsmstotals() {
        return receivedsmstotals;
    }

    public void setReceivedsmstotals(String receivedsmstotals) {
        this.receivedsmstotals = receivedsmstotals;
    }

    public String getReceivedsms() {
        return receivedsms;
    }

    public void setReceivedsms(String receivedsms) {
        this.receivedsms = receivedsms;
    }

    public String getViewsentemails() {
        return viewsentemails;
    }

    public void setViewsentemails(String viewsentemails) {
        this.viewsentemails = viewsentemails;
    }

    public String getViewsmsout() {
        return viewsmsout;
    }

    public void setViewsmsout(String viewsmsout) {
        this.viewsmsout = viewsmsout;
    }

    public String getNewuser() {
        return newuser;
    }

    public void setNewuser(String newuser) {
        this.newuser = newuser;
    }

    public String getViewusers() {
        return viewusers;
    }

    public void setViewusers(String viewusers) {
        this.viewusers = viewusers;
    }

    public String getCompletedtasks() {
        return completedtasks;
    }

    public String getMyopentasks() {
        return myopentasks;
    }

    public String getDept() {
        return dept;
    }

    public String getDiscardedtasks() {
        return discardedtasks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMsgcats() {
        return msgcats;
    }

    public String getMyclosedtasks() {
        return myclosedtasks;
    }

    public String getNewdept() {
        return newdept;
    }

    public String getNewmsgcat() {
        return newmsgcat;
    }

    public String getPendingEmailTasks() {
        return pendingEmailTasks;
    }

    public String getPendingSmsTasks() {
        return pendingSmsTasks;
    }

    public String getReassignedtasks() {
        return reassignedtasks;
    }

    public String getSendsmstoagents() {
        return sendsmstoagents;
    }

    public String getSendsmstoclients() {
        return sendsmstoclients;
    }

    public String getSentsmsdept() {
        return sentsmsdept;
    }

    public String getSmsturnaround() {
        return smsturnaround;
    }

    public String getTasksgroupedbystatus() {
        return tasksgroupedbystatus;
    }

    public String getTasksinprogress() {
        return tasksinprogress;
    }

    protected boolean loadRights(Connection conn) {
       
        conn = obj.getConnectiondbPOLICE();
        boolean ret = false;
        Statement st = null;
        ResultSet rs = null;
        if (conn != null) {
            String sql = "SELECT * FROM tUSERRIGHTS where id = " + this.id;
            System.out.println(sql);
            try {
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                if (rs.next()) {

                    if (rs.getString("receivedsms").equalsIgnoreCase("Y")) {
                        this.receivedsms = "receivedsms.xhtml";
                    } else {
                        this.receivedsms = "#";
                        System.out.println("no right to acces receivedsms");
                    }
                    if (rs.getString("smsout").equalsIgnoreCase("Y")) {
                        this.viewsmsout = "smsout.xhtml";
                    } else {
                        viewsmsout = "#";
                        System.out.println("no right to acces smsout");
                    }

                    if (rs.getString("smsDept").equalsIgnoreCase("Y")) {
                        smsDept = "smsDept.xhtml";
                    } else {
                        smsDept = "#";
                        System.out.println("no right to acces smsDept");
                    }
                    System.out.println("smsDept val: " + smsDept);

                    if (rs.getString("smsUser").equalsIgnoreCase("Y")) {
                        smsUser = "smsUser.xhtml";
                    } else {
                        smsUser = "#";
                        System.out.println("no right to acces smsUser");
                    }
                    System.out.println("smsUser val: " + smsUser);

                    if (rs.getString("receivedsmscategory").equalsIgnoreCase("Y")) {
                        receivedsmscategory = "receivedsmscategory.xhtml";
                    } else {
                        receivedsmscategory = "#";
                        System.out.println("no right to acces receivedsmscategory");
                    }
                    System.out.println("receivedsmscategory val: " + receivedsmscategory);

//                    if (rs.getString("newdept").equalsIgnoreCase("Y")) {
//                        newdept = "newdept.xhtml";
//                    } else {
//                        newdept = "#";
//                        System.out.println("no right to acces newdept");
//                    }
//                    System.out.println("newdept val: " + newdept);
                    if (rs.getString("dept").equalsIgnoreCase("Y")) {
                        dept = "dept.xhtml";
                    } else {
                        dept = "#";
                        System.out.println("no right to acces dept");
                    }
//                    if (rs.getString("newmsgcat").equalsIgnoreCase("Y")) {
//                        newmsgcat = "newmsgcat.xhtml";
//                    } else {
//                        newmsgcat = "#";
//                        System.out.println("no right to acces newmsgcat");
//                    }
                    if (rs.getString("msgcats").equalsIgnoreCase("Y")) {
                        msgcats = "msgcats.xhtml";
                    } else {
                        msgcats = "#";
                        System.out.println("no right to acces msgcats");
                    }
                    if (rs.getString("pendingSmsTasks").equalsIgnoreCase("Y")) {
                        pendingSmsTasks = "pendingSmsTasks.xhtml";
                        //  pendingSmsReplyTasks = "pendingSmsReplyTasks.xhtml";
                    } else {
                        pendingSmsTasks = "#";
                        System.out.println("no right to acces pendingSmsTasks");
                        pendingSmsReplyTasks = "#";
                        System.out.println("no right to acces pendingSmsReplyTasks");
                    }
                    if (rs.getString("pendingEmailTasks").equalsIgnoreCase("Y")) {
                        pendingEmailTasks = "pendingEmailTasks.xhtml";
                    } else {
                        pendingEmailTasks = "#";
                        System.out.println("no right to acces pendingEmailTasks");
                    }
                    if (rs.getString("tasksinprogress").equalsIgnoreCase("Y")) {
                        tasksinprogress = "tasksinprogress.xhtml";
                    } else {
                        tasksinprogress = "#";
                        System.out.println("no right to acces tasksinprogress");
                    }
                    if (rs.getString("discardedtasks").equalsIgnoreCase("Y")) {
                        discardedtasks = "discardedtasks.xhtml";
                        //discardedEtasks = "discardedtasks.xhtml";
                    } else {
                        discardedtasks = "#";
                        System.out.println("no right to acces discardedtasks");
                    }
                    if (rs.getString("reassignedtasks").equalsIgnoreCase("Y")) {
                        reassignedtasks = "reassignedtasks.xhtml";
                        //reassignedEtasks = "reassignedtasks.xhtml";
                    } else {
                        reassignedtasks = "#";
                        System.out.println("no right to acces reassignedtasks");
                    }
                    if (rs.getString("completedtasks").equalsIgnoreCase("Y")) {
                        completedtasks = "completedtasks.xhtml";
                        //completedEtasks = "completedtasks.xhtml";
                    } else {
                        completedtasks = "#";
                        System.out.println("no right to acces completedtasks");
                    }
                    if (rs.getString("myopentasks").equalsIgnoreCase("Y")) {
                        myopentasks = "myopentasks.xhtml";
                    } else {
                        myopentasks = "#";
                        System.out.println("no right to acces myopentasks");
                    }
                    if (rs.getString("myclosedtasks").equalsIgnoreCase("Y")) {
                        myclosedtasks = "myclosedtasks.xhtml";//davis added 2016-04-05
                    } else {
                        myclosedtasks = "#";
                        System.out.println("no right to acces myclosedtasks");
                    }
                    if (rs.getString("sendsmstoclients").equalsIgnoreCase("Y")) {
                        sendsmstoclients = "sendsmstoclients.xhtml";
                    } else {
                        sendsmstoclients = "#";
                        System.out.println("no right to acces sendsmstoclients");
                    }
                    if (rs.getString("sendsmstoagents").equalsIgnoreCase("Y")) {
                        sendsmstoagents = "sendsmstoagents.xhtml";
                    } else {
                        sendsmstoagents = "#";
                        System.out.println("no right to acces sendsmstoagents");
                    }
                    if (rs.getString("sentsmsdept").equalsIgnoreCase("Y")) {
                        sentsmsdept = "sentsmsdept.xhtml";
                    } else {
                        sentsmsdept = "#";
                        System.out.println("no right to acces sentsmsdept");
                    }
                    if (rs.getString("smsturnaround").equalsIgnoreCase("Y")) {
                        smsturnaround = "smsturnaround.xhtml";
                    } else {
                        smsturnaround = "#";
                        System.out.println("no right to acces smsturnaround");
                    }
                    if (rs.getString("tasksgroupedbystatus").equalsIgnoreCase("Y")) {
                        tasksgroupedbystatus = "tasksgroupedbystatus.xhtml";
                        //  tasksEgroupedbystatus = "tasksEgroupedbystatus.xhtml";
                    } else {
                        tasksgroupedbystatus = "#";
                        System.out.println("no right to acces tasksgroupedbystatus");
                    }

                    if (rs.getString("viewusers").equalsIgnoreCase("Y")) {
                        viewusers = "viewusers.xhtml";

                    } else {
                        viewusers = "#";
                        System.out.println("no right to acces viewusers");
                    }

                    if (rs.getString("receivedsmsSummary").equalsIgnoreCase("Y")) {
                        receivedsmsSummary = "summaryreceived.xhtml";
                    } else {
                        receivedsmsSummary = "#";
                        System.out.println("no right to acces receivedsmsSummary");
                    }
                    if (rs.getString("sentSmsUser").equalsIgnoreCase("Y")) {
                        sentSmsUser = "sentSmsUser.xhtml";
                    } else {
                        sentSmsUser = "#";
                        System.out.println("no right to acces sentSmsUser");
                    }
                    if (rs.getString("Billing").equalsIgnoreCase("Y")) {
                        billing = "billing.xhtml";
                    } else {
                        billing = "#";
                        System.out.println("no right to acces Billing");
                    }
//                    this.viewsentemails = "sentEmail.xhtml";
//                    //all received SMS
//                    this.sentemailsummary = "sentemailsummary.xhtml";
//                    this.receivedEmails = "receivedEmails.xhtml";
//                    //this.smsUser="smsUser.xhtml";
//                    sendemailtoclients = "sendemailtoclients.xhtml";
//                    this.tasksEinprogress = "tasksEinprogress.xhtml";
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            int a = this.admin();
            if (a > 0) {
                // this.newPublicTemp = "newPublicMsg.xhtml";
                this.allPublicTemp = "allPublicMessages.xhtml";
            } else {
//                this.newPublicTemp = "#";
//                System.out.println("no right to acces newPublicMsg");
                this.allPublicTemp = "#";
                System.out.println("no right to acces allPublicMessages");

            }
        }
        obj.close(conn);
        int i = 0;

        return ret;
    }

    public int admin() {
        int y = 0;

        Connection conn1 = obj.getConnectiondbPOLICE();
        Statement st = null;
        ResultSet rs = null;
        String una = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uname");

        String sql = "SELECT * FROM tUSER where username='" + una + "' && taskadmin='Y'";
        System.out.println(sql);
        try {
            st = conn1.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {

                y++;

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return y;
    }
}
