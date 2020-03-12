/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package police.login;

import java.io.IOException;
import javax.faces.context.FacesContext;

public class checkLogin {

    public void isLoggedIn() {
        String una = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uname");
        String pas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("passwd");

        if (una == null || una.length() < 4 || pas == null || pas.length() < 4) {
            try {
                System.out.println("<<<<<<<<<<<<<<<<<View Expired>>>>>>>>>>>>");
                FacesContext.getCurrentInstance().getExternalContext().redirect("viewExpired.jsf?faces-redirect=true");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean setSessionParam(String key, String value) {
        boolean ret = true;
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }

    public String getSessionParam(String key) {
        String ret = "";
        this.isLoggedIn();
        try {
            ret = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
        } catch (Exception e) {
            ret = "";
        }
        if (ret == null || ret.length() == 0) {
            ret = "";
        }
        return ret;
    }

    public String getRequestParam(String key) {
        String ret = "";
        this.isLoggedIn();
        try {
            ret = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
        } catch (Exception e) {
            ret = "";
        }
        if (ret == null || ret.length() == 0) {
            ret = "";
        }
        return ret;
    }

    public void redirectTo(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
