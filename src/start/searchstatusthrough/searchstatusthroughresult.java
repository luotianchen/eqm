package start.searchstatusthrough;

import java.util.ArrayList;

public class searchstatusthroughresult {
    private String result;
    private int total;
    private ArrayList<searchstatusthroughdata> data = new ArrayList<searchstatusthroughdata>();
    public searchstatusthroughresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchstatusthroughdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchstatusthroughdata> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
