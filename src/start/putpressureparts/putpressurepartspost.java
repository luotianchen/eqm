package start.putpressureparts;

import java.util.ArrayList;

public class putpressurepartspost {
    private String prodno;
    private String user;
    private ArrayList<putpressurepartspostdata> data = new ArrayList<putpressurepartspostdata>();
    public putpressurepartspost(){
        super();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public ArrayList<putpressurepartspostdata> getData() {
        return data;
    }

    public void setData(ArrayList<putpressurepartspostdata> data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
