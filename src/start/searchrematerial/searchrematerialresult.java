package start.searchrematerial;

import java.util.ArrayList;

public class searchrematerialresult {
    private String result;
    private ArrayList<searchrematerialdata> data;
    public searchrematerialresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchrematerialdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchrematerialdata> data) {
        this.data = data;
    }
}
