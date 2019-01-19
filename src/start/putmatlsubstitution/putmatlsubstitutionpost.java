package start.putmatlsubstitution;

import java.util.ArrayList;

public class putmatlsubstitutionpost {
    private String prodno;                              //产品编号
    private String why;                                 //代用原因
    private String user;                                //提交人
    private ArrayList<putmatlsubstitutionpostdata> data = new ArrayList<putmatlsubstitutionpostdata>();
    public putmatlsubstitutionpost(){
        super();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<putmatlsubstitutionpostdata> getData() {
        return data;
    }

    public void setData(ArrayList<putmatlsubstitutionpostdata> data) {
        this.data = data;
    }
}
