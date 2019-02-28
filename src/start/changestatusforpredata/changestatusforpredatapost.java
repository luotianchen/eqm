package start.changestatusforpredata;

public class changestatusforpredatapost {
    private int id;
    private String specimenno;
    private String prodno;
    private int status;
    private String audit_user;
    public changestatusforpredatapost(){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecimenno() {
        return specimenno;
    }

    public void setSpecimenno(String specimenno) {
        this.specimenno = specimenno;
    }

    public String getAudit_user() {
        return audit_user;
    }

    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }
}
