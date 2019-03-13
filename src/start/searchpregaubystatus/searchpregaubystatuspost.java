package start.searchpregaubystatus;

public class searchpregaubystatuspost {
    private int status;
    private String gaugeno;
    private String exitno;
    private String calibdate;
    public searchpregaubystatuspost(){
        super();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGaugeno() {
        return gaugeno;
    }

    public void setGaugeno(String gaugeno) {
        this.gaugeno = gaugeno;
    }

    public String getCalibdate() {
        return calibdate;
    }

    public void setCalibdate(String calibdate) {
        this.calibdate = calibdate;
    }

    public String getExitno() {
        return exitno;
    }

    public void setExitno(String exitno) {
        this.exitno = exitno;
    }
}
