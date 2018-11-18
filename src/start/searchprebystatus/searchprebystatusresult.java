package start.searchprebystatus;

import java.util.ArrayList;

public class searchprebystatusresult {
    private String result;
    private ArrayList<searchprebystatusdata> data = new ArrayList<searchprebystatusdata>();
    public searchprebystatusresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchprebystatusdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchprebystatusdata> data) {
        this.data = data;
    }
}
