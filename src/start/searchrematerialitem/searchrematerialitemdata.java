package start.searchrematerialitem;

public class searchrematerialitemdata {
    private String codedmarking;
    private String explain;                                 //数据库字段why
    private String forceperformance;
    private String chemicalcomposition;
    private String user;
    private String audit_user;
    private String date;
    public searchrematerialitemdata(){
        super();
    }

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getForceperformance() {
        return forceperformance;
    }

    public void setForceperformance(String forceperformance) {
        this.forceperformance = forceperformance;
    }

    public String getChemicalcomposition() {
        return chemicalcomposition;
    }

    public void setChemicalcomposition(String chemicalcomposition) {
        this.chemicalcomposition = chemicalcomposition;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAudit_user() {
        return audit_user;
    }

    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
