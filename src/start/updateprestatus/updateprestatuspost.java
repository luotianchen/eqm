package start.updateprestatus;

public class updateprestatuspost {
    private String audit;                           //审核码
    private int status;                          //审核状态
    private String audit_user;
    public updateprestatuspost(){
        super();
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

    public String getAudit_user() {
        return audit_user;
    }

    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }
}
