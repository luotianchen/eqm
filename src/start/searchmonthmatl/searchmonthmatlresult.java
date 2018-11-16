package start.searchmonthmatl;

import java.util.ArrayList;

public class searchmonthmatlresult {
    private String result;
    private int total;                                                  //总页数
    private ArrayList<searchmonthmatldata> data = new ArrayList<searchmonthmatldata>();

    public searchmonthmatlresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<searchmonthmatldata> getData() {
        return data;
    }

    public void setData(ArrayList<searchmonthmatldata> data) {
        this.data = data;
    }
}
