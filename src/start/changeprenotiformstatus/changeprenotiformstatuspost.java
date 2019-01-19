package start.changeprenotiformstatus;

public class changeprenotiformstatuspost {
    private String prodno;
    private int status;
    private String ppart;
    public changeprenotiformstatuspost(){
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

    public String getPpart() {
        return ppart;
    }

    public void setPpart(String ppart) {
        this.ppart = ppart;
    }
}
