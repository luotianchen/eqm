package start.getdwgnobynoaudit;

public class getdwgnobynoauditdata {
    private String dwgno;                                           //图号
    private String user;                                            //提交人
    private String date;                                            //提交时间
    private int sc;
    public getdwgnobynoauditdata(){
        super();
    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
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

    public int getSc() {
        return sc;
    }

    public void setSc(int sc) {
        this.sc = sc;
    }
}
