package start.searchpresstest;

import java.util.ArrayList;

public class searchpresstestdata {
    private String dwgno;
    private String prodname;
    private ArrayList<String> pttype;
    private ArrayList<String> name;
    private ArrayList<String> depress;
    private ArrayList<String> detemp;
    private ArrayList<String> wmedia;
    private ArrayList<String> testpress;
    private String user;
    private String audit_user;
    private String date;
    public searchpresstestdata(){
        super();
    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public ArrayList<String> getPttype() {
        return pttype;
    }

    public void setPttype(ArrayList<String> pttype) {
        this.pttype = pttype;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public ArrayList<String> getDepress() {
        return depress;
    }

    public void setDepress(ArrayList<String> depress) {
        this.depress = depress;
    }

    public ArrayList<String> getDetemp() {
        return detemp;
    }

    public void setDetemp(ArrayList<String> detemp) {
        this.detemp = detemp;
    }

    public ArrayList<String> getWmedia() {
        return wmedia;
    }

    public void setWmedia(ArrayList<String> wmedia) {
        this.wmedia = wmedia;
    }

    public ArrayList<String> getTestpress() {
        return testpress;
    }

    public void setTestpress(ArrayList<String> testpress) {
        this.testpress = testpress;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAudit_user() {
        return audit_user;
    }

    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
