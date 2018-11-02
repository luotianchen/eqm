package start.warrantystatus;

public class warrantystatusdata {
    private int id;
    private String certsitu;                            //质保书情况
    public warrantystatusdata(){
        super();
    }
    public String getCertsitu() {
        return certsitu;
    }

    public void setCertsitu(String certsitu) {
        this.certsitu = certsitu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
