package start.searchprenotiform;

import java.util.ArrayList;

public class searchprenotiformresult {
    private String result;
    private ArrayList<searchprenotiformdata> data;
    public searchprenotiformresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchprenotiformdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchprenotiformdata> data) {
        this.data = data;
    }
}
