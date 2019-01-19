package start.searchbystatus;

import java.util.ArrayList;

public class searchbystatusresult {
    private String result;
    private ArrayList<searchbystatusdata> data = new ArrayList<searchbystatusdata>();
    public searchbystatusresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchbystatusdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchbystatusdata> data) {
        this.data = data;
    }
}
