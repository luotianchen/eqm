package start.getreport;

public class searchpredata {
    private String prodno;
    private String dwgno;
    private String name;                           //试压部位
    private String ename;
    private String dated;
    private String accuclass;                       //压力表精度*
    private String measrangemin;                       //压力表量程min*
    private String measrangemax;                       //压力表量程max*
    private String calibdate;                           //压力表检定日期*
    private String pgaugeno1;                           //压力表编号*
    private String type;                                //型号(表盘直径)*
    private String testmedia;                           //试验介质*
    private String etestmedia;                          //英文*
    private String clcontent;                           //氯离子含量*
    private String circutemp;                           //环境温度*
    private String mediatemp;                           //介质温度*
    private String testpress;                           //图
    private String dewelltime;                          //图2

    public searchpredata(){
        super();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getAccuclass() {
        return accuclass;
    }

    public void setAccuclass(String accuclass) {
        this.accuclass = accuclass;
    }

    public String getMeasrangemin() {
        return measrangemin;
    }

    public void setMeasrangemin(String measrangemin) {
        this.measrangemin = measrangemin;
    }

    public String getMeasrangemax() {
        return measrangemax;
    }

    public void setMeasrangemax(String measrangemax) {
        this.measrangemax = measrangemax;
    }

    public String getCalibdate() {
        return calibdate;
    }

    public void setCalibdate(String calibdate) {
        this.calibdate = calibdate;
    }

    public String getPgaugeno1() {
        return pgaugeno1;
    }

    public void setPgaugeno1(String pgaugeno1) {
        this.pgaugeno1 = pgaugeno1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTestmedia() {
        return testmedia;
    }

    public void setTestmedia(String testmedia) {
        this.testmedia = testmedia;
    }

    public String getEtestmedia() {
        return etestmedia;
    }

    public void setEtestmedia(String etestmedia) {
        this.etestmedia = etestmedia;
    }

    public String getClcontent() {
        return clcontent;
    }

    public void setClcontent(String clcontent) {
        this.clcontent = clcontent;
    }

    public String getCircutemp() {
        return circutemp;
    }

    public void setCircutemp(String circutemp) {
        this.circutemp = circutemp;
    }

    public String getMediatemp() {
        return mediatemp;
    }

    public void setMediatemp(String mediatemp) {
        this.mediatemp = mediatemp;
    }

    public String getTestpress() {
        return testpress;
    }

    public void setTestpress(String testpress) {
        this.testpress = testpress;
    }

    public String getDewelltime() {
        return dewelltime;
    }

    public void setDewelltime(String dewelltime) {
        this.dewelltime = dewelltime;
    }
}
