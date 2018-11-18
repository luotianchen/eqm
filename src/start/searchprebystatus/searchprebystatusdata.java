package start.searchprebystatus;

public class searchprebystatusdata {
    private String audit;                                       //审核码
    private String updatetime;                                  //登记时间
    private String prodno;                                      //产品编号
    public searchprebystatusdata(){
        super();
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }
}
