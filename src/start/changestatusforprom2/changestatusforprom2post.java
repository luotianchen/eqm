package start.changestatusforprom2;

public class changestatusforprom2post {
    private String prodno;
    private int status;
    private String audit_user;
    public changestatusforprom2post(){
        super();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
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