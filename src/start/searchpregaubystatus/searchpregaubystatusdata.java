package start.searchpregaubystatus;

import java.util.ArrayList;

public class searchpregaubystatusdata {
    private int id;
    private String gaugename;
    private String gaugeno;
    private String exitno;
    private String type;
    private double measrangemin;
    private double measrangemax;
    private String accuclass;
    private String millunit;
    private String exitdate;
    private String managlevel;
    private String calibdate;
    private String recalibdate;
    private ArrayList<String> specialist;
    private String calibinterval;
    private String note;
    private String user;
    private String date;
    private String audit_user;
    public searchpregaubystatusdata(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGaugename() {
        return gaugename;
    }

    public void setGaugename(String gaugename) {
        this.gaugename = gaugename;
    }

    public String getGaugeno() {
        return gaugeno;
    }

    public void setGaugeno(String gaugeno) {
        this.gaugeno = gaugeno;
    }

    public String getExitno() {
        return exitno;
    }

    public void setExitno(String exitno) {
        this.exitno = exitno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMeasrangemin() {
        return measrangemin;
    }

    public void setMeasrangemin(double measrangemin) {
        this.measrangemin = measrangemin;
    }

    public double getMeasrangemax() {
        return measrangemax;
    }

    public void setMeasrangemax(double measrangemax) {
        this.measrangemax = measrangemax;
    }

    public String getAccuclass() {
        return accuclass;
    }

    public void setAccuclass(String accuclass) {
        this.accuclass = accuclass;
    }

    public String getMillunit() {
        return millunit;
    }

    public void setMillunit(String millunit) {
        this.millunit = millunit;
    }

    public String getExitdate() {
        return exitdate;
    }

    public void setExitdate(String exitdate) {
        this.exitdate = exitdate;
    }

    public String getManaglevel() {
        return managlevel;
    }

    public void setManaglevel(String managlevel) {
        this.managlevel = managlevel;
    }

    public String getCalibdate() {
        return calibdate;
    }

    public void setCalibdate(String calibdate) {
        this.calibdate = calibdate;
    }

    public String getRecalibdate() {
        return recalibdate;
    }

    public void setRecalibdate(String recalibdate) {
        this.recalibdate = recalibdate;
    }

    public ArrayList<String> getSpecialist() {
        return specialist;
    }

    public void setSpecialist(ArrayList<String> specialist) {
        this.specialist = specialist;
    }

    public String getCalibinterval() {
        return calibinterval;
    }

    public void setCalibinterval(String calibinterval) {
        this.calibinterval = calibinterval;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAudit_user() {
        return audit_user;
    }

    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }
}
