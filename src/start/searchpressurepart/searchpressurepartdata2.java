package start.searchpressurepart;

public class searchpressurepartdata2 {
    private String codedmarking;                        //入库编号
    private String designation;                         //牌号*
    private String spec;                                //规格
    private String spartname;                           //零件名称*，即受压元件里的name
    private String issuedate;                           //发料日期
    private String qty;                                 //数量
    public searchpressurepartdata2(){
        super();
    }

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSpartname() {
        return spartname;
    }

    public void setSpartname(String spartname) {
        this.spartname = spartname;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
