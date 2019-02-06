package start.searchbyprodnocache;

public class searchbyprodnocachepost {
    private String prodno;                                      //产品编号
    private String audit;                                       //审核码
    private int status;                                         //审核状态
    public searchbyprodnocachepost(){
        super();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
