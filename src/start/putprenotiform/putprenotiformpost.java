package start.putprenotiform;

public class putprenotiformpost {
    private String prodno;                                  //产品编号
    private String dwgno;                                   //图号
    private String ppart;                                   //试压部位
    private String eppart;                                  //试压部位英文
    private String dated1;                                  //开具日期1
    private String dated2;                                  //开具日期2
    private String dated3;                                  //开具日期3
    private String testmedia;                               //试压介质
    private String etestmedia;                              //试压介质英文
    private String clcontent;                               //氯例子含量
    private String user;                                    //提交人
    public putprenotiformpost(){
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

    public String getPpart() {
        return ppart;
    }

    public void setPpart(String ppart) {
        this.ppart = ppart;
    }

    public String getEppart() {
        return eppart;
    }

    public void setEppart(String eppart) {
        this.eppart = eppart;
    }

    public String getDated1() {
        return dated1;
    }

    public void setDated1(String dated1) {
        this.dated1 = dated1;
    }

    public String getDated2() {
        return dated2;
    }

    public void setDated2(String dated2) {
        this.dated2 = dated2;
    }

    public String getDated3() {
        return dated3;
    }

    public void setDated3(String dated3) {
        this.dated3 = dated3;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
