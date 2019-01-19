package start.searchstatusthrough;

public class searchstatusthroughdata {
    private String audit;                               //审核码
    private String date;                                //登记时间
    private String prodno;                              //产品编号
    private String user;                                //提交人
    private String why;
    public searchstatusthroughdata(){
        super();
    }
    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }
}
