package start.searchprotestboardcom;

import java.util.ArrayList;

public class searchprotestboardcomresult {
    private String result;
    private ArrayList<searchprotestboardcomdata> data;
    public searchprotestboardcomresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchprotestboardcomdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchprotestboardcomdata> data) {
        this.data = data;
    }
}
