package start.searchprematl;

import java.util.ArrayList;

public class searchprematlresult {
    private String result;
    private String prodname;                                            //产品名称
    private String ename;                                               //产品英文名称
    private String audit_user;                                          //审核人
    private String issuematl;                                           //发料人
    private String issuedate;                                           //发料日期
    private ArrayList<searchprematldata> data;
    public searchprematlresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getAudit_user() {
        return audit_user;
    }

    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }

    public String getIssuematl() {
        return issuematl;
    }

    public void setIssuematl(String issuematl) {
        this.issuematl = issuematl;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public ArrayList<searchprematldata> getData() {
        return data;
    }

    public void setData(ArrayList<searchprematldata> data) {
        this.data = data;
    }
}
