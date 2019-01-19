package start.searchvacuumbystatus;

public class searchvacuumbystatuspost {
    private String prodno;
    private int status;
    public searchvacuumbystatuspost(){
        super();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }
}
