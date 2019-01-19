package start.searchrematerialitem;

import java.util.ArrayList;

public class searchrematerialitemresult {
    private String result;
    private ArrayList<searchrematerialitemdata> data;
    public searchrematerialitemresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchrematerialitemdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchrematerialitemdata> data) {
        this.data = data;
    }
}
